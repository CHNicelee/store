package com.ice.mapping;

import com.ice.entity.Question;
import com.ice.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * Created by asd on 9/20/2017.
 */
public interface UserMapper {


    @Insert("INSERT INTO USER (USERNAME, PASSWORD, avatar,answer,questionId)\n" +
            "        VALUES (#{username}, #{password}, #{avatar},#{answer},#{questionId})" )
    void insertUser(User user);

    @Update(" UPDATE USER SET  PASSWORD=#{password}, avatar=#{avatar},answer=#{answer},questionId=#{questionId}\n" +
            "        WHERE USERNAME = #{username}")
    void updateUser(User user);

    @Select("SELECT * FROM USER WHERE id=#{id}")
    User getUserById(int id);

    @Select("SELECT * FROM USER WHERE username=#{username}")
    User getUserByUsername(String username);

    @Select("SELECT * FROM USER LIMIT #{start},#{offset}")
    List<User> getUserList(Map<String, Integer> map);

    @Select("SELECT * FROM question WHERE id=#{questionId}")
    Question getQuestion(int questionId);

    @Delete("delete from user where id=#{id}")
    void deleteUserById(int id);

    @Delete("delete from user where username=#{username}")
    void deleteUserByUsername(String username);
}
