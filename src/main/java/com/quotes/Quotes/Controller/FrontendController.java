package com.quotes.Quotes.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class FrontendController {

    @RequestMapping(value = {"/", "/about", "/notfound", "/products"})
    public String index() {
        return "forward:/index.html";
    }
    
}
