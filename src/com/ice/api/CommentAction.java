package com.ice.api;

import com.ice.entity.Comment;
import com.ice.mapping.CommentMapper;
import com.ice.mapping.UserMapper;
import com.ice.util.ReturnUtil;

import java.util.List;

/**
 * Created by asd on 9/22/2017.
 */
public class CommentAction extends BaseAction {

    private CommentMapper mapper= sqlSession.getMapper(CommentMapper.class);

    public Comment comment = new Comment();
    public String addComment() throws Exception {
        mapper.insertComment(comment);
        result.put("data",comment);
        ReturnUtil.success(result);
        return "success";
    }

    public int id;
    public String deleteComment() throws Exception {
        mapper.deleteComment(id);
        ReturnUtil.success(result);
        return "success";
    }


    public String updateComment() throws Exception {
        return "success";
    }

    public int productId;
    public String getCommentsByProductId() throws Exception {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<Comment> list = mapper.getCommentByProductId(productId);
        for (Comment comment1 : list) {
            comment1.setUser(userMapper.getUserById(comment1.getUserId()));
        }
        result.put("data",list);
        ReturnUtil.success(result);
        return "success";
    }
}
