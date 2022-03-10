package ekfkawl.dapp.domain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @GetMapping("/")
    public ModelAndView index() {
        System.out.println("dsadasd");
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
}
