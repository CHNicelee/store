package com.ice.api;

import com.ice.entity.Attribute;
import com.ice.entity.Cart;
import com.ice.mapping.AttributeMapper;
import com.ice.mapping.CartMapper;
import com.ice.util.ReturnUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartAction extends BaseAction {

    private CartMapper mapper= sqlSession.getMapper(CartMapper.class);
    AttributeMapper attributeMapper = sqlSession.getMapper(AttributeMapper.class);

    public Cart cart= new Cart();

    public String addCart(){
        Attribute attribute = attributeMapper.getAttribute(cart.getAttrId());
        Map<String,Integer> map = new HashMap<>();
        map.put("userId",cart.getUserId());
        map.put("productId",cart.getProductId());
        map.put("attrId",cart.getAttrId());
        Cart c = mapper.getSameCart(map);
        if(cart.getCount()<0){
            ReturnUtil.error(result,"数量不能为负数");
            return SUCCESS;
        }
        if(attribute.getCount()<cart.getCount()){
            ReturnUtil.error(result,"添加失败，物品库存不够");
            return SUCCESS;
        }
        try {
            if(c!=null){
                c.setCount(c.getCount() + cart.getCount());
                mapper.updateCart(c);
                result.put("data", c);

            }else {
                mapper.insertCart(cart);
                result.put("data", cart);
            }
            ReturnUtil.success(result);
            //减少库存
            attribute.setCount(attribute.getCount() - cart.getCount());
            attributeMapper.updateAttribute(attribute);
        }catch (Exception e){
            ReturnUtil.error(result,"添加失败"+e.getMessage());
        }

        return SUCCESS;
    }
    
    public int id;
    public String deleteCart(){
    	   try {
               //更新库存
               Cart cart = mapper.getCart(id);
//               Product product = productMapper.getProductById(cart.getProductId());
               Attribute attribute = attributeMapper.getAttribute(cart.getAttrId());
//               product.setCount(product.getCount() + cart.getCount());
               attribute.setCount(attribute.getCount() + cart.getCount());
//               productMapper.updateProduct(product);
               attributeMapper.updateAttribute(attribute);
               //删除记录
               mapper.deleteCart(id);
               ReturnUtil.success(result);
           }catch (Exception e){
               e.printStackTrace();
               ReturnUtil.error(result,"删除失败"+e.getMessage());
           }
    	   close();
           return SUCCESS;
     }
    
    public int userId;
    public String getCartList(){
        List<Cart> list = mapper.getCartList(userId);
        double totalPrice = 0;
        for (Cart cart : list) {
//            Product product = productMapper.getProductById(cart.getProductId());
            Attribute attribute = attributeMapper.getAttribute(cart.getAttrId());
            totalPrice+=attribute.getPrice() * cart.getCount();
            cart.setPrice(attribute.getPrice() * cart.getCount());
        }
        result.put("totalPrice",totalPrice);
        ReturnUtil.success(result);
        result.put("data",list);
        close();
        return SUCCESS;
    }
    
    public String updateCart(){
        int oldCount = mapper.getCart(cart.getId()).getCount();
        if(cart.getCount()<0){
            ReturnUtil.error(result,"数量不能为负数");
            return SUCCESS;
        }
//        Product product = productMapper.getProductById(cart.getProductId());
        Attribute attribute = attributeMapper.getAttribute(cart.getAttrId());
        if(cart.getCount()>oldCount){
            if(attribute.getCount() < cart.getCount() - oldCount){
                ReturnUtil.error(result,"商品库存不够");
                return SUCCESS;
            }
        }
        attribute.setCount(attribute.getCount()- (cart.getCount()-oldCount));
        attributeMapper.updateAttribute(attribute);
        cart.setPrice(cart.getCount()*attribute.getPrice());
        mapper.updateCart(cart);
        ReturnUtil.success(result);
        result.put("data",cart);
        close();
        return SUCCESS;
    }

    public void addCartTest(){
        cart.setCount(2);
        cart.setProductId(8);
        cart.setUserId(6);
        cart.setAttrId(4);
        addCart();
    }

    public static void main(String[] args) {
        CartAction cartAction = new CartAction();
        cartAction.addCartTest();
    }

}
