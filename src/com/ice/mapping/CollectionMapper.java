package com.ice.mapping;

import com.ice.entity.Collection;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by asd on 9/20/2017.
 */
public interface CollectionMapper {

	@Insert("INSERT INTO Collection (userId, productId, createdAt)\n" +
            "        VALUES (#{userId}, #{productId}, now())" )
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void insertCollection(Collection collection);

	@Delete("update Collection set deleted=1 where id=#{id}")
    void deleteCollection(int id);

	@Delete("update Collection set deleted=1 where userId=#{userId}")
    void deleteCollectionByUserId(int userId);

	@Select("SELECT * FROM Collection WHERE id=#{userId} and deleted <> 1")
    List<Collection> getCollectionByUserId(int userId);

}
