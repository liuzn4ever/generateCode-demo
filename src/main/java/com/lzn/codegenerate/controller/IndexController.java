package com.lzn.codegenerate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("page")
public class IndexController {
    @RequestMapping(value = "index")
    public String index(Model model){
        model.addAttribute("name","hello");
        return "index";
    }
}
