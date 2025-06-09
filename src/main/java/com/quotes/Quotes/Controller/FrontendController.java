package com.quotes.Quotes.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class FrontendController {

    @RequestMapping(value = { "/", "/{path:^(?!api|static|.*\\..*).*$}", "/{path:^(?!api|static|.*\\..*).*$}/**" })        
    public String index() {
        return "forward:/index.html";
    }
    
}
