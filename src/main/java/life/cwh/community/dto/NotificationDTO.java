package life.cwh.community.dto;

import life.cwh.community.model.User;
import lombok.Data;

/**
 * @author Cwh
 * CreateTime 2019/6/20 14:17
 */
@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private String notifierName;
    private String outerTitle;
    private Long outerid;
    private String typeName;
    private Integer type;
}
