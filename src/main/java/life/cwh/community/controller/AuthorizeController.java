package life.cwh.community.controller;

import life.cwh.community.dto.AccessTokenDTO;
import life.cwh.community.dto.GithubUser;
import life.cwh.community.model.User;
import life.cwh.community.provider.GithubProvider;
import life.cwh.community.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
@Slf4j
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;


    @Autowired
    private UserService userService;
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        System.out.println(githubUser.getName());
        if(githubUser!=null && githubUser.getId()!=null){
            User user = new User();
            /*String token = UUID.randomUUID().toString();*/
            user.setToken(accessToken);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(githubUser.getAvatarUrl());
            userService.createOrUpdate(user);
            /*response.addCookie(new Cookie("token",token));
            */
            Cookie cookie = new Cookie("token",accessToken);
            cookie.setMaxAge(60*60*24*30*6);
            response.addCookie(cookie);
            return "redirect:/";
        }else{
            //手动配置什么地方需要打印日志
            log.error("callback get github error,{}",githubUser);
            //登录失败，重新登录
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token",null);//ctrl+alt+v实例化对象
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}