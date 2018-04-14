package com.ice.api;

import com.ice.entity.History;
import com.ice.mapping.HistoryMapper;
import com.ice.util.ReturnUtil;

import java.util.List;


public class HistoryAction extends BaseAction{
    private HistoryMapper mapper= sqlSession.getMapper(HistoryMapper.class);

    public History history = new History();

    public String addHistory(){
        try {
        	 mapper.insertHistory(history);
            result.put("data",history);
            ReturnUtil.success(result);
        }catch (Exception e){
            ReturnUtil.error(result,"添加失败"+e.getMessage());
        }

        return SUCCESS;
    }

    private void addHistoryTest() {
        history.setUserId(1);
        history.setProductId(1);
        addHistory();
    }

    public int userId;
    public String getHistoryByUserId(){
        List<History> list = mapper.getHistoryByUserId(userId);
        ReturnUtil.success(result);
        result.put("data",list);
        close();
        return SUCCESS;
    }
	
    public String deleteHistoryByUserId(){
 	   try {
            mapper.deleteHistoryByUserId(userId);
            ReturnUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            ReturnUtil.error(result,"删除失败"+e.getMessage());
        }
 	   close();
        return SUCCESS;
    }
    
    public int id;
    public String deleteHistoryById(){
  	   try {
             mapper.deleteHistoryById(id);
             ReturnUtil.success(result);
         }catch (Exception e){
             e.printStackTrace();
             ReturnUtil.error(result,"删除失败"+e.getMessage());
         }
  	   close();
         return SUCCESS;
     } 

    public static void main(String[] args) {
        HistoryAction action = new HistoryAction();
        action.addHistoryTest();
    }


}
