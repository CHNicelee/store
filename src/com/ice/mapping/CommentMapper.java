package com.ice.mapping;

import com.ice.entity.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by asd on 9/22/2017.
 */
public interface CommentMapper {

    @Insert("insert comment(productId,userId,text,createdAt) values(#{productId},#{userId},#{text},now())" )
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void insertComment(Comment comment);

    @Select("select * from comment where productId=#{productId}")
    List<Comment> getCommentByProductId(int productId);

    @Delete("delete from comment where id = #{id}")
    void deleteComment(int id);


}
