package life.cwh.community.controller;

import life.cwh.community.dto.CommentDTO;
import life.cwh.community.dto.QuestionDTO;
import life.cwh.community.enums.CommentTypeEnum;
import life.cwh.community.service.CommentService;
import life.cwh.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author Cwh
 * CreateTime 2019/6/10 11:27
 */
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;


    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id, Model model) {
        QuestionDTO questionDTO = questionService.getById(id);
        //查找标签的相关问题
        List<QuestionDTO> relatedQuestions = questionService.selectRelated(questionDTO);
        //查找回复
        List<CommentDTO> comments = commentService.listByTargetId(id, CommentTypeEnum.QUESTION);

        //累加浏览次数
        questionService.incView(id);
        model.addAttribute("question", questionDTO);
        model.addAttribute("comments", comments);
        model.addAttribute("relatedQuestions", relatedQuestions);
        return "question";
    }
}
