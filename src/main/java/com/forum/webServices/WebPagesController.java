package com.forum.webServices;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Hrisi on 25.11.2018 ã..
 */
@RequestMapping("/")
@Controller
public class WebPagesController {

    @RequestMapping("/login")
    public String login() {
        return "homePage.html";
    }
}
