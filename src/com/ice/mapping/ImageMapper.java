package com.ice.mapping;

import com.ice.entity.ImageUrl;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by asd on 9/20/2017.
 */
public interface ImageMapper {

	@Insert("INSERT INTO Image(productId, imageUrl)\n" +
            "        VALUES (#{productId}, #{imageUrl})" )
    void insertImageUrl(ImageUrl imageUrl);

	@Update(" UPDATE Image SET  productId=#{productId}, imageUrl=#{imageUrl}\n" +
            "        WHERE  id=#{id}")
    void updateImageUrl(ImageUrl imageUrl);

	@Delete("delete from Image where id=#{id}")
    void deleteImageUrl(int id);

	@Select("SELECT * FROM Image WHERE id=#{productId}")
    List<ImageUrl> getImageUrl(int productId);

}
