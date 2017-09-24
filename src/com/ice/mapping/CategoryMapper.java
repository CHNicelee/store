package com.ice.mapping;

import com.ice.entity.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by asd on 9/20/2017.
 */
public interface CategoryMapper {

	@Insert("INSERT INTO Category (name, imageURL, parentId,Attributes)\n" +
            "        VALUES (#{name}, #{imageURL}, #{parentId})" )
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void insertCategory(Category category);


	@Select("SELECT * FROM Category Where parentId is null or parentId=0")
    List<Category> getRootCategory();

	@Select("SELECT * FROM Category WHERE parentId=#{id}")
    List<Category> getChildrenCategory(int id);
	
	@Delete("delete from Category where id=#{id}")
    void deleteCategory(int id);

	@Update(" UPDATE Category SET  name=#{name}, imageURL=#{imageURL},parentId=#{parentId}\n" +
            "        WHERE  id=#{id}")
    void updateCategory(Category category);

    @Select("Select * from category where name like #{name}")
    List<Category> searchCateogry(String name);
}
