package com.ice.mapping;

import com.ice.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by asd on 9/20/2017.
 */
public interface CategoryMapper {

	@Insert("INSERT INTO Category (name, imageURL, parentId,renisPat,Attributes)\n" +
            "        VALUES (#{name}, #{imageURL}, #{parentId},#{renisPat},#{Attributes})" )
    void insertCategory(Category category);

    /**
     * 鑾峰緱鎬诲垎绫�  鍗硃arentId is null
     */
	@Select("SELECT * FROM Category ")
    List<Category> getRootCategory();

	@Select("SELECT * FROM Category WHERE id=#{parentId}")
    List<Category> getChildrenCategory(int parentId);
	
	@Delete("delete from Category where id=#{id}")
    void deleteCategory(int id);

	@Update(" UPDATE Category SET  name=#{name}, imageURL=#{imageURL},parentId=#{parentId},isParent=#{isParent}, Attributes=#{Attributes}\n" +
            "        WHERE  id=#{id}")
    void updateCategory(Category category);

}
