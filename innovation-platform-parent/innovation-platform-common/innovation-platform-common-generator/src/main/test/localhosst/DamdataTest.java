package localhosst;

import java.util.HashMap;
import java.util.Map;

import com.xiaoleilu.hutool.http.Header;
import com.xiaoleilu.hutool.http.HttpRequest;

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
			long startTime = System.currentTimeMillis();
//			String sendUrl = "http://localhost:9001/services/api/insurance/thdpy";
//			String sendUrl = "http://localhost:9001/services/api/insurance/apply";
			String sendUrl = "http://www.nengxi.net/services/api/insurance/apply";
			String secret = "44a54ce509d56d1f930b9d00b09c9ec5";
			// 参数
			Map<String, Object> map = new HashMap<String, Object>();
			 //1、定义转换格式
			map.put("appKey", "4");
			map.put("channel", "test_b");
			map.put("requestTime", System.currentTimeMillis());
			map.put("name", "何远翔");
			map.put("sex", "1");
			map.put("birth", "1983-03-25");
			map.put("mobile", "13798156379");
			map.put("age", "35");
			map.put("referer", "http://www.google.com");
			map.put("hadCredit", "无信用卡");
			map.put("applyCredit", "光大银行");

			String signature = SignUtils.genSig(map, secret);
			map.put("sign", signature);
			System.out.println("sign:"+signature);
			// 请求url
			System.out.println("URL:" + sendUrl);
			
			String resultData = HttpRequest.post(sendUrl).form(map)
					.header(Header.CONTENT_TYPE, APPLICATION_FORM_URLENCODED_VALUE).execute().body();

			System.out.println("result:" + resultData);
			System.out.println("耗时:"+(System.currentTimeMillis()-startTime));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
