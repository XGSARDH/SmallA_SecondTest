package com.smalla.dao.impl;

import com.smalla.dao.ProductDao;
import com.smalla.po.Product;
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
public class ProductDaoImpl implements ProductDao {

    private static volatile ProductDaoImpl instance;

    public static ProductDaoImpl getInstance() {
        if (instance == null) {
            synchronized (ProductDaoImpl.class) {
                if (instance == null) {
                    instance = new ProductDaoImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public List<Product> listByMerchantId(int merchantId) {
        String sql = "SELECT `product_id`, `merchant_id`, `product_name`, `category_id`, `price`, `stock`, `product_health`, `sales_volume`, `positive_reviews` FROM `products` WHERE `merchant_id` = ?";
        Connection connection = ConnectionPoolManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = CRUDUtils.query(sql, connection, preparedStatement, merchantId);
        List<Product> products = new ArrayList<>();

        try {
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setMerchantId(rs.getInt("merchant_id"));
                product.setProductName(rs.getString("product_name"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setPrice(rs.getString("price"));
                product.setStock(rs.getString("stock"));
                product.setProductHealth(rs.getInt("product_health"));
                product.setSalesVolume(rs.getInt("sales_volume"));
                product.setPositiveReviews(rs.getInt("positive_reviews"));
                products.add(product);
            }
            if(rs != null) {
                rs.close();
            }
            if(preparedStatement != null) {
                preparedStatement.close();
            }
            ConnectionPoolManager.releaseConnection(connection);
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> listByProductId(int productId) {
        String sql = "SELECT `product_id`, `merchant_id`, `product_name`, `category_id`, `price`, `stock`, `product_health`, `sales_volume`, `positive_reviews` FROM `products` WHERE `product_id` = ?";
        Connection connection = ConnectionPoolManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = CRUDUtils.query(sql, connection, preparedStatement, productId);
        List<Product> products = new ArrayList<>();

        try {
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setMerchantId(rs.getInt("merchant_id"));
                product.setProductName(rs.getString("product_name"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setPrice(rs.getString("price"));
                product.setStock(rs.getString("stock"));
                product.setProductHealth(rs.getInt("product_health"));
                product.setSalesVolume(rs.getInt("sales_volume"));
                product.setPositiveReviews(rs.getInt("positive_reviews"));
                products.add(product);
            }
            if(rs != null) {
                rs.close();
            }
            if(preparedStatement != null) {
                preparedStatement.close();
            }
            ConnectionPoolManager.releaseConnection(connection);
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> listAll() {
        String sql = "SELECT `product_id`, `merchant_id`, `product_name`, `category_id`, `price`, `stock`, `product_health`, `sales_volume`, `positive_reviews` FROM `products` ";
        Connection connection = ConnectionPoolManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = CRUDUtils.query(sql, connection, preparedStatement);
        List<Product> products = new ArrayList<>();

        try {
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setMerchantId(rs.getInt("merchant_id"));
                product.setProductName(rs.getString("product_name"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setPrice(rs.getString("price"));
                product.setStock(rs.getString("stock"));
                product.setProductHealth(rs.getInt("product_health"));
                product.setSalesVolume(rs.getInt("sales_volume"));
                product.setPositiveReviews(rs.getInt("positive_reviews"));
                products.add(product);
            }
            if(rs != null) {
                rs.close();
            }
            if(preparedStatement != null) {
                preparedStatement.close();
            }
            ConnectionPoolManager.releaseConnection(connection);
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer save(Product product) throws SQLException {
        String sql = "INSERT INTO `products` (`merchant_id`, `product_name`, `category_id`, `price`, `stock`, `product_health`, `sales_volume`, `positive_reviews`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return CRUDUtils.save(sql, product.getMerchantId(), product.getProductName(), product.getCategoryId(), product.getPrice(), product.getStock(), product.getProductHealth(), product.getSalesVolume(), product.getPositiveReviews());
    }

    @Override
    public void update(Product product) throws SQLException {
        String sql = "UPDATE `products` SET `product_name` = ?, `category_id` = ?, `price` = ?, `stock` = ?, `product_health` = ?, `sales_volume` = ?, `positive_reviews` = ? WHERE `product_id` = ?";
        CRUDUtils.update(sql, product.getProductName(), product.getCategoryId(), product.getPrice(), product.getStock(), product.getProductHealth(), product.getSalesVolume(), product.getPositiveReviews(), product.getProductId());
    }

    @Override
    public void delete(int productId) throws SQLException {
        String sql = "DELETE FROM `products` WHERE `product_id` = ?";
        CRUDUtils.update(sql, productId);
    }
}
