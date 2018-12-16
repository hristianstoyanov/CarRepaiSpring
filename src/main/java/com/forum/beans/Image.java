package com.forum.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Image {

    @JsonProperty
    private String encodedData;
    @JsonProperty
    private String name;

    public Image() {

    }

    public Image(String name, String encodedData) {
        this.name = name;
        this.encodedData = encodedData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEncodedData() {
        return encodedData;
    }

    public void setEncodedData(String encodedData) {
        this.encodedData = encodedData;
    }

}
