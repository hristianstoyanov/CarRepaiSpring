package services;

import beans.Article;
import beans.Brand;
import beans.Category;
import beans.Model;
import carRepairDAOImpl.CarRepairOperationsDAO;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Created by Hristiyan on 18.5.2018 �..
 */

public class DataOperations {

//    @EJB
    CarRepairOperationsDAO carRepairOperationsDAO = new CarRepairOperationsDAO();

    public List<Brand> getBrands() {
        carRepairOperationsDAO.init();

        List<Brand> brands = carRepairOperationsDAO.getBrands();
        carRepairOperationsDAO.close();

        return brands;
    }

    public List<Model> getModels(int brandId) {
        carRepairOperationsDAO.init();
        List<Model> models = null;

        if (brandId > 0) {
            models = carRepairOperationsDAO.getModels(brandId);
        } else {
            models = new ArrayList<Model>();
        }
        carRepairOperationsDAO.close();

        return models;
    }

    public List<Category> getCategories() {
        carRepairOperationsDAO.init();
        List<Category> categories = carRepairOperationsDAO.getCategories();
        carRepairOperationsDAO.close();

        return categories;
    }

    public List<Article> getArticlesTitle(int brandId, int modelId, int categoryId) {
        List<Article> articles = new ArrayList<Article>();
        if (brandId > 0 && modelId > 0 && categoryId > 0) {
            carRepairOperationsDAO.init();
            articles = carRepairOperationsDAO.getArticlesTitle(brandId, modelId, categoryId);
            carRepairOperationsDAO.close();
        }


        return articles;
    }

    public Article getArticle(int id) {
        Article article = new Article();
        if (id > 0) {
            carRepairOperationsDAO.init();
            article = carRepairOperationsDAO.getArticleById(id);
            carRepairOperationsDAO.close();
        }

        return article;
    }

//    public int uploadImage(InputStream fileInputStream, FormDataContentDisposition fileDetails) {
//
//        carRepairOperationsDAO.init();
//        int id = carRepairOperationsDAO.uploadImage(fileInputStream, fileDetails);
//        carRepairOperationsDAO.close();
//
//        return id;
//    }

    public int categoryId(String categoryName) {
        carRepairOperationsDAO.init();
        int id = carRepairOperationsDAO.getCategoryIdByName(categoryName);
        carRepairOperationsDAO.close();

        return id;
    }

    public boolean addArticle(Article article) {
        carRepairOperationsDAO.init();
        boolean result = carRepairOperationsDAO.uploadArticle(article);
        carRepairOperationsDAO.close();

        return result;
    }

    public String saveImage(String json, String fileName) {

        String savedImagePath = null;

        StringBuilder filePath = new StringBuilder("D:\\DiplomnaRabota\\uploadedImages\\");
        FileOutputStream fileOutputStream = null;
        json = json.replaceAll(" ", "+");
        String path = null;
        try {
            filePath.append(fileName).append(".png");
            File file = new File(filePath.toString());

            if (file.exists()) {
                int fileExistsCounter = 1;
                int index = filePath.indexOf(".png");
                filePath.insert(index, "(" + fileExistsCounter + ")");
                file = new File(filePath.toString());
//                path = filePath.toString();
                while (file.exists()) {
                    filePath.replace(filePath.indexOf("("),filePath.indexOf(")") + 1, "(" + String.valueOf(++fileExistsCounter) + ")");
                    file = new File(filePath.toString());
                }
            }

//            file = new File(filePath.toString());
            if (file.createNewFile()) {
                byte[] imageDecoded = Base64.getDecoder().decode(json);
                fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(imageDecoded);
                savedImagePath = filePath.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return savedImagePath;
    }

    public int storeImageUrlInDB(String savedImagePath) {
        int id = -1;
        carRepairOperationsDAO.init();
        if (carRepairOperationsDAO.uploadImage(savedImagePath)) {
            id = carRepairOperationsDAO.getImageId(savedImagePath);
        }
        carRepairOperationsDAO.close();
        return id;
    }

    public int uploadImage(String encodedImage, String fileName) {
        int id = -1;

        String savedImagePath = saveImage(encodedImage, fileName);
        if (savedImagePath != null) {
            id = storeImageUrlInDB(savedImagePath);
        }

        return id;
    }

    public static String convertArticleContentToString(Article article) {
        StringBuilder articleContent = new StringBuilder();

        for (String line : article.getContent()) {
            articleContent.append(line);
        }

        return articleContent.toString();
    }

    public static List<String> parseArticleContent(String content) {
        List<String> list = new ArrayList<String>();

        for (int i = 0; i < content.length();) {
            if (content.charAt(i) != '<') {
                int index = content.indexOf('<', i);
                if (index < 0) {
                    index = content.length();
                }
                String notImg = content.substring(i, index);
                i = index;
                list.add(notImg);
            } else {
                int index = content.indexOf("</img>", i) + 6;
                String img = content.substring(i, index);
                i = index;
                list.add(img);
            }
        }

        return list;
    }

    public ByteArrayInputStream downloadImage(int id) {

        ByteArrayInputStream byteArrayInputStream = null;
        carRepairOperationsDAO.init();
        String savedImageUrl = carRepairOperationsDAO.getImageById(id);

        FileInputStream fileReader = null;
        if (savedImageUrl != null) {
            try {
                File file = new File(savedImageUrl);
                fileReader = new FileInputStream(file);

                byte[] imageBytes = new byte[(int) file.length()];
                if (fileReader.read(imageBytes) > 0) {
                    byteArrayInputStream = new ByteArrayInputStream(imageBytes);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch(IOException e) {
                e.printStackTrace();
            } finally {
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        carRepairOperationsDAO.close();

        return byteArrayInputStream;
    }

    public static java.sql.Date convertDateToSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

}
