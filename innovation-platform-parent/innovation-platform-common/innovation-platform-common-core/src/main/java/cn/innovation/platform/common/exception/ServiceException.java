package cn.innovation.platform.common.exception;

/**
 * @author 刘利民
 */
public class ServiceException extends Exception {

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ServiceException() {
    }

    public ServiceException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ServiceException(String message, int code, String msg) {
        super(message);
        this.code = code;
        this.msg = msg;
    }

    public ServiceException(String message, Throwable cause, int code, String msg) {
        super(message, cause);
        this.code = code;
        this.msg = msg;
    }

    public ServiceException(Throwable cause, int code, String msg) {
        super(cause);
        this.code = code;
        this.msg = msg;
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code, String msg) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.msg = msg;
    }
}
