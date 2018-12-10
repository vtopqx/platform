package cn.innovation.platform.common.exception;

/**
 * @author 刘利民
 * 参数签名异常
 */
public class ParamsSignValidErrorException extends RuntimeException {
    public ParamsSignValidErrorException() {
    }

    public ParamsSignValidErrorException(String message) {
        super(message);
    }

    public ParamsSignValidErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamsSignValidErrorException(Throwable cause) {
        super(cause);
    }

    public ParamsSignValidErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /*public ParamsSignValidErrorException(int code, String msg) {
        super(code, msg);
    }

    public ParamsSignValidErrorException(String message, int code, String msg) {
        super(message, code, msg);
    }

    public ParamsSignValidErrorException(String message, Throwable cause, int code, String msg) {
        super(message, cause, code, msg);
    }

    public ParamsSignValidErrorException(Throwable cause, int code, String msg) {
        super(cause, code, msg);
    }

    public ParamsSignValidErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code, String msg) {
        super(message, cause, enableSuppression, writableStackTrace, code, msg);
    }*/
}
