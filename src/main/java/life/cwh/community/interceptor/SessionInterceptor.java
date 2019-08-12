package life.cwh.community.interceptor;

import life.cwh.community.mapper.UserMapper;
import life.cwh.community.model.User;
import life.cwh.community.model.UserExample;
import life.cwh.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Cwh
 * CreateTime 2019/6/10 10:13
 */
@Service
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NotificationService notificationService;

    @Value("${github.redirect.uri}")
    private String redirectUri;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       //设置context级别的属性
        request.getServletContext().setAttribute("redirectUri",redirectUri);
        Cookie[] cookies = request.getCookies();
        if(cookies!=null&&cookies.length!=0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    UserExample userExample = new UserExample();
                    userExample.createCriteria()
                            .andTokenEqualTo(token);//SQL语句的拼接
                    List<User> users = userMapper.selectByExample(userExample);//语句查询
                    if (users.size() != 0) {
                        request.getSession().setAttribute("user", users.get(0));
                        Long unreadCount =notificationService.unreadCount(users.get(0).getId());
                        request.getSession().setAttribute("unreadCount",unreadCount);
                    }
                    break;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
