package life.cwh.community.exception;

public enum CustomizeErrorCode implements ICustomizeErroeCode {

    QUESTION_NOT_FOUND(2001,"你的问题不在了，要不要换一个试试？"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复"),
    NO_LOGIN(2003,"当前操作需要登录，请登录后重试"),
    SYS_ERROR(2004,"服务冒烟了，要不然你稍等一会儿再试试？"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"回复的评论不存在了，要不要换一个试试？"),
    COMMENT_IS_EMPTY(2007,"提交的内容不能为空"),
    READ_NOTIFICATION_FAIL(2008,"兄弟，你这是读别人的信息吗？"),
    NOTIFICATION_NOT_FOUND(2009,"消息莫非是不翼而飞了？"),
    FILE_UPLOAD_FAIL(2010,"图片上传失败！！！"),
    ;
    private Integer code;
    private String message;



    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }


    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }


}
