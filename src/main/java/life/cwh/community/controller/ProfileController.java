package life.cwh.community.controller;

import life.cwh.community.dto.PagenationDTO;
import life.cwh.community.model.User;
import life.cwh.community.service.NotificationService;
import life.cwh.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Cwh
 * CreateTime 2019/6/6 19:30
 */
@Controller
public class ProfileController {


    @Autowired
    private QuestionService questionService;
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "action") String action,
                          Model model,
                          @RequestParam(name="page",defaultValue = "1") Integer page,
                          @RequestParam(name="size",defaultValue = "5") Integer size){

        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return "redirect:/";
        }
        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
            PagenationDTO pagenationDTO = questionService.list(user.getId(), page, size);
            model.addAttribute("pagenation",pagenationDTO);
        }else if("replies".equals(action)){
            PagenationDTO pagenationDTO =notificationService.list(user.getId(),page,size);
            model.addAttribute("section","replies");
            model.addAttribute("pagenation",pagenationDTO);
            model.addAttribute("sectionName","最新回复");
        }

        return "profile";
    }
}
