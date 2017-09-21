package com.ice.dao;

import com.ice.entity.Admin;

import java.util.List;

/**
 * Created by asd on 9/20/2017.
 */
public interface AdminDAO {

    Admin getAdmin(String username);

    List<Admin> getAllAdmin();

    void insertAdmin(Admin admin);

    void updateAdmin(Admin admin);

    void getQuestion(int question_id);

}
