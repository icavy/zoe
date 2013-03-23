package cn.cavy.zoe.action.vo;

public class OperationResult {

    public static final String MSG_SUCCESS = "success";

    public static final String STYLE_SUCCESS = "alert-success";

    public static final String STYLE_ERROR = "alert-error";

    public static final String STYLE_INFO = "alert-info";

    public static final String STYLE_BLOCK = "alert-block";

    public static final OperationResult SUCCESS = new OperationResult(MSG_SUCCESS, STYLE_SUCCESS);

    private String message;

    private String messageStyle;

    public OperationResult(String message, String messageStyle) {
        super();
        this.message = message;
        this.messageStyle = messageStyle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageStyle() {
        return messageStyle;
    }

    public void setMessageStyle(String messageStyle) {
        this.messageStyle = messageStyle;
    }
}