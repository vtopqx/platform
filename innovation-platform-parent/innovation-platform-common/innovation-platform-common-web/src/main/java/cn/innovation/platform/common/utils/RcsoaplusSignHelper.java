package cn.innovation.platform.common.utils;

import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘利民
 */
public class RcsoaplusSignHelper {

    private static final Log logger = LogFactory.get();

    private static String concatParams(Map<String, Object> params) throws UnsupportedEncodingException {
        Object[] key_arr = params.keySet().toArray();
        Arrays.sort(key_arr);
        String str = "";

        for (Object key : key_arr) {
            if (key.equals("signature")) {
                continue;
            }
            String val = params.get(key).toString();
            key = URLEncoder.encode(key.toString(), "UTF-8");
            val = URLEncoder.encode(val, "UTF-8");
            str += "&" + key + "=" + val;
        }

        return str.replaceFirst("&", "");
    }

    private static String byte2hex(byte[] b) {
        StringBuffer buf = new StringBuffer();
        int i;

        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0) {
                i += 256;
            }
            if (i < 16) {
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }

        return buf.toString();
    }

    public static String genSig(Map<String, Object> params,
                                String consumerSecret) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        StringBuffer stringBuffer = new StringBuffer(concatParams(params));
        stringBuffer.append(consumerSecret);

        String str = stringBuffer.toString();
        logger.debug("生成签名前的字符串==>{}", str);
        System.out.println("生成签名前的字符串==>" + str);

        MessageDigest md = MessageDigest.getInstance("SHA1");
        String sig =  byte2hex(md.digest(byte2hex(stringBuffer.toString().getBytes("UTF-8")).getBytes()));
        logger.debug("生成签名后的字符串==>{}", sig);
        System.out.println("生成签名后的字符串==>" + sig);
        return sig;
    }

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Map<String, Object> params = new HashMap<>();
        params.put("name", "kkk");
        params.put("sex", "1");
        String sig = genSig(params, "111111111111111111111111");
        logger.debug("sig=====>{}", sig);
        System.out.println("sig=====>" + sig);
    }
}
