package com.ice.dao;

import com.ice.entity.Product;

import java.util.List;

/**
 * Created by asd on 9/20/2017.
 */
public interface ProductDAO {

    List<Product> getProductListByCategory(String categoryId);

    Product getProduct(String productId);

    List<Product> searchProductList(String keywords);

}
