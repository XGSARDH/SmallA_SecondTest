package com.smalla.dao;

import com.smalla.po.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentDao {
    /**
     * 根据订单来查找评论
     * @param orderId
     * @return
     */
    List<Comment> listByOrderId(int orderId);

    /**
     * 保存
     * @param comment
     * @return
     * @throws SQLException
     */
    Integer save(Comment comment) throws SQLException;

    /**
     * 更新评论
     * @param comment
     * @throws SQLException
     */
    void update(Comment comment) throws SQLException;

    /**
     * 删除评论
     * @param commentId
     * @throws SQLException
     */
    void delete(int commentId) throws SQLException;
}
