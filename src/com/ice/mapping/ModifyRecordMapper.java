package com.ice.mapping;

import com.ice.entity.ModifyRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by asd on 9/20/2017.
 */
public interface ModifyRecordMapper {

	@Insert("INSERT INTO ModifyRecord (productId, reason,adminId,createdAt)\n" +
            "        VALUES (#{productId}, #{reason}, #{adminId},#{createdAt})" )
    void insertModifyRecord(ModifyRecord modifyRecord);
	
	@Select("SELECT * FROM ModifyRecord WHERE id=#{adminId}")
    List<ModifyRecord> getModifyRecordList(int adminId);

}
