package com.ice.mapping;

import com.ice.entity.LoginRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by asd on 9/20/2017.
 */
public interface LoginRecordMapper {

	@Insert("INSERT INTO LoginRecord(ip, userId, createdAt)\n" +
            "        VALUES (#{ip}, #{userId}, #{createdAt})" )
    void insertLoginRecord(LoginRecord loginRecord);

	@Select("SELECT * FROM LoginRecord WHERE id=#{userId}")
    List<LoginRecord>  getLoginRecord(int userId);

}
