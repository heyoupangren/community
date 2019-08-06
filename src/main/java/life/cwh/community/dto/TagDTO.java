package life.cwh.community.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Cwh
 * CreateTime 2019/6/19 20:37
 */
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}
