package cn.innovation.platform.gateway.common.advice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;

import com.xiaoleilu.hutool.bean.BeanUtil;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;

/**
 * @author 刘利民
 */
public class ValidationAspect {

    private static final Log logger = LogFactory.get();

    /**
     * 前置通知
     * @param joinPoint
     */
    public void beforMethod(JoinPoint joinPoint) throws Exception {

        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        Map<String, Object> paramsMap = new HashMap<>();

        logger.debug("ValidationAspect this method {} begin. param<{}>", methodName, args);
        for (Object o : args) {
            //如果参数中遇到与业务无关的HttpServletRequest和HttpServletResponse，将被忽略
            if (o instanceof HttpServletRequest || o instanceof HttpServletResponse) {
                continue;
            }
            //提取参数
            Map<String, Object> map = BeanUtil.beanToMap(o);
            if (!map.isEmpty()) {
                paramsMap.putAll(map);
            } else {
//                paramsMap.put()
            }
            /*String consumerKey = map.getOrDefault("consumerKey", "rcsoaplus-test").toString();
            String consumerSecret = "12345678";
            String sig = RcsoaplusSignHelper.genSig(map, consumerSecret);
            String sig2 = map.get("signature").toString();
            if (!sig.equals(sig2)) {
                throw new ParamsSignValidErrorException("签名错误！");
            }*/
        }

    }

    /**
     * 后置通知（无论方法是否发生异常都会执行,所以访问不到方法的返回值）
     * @param joinPoint
     */
    public void afterMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("ValidationAspect this method "+methodName+" end.");
    }
    /**
     * 返回通知（在方法正常结束执行的代码）
     * 返回通知可以访问到方法的返回值！
     * @param joinPoint
     */
    public void afterReturnMethod(JoinPoint joinPoint,Object result){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("ValidationAspect this method "+methodName+" end.result<"+result+">");
    }
    /**
     * 异常通知（方法发生异常执行的代码）
     * 可以访问到异常对象；且可以指定在出现特定异常时执行的代码
     * @param joinPoint
     * @param ex
     */
    public void afterThrowingMethod(JoinPoint joinPoint,Exception ex){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("ValidationAspect this method "+methodName+" end.ex message<"+ex+">");
    }
}
