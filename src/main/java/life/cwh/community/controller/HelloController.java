package life.cwh.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name") String name,@RequestParam(name = "value") String value,Model model){
        model.addAttribute("name",name);
        model.addAttribute("value",value);
    return "hello";
    }
}
