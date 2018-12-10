package cn.innovation.platform.common.exception;

/**
 * @ClassName: MessageRecordException 
 * @Description: 自定义异常处理
 * @author mqx 
 * @date 2017年11月24日 下午5:59:16
 */
@SuppressWarnings("serial")
public class ApplicationRegisterException extends ServiceException {

    public ApplicationRegisterException() {
    }

    public ApplicationRegisterException(int code, String msg) {
        super(code, msg);
    }

    public ApplicationRegisterException(String message, int code, String msg) {
        super(message, code, msg);
    }

    public ApplicationRegisterException(String message, Throwable cause, int code, String msg) {
        super(message, cause, code, msg);
    }

    public ApplicationRegisterException(Throwable cause, int code, String msg) {
        super(cause, code, msg);
    }

    public ApplicationRegisterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code, String msg) {
        super(message, cause, enableSuppression, writableStackTrace, code, msg);
    }

}
