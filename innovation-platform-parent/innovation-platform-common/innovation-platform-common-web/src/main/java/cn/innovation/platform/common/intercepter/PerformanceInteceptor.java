package cn.innovation.platform.common.intercepter;

import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

/**
 * Controller层性能测试拦截器
 * @author 刘利民
 */
public class PerformanceInteceptor implements HandlerInterceptor {

    private static final Log log = LogFactory.get();

    private static final ThreadLocal<Long> startTimeThreadLocal =
            new NamedThreadLocal<>("ThreadLocal StartTime");

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        long beginTime = System.currentTimeMillis();//1、开始时间
        startTimeThreadLocal.set(beginTime);        //线程绑定变量（该数据只有当前请求的线程可见）
        log.info("开始计时: {}  URI: {}", new SimpleDateFormat("HH:mm:ss.SSS").format(beginTime), httpServletRequest.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            log.info("ViewName: " + modelAndView.getViewName());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）
        long endTime = System.currentTimeMillis();    //2、结束时间
        log.info("请求地址：{} 计时结束：{}  耗时：{}ms  URI: {}  最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m", httpServletRequest.getRequestURI(),
                new SimpleDateFormat("HH:mm:ss.SSS").format(endTime), (endTime - beginTime),
                httpServletRequest.getRequestURI(), Runtime.getRuntime().maxMemory() / 1024 / 1024, Runtime.getRuntime().totalMemory() / 1024 / 1024, Runtime.getRuntime().freeMemory() / 1024 / 1024,
                (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory()) / 1024 / 1024);
        //删除线程变量中的数据，防止内存泄漏
        startTimeThreadLocal.remove();
    }
}
