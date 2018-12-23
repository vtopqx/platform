package cn.innovation.platform.insurance.service.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaoleilu.hutool.bean.BeanUtil;
import com.xiaoleilu.hutool.json.JSONObject;
import com.xiaoleilu.hutool.json.JSONUtil;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;

import cn.innovation.platform.common.base.BaseResult;
import cn.innovation.platform.common.constant.ApiConstant;
import cn.innovation.platform.common.constant.RedisConstant;
import cn.innovation.platform.common.enums.SendStatusEnum;
import cn.innovation.platform.common.enums.SystemStatusEnum;
import cn.innovation.platform.common.utils.StringUtils;
import cn.innovation.platform.insurance.common.dto.DamdataRecordsDto;
import cn.innovation.platform.insurance.common.dto.HistoryRecordsDto;
import cn.innovation.platform.insurance.common.dto.VinsunRecordsDto;
import cn.innovation.platform.insurance.common.enums.DamdataCodeEnum;
import cn.innovation.platform.insurance.common.enums.VinsunCodeEnum;
import cn.innovation.platform.insurance.common.model.HistoryRecords;
import cn.innovation.platform.insurance.common.utils.InsuranceApiHelper;
import cn.innovation.platform.insurance.mapper.HistoryRecordsMapper;
import cn.innovation.platform.insurance.service.IDamdataRecordsService;
import cn.innovation.platform.insurance.service.IHistoryRecordsService;
import cn.innovation.platform.insurance.service.IMobileCityService;
import cn.innovation.platform.insurance.service.IVinsunRecordsService;

/**
 * @ClassName: HistoryRecordsServiceImpl
 * @Description: 保险申请记录汇总接口实现类
 * @author mqx
 * @date 2018年12月22日 下午11:18:19
 */
@Service("historyRecordsService")
public class HistoryRecordsServiceImpl extends ServiceImpl<HistoryRecordsMapper, HistoryRecords>
		implements IHistoryRecordsService {

	private static final Log logger = LogFactory.get();

	@Resource
	private InsuranceApiHelper insuranceApiHelper;
	@Resource
	private IDamdataRecordsService damdataRecordsService;
	@Resource
	private IVinsunRecordsService vinsunRecordsService;
	@Resource
	private IMobileCityService mobileCityService;
	@Resource
	private RedisTemplate<String, String> redisTemplate;

	@Override
	public BaseResult addApply(HistoryRecordsDto dto) {
		BaseResult result = new BaseResult(SystemStatusEnum.CODE_500.value(), SystemStatusEnum.CODE_500.remark());
		String reqeustId = dto.getRequestTime();
		HistoryRecords records = new HistoryRecords();
		try {
			long startTime = System.currentTimeMillis();
			logger.info("[赠险]:数据处理开始,接收参数:{}", dto);
			if (!StringUtils.isNotEmpty(dto.getApplyCredit())) {
				dto.setApplyCredit("其他");
			}
			BeanUtil.copyProperties(dto, records);
			records.setCreateTime(new Date());
			records.setUpdateTime(new Date());
			// 查询是否已经申请过
			EntityWrapper<HistoryRecords> wrapper = new EntityWrapper<HistoryRecords>();
			wrapper.where("mobile = {0}", dto.getMobile());
			StringBuffer findParam = new StringBuffer();
			findParam.append("(status='").append(SendStatusEnum.processing.getId()).append("' or ");
			findParam.append("status='").append(SendStatusEnum.success.getId()).append("')");
			wrapper.and(findParam.toString());
			List<HistoryRecords> list = this.selectList(wrapper);
			if (list != null && list.size() > 0) {
				logger.info("[赠险]:数据检查完成!流水号:{},重复提交了数据!", reqeustId);
				result = new BaseResult(SystemStatusEnum.CODE_202.value(), SystemStatusEnum.CODE_202.remark());
			} else {
				BeanUtil.copyProperties(dto, records);
				records.setUpdateTime(new Date());
				// 查询号码归属地
				String city = mobileCityService.getCityByMobile(dto.getMobile());
				if (StringUtils.isNotEmpty(city)) {
					dto.setClientCity(city);
					records.setClientCity(city);
				} else {
					logger.info("[赠险]:归属地查询完成!流水号:{},非法号码!号码:{}", reqeustId, dto.getMobile());
					// 状态为非法号码
					records.setStatus(SendStatusEnum.mobileError.getId());
					this.insert(records);
					return new BaseResult(SystemStatusEnum.CODE_400.value(), SystemStatusEnum.CODE_400.remark());
				}
				// 状态为发送中
				records.setStatus(SendStatusEnum.processing.getId());
				boolean isSave = this.insert(records);

				// 调用接口分发
				if (isSave) {
					dto.setId(records.getId());
					Map<String, Object> apiMap = sendToApi(dto);
					String status = apiMap.get("status").toString();
					String apiCode = apiMap.get("api").toString();
					result = (BaseResult) apiMap.get("data");
					if (status.equals("1")) {
						records.setStatus(SendStatusEnum.success.getId());
					} else {
						records.setStatus(SendStatusEnum.failure.getId());
					}
					// 更新处理状态
					records.setApiCode(apiCode);
					records.setUpdateTime(new Date());
					records.setResult(JSONUtil.toJsonStr(result));
					this.insertOrUpdate(records);
				}
			}
			logger.info("[赠险]:数据处理完成!,流水号:{},耗时:{}", reqeustId, (System.currentTimeMillis() - startTime));
		} catch (Exception e) {
			// 状态为发送失败
			records.setStatus(SendStatusEnum.failure.getId());
			records.setUpdateTime(new Date());
			this.insertOrUpdate(records);

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw, true);
			e.printStackTrace(pw);
			String stackTraceString = sw.getBuffer().toString();
			logger.info("[赠险]:数据处理异常!流水号:{},原因:{}", reqeustId, stackTraceString);
		}
		return result;
	}

	/**
	 * @Description: 数据分发处理
	 * @param dto
	 *            参数对象
	 * @return
	 */
	private Map<String, Object> sendToApi(HistoryRecordsDto dto) {
		long startTime = System.currentTimeMillis();
		String api = "";
		boolean isSuccess = false;
		String reqeustId = dto.getRequestTime();
		logger.info("[赠险]:数据分发,处理开始!流水号:{}", reqeustId);
		Map<String, Object> map = new HashMap<String, Object>();
		BaseResult result = null;
		// 先查询redis中是否存在API
		ValueOperations<String, String> ops = redisTemplate.opsForValue();
		String appInfoStr = ops.get(RedisConstant.REDIS_APPINFO_PREFIX + dto.getAppKey());
		String apiList = "";
		if (StringUtils.isNotEmpty(appInfoStr)) {
			JSONObject jsonObject = JSONUtil.parseObj(appInfoStr);
			apiList = jsonObject.getStr("apiList");
		}
		// 数据异常直接返回
		if (!StringUtils.isNotEmpty(apiList)) {
			logger.error("[赠险]:数据分发,处理异常!流水号:{},获取上游渠道接口为空!", reqeustId);
			result = new BaseResult(SystemStatusEnum.CODE_403.value(), SystemStatusEnum.CODE_403.remark());
			map.put("api", api);
			map.put("status", isSuccess == true ? "1" : "0");
			map.put("data", result);
			return map;
		}
		String[] apiArray = apiList.split(",");
		logger.info("[赠险]:数据分发,获取上游渠道接口完成!流水号:{},接口:{}", reqeustId, apiArray);
		for (int i = 0; i < apiArray.length; i++) {
			api = apiArray[i];
			if (api.toLowerCase().contains(ApiConstant.API_DB)) {
				long dbTime = System.currentTimeMillis();
				logger.info("[赠险]:数据分发,当前调用大坝接口!流水号:{}", reqeustId);
				// 调用大坝接口
				DamdataRecordsDto dbDto = new DamdataRecordsDto();
				BeanUtil.copyProperties(dto, dbDto);
				// 个别属性不同
				dbDto.setHistoryId(dto.getId());
				dbDto.setIp(dto.getClientIp());
				dbDto.setBirthDay(dto.getBirth());
				String apiResult = damdataRecordsService.addApply(dbDto);
				if (StringUtils.isNotEmpty(apiResult)) {
					if (apiResult.equals(DamdataCodeEnum.SUCCESS.value())) {
						result = new BaseResult(SystemStatusEnum.CODE_200.value(), SystemStatusEnum.CODE_200.remark());
						isSuccess = true;
						break;
					} else if (apiResult.equals(DamdataCodeEnum.REPEAT.value())) {
						result = new BaseResult(SystemStatusEnum.CODE_202.value(), SystemStatusEnum.CODE_202.remark());
					} else {
						result = new BaseResult(SystemStatusEnum.CODE_417.value(), SystemStatusEnum.CODE_417.remark());
					}
				} else {
					result = new BaseResult(SystemStatusEnum.CODE_500.value(), SystemStatusEnum.CODE_500.remark());
				}
				logger.info("[赠险]:数据分发,当前调用大坝接口完成!流水号:{},是否成功:{},耗时:{}", reqeustId, isSuccess,
						(System.currentTimeMillis() - dbTime));
			} else if (api.toLowerCase().contains(ApiConstant.API_CS)) {
				long csTime = System.currentTimeMillis();
				logger.info("[赠险]:数据分发,当前调用畅思接口!流水号:{}", reqeustId);
				// 调用畅思接口
				VinsunRecordsDto csDto = new VinsunRecordsDto();
				BeanUtil.copyProperties(dto, csDto);
				csDto.setHistoryId(dto.getId());
				csDto.setUa("Mozilla");
				Map<String, String> resultMap = vinsunRecordsService.addApply(csDto);
				if (StringUtils.isNotEmpty(resultMap)) {
					String code = resultMap.get("code");
					if (code.equals(VinsunCodeEnum.CODE_1001.value())) {
						result = new BaseResult(SystemStatusEnum.CODE_200.value(), SystemStatusEnum.CODE_200.remark(),
								resultMap);
						isSuccess = true;
						break;
					} else if (code.equals(VinsunCodeEnum.CODE_1010.value())) {
						result = new BaseResult(SystemStatusEnum.CODE_202.value(), SystemStatusEnum.CODE_202.remark());
					} else if (code.equals(VinsunCodeEnum.CODE_1005.value())) {
						result = new BaseResult(SystemStatusEnum.CODE_400.value(), SystemStatusEnum.CODE_400.remark());
					} else {
						result = new BaseResult(SystemStatusEnum.CODE_417.value(), SystemStatusEnum.CODE_417.remark());
					}
				} else {
					result = new BaseResult(SystemStatusEnum.CODE_500.value(), SystemStatusEnum.CODE_500.remark());
				}
				logger.info("[赠险]:数据分发,当前调用畅思接口完成!流水号:{},是否成功:{},耗时:{}", reqeustId, isSuccess,
						(System.currentTimeMillis() - csTime));
			}
		}
		// 返回结果
		map.put("api", isSuccess == true ? api : apiList);
		map.put("status", isSuccess == true ? "1" : "0");
		map.put("data", result);
		logger.info("[赠险]:数据分发,处理完成!流水号:{},耗时:{},返回:{}", reqeustId, (System.currentTimeMillis() - startTime),
				JSONUtil.toJsonStr(map));
		return map;
	}
}
