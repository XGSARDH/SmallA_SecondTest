package com.smalla.dao;

import com.smalla.dao.CategoryDao;
import com.smalla.dao.impl.CategoryDaoImpl;
import com.smalla.po.Category;

import java.sql.SQLException;
import java.util.List;

public class TestCategoryDao {

    public static void main(String[] args) {
        CategoryDao categoryDao = CategoryDaoImpl.getInstance();

        // 测试保存类别
        Category newCategory = new Category();
        newCategory.setCategory("Electronics");
        try {
            Integer categoryId = categoryDao.save(newCategory);
            System.out.println("新类别的 ID：" + categoryId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 测试查找所有类别
        List<Category> allCategories = categoryDao.findAll();
        if (!allCategories.isEmpty()) {
            System.out.println("所有类别：");
            for (Category category : allCategories) {
                System.out.println(category);
            }
        } else {
            System.out.println("未找到任何类别");
        }

        // 测试更新类别信息
        int categoryIdToUpdate = 1; // 修改为你要更新的类别 ID
        List<Category> categoriesToUpdate = categoryDao.findAll();
        if (!categoriesToUpdate.isEmpty()) {
            Category categoryToUpdate = categoriesToUpdate.get(0);
            categoryToUpdate.setCategory("Books");
            try {
                categoryDao.update(categoryToUpdate);
                System.out.println("类别信息已更新");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("未找到类别 ID 为 " + categoryIdToUpdate + " 的类别，无法更新");
        }

        // 测试删除类别
        int categoryIdToDelete = 1; // 修改为你要删除的类别 ID
        try {
            categoryDao.delete(categoryIdToDelete);
            System.out.println("类别删除成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
