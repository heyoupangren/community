package life.cwh.community.dto;

import lombok.Data;

/**
 * @author Cwh
 * CreateTime 2019/6/16 20:29
 */
@Data
public class CommentCreateDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
