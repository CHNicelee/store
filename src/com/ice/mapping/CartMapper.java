package com.ice.mapping;

import com.ice.entity.Cart;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by asd on 9/20/2017.
 */
public interface CartMapper {

	 @Insert("INSERT INTO Cart (userId, productId,attrId,count,createdAt)\n" +
	            "        VALUES (#{userId}, #{productId},#{attrId},#{count},now())" )
	 @Options(useGeneratedKeys = true,keyProperty = "id")
    void insertCart(Cart cart);

	 @Delete("delete from Cart where id=#{id}")
    void deleteCart(int id);

	 @Select("SELECT * FROM Cart WHERE userId=#{userId}")
    List<Cart> getCartList(int userId);
	 
	 @Update(" UPDATE Cart SET  userId=#{userId}, productId=#{productId},attrId=#{attrId},count=#{count}\n" +
	            "        WHERE  id=#{id}")
    void updateCart(Cart cart);

	@Select("select * from cart where id=#{id}")
	Cart getCart(int id);

}

