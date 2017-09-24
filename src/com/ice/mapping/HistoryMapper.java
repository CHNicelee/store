package com.ice.mapping;

import com.ice.entity.History;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by asd on 9/20/2017.
 */
public interface HistoryMapper {

	@Insert("INSERT INTO History (userId, productId,createdAt)\n" +
            "        VALUES (#{userId}, #{productId},now())" )
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void insertHistory(History history);

	@Select("SELECT * FROM History  WHERE userId=#{userId} and  (deleted is null or deleted =0)")
    List<History> getHistoryByUserId(int userId);
	
	@Delete("update  history set deleted=1 where userId=#{userId}")
    void deleteHistoryByUserId(int userId);

	@Delete("update  history set deleted=1 where id = #{id}")
    void deleteHistoryById(int id);

    @Select("select productId from history where createdAt > #{createdAt}")
    List<Integer> getItemIdListByDate(Timestamp createdAt);
}
