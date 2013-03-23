package cn.cavy.zoe.exception;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -7750415680269405778L;

    private Object[] params;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Object... params) {
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
