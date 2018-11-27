package com.forum.carRepairDAO;

import com.forum.beans.Article;
import com.forum.beans.Brand;
import com.forum.beans.Category;
import com.forum.beans.Model;

import java.util.List;

/**
 * Created by Hristiyan on 14.5.2018 ã..
 */

public interface Repairable {

    List<Brand> getBrands();
    List<Model> getModels(int brandId);
    List<Category> getCategories();
    int getCategoryIdByName(String category);
    List<Article> getArticlesTitle(int brandId, int modelId, int categoryId);
    Article getArticleById(int id);
    boolean uploadArticle(Article article);
    boolean uploadImage(String filePath);
    int getImageId(String filePath);
}
