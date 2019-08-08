package life.cwh.community.controller;

import life.cwh.community.cache.TagCache;
import life.cwh.community.dto.QuestionDTO;
import life.cwh.community.model.Question;
import life.cwh.community.model.User;
import life.cwh.community.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
//Ctrl+Alt+O  删除多余的依赖

/**
 * @author Cwh
 * CreateTime 2019/6/4 10:36
 */
@Controller
public class PublishController {
    //注入需要的类


    @Autowired
    public QuestionService questionService;

    @GetMapping("/publish")
    public String publish(Model model){
        model.addAttribute("tags", TagCache.get());
        return "publish";
}

    @PostMapping("/publish")
    public String doPublish(@RequestParam(value = "title",required = false) String title,
                            @RequestParam(value = "description",required = false) String description,
                            @RequestParam(value = "tag",required = false) String tag,
                            @RequestParam(value = "id",required = false) Long id,
                            HttpServletRequest request,
                            Model model){//接收页面参数
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        model.addAttribute("tags", TagCache.get());

        if(title ==null || title== ""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(description ==null || description== ""){
            model.addAttribute("error","问题补充不能为空");
            return "publish";
        }
        if(tag ==null || tag== ""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }
        String invalid = TagCache.filterInvalid(tag);
        if(StringUtils.isNoneBlank(invalid)){
            model.addAttribute("error","输入非法标签："+invalid);
            return "publish";
        }

        User user = (User)request.getSession().getAttribute("user");
            if (user == null) {
                model.addAttribute("error", "用户未登录");
                return "publish";
            }
            Question question = new Question();
            question.setTitle(title);
            question.setDescription(description);
            question.setTag(tag);
            question.setCreator(user.getId());
            question.setId(id);
            questionService.createOrUpdate(question);
            //Ctrl+Alt+V 自动创建所需的对象
            return "redirect:/";
    }
    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Long id,
                       Model model){

        QuestionDTO question =questionService.getById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",question.getId());
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }
}
