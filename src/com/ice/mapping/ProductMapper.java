package com.ice.mapping;

import com.ice.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by asd on 9/20/2017.
 */
public interface ProductMapper {

	@Insert("INSERT INTO Product(categoryId, name, description,attribute)\n" +
            "        VALUES (#{categoryId}, #{name}, #{description},#{attribute})" )
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void insertProduct(Product product);

	@Update(" UPDATE Product SET fakeId=#{fakeId}, categoryId=#{categoryId}, name=#{name},description=#{description},count=#{count},price=#{price},Attributes=#{attributes}\n" +
            "        WHERE  id=#{id}")
    void updateProduct(Product product);

	@Delete("delete from Product where id=#{id}")
    void deleteProduct(int id);

	@Select("SELECT * FROM Product WHERE id=#{id}")
    Product getProductById(int id);

	@Select("SELECT * FROM Product WHERE categoryId=#{categoryId}")
    List<Product> getProductByCategoryId(int categoryId);

    @Select("SELECT * FROM Product WHERE fakeId=#{fakeId}")
    List<Product> getProductByFakeId(int fakeId);

    @Select("select * from product where name like #{name}")
    List<Product> searchProductByName(String name);

    @Select("select * from product where name = #{name}")
    List<Product> getProductByName(String name);

    @Select("select max(fakeId) from product")
    Integer getMaxFakeId();

    @Select("select * from product order by id desc limit 0,20")
    List<Product> getLatestProduct();

    @Select("select * from product where categoryId=#{categoryId} and id>#{startId} and id<#{endId}")
    List<Product> getMore(Map<String,Integer> map);
}
