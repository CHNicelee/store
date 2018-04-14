package com.ice.api;

import com.ice.entity.Admin;
import com.ice.mapping.AdminMapper;
import com.ice.util.Constant;
import com.ice.util.MD5Util;
import com.ice.util.ReturnUtil;
import org.apache.struts2.interceptor.SessionAware;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by asd on 9/21/2017.
 */
public class AdminAction extends BaseAction implements SessionAware{
    private AdminMapper mapper=sqlSession.getMapper(AdminMapper.class);


    public Admin admin = new Admin();

    public String addAdmin(){
        try {
            admin.setPassword(MD5Util.EncoderByMd5(admin.getPassword()));
            mapper.insertAdmin(admin);
            result.put("data",admin);
            ReturnUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            ReturnUtil.error(result,"添加失败"+e.getMessage());
        }

        return SUCCESS;
    }

    public String getAdmins(){
        List<Admin> list = mapper.getAllAdmin();
        ReturnUtil.success(result);
        result.put("data",list);
        return SUCCESS;
    }

    public static void main(String[] args) {
        AdminAction action = new AdminAction();
        action.admin.setName("ice");
        action.admin.setPassword("ice");
        action.admin.setAuth(3);
        action.addAdmin();
        System.out.println(action.result.toString());

        action.result = new HashMap<>();
        action.getAdmins();
        System.out.println(action.result.toString());
    }

    public String username;
    public String password;
    public String login() throws Exception {
        Admin tempAdmin = mapper.getAdminByUsername(username);
        if(tempAdmin!=null && tempAdmin.getPassword().equals(MD5Util.EncoderByMd5(password))){
            result.put("data",tempAdmin);
            ReturnUtil.success(result);

            //添加到session
            this.session.put(Constant.USER_TYPE,Constant.ADMIN);
            this.session.put(Constant.USER_ID,tempAdmin.getId());
        }else{
            ReturnUtil.error(result,"账号密码错误");
        }
        return SUCCESS;

    }


    private Map<String,Object> session;
    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String logout() throws Exception {
        if(this.session.get(Constant.USER_TYPE)!=null)
            this.session.put(Constant.USER_TYPE,null);
        if(this.session.get(Constant.USER_ID)!=null)
            this.session.put(Constant.USER_ID,null);
        ReturnUtil.success(result);
        return "success";
    }

    public String newPassword;
    public int id;
    public String modifyPassword() throws Exception {
        Admin tempAdmin = mapper.getAdmin(id);
        if(tempAdmin!=null && tempAdmin.getPassword().equals(MD5Util.EncoderByMd5(password))){
            tempAdmin.setPassword(MD5Util.EncoderByMd5(newPassword));
            mapper.updateAdmin(admin);
            ReturnUtil.success(result);
        }else{
            ReturnUtil.error(result,"原密码错误");
        }
        return SUCCESS;
    }


    public String updateAdmin() throws Exception {
        mapper.updateAdmin(admin);
        ReturnUtil.success(result);
        return "success";
    }

    public String deleteAdmin() throws Exception {
        mapper.deleteAdmin(id);
        ReturnUtil.success(result);
        return "success";
    }
}
