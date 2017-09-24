package com.ice.api;

import com.ice.entity.SystemAdmin;
import com.ice.mapping.SystemAdminMapper;
import com.ice.util.MybatisUtil;
import com.ice.util.ReturnUtil;
import com.opensymphony.xwork2.Action;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.HashMap;


public class SystemAdminAction implements Action{
	private SqlSessionFactory factory= MybatisUtil.getFactory();
    private SqlSession session=factory.openSession(true);
    private SystemAdminMapper mapper=session.getMapper(SystemAdminMapper.class);

    private void close(){
        session.commit();
        session.close();
    } 
	
    public SystemAdmin systemAdmin = new SystemAdmin();
    public HashMap<String,Object> result = new HashMap<>();
    
    public String addISystemAdmin(){
        try {
        	 mapper.insertSystemAdmin(systemAdmin);
            result.put("data",systemAdmin);
            ReturnUtil.success(result);
        }catch (Exception e){
            ReturnUtil.error(result,"���ʧ��"+e.getMessage());
        }

        return SUCCESS;
    }
    
    public String updateSystemAdmin(){
        mapper.updateSystemAdmin(systemAdmin);
        ReturnUtil.success(result);
        result.put("data",systemAdmin);
        close();
        return SUCCESS;
    }
	
    public String username;
    public String getSystemAdmin(){
    	systemAdmin = mapper.getSystemAdmin(username);
        if(systemAdmin!=null) {
            ReturnUtil.success(result);
            result.put("data",systemAdmin);
        }else{
            ReturnUtil.error(result,"��ȡ�ɹ�");
        }
        close();
        return SUCCESS;
    }
    
    @Override
   	public String execute() throws Exception {
   		// TODO Auto-generated method stub
   		return null;
   	}
    
    
}
