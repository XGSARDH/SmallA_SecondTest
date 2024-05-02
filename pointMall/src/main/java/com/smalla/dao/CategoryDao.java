package com.smalla.dao;

import com.smalla.po.Category;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Sardh
 */
public interface CategoryDao {
    /**
     * 查找全部
     * @return
     */
    List<Category> listAll();

    /**
     * 保存
     * @param category
     * @return
     * @throws SQLException
     */
    Integer save(Category category) throws SQLException;

    /**
     * 更新
     * @param category
     * @throws SQLException
     */
    void update(Category category) throws SQLException;

    /**
     * 删除指定条目
     * @param categoryId
     * @throws SQLException
     */
    void delete(int categoryId) throws SQLException;
}
