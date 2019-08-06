package life.cwh.community.dto;

import lombok.Data;

/**
 * @author Cwh
 * CreateTime 2019/6/1 17:05
 */

//获取GitHub的Access_token的信息
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
