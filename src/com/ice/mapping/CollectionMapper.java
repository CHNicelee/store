package com.ice.mapping;

import com.ice.entity.Collection;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by asd on 9/20/2017.
 */
public interface CollectionMapper {

	@Insert("INSERT INTO Collection (userId, productId, createdAt)\n" +
            "        VALUES (#{userId}, #{productId}, #{createdAt})" )
    void insertCollection(Collection collection);

	@Delete("delete from Collection where id=#{id}")
    void deleteCollection(int id);

	@Delete("delete from Collection where id=#{userId}")
    void deleteCollectionByUserId(int userId);

	@Select("SELECT * FROM Collection WHERE id=#{userId}")
    List<Collection> getCollectionByUserId(int userId);

}
