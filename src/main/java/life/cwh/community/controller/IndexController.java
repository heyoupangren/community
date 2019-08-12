package life.cwh.community.controller;

import life.cwh.community.cache.HotTagCache;
import life.cwh.community.dto.PagenationDTO;
import life.cwh.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private HotTagCache hotTagCache;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name="page",defaultValue = "1") Integer page,
                        @RequestParam(name="size",defaultValue = "5") Integer size,
                        @RequestParam(name = "search",required = false) String search,
                        @RequestParam(name = "tag",required = false) String tag){
        PagenationDTO pagenation =questionService.list(search,tag,page,size);
        List<String> tags = hotTagCache.getHots();
        Map<String, Integer> numbers = hotTagCache.getNumbers();
        model.addAttribute("pagenation",pagenation);
        model.addAttribute("search",search);
        model.addAttribute("tags",tags);
        model.addAttribute("tag",tag);
        model.addAttribute("numbers",numbers);
        return "index";
    }
}