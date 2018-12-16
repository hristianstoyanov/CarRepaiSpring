package com.forum.webServices;

import com.forum.services.DataOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/")
@Controller
public class WebPagesController {

    @Autowired
    private DataOperations operations;

    @RequestMapping("/login")
    public String login() {
        return "login.html";
    }

    @RequestMapping("/home")
    public String home() {
        return "home.html";
    }

    @RequestMapping("/articles_page")
    public String articles(@RequestParam("email") String email) {
        if (operations.isAdmin(email)) {
            return "articles_admin_page.html";
        }
        return "articles.html";
    }

    @RequestMapping("/loaded_article")
    public String loadedArticle() {
        return "loaded_article.html";
    }

    @RequestMapping("/create_article")
    public String createArticle() {
        return "create_article.html";
    }
}
