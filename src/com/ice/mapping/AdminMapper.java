package com.ice.mapping;

import com.ice.entity.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by asd on 9/20/2017.
 */
public interface AdminMapper {

	@Insert("INSERT INTO Admin (username, password, auth, name)\n" +
            "        VALUES (#{username}, #{password}, #{auth},#{name})" )
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void insertAdmin(Admin admin);

	@Delete("delete from Admin where id=#{id}")
    void deleteAdmin(int id);

	@Update(" UPDATE Admin  SET  password=#{password}, auth=#{auth},name=#{name}\n" +
            "        WHERE  id=#{id}")
    void updateAdmin(Admin admin);

	@Select("SELECT * FROM Admin WHERE id=#{id}")
    Admin getAdmin(int id);

	@Select("SELECT * FROM Admin ")
    List<Admin> getAllAdmin();

    @Select("Select * from admin where username=#{username}")
    Admin getAdminByUsername(String username);

}
