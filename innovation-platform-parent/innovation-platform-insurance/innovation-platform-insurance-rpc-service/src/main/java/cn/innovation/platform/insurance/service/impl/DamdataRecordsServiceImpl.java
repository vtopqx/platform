package cn.innovation.platform.insurance.service.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaoleilu.hutool.bean.BeanUtil;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;

import cn.innovation.platform.common.enums.SendStatusEnum;
import cn.innovation.platform.insurance.common.dto.DamdataRecordsDto;
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
		String result = "0";
		try {
			long startTime = System.currentTimeMillis();
			DamdataRecords records = new DamdataRecords();
			logger.info("[赠险](大坝保险):数据处理开始,接收参数:{}", dto);
			BeanUtil.copyProperties(dto, records);
			records.setCreateTime(new Date());
			records.setUpdateTime(new Date());
			// 状态为发送中
			records.setStatus(SendStatusEnum.processing.getId());
			boolean isSuccess = this.insert(records);
			// //调用接口发送
			// if (isSuccess) {
			// Map<String, String> params = new HashMap<String, String>();
			// params.put("requestTime", "");
			// params.put("sign", "");
			// params.put("name", "");
			// params.put("sex", "");
			// params.put("birthDay", "");
			// params.put("mobile", "");
			// params.put("age", "");
			// params.put("referer", "");
			// params.put("ip", "");
			// params.put("userAgent", "");
			// params.put("hadCredit", "");
			// params.put("applyCredit", "");
			//
			// String requestUrl =
			// StringUtils.getRequestUrl(insuranceApiHelper.getDamdataUrl(),
			// params);
			// String resultData =
			// HttpRequest.post(requestUrl).execute().body();
			//
			// // 消息处理拆分
			//// JSONObject jsonObject = JSONUtil.parseObj(requestUrl);
			//// MessageRecordDto record =
			// jsonObject.toBean(MessageRecordDto.class);
			//
			// }
			logger.info("[赠险](大坝保险):数据处理完成!,流水号:{},耗时:{}", dto.getRequestTime(),
					(System.currentTimeMillis() - startTime));
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw, true);
			e.printStackTrace(pw);
			String stackTraceString = sw.getBuffer().toString();
			logger.info("[赠险](大坝保险):数据处理异常!流水号:{},原因:{}", dto.getRequestTime(),stackTraceString);
		}
		return result;
	}
}
