package cn.cavy.zoe.action.exception;

public class SystemException extends RuntimeException {

    private static final long serialVersionUID = -7750415680269405778L;

    public static final String USER_IS_NOT_EXIST = "userIsNotExist";
    public static final String USER_PASSWORD_IS_ERROR = "userPasswordIsError";

    private Object[] params;

    public SystemException(String message, Object... params) {
        super(message);
        this.setParams(params);
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
