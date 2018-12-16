package cn.innovation.platform.gateway.common.advice;

import java.util.List;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.alibaba.dubbo.rpc.RpcException;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;

import cn.innovation.platform.common.base.BaseResult;
import cn.innovation.platform.common.enums.SystemStatusEnum;
import cn.innovation.platform.common.exception.ParamsSignValidErrorException;
import cn.innovation.platform.common.exception.ServiceException;

/**
 * @author 刘利民
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Log logger = LogFactory.get();

    /**
     * 系统方面出了问题的异常
     * @param e
     * @return
     */
    @ExceptionHandler({RuntimeException.class})
    public BaseResult runtimeExceptionHandler(RuntimeException e) {
        logger.error(e);
        return new BaseResult(SystemStatusEnum.CODE_500.value(), SystemStatusEnum.CODE_500.remark());
    }

    @ExceptionHandler({ParamsSignValidErrorException.class})
    public BaseResult paramsSignValidErrorExceptionHandler(ParamsSignValidErrorException e) {
        logger.error(e);
        return new BaseResult(SystemStatusEnum.CODE_600.value(), SystemStatusEnum.CODE_600.remark());
    }

    /**
     * dubbo服务方面的异常
     * @param e
     * @return
     */
    @ExceptionHandler({RpcException.class})
    public BaseResult rpcExceptionHandler(RpcException e) {
        logger.error(e);
        return new BaseResult(SystemStatusEnum.CODE_500.value(), SystemStatusEnum.CODE_500.remark());
    }

    /**
     * 业务方面的异常
     * @param e
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    public BaseResult serviceExceptionHandler(ServiceException e) {
        logger.error(e);
        return new BaseResult(e.getCode(), e.getMsg());
    }

    /**
     * 接口参数检验方面的异常
     * @param e
     * @param bindingResult
     * @return
     */
    @ExceptionHandler(BindException.class)
    public BaseResult bindExceptionHandler(BindException e, BindingResult bindingResult) {
        logger.error(e);
        List<ObjectError> objectErrorList = bindingResult.getAllErrors();
        StringBuilder sb = new StringBuilder();
        ObjectError objectError;
        for (int i = 0; i < objectErrorList.size(); i++) {
            objectError = objectErrorList.get(i);
            sb.append(objectError.getDefaultMessage());
            if (i < objectErrorList.size() - 1) {
                sb.append(",");
            }
        }
        return new BaseResult(SystemStatusEnum.CODE_400.value(), sb.toString());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public BaseResult noHandlerFoundExceptionHandler(NoHandlerFoundException e) {
        logger.error(e);
        return new BaseResult(SystemStatusEnum.CODE_404.value(), new StringBuffer(SystemStatusEnum.CODE_404.remark())
                .append(":").append(e.getRequestURL())
                .append("|").append(e.getHttpMethod()).toString());
    }
}
