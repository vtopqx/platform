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

import cn.innovation.platform.common.enums.SendStatusEnum;
import cn.innovation.platform.common.utils.MD5Utils;
import cn.innovation.platform.common.utils.StringUtils;
import cn.innovation.platform.insurance.common.dto.DamdataRecordsDto;
import cn.innovation.platform.insurance.common.enums.DamdataCodeEnum;
import cn.innovation.platform.insurance.common.model.DamdataRecords;
import cn.innovation.platform.insurance.common.utils.InsuranceApiHelper;
import cn.innovation.platform.insurance.mapper.DamdataRecordsMapper;
import cn.innovation.platform.insurance.service.IDamdataRecordsService;

/**
 * @ClassName: DamdataRecordsServiceImpl
 * @Description: 大坝保险申请记录表 服务实现类
 * @author mqx
 * @date 2018年12月17日 下午8:17:39
 */
@Service("damdataRecordsService")
public class DamdataRecordsServiceImpl extends ServiceImpl<DamdataRecordsMapper, DamdataRecords>
		implements IDamdataRecordsService {

	private static final Log logger = LogFactory.get();

	@Resource
	private InsuranceApiHelper insuranceApiHelper;

	@Override
	public String addApply(DamdataRecordsDto dto) {
		String code = "";
		String reqeustId = dto.getRequestTime();
		DamdataRecords records = new DamdataRecords();
		try {
			long startTime = System.currentTimeMillis();
			logger.info("[赠险](大坝保险):数据处理开始,接收参数:{}", dto);
			if (!StringUtils.isNotEmpty(dto.getApplyCredit())) {
				dto.setApplyCredit("其他");
			}
			BeanUtil.copyProperties(dto, records);
			records.setCreateTime(new Date());
			records.setUpdateTime(new Date());
			// 查收是否已经申请过
			EntityWrapper<DamdataRecords> wrapper = new EntityWrapper<DamdataRecords>();
			wrapper.where("mobile = {0}", dto.getMobile());
			StringBuffer findParam = new StringBuffer();
			findParam.append("(status='").append(SendStatusEnum.processing.getId()).append("' or ");
			findParam.append("status='").append(SendStatusEnum.success.getId()).append("')");
			wrapper.and(findParam.toString());
			List<DamdataRecords> list = this.selectList(wrapper);
			if (list != null && list.size() > 0) {
				logger.info("[赠险](大坝保险):数据检查完成!流水号:{},重复提交了数据!", reqeustId);
				code = DamdataCodeEnum.REPEAT.value();
			} else {
				// 状态为发送中
				records.setStatus(SendStatusEnum.processing.getId());
				boolean isSave = this.insert(records);
				if (isSave) {
					// 调用接口发送
					String resultData = sendDamdata(dto);
					if (StringUtils.isNotEmpty(resultData)) {
						// 获取返回Code
						JSONObject jsonObject = JSONUtil.parseObj(resultData);
						code = jsonObject.get("code").toString();
						records.setResult(resultData);
						if (StringUtils.isNotEmpty(code) && code.equals(DamdataCodeEnum.SUCCESS.value())) {
							// 状态为发送成功
							records.setStatus(SendStatusEnum.success.getId());
							logger.info("[赠险](大坝保险):接口请求完成!数据提交成功,流水号:{},", reqeustId);
						} else {
							records.setStatus(SendStatusEnum.failure.getId());
							logger.info("[赠险](大坝保险):接口请求失败!流水号:{},", reqeustId);
						}
					} else {
						// 状态为发送失败
						records.setStatus(SendStatusEnum.failure.getId());
					}
					records.setUpdateTime(new Date());
					this.insertOrUpdate(records);
				}
			}
			logger.info("[赠险](大坝保险):数据处理完成!,流水号:{},耗时:{}", reqeustId, (System.currentTimeMillis() - startTime));
		} catch (Exception e) {
			// 状态为发送失败
			records.setStatus(SendStatusEnum.failure.getId());
			records.setUpdateTime(new Date());
			this.insertOrUpdate(records);
			
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw, true);
			e.printStackTrace(pw);
			String stackTraceString = sw.getBuffer().toString();
			logger.info("[赠险](大坝保险):数据处理异常!流水号:{},原因:{}", reqeustId, stackTraceString);
		}
		return code;
	}

	private String sendDamdata(DamdataRecordsDto dto) {
		String resultData = "";
		String reqeustId = dto.getRequestTime();
		try {
			// 组织接口请求参数
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("requestTime", dto.getRequestTime());
			params.put("name", dto.getName());
			params.put("sex", dto.getSex());
			params.put("birthDay", dto.getBirthDay());
			params.put("mobile", dto.getMobile());
			params.put("age", dto.getAge());

			String str = StringUtils.concatParams(params) + insuranceApiHelper.getDamdataSecret();
			String sign = MD5Utils.md5(str);
			params.put("sign", sign);

			params.put("userAgent", dto.getUserAgent());
			params.put("hadCredit", dto.getHadCredit());
			params.put("ip", dto.getIp());
			params.put("referer", dto.getReferer());
			params.put("applyCredit", dto.getApplyCredit());

			// 调用大坝接口
			logger.info("[赠险](大坝保险):接口请求开始!流水号:{},发送地址:{}", reqeustId, insuranceApiHelper.getDamdataUrl());
			logger.info("[赠险](大坝保险):接口请求开始!流水号:{},发送参数:{}", reqeustId, params);
			resultData = HttpRequest.post(insuranceApiHelper.getDamdataUrl()).charset("UTF-8").form(params).execute()
					.body();
			logger.info("[赠险](大坝保险):接口请求完成!流水号:{},返回:{}", reqeustId, resultData);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw, true);
			e.printStackTrace(pw);
			String stackTraceString = sw.getBuffer().toString();
			logger.info("[赠险](大坝保险):接口请求异常!流水号:{},原因:{}", reqeustId, stackTraceString);
		}
		return resultData;
	}
}
