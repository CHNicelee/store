package com.ice.mapping;

import com.ice.entity.SystemAdmin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by asd on 9/20/2017.
 */
public interface SystemAdminMapper {

	@Insert("INSERT INTO SystemAdmin(username, password)\n" +
            "        VALUES (#{username}, #{password})" )
    void insertSystemAdmin(SystemAdmin admin);

	@Update(" UPDATE SystemAdmin SET  password=#{password}\n" +
            "        WHERE  id=#{id}")
    void updateSystemAdmin(SystemAdmin admin);

	@Select("SELECT * FROM SystemAdmin WHERE username=#{username}")
    SystemAdmin getSystemAdmin(String username);

}

