package com.ice.mapping;

import com.ice.entity.Attribute;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by asd on 9/24/2017.
 */
public interface AttributeMapper {

    @Insert("insert attribute(productId,count,price,attributes) values(#{productId},#{count},#{price},#{attributes})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void insertAttribute(Attribute attribute);

    @Select("select * from attribute where productId=#{productId}")
    List<Attribute> getAttributesByProductId(int productId);

    @Select("select * from attribute where id=#{id}")
    Attribute getAttribute(int id);

    @Select("update attribute set count=#{count},price=#{price},attributes=#{attributes} where id = #{id}")
    void updateAttribute(Attribute attribute);
}
