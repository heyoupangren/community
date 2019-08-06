package life.cwh.community.dto;

import lombok.Data;

/**
 * @author Cwh
 * CreateTime 2019/7/9 14:50
 */
@Data
public class QuestionQueryDTO {
    private String search;
    private Integer page;
    private Integer size;
}
