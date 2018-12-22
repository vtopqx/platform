package insurance;

import java.util.HashMap;
import java.util.Map;

import com.xiaoleilu.hutool.http.HttpRequest;

import cn.innovation.platform.common.utils.MD5Utils;
import cn.innovation.platform.common.utils.StringUtils;

/**
 * @ClassName: DamdataControllerDemo 
 * @Description: 大坝接口联调
 * @author mqx 
 * @date 2018年12月22日 上午10:32:46
 */
public class DamdataControllerDemo {

    public static void main(String[] args) {
    	try {
    		final String secretKey = "25461A273D9A48EB8F8E7539737F4C0B";
            String requestTime = "20181218222022";//DateUtils.formatDate(new Date(),"yyyyMMddHHmmss");
            String sign = null;

            String name = "何远翔";
            String sex = "1";
            String birthDay = "1983-03-25";
            String mobile = "13798156379";
            String age = "35";
            String referer = "http://www.google.com";
            String ip = "58.248.29.11";
            String userAgent = "Mozilla1";
            String hadCredit = "无信用卡";

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("requestTime",requestTime);
            params.put("name",name);
            params.put("sex",sex);
            params.put("birthDay",birthDay);
            params.put("mobile",mobile);
            params.put("age",age);
            
            String str = StringUtils.concatParams(params) + secretKey;
			sign = MD5Utils.md5(str);
            params.put("sign", sign);
            System.out.println("sign:"+sign);
            
            params.put("userAgent", userAgent);
            params.put("hadCredit", hadCredit);
            params.put("ip", ip);
            params.put("referer", referer);
    		
    		String resultData = HttpRequest.post("http://www.damdata.cn/api/thdpy/ty2").charset("UTF-8").form(params).execute().body();
    		System.out.println("resultData:"+resultData);
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
