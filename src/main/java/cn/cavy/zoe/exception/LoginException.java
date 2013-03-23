package cn.cavy.zoe.exception;

public class LoginException extends BusinessException {

    private static final long serialVersionUID = 7619825483309913127L;

    public static final String UNKNOWN_ACCOUNT = "error.login.unknownAccount";

    public static final String INCORRECT_CREDENTIALS = "error.login.incorrectCredentials";

    public static final String LOCKED_ACCOUNT = "error.login.lockedAccount";

    public static final String EXCESSIVE_ATTEMPTS = "error.login.excessiveAttempts";

    public static final String AUTHENTICATION = "error.login.authentication";

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Object[] params) {
        super(message, params);
    }

}
