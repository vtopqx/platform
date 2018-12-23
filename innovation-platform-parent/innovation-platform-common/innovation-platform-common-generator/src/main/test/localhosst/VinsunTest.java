package localhosst;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.xiaoleilu.hutool.http.Header;
import com.xiaoleilu.hutool.http.HttpRequest;

import cn.innovation.platform.common.utils.SignUtils;

/**
 * 
 * @ClassName: VinsunTest
 * @Description: 畅思接口测试
 * @author mqx
 * @date 2018年12月22日 上午10:32:05
 */
public class VinsunTest {

	/**
	 */
	public final static String APPLICATION_FORM_URLENCODED_VALUE = "application/x-www-form-urlencoded";

	/**
	 * @Description: 接口调用示例
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// 接口地址
			String sendUrl = "http://localhost:9001/services/api/insurance/apply";
			// 应用Key
			String appKey = "5";
			// 渠道
			String channel = "test_c";
			// 加密秘钥
			String appSecret = "8c078eec5c0296524e27328252892334";

			// 参数组装
			Map<String, Object> map = new HashMap<String, Object>();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			String once = formatter.format(new Date());
			map.put("appKey", appKey);
			map.put("channel", channel);
			map.put("requestTime", once);
			map.put("name", "何远翔");
			map.put("sex", "1");
			map.put("birth", "1983-03-25");
			map.put("mobile", "13601384777");
			map.put("age", "35");

			// 计算签名
			String signature = SignUtils.genSig(map, appSecret);
			map.put("sign", signature);

			System.out.println("URL:" + sendUrl);
			// 调用接口
			String resultData = HttpRequest.post(sendUrl).form(map)
					.header(Header.CONTENT_TYPE, APPLICATION_FORM_URLENCODED_VALUE).execute().body();

			System.out.println("result:" + resultData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
