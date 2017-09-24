package com.ice.entity;

import java.util.List;

public class Product {
    int id;
    int categoryId;
    String name;
    String description;
    String attributes;  //例如 ["颜色","内存大小"]
    List<ImageUrl> imageUrls;
    List<Attribute> attr;  //这里面是attributes的值 等等

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public List<Attribute> getAttr() {
        return attr;
    }

    public void setAttr(List<Attribute> attr) {
        this.attr = attr;
    }

    public List<ImageUrl> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<ImageUrl> imageUrls) {
        this.imageUrls = imageUrls;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
