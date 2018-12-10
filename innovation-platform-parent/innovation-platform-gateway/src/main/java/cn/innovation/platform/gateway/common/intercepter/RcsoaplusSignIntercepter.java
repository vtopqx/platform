package cn.innovation.platform.gateway.common.intercepter;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import com.xiaoleilu.hutool.util.StrUtil;

import cn.innovation.platform.common.enums.GlobalStatusCode;
import cn.innovation.platform.common.exception.ParamsSignValidErrorException;
import cn.innovation.platform.common.exception.ServiceException;
import cn.innovation.platform.common.utils.RcsoaplusSignHelper;
import cn.innovation.platform.upms.common.model.ApplicationRegisterInfo;
import cn.innovation.platform.upms.service.IApplicationRegisterInfoService;

/**
 * @author 刘利民
 * 验证接口签名拦截器
 */
public class RcsoaplusSignIntercepter implements HandlerInterceptor {

    private static final Log logger = LogFactory.get();
    
    @Resource
	private IApplicationRegisterInfoService applicationRegisterInfoService;

    private Boolean isOpenSign;

    public void setOpenSign(Boolean openSign) {
        isOpenSign = openSign;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServiceException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (handler instanceof HandlerMethod) {
        	//设置允许跨域访问
            response.setHeader("Access-Control-Allow-Origin", "*");
            String contextType = request.getHeader(HttpHeaders.CONTENT_TYPE);
            Enumeration<String> headerNames =  request.getHeaderNames();

            StringBuffer heads = new StringBuffer();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                String headerValue = request.getHeader(headerName);
                heads.append(headerName).append("\t\t\t==>").append(headerValue).append("\n");
            }
            logger.debug("第三方平台发送过来的请求头\n{}", heads);
            StringBuilder sb = new StringBuilder();
            StringBuilder paramsb = new StringBuilder();
            Map<String, String[]> stringMap = request.getParameterMap();
            Map<String, Object> params = new HashMap<>();
            for (Map.Entry entry : stringMap.entrySet()) {
                String key = entry.getKey().toString();
                String[] value = (String[])(entry.getValue());
                params.put(key, value[0]);
                paramsb.append(key).append("\t\t\t==>").append(value[0]).append("\n");
            }
            logger.debug("第三方平台发送过来的请求参数\n{}", paramsb);
            if (StrUtil.isNotBlank(contextType)) {
                if (contextType.equals(MediaType.APPLICATION_FORM_URLENCODED_VALUE)) {
                    HandlerMethod h = (HandlerMethod) handler;
                    sb.append("Controller: ").append(h.getBean().getClass().getName()).append("\n");
                    sb.append("Method    : ").append(h.getMethod().getName()).append("\n");
                    sb.append("Params    : ").append(getParamString(request.getParameterMap())).append("\n");
                    sb.append("URI       : ").append(request.getRequestURI()).append("\n");
                    logger.debug("第三方平台的请求情况\n{}", sb.toString());
                    //获取redis缓存中注册信息
					ApplicationRegisterInfo appInfo = null;
					//开始验证参数
                    String consumerKey = params.getOrDefault("consumerKey", "").toString();
                    if (StrUtil.isBlank(consumerKey)) {
                        throw new ServiceException(GlobalStatusCode.CODE_400.value(), "consumerKey不能为空");
					} else {
						// 验证consumerKey是否正确
						long startTime = System.currentTimeMillis();
						appInfo = applicationRegisterInfoService.getApplicationAccount(consumerKey);
						long endTime = System.currentTimeMillis();
						logger.debug("调用upms服务获取应用创新平台权限，耗时{}",(endTime - startTime));
						
						if (StringUtils.isEmpty(appInfo)) {
							throw new ServiceException(GlobalStatusCode.CODE_401.value(), "consumerKey验证不通过");
						}
					}
                    String signature = params.getOrDefault("signature", "").toString();
                    if (StrUtil.isBlank(signature)) {
                        throw new ServiceException(GlobalStatusCode.CODE_400.value(), "signature不能为空");
                    }
                    String requestId = params.getOrDefault("requestId", "").toString();
                    if (StrUtil.isBlank(requestId)) {
                        throw new ServiceException(GlobalStatusCode.CODE_400.value(), "requestId不能为空");
                    }
                    String version = params.getOrDefault("version", "").toString();
                    if (StrUtil.isBlank(version)) {
                        throw new ServiceException(GlobalStatusCode.CODE_400.value(), "version不能为空");
                    }
                    //通过密钥进行签名验证
                    String consumerSecret = appInfo.getConsumerSecret();;
                    String sig = RcsoaplusSignHelper.genSig(params, consumerSecret.trim());
                    String sig2 = params.get("signature").toString();
                    //检查是否开启签名验证，true代表开启，false代表关闭
                    if (isOpenSign && !sig.equals(sig2)) {
                        logger.error("签名错误");
                        throw new ParamsSignValidErrorException("签名错误！");
                    }
                } else {
                    logger.error("Content-Type错误");
                    throw new ServiceException(GlobalStatusCode.CODE_400.value(), "Content-Type错误");
                }
            } else {
                logger.error("Content-Type为空");
                throw new ServiceException(GlobalStatusCode.CODE_400.value(), "Content-Type为空");
            }
        }
        return true;
    }

    private String getParamString(Map<String, String[]> map) {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String,String[]> e:map.entrySet()){
            sb.append(e.getKey()).append("=");
            String[] value = e.getValue();
            if(value != null && value.length == 1){
                sb.append(value[0]).append("\t");
            }else{
                sb.append(Arrays.toString(value)).append("\t");
            }
        }
        return sb.toString();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    	
    }
}
