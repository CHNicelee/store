package com.ice.api;

import com.ice.entity.ModifyRecord;
import com.ice.mapping.ModifyRecordMapper;
import com.ice.util.ReturnUtil;

import java.util.List;

/**
 * Created by asd on 9/22/2017.
 */
public class ModifyRecordAction extends BaseAction {

    private ModifyRecordMapper mapper= sqlSession.getMapper(ModifyRecordMapper.class);


    public ModifyRecord modifyRecord = new ModifyRecord();
    public String addModifyRecord() throws Exception {
        mapper.insertModifyRecord(modifyRecord);
        ReturnUtil.success(result);
        return "success";
    }

    public String getModifyRecords() throws Exception {
        List<ModifyRecord> list = mapper.getModifyRecordList();
        result.put("data",list);
        ReturnUtil.success(result);
        return "success";
    }

    public int productId;
    public String getModifyRecordByProductId() throws Exception {
        List<ModifyRecord> list = mapper.getModifyRecordByProductId(productId);
        result.put("data",list);
        ReturnUtil.success(result);
        return "success";
    }

    public int adminId;
    public String getModifyRecordByAdminId() throws Exception {
        List<ModifyRecord> list = mapper.getModifyRecordByAdminId(adminId);
        result.put("data",list);
        ReturnUtil.success(result);
        return "success";
    }
}
