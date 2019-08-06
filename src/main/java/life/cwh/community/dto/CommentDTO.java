package life.cwh.community.dto;

import life.cwh.community.model.User;
import lombok.Data;

/**
 * @author Cwh
 * CreateTime 2019/6/18 14:44
 */
@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private Integer commentCount;
    private String content;
    private User user;
}
