package com.smalla.dao.impl;

import com.smalla.dao.CommentDao;
import com.smalla.po.Comment;
import com.smalla.util.connectPool.ConnectionPoolManager;
import com.smalla.util.crudUtils.CRUDUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDao {

    private static volatile CommentDaoImpl instance;

    public static CommentDaoImpl getInstance() {
        if (instance == null) {
            synchronized (CommentDaoImpl.class) {
                if (instance == null) {
                    instance = new CommentDaoImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public List<Comment> listByOrderId(int orderId) {
        String sql = "SELECT `comment_id`, `user_id`, `order_id`, `product_id`, `merchant_id`, `comment_detail` FROM `comments` WHERE `order_id` = ?";
        Connection connection = ConnectionPoolManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = CRUDUtils.query(sql, connection, preparedStatement, orderId);
        List<Comment> comments = new ArrayList<>();
        try {
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setCommentId(rs.getInt("comment_id"));
                comment.setUserId(rs.getInt("user_id"));
                comment.setOrderId(rs.getInt("order_id"));
                comment.setProductId(rs.getInt("product_id"));
                comment.setMerchantId(rs.getInt("merchant_id"));
                comment.setCommentDetail(rs.getString("comment_detail"));
                comments.add(comment);
            }
            return comments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            ConnectionPoolManager.releaseConnection(connection);
        }
    }

    @Override
    public Integer save(Comment comment) throws SQLException {
        String sql = "INSERT INTO `comments` (`user_id`, `order_id`, `product_id`, `merchant_id`, `comment_detail`) VALUES (?, ?, ?, ?, ?)";
        return CRUDUtils.save(sql, comment.getUserId(), comment.getOrderId(), comment.getProductId(), comment.getMerchantId(), comment.getCommentDetail());
    }

    @Override
    public void update(Comment comment) throws SQLException {
        String sql = "UPDATE `comments` SET `user_id` = ?, `order_id` = ?, `product_id` = ?, `merchant_id` = ?, `comment_detail` = ? WHERE `comment_id` = ?";
        CRUDUtils.update(sql, comment.getUserId(), comment.getOrderId(), comment.getProductId(), comment.getMerchantId(), comment.getCommentDetail(), comment.getCommentId());
    }

    @Override
    public void delete(int commentId) throws SQLException {
        String sql = "DELETE FROM `comments` WHERE `comment_id` = ?";
        CRUDUtils.update(sql, commentId);
    }
}
