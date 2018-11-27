package com.forum.beans;

import java.util.Date;
import java.util.List;

/**
 * Created by Hristiyan on 14.5.2018 ã..
 */
public class Article {

    private int id;
    private String title;
    private int brandId;
    private int modelId;
    private int categoryId;
    private String author;
    private List<String> content;
    Date date;

    public Article() {

    }

    public Article(int id, String title, Date date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }

    public Article(int id, String title, int brandId, int modelId, int categoryId, String author, List<String> content, Date date) {
        this.id = id;
        this.title = title;
        this.brandId = brandId;
        this.modelId = modelId;
        this.categoryId = categoryId;
        this.author = author;
        this.content = content;
        this.date = date;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
