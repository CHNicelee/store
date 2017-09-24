package com.ice.api;

import com.ice.entity.Address;
import com.ice.mapping.AddressMapper;
import com.ice.util.ReturnUtil;

import java.util.List;


public class AddressAction extends BaseAction{
    AddressMapper mapper = sqlSession.getMapper(AddressMapper.class);
    public Address address = new Address();

    public String addAddress(){
        try {
        	 mapper.insertAddress(address);
            result.put("data",address);
            ReturnUtil.success(result);
        }catch (Exception e){
            ReturnUtil.error(result,"添加失败"+e.getMessage());
        }

        return SUCCESS;
    }
    
    
    public int id;
    public String getAddressById(){
   address = mapper.getAddressById(id);
        if(address!=null) {
            ReturnUtil.success(result);
            result.put("data",address);
        }else{
        	   ReturnUtil.error(result,"获取失败");
        }
        close();
        return SUCCESS;
    }
    
    public int userId;
    public String getAddressByUserId(){
        List<Address> list = mapper.getAddressByUserId(userId);
        ReturnUtil.success(result);
        result.put("data",list);
        close();
        return SUCCESS;
    }
    
    public String deleteAddress(){
  	   try {
             mapper.deleteAddress(id);
             ReturnUtil.success(result);
         }catch (Exception e){
             e.printStackTrace();
             ReturnUtil.error(result,"删除失败"+e.getMessage());
         }
  	   close();
         return SUCCESS;
     }
    
    public String updateAddress(){
        mapper.updateAddress(address);
        ReturnUtil.success(result);
        result.put("data",address);
        close();
        return SUCCESS;
    }

    public static void main(String[] args) {
    	AddressAction a=new AddressAction();
    	a.address.setId(1);
    	a.address.setUserId(2);
    	a.address.setPhone("12111");
    	a.address.setAddress("22221");
    	a.address.setAddress("1221111");
    	a.address.setName("2222");
    	a.address.setPostcode("112211");
    	a.updateAddress();
    	
       System.out.print(a.result.toString());
    		  
    	  }
    
}
