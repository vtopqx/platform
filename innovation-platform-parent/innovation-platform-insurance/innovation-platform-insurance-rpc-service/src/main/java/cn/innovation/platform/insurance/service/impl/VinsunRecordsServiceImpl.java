package cn.innovation.platform.insurance.service.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaoleilu.hutool.bean.BeanUtil;
import com.xiaoleilu.hutool.http.HttpRequest;
import com.xiaoleilu.hutool.json.JSONObject;
import com.xiaoleilu.hutool.json.JSONUtil;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;

import cn.innovation.platform.common.constant.GlobalConstant;
import cn.innovation.platform.common.enums.SendStatusEnum;
import cn.innovation.platform.common.utils.Base64Utils;
import cn.innovation.platform.common.utils.MD5Utils;
import cn.innovation.platform.common.utils.StringUtils;
import cn.innovation.platform.insurance.common.dto.VinsunRecordsDto;
import cn.innovation.platform.insurance.common.enums.VinsunCodeEnum;
import cn.innovation.platform.insurance.common.model.VinsunRecords;
import cn.innovation.platform.insurance.common.utils.InsuranceApiHelper;
import cn.innovation.platform.insurance.mapper.VinsunRecordsMapper;
import cn.innovation.platform.insurance.service.IMobileCityService;
import cn.innovation.platform.insurance.service.IVinsunRecordsService;

/**
 * @ClassName: VinsunRecordsServiceImpl
 * @Description: 畅思保险接口实现
 * @author mqx
 * @date 2018年12月18日 下午11:09:24
 */
@Service("vinsunRecordsService")
public class VinsunRecordsServiceImpl extends ServiceImpl<VinsunRecordsMapper, VinsunRecords>
		implements IVinsunRecordsService {

	private static final Log logger = LogFactory.get();

	@Resource
	private InsuranceApiHelper insuranceApiHelper;

	@Resource
	private IMobileCityService mobileCityService;

	@Override
	public Map<String, String> addApply(VinsunRecordsDto dto) {
		Map<String, String> map = new HashMap<String, String>();
		String reqeustId = dto.getRequestTime();
		VinsunRecords records = new VinsunRecords();
		try {
			// 读取基础参数
			String mchId = insuranceApiHelper.getVinsunMchId();
			Long spBillno = System.currentTimeMillis();
			String media = insuranceApiHelper.getVinsunMedia();

			// 流水号
			long startTime = System.currentTimeMillis();
			logger.info("[赠险](畅思保险):数据处理开始,接收参数:{}", dto);
			dto.setMchId(mchId);
			dto.setSpBillno(spBillno.toString());
			dto.setMedia(media);

			// 根据号码查询是否已发送过
			boolean isNotApply = true;
			EntityWrapper<VinsunRecords> wrapper = new EntityWrapper<VinsunRecords>();
			wrapper.where("mobile = {0}", dto.getMobile());
			StringBuffer findParam = new StringBuffer();
			findParam.append(" (status='").append(SendStatusEnum.processing.getId()).append("' or ");
			findParam.append("status='").append(SendStatusEnum.success.getId()).append("' or ");
			findParam.append("status='").append(SendStatusEnum.noBalance.getId()).append("' or ");
			findParam.append("status='").append(SendStatusEnum.delayBalance.getId()).append("')");
			wrapper.and(findParam.toString());
			List<VinsunRecords> list = this.selectList(wrapper);
			if (list != null && list.size() > 0) {
				isNotApply = false;
				logger.info("[赠险](畅思保险):数据检查完成!流水号:{},重复提交了数据!", reqeustId);
				map.put("code", VinsunCodeEnum.CODE_1010.value());
				return map;
			}
			boolean isNotCity = false;
			if (isNotApply) {
				// 查询号码归属地
				String city = mobileCityService.getCityByMobile(dto.getMobile());
				if (StringUtils.isNotEmpty(city)) {
					dto.setClientCity(city);
					BeanUtil.copyProperties(dto, records);
				} else {
					isNotCity = true;
					logger.info("[赠险](畅思保险):归属地查询完成!流水号:{},非法号码!号码:{}", reqeustId, dto.getMobile());
					// 状态为非法号码
					records.setStatus(SendStatusEnum.processing.getId());
					this.insert(records);
					map.put("code", VinsunCodeEnum.CODE_1005.value());
					return map;
				}
			}
			if (!isNotCity) {
				// 状态为发送中
				records.setCreateTime(new Date());
				records.setUpdateTime(new Date());
				records.setStatus(SendStatusEnum.processing.getId());
				boolean isSave = this.insert(records);
				String resultData = "";
				if (isSave) {
					// 调用接口发送
					resultData = sendVinsun(dto);
					if (StringUtils.isNotEmpty(resultData)) {
						// 获取返回Code
						JSONObject jsonObject = JSONUtil.parseObj(resultData);
						String code = jsonObject.get("code").toString();
						map.put("code", code);
						if (StringUtils.isNotEmpty(code)) {
							if (code.equals(VinsunCodeEnum.CODE_1001.value()) && jsonObject.get("data") != null) {
								Object data = jsonObject.get("data");
								JSONObject jsonData = JSONUtil.parseObj(data);
								String orderId = jsonData.get("order_id").toString();
								String policyNo = jsonData.get("policy_no").toString();
								// 当单号正常返回时才当作正常
								if (StringUtils.isNotEmpty(orderId) && StringUtils.isNotEmpty(policyNo)) {
									map.put("orderId", code);
									map.put("policyNo", policyNo);
									// 状态为发送成功
									records.setStatus(SendStatusEnum.success.getId());
									logger.info("[赠险](畅思保险):接口请求完成!流水号:{},数据提交成功！", reqeustId);
								}
							} else if (code.equals(VinsunCodeEnum.CODE_1101.value())) {
								Object data = jsonObject.get("data");
								JSONObject jsonData = JSONUtil.parseObj(data);
								String err_code = jsonData.get("err_code").toString();
								// 投保延迟
								if (err_code.equals(VinsunCodeEnum.CODE_302.value())) {
									records.setStatus(SendStatusEnum.delayBalance.getId());
								}
							} else if (code.equals(VinsunCodeEnum.CODE_1010.value())) {
								// 重复提交数据，当作延迟结算
								records.setStatus(SendStatusEnum.delayBalance.getId());
							} else {
								// 状态为发送失败
								records.setStatus(SendStatusEnum.failure.getId());
							}
						}
					} else {
						// 状态为发送失败
						records.setStatus(SendStatusEnum.failure.getId());
					}
					// 更新发送状态
					records.setResult(resultData);
					records.setUpdateTime(new Date());
					this.insertOrUpdate(records);
				}
				logger.info("[赠险](畅思保险):数据处理完成!,流水号:{},耗时:{}", reqeustId, (System.currentTimeMillis() - startTime));
			}
		} catch (Exception e) {
			// 状态为发送失败
			records.setStatus(SendStatusEnum.failure.getId());
			records.setUpdateTime(new Date());
			this.insertOrUpdate(records);

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw, true);
			e.printStackTrace(pw);
			String stackTraceString = sw.getBuffer().toString();
			logger.info("[赠险](畅思保险):数据处理异常!流水号:{},原因:{}", reqeustId, stackTraceString);
		}
		return map;
	}

	/**
	 * @Description: 调用畅思接口
	 * @param dto
	 *            参数
	 * @return
	 */
	private String sendVinsun(VinsunRecordsDto dto) {
		String resultData = null;
		String reqeustId = dto.getRequestTime();
		try {
			String mchSecret = insuranceApiHelper.getVinsunSecret();

			// 组织接口请求参数
			String name = Base64Utils.encode(dto.getName()).replace("+", "-").replace("/", "_");
			String clientCity = Base64Utils.encode(dto.getClientCity()).replace("+", "-").replace("/", "_");

			// 签名参数
			StringBuffer sbf = new StringBuffer();
			sbf.append("mch_id=").append(dto.getMchId()).append("&");
			sbf.append("sp_billno=").append(dto.getSpBillno()).append("&");
			sbf.append("name=").append(name).append("&");
			sbf.append("sex=").append(dto.getSex()).append("&");
			sbf.append("birth=").append(dto.getBirth()).append("&");
			sbf.append("mobile=").append(dto.getMobile()).append("&");
			sbf.append("client_ip=").append(dto.getClientIp()).append("&");
			sbf.append("client_city=").append(clientCity).append("&");
			sbf.append("media=").append(dto.getMedia()).append("&");
			sbf.append("ua=").append(dto.getUa()).append("&");
			sbf.append("mch_secret=").append(mchSecret);

			// 计算签名
			String sign = MD5Utils.md5(sbf.toString());
			sbf.append("&sign=").append(sign);

			// 调用畅思接口
			long startTime = System.currentTimeMillis();
			String requestUrl = insuranceApiHelper.getVinsunUrl() + "&" + sbf.toString();
			logger.info("[赠险](畅思保险):接口请求开始!发送参数:{}", requestUrl);
			resultData = HttpRequest.post(requestUrl).charset("UTF-8").timeout(GlobalConstant.API_REQUEST_TIMEOUT)
					.execute().body();
			logger.info("[赠险](畅思保险):接口请求完成!耗时:{},流水号:{},返回:{}", (System.currentTimeMillis() - startTime), reqeustId,
					resultData);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw, true);
			e.printStackTrace(pw);
			String stackTraceString = sw.getBuffer().toString();
			logger.info("[赠险](畅思保险):接口请求异常!流水号:{},原因:{}", reqeustId, stackTraceString);
		}
		return resultData;
	}

}
