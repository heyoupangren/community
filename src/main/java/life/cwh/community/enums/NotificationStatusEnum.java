package life.cwh.community.enums;

/**
 * @author Cwh
 * CreateTime 2019/6/20 12:25
 */
public enum  NotificationStatusEnum {
    UNREAD(0),
    READ(1);

    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
