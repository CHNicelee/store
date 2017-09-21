package com.ice.dao;

import com.ice.entity.Question;
import com.ice.entity.User;

import java.util.List;

/**
 * Created by asd on 9/20/2017.
 */
public interface UserDAO {

    void insertUser(User user);

    void updateUser(User user);

    User getUser(String username);

    List<User> getUserList(int start,int offset);

    Question getQuestion(User user);

}
