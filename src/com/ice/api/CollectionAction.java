package com.ice.api;

import com.ice.entity.Collection;
import com.ice.mapping.CollectionMapper;
import com.ice.util.ReturnUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CollectionAction extends BaseAction{
    private CollectionMapper mapper= sqlSession.getMapper(CollectionMapper.class);


    public Collection collection = new Collection();

    public String addCollection(){
        Map<String,Integer> map = new HashMap<>();
        map.put("userId",collection.getUserId());
        map.put("productId",collection.getProductId());
        System.out.println(map);
        List<Collection> c = mapper.checkCollection(map);
        if(c.size()!=0){
            ReturnUtil.error(result,"添加失败，不能重复添加");
            return SUCCESS;
        }
        try {
        	 mapper.insertCollection(collection);
            result.put("data",collection);
            ReturnUtil.success(result);
        }catch (Exception e){
            ReturnUtil.error(result,"添加失败"+e.getMessage());
        }

        return SUCCESS;
    }
    
    public int id;
    public String deleteCollection(){
    	   try {
               mapper.deleteCollection(id);
               ReturnUtil.success(result);
           }catch (Exception e){
               e.printStackTrace();
               ReturnUtil.error(result,"删除失败"+e.getMessage());
           }
    	   close();
           return SUCCESS;
       }
    
    public int userId;
    public String deleteCollectionByUserId(){
    	   try {
               mapper.deleteCollectionByUserId(userId);
               ReturnUtil.success(result);
           }catch (Exception e){
               e.printStackTrace();
               ReturnUtil.error(result,"ɾ��ʧ��"+e.getMessage());
           }
    	   close();
           return SUCCESS;
       }
    
    public String getCollectionByUserId(){
        List<Collection> list = mapper.getCollectionByUserId(userId);
        ReturnUtil.success(result);
        result.put("data",list);
        close();
        return SUCCESS;
    }

    
   
}
