package cn.innovation.platform.common.exception;

/**
 * @author 刘利民
 */
public class AddressListException extends ServiceException {

    public AddressListException() {
    }

    public AddressListException(int code, String msg) {
        super(code, msg);
    }

    public AddressListException(String message, int code, String msg) {
        super(message, code, msg);
    }

    public AddressListException(String message, Throwable cause, int code, String msg) {
        super(message, cause, code, msg);
    }

    public AddressListException(Throwable cause, int code, String msg) {
        super(cause, code, msg);
    }

    public AddressListException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code, String msg) {
        super(message, cause, enableSuppression, writableStackTrace, code, msg);
    }
}
