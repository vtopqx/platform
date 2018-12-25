package cn.innovation.platform.gateway.common.intercepter;

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

import cn.innovation.platform.common.constant.GlobalConstant;
import cn.innovation.platform.common.enums.SystemStatusEnum;
import cn.innovation.platform.common.exception.ParamsSignValidErrorException;
import cn.innovation.platform.common.exception.ServiceException;
import cn.innovation.platform.common.utils.SignUtils;
import cn.innovation.platform.upms.common.model.TbAppInfo;
import cn.innovation.platform.upms.service.IApiInfoService;
import cn.innovation.platform.upms.service.IAppInfoService;

/**
 * @ClassName: GateWaySignIntercepter
 * @Description: 验证接口签名拦截器
 * @author mqx
 * @date 2018年12月17日 下午9:25:48
 */
public class GateWaySignIntercepter implements HandlerInterceptor {

	private static final Log logger = LogFactory.get();

	@Resource
	private IAppInfoService appInfoService;

	@Resource
	private IApiInfoService apiInfoService;

	private Boolean isOpenSign;

	public void setOpenSign(Boolean openSign) {
		isOpenSign = openSign;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			// 设置允许跨域访问
			response.setHeader("Access-Control-Allow-Origin", "*");
			String contextType = request.getHeader(HttpHeaders.CONTENT_TYPE);
			Enumeration<String> headerNames = request.getHeaderNames();

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
			for (Map.Entry<String, String[]> entry : stringMap.entrySet()) {
				String key = entry.getKey().toString();
				String[] value = (String[]) (entry.getValue());
				params.put(key, value[0]);
				paramsb.append(key).append("\t\t\t==>").append(value[0]).append("\n");
			}
			logger.debug("第三方平台发送过来的请求参数\n{}", paramsb);
			TbAppInfo appInfo = null;
			if (StrUtil.isNotBlank(contextType)) {
				if (contextType.equals(MediaType.APPLICATION_FORM_URLENCODED_VALUE)) {
					HandlerMethod h = (HandlerMethod) handler;
					sb.append("Controller: ").append(h.getBean().getClass().getName()).append("\n");
					sb.append("Method    : ").append(h.getMethod().getName()).append("\n");
					sb.append("Params    : ").append(getParamString(request.getParameterMap())).append("\n");
					sb.append("URI       : ").append(request.getRequestURI()).append("\n");
					logger.debug("第三方平台的请求情况\n{}", sb.toString());
					// 获取redis缓存中注册信息
					// 1)开始验证参数
					String appKey = params.getOrDefault("appKey", "").toString();
					String channelCode = params.getOrDefault("channel", "").toString();
					if (StrUtil.isBlank(appKey)) {
						throw new ServiceException(SystemStatusEnum.CODE_400.value(), "appKey不能为空");
					} else {
						// 验证consumerKey是否正确
						long startTime = System.currentTimeMillis();
						appInfo = appInfoService.getAppInfo(appKey);
						long endTime = System.currentTimeMillis();
						logger.debug("调用upms服务获取应用创新平台权限，耗时{}", (endTime - startTime));

						if (StringUtils.isEmpty(appInfo.getAppSecret())) {
							throw new ServiceException(SystemStatusEnum.CODE_401.value(), "appKey验证不通过");
						}
						// 验证channel是否正确
						if (StringUtils.isEmpty(channelCode) || !channelCode.equals(appInfo.getChannelCode())) {
							throw new ServiceException(SystemStatusEnum.CODE_401.value(), "channel验证不通过");
						}
					}
					String signature = params.getOrDefault("sign", "").toString();
					if (StrUtil.isBlank(signature)) {
						throw new ServiceException(SystemStatusEnum.CODE_400.value(), "sign不能为空");
					}
					String requestId = params.getOrDefault("requestTime", "").toString();
					if (StrUtil.isBlank(requestId)) {
						throw new ServiceException(SystemStatusEnum.CODE_400.value(), "requestTime不能为空");
					}
					// 2)通过密钥进行签名验证
					String sig = SignUtils.genSig(params, appInfo.getAppSecret().trim());
					String sig2 = params.get("sign").toString();
					// 检查是否开启签名验证，true代表开启，false代表关闭
					if (isOpenSign && !sig.equals(sig2)) {
						logger.error("签名错误");
						throw new ParamsSignValidErrorException("签名错误！");
					}
				} else {
					logger.error("Content-Type错误");
					throw new ServiceException(SystemStatusEnum.CODE_400.value(), "Content-Type错误");
				}
				// 3)校验流水号是否在有效期
				String requestTime = params.getOrDefault("requestTime", "").toString();
				if (StrUtil.isBlank(requestTime)) {
					throw new ServiceException(SystemStatusEnum.CODE_400.value(), "requestTime不能为空");
				} else {
					// 验证requestTime是否在有效期
					long timeStr = Long.valueOf(requestTime);
					long currentTime = System.currentTimeMillis();
					// 有效期
					if ((currentTime - timeStr) > GlobalConstant.GATEWAY_REQUEST_TIMEOUT) {
						throw new ServiceException(SystemStatusEnum.CODE_401.value(), "requestTime验证不通过");
					}
				}
				// 4)校验API访问权限
				if (StringUtils.isEmpty(appInfo.getApiList())) {
					throw new ServiceException(SystemStatusEnum.CODE_403.value(), SystemStatusEnum.CODE_403.remark());
				}
			} else {
				logger.error("Content-Type为空");
				throw new ServiceException(SystemStatusEnum.CODE_400.value(), "Content-Type为空");
			}
		}
		return true;
	}

	private String getParamString(Map<String, String[]> map) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String[]> e : map.entrySet()) {
			sb.append(e.getKey()).append("=");
			String[] value = e.getValue();
			if (value != null && value.length == 1) {
				sb.append(value[0]).append("\t");
			} else {
				sb.append(Arrays.toString(value)).append("\t");
			}
		}
		return sb.toString();
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}
}
