package cn.cavy.zoe.exception;

public class UploadException extends BusinessException {

    private static final long serialVersionUID = 3394433092455768367L;

    public static final String DIR = "DIR";// 目录创建失败

    public static final String NOFILE = "NOFILE";// 未包含文件上传域

    public static final String SIZE = "SIZE";// 文件大小超出限制

    public static final String ENTYPE = "ENTYPE";// 请求类型ENTYPE错误

    public static final String REQUEST = "REQUEST";// 上传请求异常

    public static final String UNKNOWN = "UNKNOWN";// 未知错误

    public static final String EXISTS = "EXISTS";// 同名文件已存在

    public UploadException(String message) {
        super(message);
    }
}
