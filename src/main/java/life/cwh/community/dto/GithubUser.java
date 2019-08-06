package life.cwh.community.dto;

import lombok.Data;

/**
 * @author Cwh
 * CreateTime 2019/6/1 18:00
 */

//获取GitHub的用户信息
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;

}