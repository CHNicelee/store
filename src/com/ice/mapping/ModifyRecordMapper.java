package com.ice.mapping;

import com.ice.entity.ModifyRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by asd on 9/20/2017.
 */
public interface ModifyRecordMapper {

	@Insert("INSERT INTO ModifyRecord (productId, reason,adminId,createdAt)\n" +
            "        VALUES (#{productId}, #{reason}, #{adminId},now())" )
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void insertModifyRecord(ModifyRecord modifyRecord);

    @Select("SELECT * FROM ModifyRecord")
    List<ModifyRecord> getModifyRecordList();

    @Select("SELECT * FROM ModifyRecord WHERE adminId=#{adminId}")
    List<ModifyRecord> getModifyRecordByAdminId(int adminId);

    @Select("SELECT * FROM ModifyRecord WHERE productId=#{productId}")
    List<ModifyRecord> getModifyRecordByProductId(int productId);

}
