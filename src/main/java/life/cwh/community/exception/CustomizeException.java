package life.cwh.community.exception;

/**
 * @author Cwh
 * CreateTime 2019/6/16 10:41
 */
public class CustomizeException extends RuntimeException {
    private String message;
    private Integer code;

    public CustomizeException(ICustomizeErroeCode erroeCode) {
        this.code=erroeCode.getCode();
        this.message = erroeCode.getMessage();

    }
    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
