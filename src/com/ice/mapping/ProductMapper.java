package com.ice.mapping;

import com.ice.entity.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by asd on 9/20/2017.
 */
public interface ProductMapper {

	@Insert("INSERT INTO Product(categoryId, name, description,count,price,Attributes)\n" +
            "        VALUES (#{categoryId}, #{name}, #{description},#{count},#{price},#{Attributes})" )
    void insertProduct(Product product);

	@Update(" UPDATE Product SET  categoryId=#{categoryId}, name=#{name},description=#{description},count=#{count},price=#{price},Attributes=#{Attributes}\n" +
            "        WHERE  id=#{id}")
    void updateProduct(Product product);

	@Delete("delete from Product where id=#{id}")
    void deleteProduct(Product product);

	@Select("SELECT * FROM Product WHERE id=#{id}")
    Product getProductById(int id);

	@Select("SELECT * FROM Product WHERE id=#{categoryId}")
    List<Product> getProductByCategoryId(int categoryId);


}
