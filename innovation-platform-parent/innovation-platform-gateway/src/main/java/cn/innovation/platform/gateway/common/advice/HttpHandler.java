package cn.innovation.platform.gateway.common.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.StringTokenizer;

/**
 * @ClassName: HttpHandler 
 * @Description: Http工具类
 * @author mqx 
 * @date 2018年12月17日 下午9:07:12
 */
public class HttpHandler {
  private static final Logger LOGGER = LoggerFactory.getLogger(HttpHandler.class);

  /**
   * 获取客户端IP.
   * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
   *
   * @param request HttpServletRequest
   * @return 客户端IP
   */
  public static String getClientIp(HttpServletRequest request) {

    // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
    String ip = request.getHeader("X-Forwarded-For");

    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("Proxy-Client-IP");

      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("WL-Proxy-Client-IP");

      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("HTTP_CLIENT_IP");

      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("HTTP_X_FORWARDED_FOR");

      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getRemoteAddr();
        if (ip!=null && ip.contains(":")) {
          ip = "127.0.0.1";
        }
      }

    } else if (ip.length() > 15) {
      String[] ips = ip.split(",");
      for (String strIp : ips) {
        if (!("unknown".equalsIgnoreCase(strIp))) {
          ip = strIp;
          break;
        }
      }
    }
    return ip;
  }

  /**
   * 获取用户的操作系统名.
   *
   * @param request 客户端请求
   * @return 用户的操作系统名
   */
  public static String getClientOsName(HttpServletRequest request) {
    String agent = request.getHeader("user-agent");

    StringTokenizer st = new StringTokenizer(agent, ";");
    st.nextToken();
    //得到用户的操作系统名
    return st.nextToken();
  }

  /**
   * 获取用户的浏览器名.
   *
   * @param request 客户端请求
   * @return 用户的浏览器名
   */
  public static String getClientBrowserName(HttpServletRequest request) {
    String agent = request.getHeader("user-agent");

    StringTokenizer st = new StringTokenizer(agent, ";");
    //得到用户的浏览器名
    return st.nextToken();
  }

  /**
   * 标识客户端.
   *
   * @param request 客户端请求
   * @return 客户端标识
   */
  public static String identificationClient(HttpServletRequest request) {
    return encodeMD5(request.getHeader("user-agent"));
  }

  /**
   * md5
   *
   * @param str
   * @return
   */
  public static String encodeMD5(String str) {
    try {
      java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
      md.update(str.getBytes());
      byte[] encodedPassword = md.digest();
      java.lang.StringBuilder sb = new java.lang.StringBuilder();
      for (int i = 0; i < encodedPassword.length; i++) {
        if ((encodedPassword[i] & 0xff) < 0x10) {
          sb.append("0");
        }
        sb.append(Long.toString((encodedPassword[i] & 0xff), 16));
      }
      return (sb.toString().toUpperCase());
    } catch (Exception ex) {
      LOGGER.error("",ex);
    }
    return null;
  }

  /**
   * 从URL地址中，提取其中的主机（域名）名称.
   *
   * @param url URL地址
   * @return 主机（域名）名称
   */
  public static String getHost(String url) {
    String host = "";
    try {
      URL uri = new URL(url);
      host = uri.getHost();
    } catch (MalformedURLException e) {
      LOGGER.warn(e.getMessage() + "(" + url + ")");
    }
    return host;
  }

  /**
   * 处理不同浏览器下文件编码问题。若出现异常，则返回源字符.
   *
   * @param request HttpServletRequest
   * @param src     源字符
   * @return 处理后的字符
   */
  public static String dealEncode(HttpServletRequest request, String src) {
    try {
      // 处理不同浏览器下文件编码问题
      String agent = request.getHeader("USER-AGENT");
      if ((agent != null) && (agent.contains("MSIE"))) { // IE浏览器
        src = URLEncoder.encode(src, "UTF8");
      } else if ((agent != null) && (agent.contains("Chrome")
          || agent.contains("Firefox"))) { // google,火狐浏览器
        src = new String(src.getBytes(), "ISO8859-1");

      } else {
        src = URLEncoder.encode(src, "UTF8"); // 其他浏览器
      }
      return src.replaceAll("\\+", "%20").replaceAll(":", "%3A").replaceAll("~", "%7E");
    } catch (UnsupportedEncodingException e) {
      LOGGER.warn(e.getMessage());
      return src;
    }
  }

  /**
   * 获取请求头信息.
   *
   * @param request HTTP请求
   * @param name    名称
   * @return 请求头中的信息，若没有设置该信息，则返回空字符串。
   */
  public static String getHeader(HttpServletRequest request, String name) {
    String value = request.getHeader(name);

    if (null == value) {
      value = "";
    }
    return value;
  }

}
