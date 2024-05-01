package com.smalla.dao;

import com.smalla.dao.impl.ProductDaoImpl;
import com.smalla.po.Product;

import java.sql.SQLException;
import java.util.List;

public class TestProductDao {

    public static void main(String[] args) {
        ProductDao productDao = ProductDaoImpl.getInstance();

        // 测试保存产品
        Product newProduct = new Product();
        newProduct.setMerchantId(1);
        newProduct.setProductName("testProduct");
        newProduct.setCategoryId(1);
        newProduct.setPrice("100.00");
        newProduct.setStock("10");
        newProduct.setProductHealth(1);
        try {
            Integer productId = productDao.save(newProduct);
            System.out.println("新产品的 ID：" + productId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 测试根据商户 ID 查找产品
        int merchantIdToFind = 1; // 修改为你要查找的商户 ID
        List<Product> foundProducts = productDao.findByMerchantId(merchantIdToFind);
        if (!foundProducts.isEmpty()) {
            Product foundProduct = foundProducts.get(0);
            System.out.println("找到的产品信息：" + foundProduct);
        } else {
            System.out.println("未找到商户 ID 为 " + merchantIdToFind + " 的产品");
        }

        // 测试更新产品信息
        int productIdToUpdate = 1; // 修改为你要更新的产品 ID
        List<Product> productsToUpdate = productDao.findByMerchantId(merchantIdToFind);
        if (!productsToUpdate.isEmpty()) {
            Product productToUpdate = productsToUpdate.get(0);
            productToUpdate.setProductName("updatedProduct");
            productToUpdate.setPrice("150.00");
            productToUpdate.setStock("5");
            try {
                productDao.update(productToUpdate);
                System.out.println("产品信息已更新");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("未找到产品 ID 为 " + productIdToUpdate + " 的产品，无法更新");
        }

        // 测试删除产品
        int productIdToDelete = 1; // 修改为你要删除的产品 ID
        try {
            productDao.delete(productIdToDelete);
            System.out.println("产品删除成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
