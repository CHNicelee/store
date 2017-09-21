package com.ice.dao;

import com.ice.entity.Category;

import java.util.List;

/**
 * Created by asd on 9/20/2017.
 */
public interface CategoryDAO {

    List<Category> getCategoryList();

    Category getCategory(int id);
}
