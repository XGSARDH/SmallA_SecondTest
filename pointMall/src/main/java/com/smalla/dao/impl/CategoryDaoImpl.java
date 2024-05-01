package com.smalla.dao.impl;

import com.smalla.dao.CategoryDao;
import com.smalla.po.Category;
import com.smalla.util.connectPool.ConnectionPoolManager;
import com.smalla.util.crudUtils.CRUDUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sardh
 */
public class CategoryDaoImpl implements CategoryDao {

    private static volatile CategoryDaoImpl instance;

    public static CategoryDaoImpl getInstance() {
        if (instance == null) {
            synchronized (CategoryDaoImpl.class) {
                if (instance == null) {
                    instance = new CategoryDaoImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public List<Category> findAll() {
        String sql = "SELECT `category_id`, `category` FROM `categories`";
        Connection connection = ConnectionPoolManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = CRUDUtils.query(sql, connection, preparedStatement);
        List<Category> categories = new ArrayList<>();

        try {
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("category_id"));
                category.setCategory(rs.getString("category"));
                categories.add(category);
            }
            if (rs != null) {
                rs.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            ConnectionPoolManager.releaseConnection(connection);
            return categories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer save(Category category) throws SQLException {
        String sql = "INSERT INTO `categories` (`category`) VALUES (?)";
        return CRUDUtils.save(sql, category.getCategory());
    }

    @Override
    public void update(Category category) throws SQLException {
        String sql = "UPDATE `categories` SET `category` = ? WHERE `category_id` = ?";
        CRUDUtils.update(sql, category.getCategory(), category.getCategoryId());
    }

    @Override
    public void delete(int categoryId) throws SQLException {
        String sql = "DELETE FROM `categories` WHERE `category_id` = ?";
        CRUDUtils.update(sql, categoryId);
    }
}
