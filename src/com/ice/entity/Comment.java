package com.ice.entity;

import java.sql.Timestamp;

/**
 * Created by asd on 9/22/2017.
 */
public class Comment {

    int id;
    int productId;
    int userId;
    String text;
    Timestamp createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", productId=" + productId +
                ", userId=" + userId +
                ", text='" + text + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
