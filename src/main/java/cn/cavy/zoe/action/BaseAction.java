package cn.cavy.zoe.action;

public class BaseAction {

    public static final String OPERATION_RESULT = "operationResult";

    public static final String MESSAGE = "message";

    public static final String MESSAGE_LEVEL = "error";

    public String forward(String url) {
        return "forward:" + url;
    }

    public String redirect(String url) {
        return "redirect:" + url;
    }
    
}
