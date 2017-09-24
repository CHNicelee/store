package com.ice.api;

import com.ice.entity.ImageUrl;
import com.ice.mapping.ImageMapper;
import com.ice.util.ReturnUtil;

import java.util.List;


public class ImageUrlAction extends BaseAction{
    private ImageMapper mapper=sqlSession.getMapper(ImageMapper.class);


    public ImageUrl image = new ImageUrl();

    public String addImageUrl(){
        try {
        	 mapper.insertImageUrl(image);
            result.put("data", image);
            ReturnUtil.success(result);
        }catch (Exception e){
            ReturnUtil.error(result,"添加失败"+e.getMessage());
        }
        return SUCCESS;
    }
    
    public String updateImageUrl(){
        mapper.updateImageUrl(image);
        ReturnUtil.success(result);
        result.put("data", image);
        close();
        return SUCCESS;
    }
    
    
    public int id;
    public String deleteImageUrl(){
    	   try {
               mapper.deleteImageUrl(id);
               ReturnUtil.success(result);
           }catch (Exception e){
               e.printStackTrace();
               ReturnUtil.error(result,"ɾ��ʧ��"+e.getMessage());
           }
    	   close();
           return SUCCESS;
       }
    
    public int productId;
    public String getImageUrl(){
        List<ImageUrl> list = mapper.getImageUrl(productId);
        ReturnUtil.success(result);
        result.put("data",list);
        close();
        return SUCCESS;
    }

    
   

}
