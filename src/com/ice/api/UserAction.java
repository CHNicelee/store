package com.ice.api;

import com.ice.entity.User;
import com.ice.mapping.OrderInfoMapper;
import com.ice.mapping.UserMapper;
import com.ice.util.Constant;
import com.ice.util.MD5Util;
import com.ice.util.ReturnUtil;
import org.apache.struts2.interceptor.SessionAware;

import java.util.List;

/**
 * Created by asd on 9/20/2017.
 */
public class UserAction extends BaseAction implements SessionAware{

    private UserMapper mapper= sqlSession.getMapper(UserMapper.class);
    /**
     * 是否存在该用户
     */
    public String username;
    public String isExistUser(){

        if(mapper.getUserByUsername(username)==null){
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
        try {
            user.setPassword(MD5Util.EncoderByMd5(user.getPassword()));
            user.setAnswer(MD5Util.EncoderByMd5(user.getAnswer()));
            mapper.insertUser(user);
            result.put("data",user);
            ReturnUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            ReturnUtil.error(result,"添加失败"+e.getMessage());
        }
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
            ReturnUtil.error(result,"未知错误"+e.getMessage());
        }
        close();
        return SUCCESS;
    }

    /**
     * 通过id 删除用户
     * @return
     */
    public String deleteUser(){
        OrderInfoMapper orderMapper = sqlSession.getMapper(OrderInfoMapper.class);
        if(orderMapper.getOrderInfoByUserId(id).size()>0){
            ReturnUtil.error(result,"用户含有订单");
            return SUCCESS;
        }
        mapper.deleteUserById(id);
        ReturnUtil.success(result);
        close();
        return SUCCESS;
    }

    /**
     * 登录
     * @return
     */
    public String password;
    public String login(){
        User tempUser = mapper.getUserByUsername(username);
        if(tempUser!=null && tempUser.getPassword().equals(MD5Util.EncoderByMd5(password))){
            result.put("data",tempUser);
            ReturnUtil.success(result);
            this.session.put(Constant.USER_TYPE,Constant.USER);
            this.session.put(Constant.USER_ID,tempUser.getId());
        }else{
            ReturnUtil.error(result,"账号密码错误");
        }
        return SUCCESS;
    }


    /**
     * 修改密码
     */
    public String newPassword;
    public String answer;
    public String findPassword() throws Exception {
        User u = mapper.getUserByUsername(username);
        if(MD5Util.EncoderByMd5(answer).equals(u.getAnswer())){
            u.setPassword(MD5Util.EncoderByMd5(newPassword));
            mapper.updateUser(u);
            result.put("data",u);
            ReturnUtil.success(result);
        }else{
            ReturnUtil.error(result,"答案不正确");
        }
        return "success";
    }

    public String logout() throws Exception {
        if(this.session.get(Constant.USER_TYPE)!=null)
            this.session.put(Constant.USER_TYPE,null);
        if(this.session.get(Constant.USER_ID)!=null)
            this.session.put(Constant.USER_ID,null);
        ReturnUtil.success(result);
        return "success";
    }


    public String modifyPassword() throws Exception {
        User user = mapper.getUserById(id);
        if(user.getPassword().equals(MD5Util.EncoderByMd5(password))){
            user.setPassword(MD5Util.EncoderByMd5(newPassword));
            ReturnUtil.success(result);
            result.put("data",user);
        }else{
            ReturnUtil.error(result,"原密码错误");
        }
        return "success";
    }


    public String queryUser() throws Exception {
        List<User> list = mapper.queryUser("%"+username+"%");
        result.put("data",list);
        ReturnUtil.success(result);
        return "success";
    }


    public String isLogin() throws Exception {
        if(session.get(Constant.USER_TYPE) != null &&
                session.get(Constant.USER_ID) != null){
            if((int)session.get(Constant.USER_TYPE) == Constant.USER){
                user = mapper.getUserById((Integer) session.get(Constant.USER_ID));
                result.put("data",user);
            }
            result.put("isLogin",true);
            ReturnUtil.success(result);
        }else {
            result.put("isLogin",false);
            ReturnUtil.success(result);
        }
        return "success";
    }

    public String getAvatarByUsername() throws Exception {
        user = mapper.getUserByUsername(username);
        if(user == null){
            ReturnUtil.error(result,"不存在该用户");
        }else{
            ReturnUtil.success(result);
            result.put("avatar",user.getAvatar());
        }
        return "success";
    }
}
