package com.ice.mapping;

import com.ice.entity.History;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by asd on 9/20/2017.
 */
public interface HistoryMapper {

	@Insert("INSERT INTO History (userId, item_id)\n" +
            "        VALUES (#{userId}, #{item_id})" )
    void insertHistory(History history);

	@Select("SELECT * FROM History  WHERE id=#{userId}")
    List<History> getHistoryByUserId(int userId);
	
	@Delete("delete from Category where id=#{userId}")
    void deleteHistoryByUserId(int userId);

	@Delete("delete from Category where id=#{id}")
    void deleteHistoryById(int id);

}
