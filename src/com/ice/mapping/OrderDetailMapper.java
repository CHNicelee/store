package com.ice.mapping;

import com.ice.entity.OrderDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by asd on 9/20/2017.
 */
public interface OrderDetailMapper {

	@Insert("INSERT INTO OrderDetail(orderId, productId, number,price)\n" +
            "        VALUES (#{orderId}, #{productId}, #{number},#{price})" )
    void insertOrderDetail(OrderDetail orderDetail);

	@Select("SELECT * FROM ModifyRecord WHERE id=#{id}")
    OrderDetail getOrderDetail(int id);

	@Select("SELECT * FROM ModifyRecord WHERE id=#{userId}")
    List<OrderDetailMapper> getOrderDetailByUserId(int userId);



}
