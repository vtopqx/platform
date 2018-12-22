package insurance;

import com.xiaoleilu.hutool.http.HttpRequest;

import cn.innovation.platform.common.utils.Base64Utils;
import cn.innovation.platform.common.utils.MD5Utils;

/**
 * @ClassName: VisumControllerDemo 
 * @Description: 畅思接口联调
 * @author mqx 
 * @date 2018年12月22日 上午10:32:59
 */
public class VisumControllerDemo {

    public static void main(String[] args) {
    	try {
    		String mch_secret = "5879f7605c11fd17b4e1e2558064ea4c";
    		
    		String mch_id = "nengxi01";
    		String sp_billno = "1545461649430";
    		String sex = "1";
    		String birth = "1983-03-25";
    		String mobile = "13601384777";
    		String client_ip = "58.248.29.11";
    		String media = "nengxi01-vinsun-01a";
    		String ua = "Mozilla1";
    		
    		String name = Base64Utils.encode("何远翔").replace("+", "-").replace("/", "_");
    		String client_city = Base64Utils.encode("北京").replace("+", "-").replace("/", "_");
    				
    		//签名参数
    		StringBuffer sbf =  new StringBuffer();
    		sbf.append("mch_id=").append(mch_id).append("&");
    		sbf.append("sp_billno=").append(sp_billno).append("&");
    		sbf.append("name=").append(name).append("&");
    		sbf.append("sex=").append(sex).append("&");
    		sbf.append("birth=").append(birth).append("&");
    		sbf.append("mobile=").append(mobile).append("&");
    		sbf.append("client_ip=").append(client_ip).append("&");
    		sbf.append("client_city=").append(client_city).append("&");
    		sbf.append("media=").append(media).append("&");
    		sbf.append("ua=").append(ua).append("&");
    		sbf.append("mch_secret=").append(mch_secret);
    		
    		System.out.println("加密前:"+sbf.toString());
    		String sign = MD5Utils.md5(sbf.toString());
    		System.out.println("sign:"+sign);
    		
    		sbf.append("&sign=").append(sign);
    		
    		System.out.println(sbf.toString());
            
    		String url = "http://vinsun.axhao.com/api.aspx?cmd=submit_3dorder&"+sbf.toString();
    		System.out.println("url:"+url);
    		String resultData = HttpRequest.post(url).charset("UTF-8").execute().body();
    		System.out.println("resultData:"+resultData);
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
