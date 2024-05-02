package com.smalla.dao;

import com.smalla.dao.impl.CommentDaoImpl;
import com.smalla.po.Comment;

import java.sql.SQLException;
import java.util.List;

public class TestCommentDao {

    public static void main(String[] args) {
        // 创建CommentDaoImpl对象
        CommentDaoImpl commentDao = CommentDaoImpl.getInstance();

        // 测试findByOrderId()方法
        System.out.println("Testing findByOrderId()...");
        int orderId = 123; // 假设订单ID为123
        List<Comment> comments = commentDao.findByOrderId(orderId);
        for (Comment comment : comments) {
            System.out.println(comment);
        }
        System.out.println();

        // 测试save()方法
        System.out.println("Testing save()...");
        Comment newComment = new Comment();
        newComment.setUserId(456); // 假设用户ID为456
        newComment.setOrderId(789); // 假设订单ID为789
        newComment.setProductId(101); // 假设产品ID为101
        newComment.setMerchantId(202); // 假设商家ID为202
        newComment.setCommentDetail("This is a test comment."); // 假设评论内容为测试评论
        try {
            int commentId = commentDao.save(newComment);
            System.out.println("New comment saved successfully with ID: " + commentId);
        } catch (SQLException e) {
            System.out.println("Failed to save new comment: " + e.getMessage());
        }
        System.out.println();

        // 测试update()方法
        System.out.println("Testing update()...");
        Comment existingComment = new Comment();
        existingComment.setCommentId(1); // 假设要更新的评论ID为1001
        existingComment.setUserId(456); // 假设用户ID为456
        existingComment.setOrderId(789); // 假设订单ID为789
        existingComment.setProductId(101); // 假设产品ID为101
        existingComment.setMerchantId(300); // 假设商家ID为202
        existingComment.setCommentDetail("Updated comment detail."); // 假设更新后的评论内容
        try {
            commentDao.update(existingComment);
            System.out.println("Comment updated successfully with ID: " + existingComment.getCommentId());
        } catch (SQLException e) {
            System.out.println("Failed to update comment: " + e.getMessage());
        }
        System.out.println();

        // 测试delete()方法
        /*
        System.out.println("Testing delete()...");
        int commentIdToDelete = 1002; // 假设要删除的评论ID为1002
        try {
            commentDao.delete(commentIdToDelete);
            System.out.println("Comment deleted successfully with ID: " + commentIdToDelete);
        } catch (SQLException e) {
            System.out.println("Failed to delete comment with ID " + commentIdToDelete + ": " + e.getMessage());
        }
        */
    }
}
