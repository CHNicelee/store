package com.ice.mapping;

import com.ice.entity.OrderDetail;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by asd on 9/20/2017.
 */
public interface OrderDetailMapper {

	@Insert("INSERT INTO OrderDetail(orderId, productId,deleted,attrId ,number,price)\n" +
            "        VALUES (#{orderId}, #{productId},0,#{attrId}, #{number},#{price})" )
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void insertOrderDetail(OrderDetail orderDetail);

	@Select("SELECT * FROM OrderDetail WHERE id=#{id}")
    OrderDetail getOrderDetail(int id);

    @Delete("update orderDetail set deleted = 1 where orderId=#{orderId}")
    void deleteOrderDetail(int orderId);

    @Select("Select * from orderDetail where orderId =#{orderId}")
    List<OrderDetail> getOrderDetails(int orderId);

    @Select("Select * from orderDetail where productId=#{productId} and deleted <> 1")
    List<OrderDetail> getOrderDetailsByProductId(int productId);


}
