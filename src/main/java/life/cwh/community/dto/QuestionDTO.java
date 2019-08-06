package life.cwh.community.dto;

import life.cwh.community.model.User;
import lombok.Data;

/**
 * @author Cwh
 * CreateTime 2019/6/5 11:03
 */
@Data
public class QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Long creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;

}
