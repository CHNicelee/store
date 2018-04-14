package com.ice.api;

import com.ice.entity.Address;
import com.ice.entity.Attribute;
import com.ice.entity.OrderDetail;
import com.ice.entity.OrderInfo;
import com.ice.mapping.*;
import com.ice.util.ReturnUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asd on 9/22/2017.
 */
public class OrderInfoAction extends BaseAction{

    private OrderInfoMapper orderInfoMapper = sqlSession.getMapper(OrderInfoMapper.class);
    private OrderDetailMapper orderDetailMapper = sqlSession.getMapper(OrderDetailMapper.class);
    private ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
    private CartMapper cartMapper = sqlSession.getMapper(CartMapper.class);
    private AttributeMapper attributeMapper = sqlSession.getMapper(AttributeMapper.class);
    public OrderInfo order = new OrderInfo();
    public String json;

    /*
    {ids:[1,5,3,4],count:[1,2,2,2],carts:[1,2,3,4]}
     */
    public int addressId;
    public String addOrder() throws Exception {
        AddressMapper addressMapper = sqlSession.getMapper(AddressMapper.class);
        Address address = addressMapper.getAddressById(addressId);
        order.setAddress(address.getAddress());
        order.setName(address.getName());
        order.setPhone(address.getPhone());
        order.setPostcode(address.getPostcode());
        try{
            orderInfoMapper.insertOrderInfo(order);
            order.setDetails(new ArrayList<>());
            JSONObject jsonObject = JSONObject.fromObject(json);
            JSONArray ids = (JSONArray) jsonObject.get("ids");
            JSONArray count = (JSONArray) jsonObject.get("count");
            JSONArray carts = (JSONArray) jsonObject.get("carts");

            //添加商品详情
            for (int i =0;i<ids.size();i++) {
                OrderDetail detail = new OrderDetail();
                detail.setNumber((Integer) count.get(i));
//                Product product = productMapper.getProductById((Integer) ids.get(i));
                Attribute attribute = attributeMapper.getAttribute((Integer) ids.get(i));
                detail.setPrice(attribute.getPrice());
                detail.setOrderId(order.getId());
                detail.setProductId(attribute.getProductId());
                detail.setAttrId((Integer) ids.get(i));
                orderDetailMapper.insertOrderDetail(detail);
                order.getDetails().add(detail);
            }
            //删除购物车中的部分商品
            for (int i = 0; i < carts.size(); i++) {
                cartMapper.deleteCart((Integer) carts.get(i));
            }
            result.put("data",order);
            ReturnUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            ReturnUtil.error(result,"添加失败"+e.getMessage());
        }
        return "success";
    }

    private void addOrderTest()  {
        order.setUserId(1);
        order.setPhone("1243");
        order.setAddress("dddd");
        json = "{ids:[1],count:[2],carts:[2]}";
        try {
            addOrder();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

    public String updateOrder() throws Exception {
        try {
            orderInfoMapper.updateOrderInfo(order);
            ReturnUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            ReturnUtil.error(result,"更新失败"+e.getMessage());
        }
        return "success";
    }

    public int id;
    public String deleteOrder() throws Exception {
        order = orderInfoMapper.getOrderInfo(id);
        if(order.getState() != 4){
            ReturnUtil.error(result,"非作废订单，无法删除");
            return SUCCESS;
        }
        orderDetailMapper.deleteOrderDetail(id);
        orderInfoMapper.deleteOrderInfo(id);
        ReturnUtil.success(result);
        return "success";
    }

    public String getOrderInfo() throws Exception {
        OrderInfo info = orderInfoMapper.getOrderInfo(id);
        List<OrderDetail> details = orderDetailMapper.getOrderDetails(info.getId());
        info.setDetails(details);
        ReturnUtil.success(result);
        result.put("data",info);
        return "success";
    }

    public int userId;
    public String getAllOrders() throws Exception {
        List<OrderInfo> list = orderInfoMapper.getOrderInfoByUserId(userId);
        getDetails(list);
        ReturnUtil.success(result);
        result.put("data",list);
        return "success";
    }


    public ArrayList<Integer> userlist = new ArrayList<>();
    public String test() throws Exception {
        System.out.println(userlist.toString());
        return "success";
    }

    public static void main(String[] args) {
        OrderInfoAction action = new OrderInfoAction();
        action.addOrderTest();
    }

    private void getDetails(List<OrderInfo> list){
        for (OrderInfo info : list) {
            List<OrderDetail> details = orderDetailMapper.getOrderDetails(info.getId());
            info.setDetails(details);
            info.setTotalPrice(getTotalPrice(details));
        }
    }

    private double getTotalPrice(List<OrderDetail> list){
        double r = 0;
        for (OrderDetail orderDetail : list) {
            r+=orderDetail.getPrice()*orderDetail.getNumber();
        }
        return r;
    }

    public int state;
    public String queryOrdersByState() throws Exception {
        List<OrderInfo> list = orderInfoMapper.queryOrderByState(state);
        getDetails(list);
        result.put("data",list);
        ReturnUtil.success(result);
        return "success";
    }


    public String modifyState() throws Exception {
        order = orderInfoMapper.getOrderInfo(id);
        order.setState(state);
        orderInfoMapper.updateOrderInfo(order);
        ReturnUtil.success(result);
        return "success";
    }
}
