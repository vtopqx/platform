package insurance;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.xiaoleilu.hutool.http.Header;
import com.xiaoleilu.hutool.http.HttpRequest;
import com.xiaoleilu.hutool.util.MapUtil;

import cn.innovation.platform.common.utils.SignUtils;

/**
 * @ClassName: DamdataTest
 * @Description: 大坝接口测试
 * @date 2018年12月17日 下午8:28:38
 */
public class DamdataTest {

	/**
	 */
	public final static String APPLICATION_FORM_URLENCODED_VALUE = "application/x-www-form-urlencoded";

	public static void main(String[] args) {
		try {
			String sendUrl = "http://localhost:9001/services/api/insurance/thdpy";
			String secret = "b7d5bae95d6de271851e45357aa0f3e6";
			// 参数
			Map<String, Object> map = new HashMap<String, Object>();
			 //1、定义转换格式
//		    SimpleDateFormat formatter  = new SimpleDateFormat("yyyyMMddHHmmss");
//		    String once = formatter.format(new Date());
			String once = "20181217213240";
			map.put("appKey", "448");
			map.put("channel", "ty2");
			map.put("requestTime", once);
			map.put("name", "测试");
			map.put("sex", "1");
			map.put("birthDay", "2018-12-12");
			map.put("mobile", "13798156379");
			map.put("age", "30");
			map.put("referer", "21");
			map.put("hadCredit", "无信用卡");
			map.put("applyCredit", "其他");

			String signature = SignUtils.genSig(map, secret);
			map.put("sign", signature);
			System.out.println("sign:"+signature);
			// 请求url
			String requestUrl = sendUrl + "?" + MapUtil.join(map, "&", "=");
			System.out.println("URL:" + requestUrl);
			String resultData = HttpRequest.post(requestUrl.toString())
					.header(Header.CONTENT_TYPE, APPLICATION_FORM_URLENCODED_VALUE).execute().body();

			System.out.println("result:" + resultData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
