package com.forum.beans;

/**
 * Created by Hristiyan on 20.5.2018 ã..
 */
public class Model {

    private int id;
    private String modelName;
    private int brandId;

    public Model() {

    }

    public Model(int id, String modelName, int brandId) {
        this.id = id;
        this.modelName = modelName;
        this.brandId = brandId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", modelName='" + modelName + '\'' +
                ", brandId=" + brandId +
                '}';
    }
}
