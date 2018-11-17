package carRepairDAOImpl;

import beans.Article;
import beans.Brand;
import beans.Category;
import beans.Model;
import carRepairDAO.Repairable;
import dbConnection.DatabaseConnection;
import services.DataOperations;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * Created by Hristiyan on 14.5.2018 �..
 */

//@Stateless
public class CarRepairOperationsDAO implements Repairable{

    private static final String BRANDS_TABLE = "brands";
    private static final String MODELS_TABLE = "models";
    private static final String CATEGORIES_TABLE = "categories";
    private static final String ARTICLES_TABLE = "articles";
    private static final String IMAGES_TABLE = "uploaded_images";

    private DatabaseConnection databaseConnection;
    Connection connection;

    public CarRepairOperationsDAO() {

    }

    public void init() {
        databaseConnection = new DatabaseConnection();
        databaseConnection.init();
        connection = databaseConnection.getConnection();
    }

    public void close() {
        databaseConnection.closeConnection();
    }


    public List<Brand> getBrands() {

        String query = "SELECT * FROM " + BRANDS_TABLE;
        List<Brand> brands = new ArrayList<Brand>();

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String brand = resultSet.getString("brand_name");
                brands.add(new Brand(id, brand));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.close(resultSet);
            DatabaseConnection.close(statement);
        }

        return brands;
    }


    public List<Model> getModels(int brandId) {

        String query = "SELECT * FROM " + MODELS_TABLE + " WHERE brand_id=?";
        List<Model> models = new ArrayList<Model>();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, brandId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String model = resultSet.getString("model_name");
                models.add(new Model(id, model, brandId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.close(resultSet);
            DatabaseConnection.close(preparedStatement);
        }

        return models;
    }


    public List<Category> getCategories() {

        String query = "SELECT * FROM " + CATEGORIES_TABLE;
        List<Category> categories = new ArrayList<Category>();

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String category = resultSet.getString("category_name");
                categories.add(new Category(id, category));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.close(resultSet);
            DatabaseConnection.close(statement);
        }

        return categories;
    }

    public int getCategoryIdByName(String name) {

        String query = "SELECT id FROM " + CATEGORIES_TABLE + " WHERE category_name=?";
        int id = -1;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.close(resultSet);
            DatabaseConnection.close(preparedStatement);
        }

        return id;
    }

    public List<Article> getArticlesTitle(int brandId, int modelId, int categoryId) {

        String query = "SELECT id, title, uploaded_date FROM " + ARTICLES_TABLE + " WHERE brand_id=? AND model_id=? AND category_id=?";
        List<Article> articles = new ArrayList<Article>();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, brandId);
            preparedStatement.setInt(2, modelId);
            preparedStatement.setInt(3, categoryId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String articleTitle = resultSet.getString("title");
                java.sql.Date sqlDate = resultSet.getDate("uploaded_date");
                java.util.Date uploadedDate = new java.util.Date(sqlDate.getTime());
//                int id = resultSet.getInt("id");
//                String title = resultSet.getString("title");
//                int brandId = resultSet.getInt("brand_id");
//                int modelId = resultSet.getInt("model_id");
//                int categoryId = resultSet.getInt("category_id");
//                String content = resultSet.getString("content");

//                Article article = new Article(id, title, brandId, modelId, categoryId, content);
                articles.add(new Article(id, articleTitle, uploadedDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.close(resultSet);
            DatabaseConnection.close(preparedStatement);
        }

        return articles;
    }

    public Article getArticleById(int id) {

        String query = "SELECT * FROM " + ARTICLES_TABLE + " WHERE id=?";

        Article article = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String title = resultSet.getString("title");
                int brandId = resultSet.getInt("brand_id");
                int modelId =  resultSet.getInt("model_id");
                int categoryId = resultSet.getInt("category_id");
                String author = resultSet.getString("author");
                String content = resultSet.getString("article_content");
                java.sql.Date sqlDate = resultSet.getDate("uploaded_date");
                java.util.Date uploadedDate = new java.util.Date(sqlDate.getDate());

                List<String> articleContent = DataOperations.parseArticleContent(content);
                article = new Article(id, title, brandId, modelId, categoryId, author, articleContent, uploadedDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.close(resultSet);
            DatabaseConnection.close(preparedStatement);
        }

        return article;
    }


    public String getImageById(int id) {

        String query = "SELECT image_path FROM " + IMAGES_TABLE + " WHERE id=?";

        String url = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                url = resultSet.getString("image_path");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public boolean uploadArticle(Article article) {
        boolean result = false;

        String query = "INSERT INTO " + ARTICLES_TABLE + "(title, brand_id, model_id, category_id, author, article_content, uploaded_date)" +
                " VALUES(?,?,?,?,?,?,?)";

        String articleContent = DataOperations.convertArticleContentToString(article);
        article.setDate(new Date());

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, article.getTitle());
            preparedStatement.setInt(2, article.getBrandId());
            preparedStatement.setInt(3, article.getModelId());
            preparedStatement.setInt(4, article.getCategoryId());
            preparedStatement.setString(5, article.getAuthor());
            preparedStatement.setString(6, articleContent);
            preparedStatement.setDate(7, DataOperations.convertDateToSqlDate(article.getDate()));

            if (preparedStatement.executeUpdate() == 1) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }



    public boolean uploadImage(String filePath) {
        boolean result = false;

        String query = "INSERT INTO " + IMAGES_TABLE + "(image_path) VALUES(?)";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, filePath);

            if (preparedStatement.executeUpdate() == 1) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public int getImageId(String filePath) {
        int id = -1;

        String query = "SELECT id FROM " + IMAGES_TABLE + " WHERE image_path=?";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, filePath);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                id = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

}
