package com.ice.api;

import com.ice.entity.Attribute;
import com.ice.mapping.AttributeMapper;
import com.ice.util.ReturnUtil;

/**
 * Created by asd on 9/27/2017.
 */
public class AttrAction extends BaseAction {

    AttributeMapper mapper = sqlSession.getMapper(AttributeMapper.class);

    public Attribute attr = new Attribute();
    public String updateAttr() throws Exception {
        mapper.updateAttribute(attr);
        ReturnUtil.success(result);
        return "success";
    }
}
