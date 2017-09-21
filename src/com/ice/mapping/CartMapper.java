package com.ice.mapping;

import com.ice.entity.Cart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by asd on 9/20/2017.
 */
public interface CartMapper {

	 @Insert("INSERT INTO Cart (userId, productId,count)\n" +
	            "        VALUES (#{userId}, #{productId}, #{count})" )
    void insertCart(Cart cart);

	 @Delete("delete from Cart where id=#{id}")
    void deleteCart(int id);

	 @Select("SELECT * FROM Cart WHERE id=#{userId}")
    List<Cart> getCartList(int userId);
	 
	 @Update(" UPDATE Cart SET  userId=#{userId}, productId=#{productId},count=#{count}\n" +
	            "        WHERE  id=#{id}")
    void updateCart(Cart cart);

}

