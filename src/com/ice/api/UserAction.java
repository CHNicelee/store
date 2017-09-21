package com.ice.api;

import com.ice.entity.User;
import com.ice.mapping.UserMapper;
import com.ice.util.MybatisUtil;
import com.ice.util.ReturnUtil;
import com.opensymphony.xwork2.Action;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.HashMap;

/**
 * Created by asd on 9/20/2017.
 */
public class UserAction implements Action {
    private SqlSessionFactory factory= MybatisUtil.getFactory();
    private SqlSession session=factory.openSession(true);
    private UserMapper mapper=session.getMapper(UserMapper.class);

    private void close(){
        session.commit();
        session.close();
    }

    //用于返回json数据的map
    public HashMap<String,Object> result = new HashMap<>();

    /**
     * 是否存在该用户
     */
    public String username;
    public String isExistUser(){
        if(mapper.getUserByUsername(username)!=null){
            ReturnUtil.success(result);
            result.put("exist",false);
        }else {
            ReturnUtil.success(result);
            result.put("exist",true);
        }
        close();
        return SUCCESS;
    }

    /**
     * 添加用户  直接给user赋值
     */
    public User user = new User();
    public String addUser(){
        System.out.println(user);
        try {
            mapper.insertUser(user);
            ReturnUtil.success(result);
            result.put("data",user);
        }catch (Exception e){
            e.printStackTrace();
            ReturnUtil.error(result,"用户名已经存在");
        }
        close();
        return SUCCESS;
    }

    /**
     * 通过id获取用户信息
     * @return
     */
    public int id;
    public String getUserById(){
        user = mapper.getUserById(id);
        if(user!=null) {
            ReturnUtil.success(result);
            result.put("data", user);
        }else{
            ReturnUtil.error(result,"不存在改id的");
        }
        close();
        return SUCCESS;
    }

    /**
     * 更新用户  直接赋值
     * @return
     */
    public String updateUser(){
        try{
            mapper.updateUser(user);
            ReturnUtil.success(result);
            result.put("user",user);
        }catch (Exception e){
            ReturnUtil.error(result,"未知错误");
        }
        close();
        return SUCCESS;
    }

    /**
     * 通过id 删除用户
     * @return
     */
    public String deleteUser(){
        mapper.deleteUserById(id);
        ReturnUtil.success(result);
        close();
        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {
        return null;
    }
}
