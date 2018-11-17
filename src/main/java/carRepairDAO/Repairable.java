package carRepairDAO;

import beans.Article;
import beans.Brand;
import beans.Category;
import beans.Model;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.InputStream;
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
