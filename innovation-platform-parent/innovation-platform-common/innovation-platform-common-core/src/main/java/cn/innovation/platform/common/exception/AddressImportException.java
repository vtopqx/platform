package cn.innovation.platform.common.exception;

/**
 * @ClassName: AddressImportException 
 * @Description: 自定义异常
 * @author mqx 
 * @date 2018年1月19日 下午2:31:15
 */
@SuppressWarnings("serial")
public class AddressImportException extends ServiceException {

    public AddressImportException() {
    }

    public AddressImportException(int code, String msg) {
        super(code, msg);
    }

    public AddressImportException(String message, int code, String msg) {
        super(message, code, msg);
    }

    public AddressImportException(String message, Throwable cause, int code, String msg) {
        super(message, cause, code, msg);
    }

    public AddressImportException(Throwable cause, int code, String msg) {
        super(cause, code, msg);
    }

    public AddressImportException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code, String msg) {
        super(message, cause, enableSuppression, writableStackTrace, code, msg);
    }
}
