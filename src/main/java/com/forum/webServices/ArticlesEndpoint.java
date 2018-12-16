package com.forum.webServices;

import com.forum.beans.Article;
import com.forum.services.DataOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/articles")
@RestController
public class ArticlesEndpoint {

    @Autowired
    private DataOperations operations;

    @RequestMapping(value = "/articleById", method = RequestMethod.GET)
    public Article articleId(@RequestParam("id") int id) {
        return operations.getArticle(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addArticle(@RequestBody Article article) {
        boolean response = false;
        response = operations.addArticle(article);
        if (response) {
            return "Upload success!";
        } else {
            return"Internal server error!";
        }
    }

    @RequestMapping("/all")
    public List<Article> getAllArticles(@RequestParam("brandId")int brandId, @RequestParam("modelId")int modelId, @RequestParam("categoryId")int categoryId) {
        return operations.getArticlesTitle(brandId, modelId, categoryId);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public boolean delete(@RequestParam("articleId") int articleId) {
        return operations.deleteArticle(articleId);
    }

}
