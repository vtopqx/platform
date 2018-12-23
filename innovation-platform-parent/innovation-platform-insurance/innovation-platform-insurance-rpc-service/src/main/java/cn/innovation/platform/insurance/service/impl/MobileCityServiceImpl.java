package cn.innovation.platform.insurance.service.impl;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xiaoleilu.hutool.http.HttpRequest;
import com.xiaoleilu.hutool.json.JSONArray;
import com.xiaoleilu.hutool.json.JSONObject;
import com.xiaoleilu.hutool.json.JSONUtil;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;

import cn.innovation.platform.common.constant.GlobalConstant;
import cn.innovation.platform.insurance.common.utils.InsuranceApiHelper;
import cn.innovation.platform.insurance.service.IMobileCityService;

/**
 * @ClassName: MobileCityServiceImpl
 * @Description: 号码归属地查询接口实现
 * @author mqx
 * @date 2018年12月22日 下午12:49:22
 */
@Service("mobileCityService")
public class MobileCityServiceImpl implements IMobileCityService {

	private static final Log logger = LogFactory.get();

	@Resource
	private InsuranceApiHelper insuranceApiHelper;

	@Override
	public String getCityByMobile(String mobile) {
		String city = null;
		long snycTime = System.currentTimeMillis();
		try {
			StringBuffer sbf = new StringBuffer(insuranceApiHelper.getMobileUrl());
			sbf.append("?").append("tel=").append(mobile);
			String sendUrl = sbf.toString();
			logger.info("[号码归属地],根据号码获取归属地开始!请求参数:{}", sendUrl);
			String result = HttpRequest.get(sendUrl).timeout(GlobalConstant.API_REQUEST_TIMEOUT).execute().body();
			logger.info("[号码归属地],根据号码获取归属地完成!号码:{},耗时:{},返回:{}", mobile, (System.currentTimeMillis() - snycTime),
					result);
			// 获取归属地省份
			if (!StringUtils.isEmpty(result)) {
				// 解析json
				JSONObject jsonObject = JSONUtil.parseObj(result);
				String response = jsonObject.getStr("response");
				String mbStr = JSONUtil.parseObj(response).getStr(mobile);
				String detail = JSONUtil.parseObj(mbStr).getStr("detail");
				String area = JSONUtil.parseObj(detail).getStr("area");
				// province = JSONUtil.parseObj(detail).getStr("province");
				JSONArray array = JSONUtil.parseArray(area);
				city = array.getJSONObject(0).getStr("city");
			}
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw, true);
			e.printStackTrace(pw);
			String stackTraceString = sw.getBuffer().toString();
			logger.error("[号码归属地],根据号码获取归属地异常!号码:{},耗时:{},异常信息:{}", mobile, (System.currentTimeMillis() - snycTime),
					stackTraceString);
		}
		return city;
	}

}
