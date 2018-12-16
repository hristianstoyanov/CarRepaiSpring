package com.forum.webServices;

import com.forum.beans.Category;
import com.forum.services.DataOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/categories")
@RestController
public class CategoriesEndpoint {

    @Autowired
    private DataOperations operations;

    @RequestMapping(value = "/categoryId", method = RequestMethod.GET)
    public int categoryId(@RequestParam("category") String category) {
        return operations.categoryId(category);
    }

    @RequestMapping("/all")
    public List<Category> getAllCategories() {
        return operations.getCategories();
    }
}
