package com.forum.services;

import com.forum.beans.Article;
import com.forum.beans.Brand;
import com.forum.beans.Category;
import com.forum.beans.Model;
import com.forum.carRepairDAOImpl.CarRepairOperationsDAO;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


@Service
public class DataOperations {

    @Autowired
    CarRepairOperationsDAO carRepairOperationsDAO;

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

    public String saveImage(byte[] imageBytes, String fileName) {

        String savedImagePath = null;

        StringBuilder filePath = new StringBuilder("D:\\DiplomnaRabota\\uploadedImages\\" + fileName);
        FileOutputStream fileOutputStream = null;
        String path = null;
        try {
            File file = new File(filePath.toString());

            if (file.exists()) {
                int fileExistsCounter = 1;
                int index = filePath.lastIndexOf(".");
                if (index < 0) {
                    filePath.append(".png");
                }
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
                fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(imageBytes);
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

    public int uploadImage(byte[] imageBytes, String fileName) {
        int id = -1;

        String savedImagePath = saveImage(imageBytes, fileName);
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

    public String downloadImage(int id) {
        byte[] imageBytes = null;
//        ByteArrayInputStream byteArrayInputStream = null;
        carRepairOperationsDAO.init();
        String savedImageUrl = carRepairOperationsDAO.getImageById(id);

        FileInputStream fileReader = null;
        if (savedImageUrl != null) {
            try {
                File file = new File(savedImageUrl);
                fileReader = new FileInputStream(file);

                 imageBytes = new byte[(int) file.length()];
                fileReader.read(imageBytes);
//                if (fileReader.read(imageBytes) > 0) {
//                    byteArrayInputStream = new ByteArrayInputStream(imageBytes);
//                }

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

        return new String(Base64.encodeBase64(imageBytes));
    }

    public static java.sql.Date convertDateToSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    public boolean deleteArticle(int articleId) {
        carRepairOperationsDAO.init();

        boolean isDeletedSuccessfully = carRepairOperationsDAO.deleteArticle(articleId);
        carRepairOperationsDAO.close();

        return isDeletedSuccessfully;
    }

    public boolean isAdmin(String email) {
        carRepairOperationsDAO.init();

        boolean isAdmin = carRepairOperationsDAO.isAdmin(email);
        carRepairOperationsDAO.close();

        return isAdmin;
    }
}
