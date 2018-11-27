package com.forum.webServices;

import com.forum.beans.Article;
import com.forum.services.DataOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Hrisi on 25.11.2018 ã..
 */
@RequestMapping("/articles")
public class ArticlesEndpoint {

    @Autowired
    private DataOperations operations;

    @RequestMapping(value = "/articleById", method = RequestMethod.GET)
    public Article articleId(@RequestParam("id") int id) {
        return operations.getArticle(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ResponseEntity addArticle(Article article) {
        boolean response = false;
        response = operations.addArticle(article);
        if (response) {
            return ResponseEntity.ok("Upload success!");
        } else {
            return ResponseEntity.ok("Internal server error!");
        }
    }

    @RequestMapping("/all")
    public List<Article> getAllArticles(@RequestParam("brandId")int brandId, @RequestParam("modelId")int modelId, @RequestParam("categoryId")int categoryId) {
        return operations.getArticlesTitle(brandId, modelId, categoryId);
    }

}
