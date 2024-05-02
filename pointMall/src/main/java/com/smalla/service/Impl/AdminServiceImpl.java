package com.smalla.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smalla.factory.DaoFactory;
import com.smalla.po.*;
import com.smalla.service.AdminService;

import java.sql.SQLException;
import java.util.List;

public class AdminServiceImpl implements AdminService {

    private static volatile AdminServiceImpl instance;

    public static AdminServiceImpl getInstance() {
        if (instance == null) {
            synchronized (AdminServiceImpl.class) {
                if (instance == null) {
                    instance = new AdminServiceImpl();
                }
            }
        }
        return instance;
    }

    public AdminServiceImpl() {
    }

    @Override
    public String viewUserByAdmin() {
        List<User> all = DaoFactory.getUserDao().listAll();
        JSONArray jsonArray = new JSONArray();
        for (User user : all) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user_id", user.getUserId());
            jsonObject.put("fund_id", user.getFundId());
            jsonObject.put("username", user.getUsername());
            jsonObject.put("password", user.getPassword());
            jsonObject.put("phone", user.getPhone());
            jsonObject.put("user_health", user.getUserHealth());
            jsonObject.put("is_merchant", user.getIsMerchant());
            jsonObject.put("default_address", user.getDefaultAddress());
            jsonArray.add(jsonObject);
        }
        String output = jsonArray.toJSONString();
        return output;
    }

    @Override
    public String updateUserByAdmin(int userId, String username, String password, String phone) {

        User user = DaoFactory.getUserDao().getById(userId);
        User phoneUser = DaoFactory.getUserDao().getByPhone(phone);

        if (user == null) {
            return "user is null";
        }

        if (phoneUser != null) {
            if (phoneUser.getUserId() != user.getUserId()) {
                return "phone is be used";
            }
        }

        user.setUsername(username);
        user.setPassword(password);
        user.setPhone(phone);
        try {
            DaoFactory.getUserDao().update(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "success";
    }

    @Override
    public String viewMerchantByAdmin() {
        List<Merchant> all = DaoFactory.getMerchantDao().listAll();
        JSONArray jsonArray = new JSONArray();
        for (Merchant merchant : all) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("merchant_id", merchant.getMerchantId());
            jsonObject.put("user_id", merchant.getUserId());
            jsonObject.put("fund_id", merchant.getFundId());
            jsonObject.put("merchant_name", merchant.getMerchantName());
            jsonObject.put("merchant_health", merchant.getMerchantHealth());
            jsonArray.add(jsonObject);
        }
        String output = jsonArray.toJSONString();
        return output;
    }

    @Override
    public String updateMerchantByAdmin(int merchantId, String merchantName, int merchantHealth) {

        List<Merchant> byMerchantId = DaoFactory.getMerchantDao().listByMerchantId(merchantId);
        Merchant merchant = null;

        if(byMerchantId != null && !byMerchantId.isEmpty()) {
            merchant = byMerchantId.get(0);
        }

        if (merchant != null) {
            merchant.setMerchantName(merchantName);
            merchant.setMerchantHealth(merchantHealth);
            try {
                DaoFactory.getMerchantDao().update(merchant);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return "success";
        }
        else {
            return "failed";
        }
    }

    @Override
    public String viewProductByAdmin() {
        List<Product> all = DaoFactory.getProductDao().listAll();
        JSONArray jsonArray = new JSONArray();
        for (Product product : all) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("product_id", product.getProductId());
            jsonObject.put("merchant_id", product.getMerchantId());
            jsonObject.put("product_name", product.getProductName());
            jsonObject.put("category_id", product.getCategoryId());
            jsonObject.put("price", product.getPrice());
            jsonObject.put("stock", product.getStock());
            jsonObject.put("product_health", product.getProductHealth());
            jsonArray.add(jsonObject);
        }
        String output = jsonArray.toJSONString();
        return output;
    }

    @Override
    public String updateProductByAdmin(int productId, int productHealth) {
        List<Product> byProductId = DaoFactory.getProductDao().listByProductId(productId);
        Product product = null;

        if(byProductId != null && !byProductId.isEmpty()) {
            product = byProductId.get(0);
        }

        if (product != null) {
            product.setProductHealth(productHealth);
            try {
                DaoFactory.getProductDao().update(product);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return "success";
        }
        else {
            return "failed";
        }
    }

    @Override
    public String viewCategoryByAdmin() {
        List<Category> all = DaoFactory.getCategoryDao().listAll();
        JSONArray jsonArray = new JSONArray();
        for (Category category : all) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("categoryId", category.getCategoryId());
            jsonObject.put("category", category.getCategory());
            jsonArray.add(jsonObject);
        }
        String output = jsonArray.toJSONString();
        return output;
    }

    @Override
    public String insertCategoryByAdmin(String category) {
        Category newcategory = new Category();
        newcategory.setCategory(category);
        int categoryId = 0;
        try {
            categoryId = DaoFactory.getCategoryDao().save(newcategory);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (categoryId == 0) {
            return "failed";
        }
        else {
            return String.valueOf(categoryId);
        }
    }

    @Override
    public String viewDailycheckinByAdmin() {
        List<DailyCheckIn> allCheckIns = DaoFactory.getDailyCheckInDao().getAllCheckIns();
        JSONArray jsonArray = new JSONArray();
        for (DailyCheckIn allCheckIn : allCheckIns) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("check_in_date", allCheckIn.getCheckInDate());
            jsonObject.put("number_of_people", allCheckIn.getNumberOfPeople());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }
}
