package com.ice.mapping;

import com.ice.entity.OrderInfo;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by asd on 9/20/2017.
 */
public interface OrderInfoMapper {

	@Insert("INSERT INTO OrderInfo(userId, state, phone,address,name,postcode,express,payMethod,createdAt)\n" +
            "        VALUES (#{userId}, #{state}, #{phone},#{address},#{name},#{postcode},#{express},#{payMethod},now())" )
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertOrderInfo(OrderInfo info);

	@Select("SELECT * FROM OrderInfo WHERE id=#{id}")
    OrderInfo getOrderInfo(int id);

	@Select("SELECT * FROM OrderInfo WHERE userId=#{userId} and deleted = 0")
    List<OrderInfo> getOrderInfoByUserId(int userId);

	@Update(" UPDATE OrderInfo SET  userId=#{userId}, state=#{state},phone=#{phone},address=#{address},name=#{name},postcode=#{postcode},express=#{express},payMethod=#{payMethod},createdAt=#{createdAt}\n" +
            "        WHERE  id=#{id}")
    void updateOrderInfo(OrderInfo info);

	@Select("SELECT * FROM OrderInfo WHERE createdAt >= #{createdAt}")
    List<OrderInfo> getOrderInfoByTimestamp(Timestamp createdAt);

    @Delete("update orderInfo set deleted = 1 where id = #{id} ")
    void deleteOrderInfo(int id);

    @Select("select * from orderinfo where state=#{state} and deleted <> 1")
    List<OrderInfo> queryOrderByState(int state);

}
