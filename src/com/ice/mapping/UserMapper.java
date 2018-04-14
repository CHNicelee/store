package com.ice.mapping;

import com.ice.entity.Question;
import com.ice.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by asd on 9/20/2017.
 */
public interface UserMapper {


    @Insert("INSERT INTO User (USERNAME, PASSWORD,answer,questionId)\n" +
            "        VALUES (#{username}, #{password},#{answer},#{questionId})" )
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertUser(User user);

    @Update(" UPDATE User SET  PASSWORD=#{password}, avatar=#{avatar},answer=#{answer},questionId=#{questionId}\n" +
            "        WHERE USERNAME = #{username}")
    void updateUser(User user);

    @Select("SELECT * FROM User WHERE id=#{id}")
    User getUserById(int id);

    @Select("SELECT * FROM User WHERE username=#{username}")
    User getUserByUsername(String username);

    @Select("SELECT * FROM User LIMIT #{start},#{offset}")
    List<User> getUserList(Map<String, Integer> map);

    @Select("SELECT * FROM Question WHERE id=#{questionId}")
    Question getQuestion(int questionId);

    @Delete("delete from User where id=#{id}")
    void deleteUserById(int id);

    @Delete("delete from User where username=#{username}")
    void deleteUserByUsername(String username);

    @Select("select * from User where username like #{username}")
    List<User> queryUser(String username);


}
