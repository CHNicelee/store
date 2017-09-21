package com.ice.test;

import com.ice.entity.Question;
import com.ice.entity.User;
import com.ice.mapping.UserMapper;
import com.ice.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by asd on 9/20/2017.
 */
public class UserTest {

    public User getUser(){
        User user = new User();
        user.setUsername("ice");
        user.setQuestionId(1);
        user.setPassword("icelee");
        user.setAnswer("哎哟不错哦");
        user.setAvatar("ice97.jpg");
        return user;
    }

    public static void main(String[] args) {
        UserTest entity = new UserTest();
        SqlSessionFactory factory= MybatisUtil.getFactory();
        SqlSession session=factory.openSession(true);
        //使用反射的方法
        UserMapper mapper=session.getMapper(UserMapper.class);

        //删除
        mapper.deleteUserByUsername("ice");

        //插入
        User user = entity.getUser();
        mapper.insertUser(user);
        //修改User
        user.setAvatar("修改过了");
        mapper.updateUser(user);

        //获得问题
        Question question = mapper.getQuestion(user.getQuestionId());
        System.out.println(question.getQuestion());
        //查询
        Map<String,Integer> map = new HashMap<>();
        map.put("start",0);
        map.put("offset",1);
        System.out.println(mapper.getUserList(map));
        session.close();
    }

}
