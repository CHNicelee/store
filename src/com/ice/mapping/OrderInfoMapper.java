package com.ice.mapping;

import com.ice.entity.OrderInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by asd on 9/20/2017.
 */
public interface OrderInfoMapper {

	@Insert("INSERT INTO OrderInfo(userId, state, phone,address,name,postcode,express,payMethod,createdAt)\n" +
            "        VALUES (#{userId}, #{state}, #{phone},#{address},#{name},#{postcode},#{express},#{payMethod},#{createdAt})" )
    void insertOrderInfo(OrderInfo info);

	@Select("SELECT * FROM OrderInfo WHERE id=#{id}")
    OrderInfo getOrderInfo(int id);

	@Select("SELECT * FROM OrderInfo WHERE id=#{iduserId}")
    List<OrderInfo> getOrderInfoByUserId(int userId);

	@Update(" UPDATE OrderInfo SET  userId=#{userId}, state=#{state},phone=#{phone},address=#{address},name=#{name},postcode=#{postcode},express=#{express},payMethod=#{payMethod},createdAt=#{createdAt}\n" +
            "        WHERE  id=#{id}")
    void updateOrderInfo(OrderInfo info);

	@Select("SELECT * FROM OrderInfo WHERE createAt=#{createAt}")
    List<OrderInfo> getOrderInfoByCreatedAt(String createAt);

}
