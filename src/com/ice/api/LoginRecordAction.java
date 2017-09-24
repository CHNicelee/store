package com.ice.api;

import com.ice.entity.LoginRecord;
import com.ice.mapping.LoginRecordMapper;
import com.ice.util.ReturnUtil;

import java.util.List;

/**
 * Created by asd on 9/22/2017.
 */
public class LoginRecordAction  extends BaseAction{
    private LoginRecordMapper mapper= sqlSession.getMapper(LoginRecordMapper.class);

    public LoginRecord record = new LoginRecord();
    public String addLoginRecord() throws Exception {
        mapper.insertLoginRecord(record);
        ReturnUtil.success(result);
        return "success";
    }

    public int userId;
    public String getLoginRecordByUserId() throws Exception {
        List<LoginRecord> list = mapper.getLoginRecord(userId);
        result.put("data",list);
        ReturnUtil.success(result);
        return "success";
    }




}
