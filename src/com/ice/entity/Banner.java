package com.ice.entity;

/**
 * Created by asd on 9/20/2017.
 */
public class Banner {

    int id;
    int productId;
    String title;
    Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "id=" + id +
                ", productId=" + productId +
                ", title='" + title + '\'' +
                '}';
    }
}
