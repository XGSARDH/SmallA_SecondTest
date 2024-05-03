package com.smalla.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smalla.config.LoginMode;
import com.smalla.config.SortType;
import com.smalla.factory.DaoFactory;
import com.smalla.po.*;
import com.smalla.service.TouristService;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

public class TouristServiceImpl implements TouristService {

    private static volatile TouristServiceImpl instance;

    public static TouristServiceImpl getInstance() {
        if (instance == null) {
            synchronized (TouristServiceImpl.class) {
                if (instance == null) {
                    instance = new TouristServiceImpl();
                }
            }
        }
        return instance;
    }

    public TouristServiceImpl() {
    }

    /**
     * 正则表达式判断字符串是否全数字
     * @param str
     * @return
     */
    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    @Override
    public String login(String loginMode, String loginCredentials, String password) {
        // 判断loginCredentials是否是纯数字
        if (!isNumeric(loginCredentials)) {
            return "LoginCredentials is wrong";
        }

        if (loginMode.equals(String.valueOf(LoginMode.ID))) {
            // 使用账号密码方式进行登录
            User byId = DaoFactory.getUserDao().getById(Integer.parseInt(loginCredentials));
            if (byId == null) {
                return "userId is null";
            } else if(byId.getPassword().equals(password)) {
                return "Success";
            } else {
                return "Password is wrong.";
            }
        } else if (loginMode.equals(String.valueOf(LoginMode.PHONE))) {
            // 使用手机号方式进行登录
            User byPhone = DaoFactory.getUserDao().getByPhone(loginCredentials);
            if (byPhone == null) {
                return "userId is null";
            } else if(byPhone.getPassword().equals(password)) {
                return "Success";
            } else {
                return "Password is wrong.";
            }
        } else {
            return "loginMode is wrong.";
        }
    }

    @Override
    public String register(String username, String password, String phone, String defaultAddress) {
        User byPhone = DaoFactory.getUserDao().getByPhone(phone);
        if (byPhone != null) {
            return "Phone is be used.";
        }

        // 设置一个新user
        User user = new User();
        user.setPhone(phone);
        user.setPassword(password);
        user.setUsername(username);
        user.setDefaultAddress(defaultAddress);

        try {
            user.setUserId(DaoFactory.getUserDao().save(user));
        } catch (SQLException e) {
            return "error" + e.getMessage();
        }

        // 注册一个新地址(默认地址)
        Address address = new Address();
        address.setAddress(defaultAddress);
        address.setPhone(phone);
        address.setUserId(user.getUserId());
        try {
            address.setAddressId(DaoFactory.getAddressDao().save(address));
        } catch (SQLException e) {
            return "error" + e.getMessage();
        }

        // 注册一个新账户
        Fund fund = new Fund();
        fund.setUserId(user.getUserId());
        fund.setAvailableFunds("0");
        fund.setFrozenFunds("0");
        fund.setTotalFunds("0");
        fund.setFundHealth(1);
        try {
            fund.setFundId(DaoFactory.getFundDao().save(fund));
        } catch (SQLException e) {
            return "error" + e.getMessage();
        }

        return "Success";

    }

    @Override
    public String viewProductByConditionAndSorting(String conditionType, String conditionDetail, String sortType, int start, int end) {

        List<Product> products = null;
        // 先进行检索

        products = DaoFactory.getProductDao().listAll();

        if (start > products.size()) {
            return "null";
        }

        // 再进行排序
        products = productBySorting(products, sortType);
        JSONArray jsonArray = new JSONArray();
        for (int i = start; i < (Math.min(end, products.size())) ; i++) {
            Product product = products.get(i);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("product_id", product.getProductId());
            jsonObject.put("merchant_id", product.getMerchantId());
            jsonObject.put("product_name", product.getProductName());
            jsonObject.put("category_id", product.getCategoryId());
            jsonObject.put("price", product.getPrice());
            jsonObject.put("stock", product.getStock());
            jsonObject.put("product_health", product.getProductHealth());
            jsonObject.put("sales_volume", product.getSalesVolume());
            jsonObject.put("positive_reviews", product.getPositiveReviews());
            jsonArray.add(jsonObject);
            System.out.println(product);
        }

        return jsonArray.toJSONString();
    }

    /**
     * 对商品序列进行排序
     * @param products
     * @param sortType
     * @return
     */
    private List<Product> productBySorting(List<Product> products, String sortType) {
        Comparator<Product> comparator;
        if (sortType.equals(String.valueOf(SortType.DATE))) {
            return products;
        } else if (sortType.equals(String.valueOf(SortType.SALES_VOLUME))) {
            // 根据销量排序
            comparator = Comparator.comparing(Product::getSalesVolume);
        } else if (sortType.equals(String.valueOf(SortType.POSITIVE_REVIVWS))) {
            // 根据好评数排序
            comparator = Comparator.comparing(Product::getPositiveReviews);
        } else if (sortType.equals(String.valueOf(SortType.NULL))) {
            return products;
        } else {
            return products;
        }
        products.sort(comparator);
        return products;
    }

    @Override
    public String viewProductDetails(int productId) {
        Product byProductId = DaoFactory.getProductDao().getByProductId(productId);
        if(byProductId == null) {
            return "Product is not exist.";
        }else {
            JSONObject firstJsonObject = new JSONObject();

            firstJsonObject.put("product_id", byProductId.getProductId());
            firstJsonObject.put("merchant_id", byProductId.getMerchantId());
            firstJsonObject.put("product_name", byProductId.getProductName());
            firstJsonObject.put("category_id", byProductId.getCategoryId());
            firstJsonObject.put("price", byProductId.getPrice());
            firstJsonObject.put("stock", byProductId.getStock());
            firstJsonObject.put("sales_volume", byProductId.getSalesVolume());
            firstJsonObject.put("positive_reviews", byProductId.getPositiveReviews());

            List<Comment> comments = DaoFactory.getCommentDao().listByProductId(productId);
            JSONArray jsonArray = new JSONArray();
            for (Comment comment : comments) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("comment_id", comment.getCommentId());
                jsonObject.put("comment_user_id", comment.getCommentId());
                jsonObject.put("order_id", comment.getCommentId());
                jsonObject.put("product_id", comment.getCommentId());
                jsonObject.put("merchant_id", comment.getCommentId());
                jsonObject.put("comment_detail", comment.getCommentId());
                jsonObject.put("Is_positive", comment.getCommentId());
                jsonArray.add(jsonObject);
            }
            firstJsonObject.put("comments", jsonArray);

            return firstJsonObject.toJSONString();
        }
    }

    

}

