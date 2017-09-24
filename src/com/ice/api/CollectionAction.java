package com.ice.api;

import com.ice.entity.Collection;
import com.ice.mapping.CollectionMapper;
import com.ice.util.ReturnUtil;

import java.util.List;



public class CollectionAction extends BaseAction{
    private CollectionMapper mapper= sqlSession.getMapper(CollectionMapper.class);


    public Collection collection = new Collection();

    public String addCollection(){
        try {
        	 mapper.insertCollection(collection);
            result.put("data",collection);
            ReturnUtil.success(result);
        }catch (Exception e){
            ReturnUtil.error(result,"���ʧ��"+e.getMessage());
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
               ReturnUtil.error(result,"���ʧ��"+e.getMessage());
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
