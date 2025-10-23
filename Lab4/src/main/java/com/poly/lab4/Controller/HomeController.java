package com.poly.lab4.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("/home/index")
    public String index(Model model) {
        return "bai4/home";
    }

    @RequestMapping("/home/about")
    public String about(Model model) {
        return "bai4/about";
    }
}
