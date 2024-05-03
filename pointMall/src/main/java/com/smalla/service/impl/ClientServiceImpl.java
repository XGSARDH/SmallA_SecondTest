package com.smalla.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smalla.config.FundFlowStatus;
import com.smalla.config.OrderStatus;
import com.smalla.factory.DaoFactory;
import com.smalla.po.*;
import com.smalla.service.ClientService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sardh
 */
public class ClientServiceImpl implements ClientService {

    private static volatile ClientServiceImpl instance;

    public static ClientServiceImpl getInstance() {
        if (instance == null) {
            synchronized (ClientServiceImpl.class) {
                if (instance == null) {
                    instance = new ClientServiceImpl();
                }
            }
        }
        return instance;
    }

    public ClientServiceImpl() {
    }

    @Override
    public String viewUserInformation(int userId) {
        // 查看个人信息
        User user = DaoFactory.getUserDao().getById(userId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_id", user.getUserId());
        jsonObject.put("fund_id", user.getFundId());
        jsonObject.put("username", user.getUsername());
        jsonObject.put("password", user.getPassword());
        jsonObject.put("phone", user.getPhone());
        jsonObject.put("user_health", user.getUserHealth());
        jsonObject.put("is_merchant", user.getIsMerchant());
        jsonObject.put("default_address", user.getDefaultAddress());
        String jsonString = jsonObject.toJSONString();
        return jsonString;
    }

    @Override
    public String updateUserInformation(int userId, String username, String phone, String password) {
        // 修改个人信息
        User user = DaoFactory.getUserDao().getById(userId);
        User phoneUser = DaoFactory.getUserDao().getByPhone(phone);

        if (user == null) {
            return "User is not exist.";
        }

        if (phoneUser != null ) {
            if (phoneUser.getUserId() != user.getUserId()) {
                return "Phone is be used.";
            }
        }

        user.setUsername(username);
        user.setPhone(phone);
        user.setPassword(password);

        try {
            DaoFactory.getUserDao().update(user);
        } catch (SQLException e) {
            return "Error" + e.getMessage();
        }

        return "Success";

    }

    @Override
    public String viewUserAddress(int userId) {
        // 查看个人地址信息
        List<Address> addresses = DaoFactory.getAddressDao().listByUserId(userId);

        JSONArray jsonArray = new JSONArray();
        for (Address address : addresses) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("address_id", address.getAddressId());
            jsonObject.put("user_id", address.getUserId());
            jsonObject.put("phone", address.getPhone());
            jsonObject.put("address", address.getAddress());

            jsonArray.add(jsonObject);
        }

        return jsonArray.toJSONString();
    }

    @Override
    public String updateUserAddress(int addressId, String addressDetail, String phone) {
        // 修改个人地址
        Address address = DaoFactory.getAddressDao().getByAddressId(addressId);
        if (address == null) {
            return "address is not exist";
        }else {
            address.setAddress(addressDetail);
            address.setPhone(phone);
            try {
                DaoFactory.getAddressDao().update(address);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return "Success";
        }
    }

    @Override
    public String insertUserAddress(int userId, String addressDetail, String phone) {
        // 增加个人地址
        Address address = new Address();
        address.setUserId(userId);
        address.setAddress(addressDetail);
        address.setPhone(phone);

        try {
            address.setAddressId(DaoFactory.getAddressDao().save(address));
        } catch (SQLException e) {
            return "Error" + e.getMessage();
        }

        return "Success";

    }

    @Override
    public String setDefaultAddress(int userId, int addressId) {
        // 设置默认地址
        User user = DaoFactory.getUserDao().getById(userId);
        Address address = DaoFactory.getAddressDao().getByAddressId(addressId);
        if (address == null) {
            return "Address is not exist";
        } else if (user == null) {
            return "User is not exist";
        } else if (address.getUserId() != userId) {
            return "The address does not belong to this user";
        } else {
            user.setDefaultAddress(address.getAddress());
            try {
                DaoFactory.getUserDao().update(user);
            } catch (SQLException e) {
                return "Error" + e.getMessage();
            }
            return "Success";
        }

    }

    @Override
    public String addProductToCart(int userId, int productId, int productQuantity) {
        // 将商品添加到购物车
        Product product = DaoFactory.getProductDao().getByProductId(productId);
        if (product == null) {
            return "Product is not exist";
        }

        List<Cart> carts = DaoFactory.getCartDao().listByUserIdAndCartHealth(userId, 1);

        Cart cart = null;

        if(carts != null && !carts.isEmpty()){
            for (Cart cart1 : carts) {
                if (cart1.getProductId() == productId) {
                    cart = cart1;
                }
            }
        }

        if (cart == null) {
            cart = new Cart();
            cart.setUserId(userId);
            cart.setProductId(productId);
            cart.setProductQuantity(productQuantity);
            cart.setCartHealth(1);
            try {
                cart.setCartId(DaoFactory.getCartDao().save(cart));
            } catch (SQLException e) {
                return "Error" + e.getMessage();
            }
            return "Success";
        } else {
            cart.setProductQuantity(productQuantity + cart.getProductQuantity());
            try {
                DaoFactory.getCartDao().update(cart);
            } catch (SQLException e) {
                return "Error" + e.getMessage();
            }
            return "Success";
        }
    }

    @Override
    public String viewUserCart(int userId) {
        // 查看个人购物车
        List<Cart> carts = DaoFactory.getCartDao().listByUserIdAndCartHealth(userId, 1);
        if (carts == null || carts.isEmpty()) {
            return "Cart is not exist";
        }

        JSONArray jsonArray = new JSONArray();
        for (Cart cart : carts) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("cart_id", cart.getCartId());
            jsonObject.put("user_id", cart.getUserId());
            jsonObject.put("product_id", cart.getProductId());
            jsonObject.put("product_quantity", cart.getProductQuantity());
            jsonObject.put("cart_health", cart.getCartHealth());

            jsonArray.add(jsonObject);
        }

        return jsonArray.toJSONString();

    }

    @Override
    public String deleteProductFromCart(int cartId, int userId) {
        // 删除个人购物车, 注意只是作废该购物车, 后续可以查找记录
        Cart cart = DaoFactory.getCartDao().getByCartId(cartId);
        if (cart == null) {
            return "Cart is not exist";
        } else if(cart.getUserId() != userId) {
            return "This shopping cart does not belong to the user";
        } else {
            cart.setCartHealth(0);
            try {
                DaoFactory.getCartDao().update(cart);
            } catch (SQLException e) {
                return "Error" + e.getMessage();
            }
            return "Success";
        }
    }

    @Override
    public String updateProductFromCart(int cartId, int userId, int productQuantity) {
        // 修改个人购物车
        Cart cart = DaoFactory.getCartDao().getByCartId(cartId);
        if (cart == null) {
            return "Cart is not exist";
        } else if(cart.getUserId() != userId) {
            return "This shopping cart does not belong to the user";
        } else {
            cart.setCartHealth(1);
            cart.setProductQuantity(productQuantity);
            try {
                DaoFactory.getCartDao().update(cart);
            } catch (SQLException e) {
                return "Error" + e.getMessage();
            }
            return "Success";
        }
    }

    @Override
    public String settlementUserAllCart(int userId) {
        // 结算个人全部购物车

        List<Cart> carts = DaoFactory.getCartDao().listByUserIdAndCartHealth(userId, 1);
        if (carts == null || carts.isEmpty()) {
            return "No carts can be used to generate orders";
        }

        for (Cart cart : carts) {
            Product product = DaoFactory.getProductDao().getByProductId(cart.getProductId());
            // 检验产品是否可交易
            if (product == null) {
                cart.setCartHealth(0);
                try {
                    DaoFactory.getCartDao().update(cart);
                } catch (SQLException e) {
                    return "Error" + e.getMessage();
                }
                continue;
            }else if (product.getProductHealth() == 0) {
                cart.setCartHealth(0);
                try {
                    DaoFactory.getCartDao().update(cart);
                } catch (SQLException e) {
                    return "Error" + e.getMessage();
                }
                continue;
            }
            // 订单生成
            Order order = getOrder(userId, cart, product);

            // 保存订单
            try {
                DaoFactory.getOrderDao().save(order);
            } catch (SQLException e) {
                return "Error" + e.getMessage();
            }

            cart.setCartHealth(2);
            try {
                DaoFactory.getCartDao().update(cart);
            } catch (SQLException e) {
                return "Error" + e.getMessage();
            }
        }

        return "Success";
    }

    /**
     * 生成订单
     * @param userId
     * @param cart
     * @param product
     * @return
     */
    private static Order getOrder(int userId, Cart cart, Product product) {
        Order order = new Order();
        order.setActiveId(userId);
        order.setPassiveId(product.getMerchantId());
        order.setProductId(product.getProductId());
        order.setProductNumber(cart.getProductQuantity());
        order.setProductUnitPrice(product.getPrice());
        order.setProductTotalPrice(String.valueOf(new BigDecimal(product.getPrice()).multiply(new BigDecimal(cart.getProductQuantity()))));
        order.setOrderStatus(String.valueOf(OrderStatus.DOING));
        order.setDescription("Not yet paid");
        return order;
    }

    @Override
    public String viewUserOrder(int userId) {
        // 查看个人正在进行订单
        List<Order> orders = DaoFactory.getOrderDao().listByActiveIdAndCartHealth(userId, "1");
        if (orders == null || orders.isEmpty()) {
            return "Order is not exist";
        }

        JSONArray jsonArray = new JSONArray();
        for (Order order : orders) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("order_id", order.getOrderId());
            jsonObject.put("active_id", order.getActiveId());
            jsonObject.put("passive_id", order.getPassiveId());
            jsonObject.put("product_id", order.getProductId());
            jsonObject.put("product_number", order.getProductNumber());
            jsonObject.put("product_unit_price", order.getProductUnitPrice());
            jsonObject.put("product_totle_price", order.getProductTotalPrice());
            jsonObject.put("description", order.getDescription());
            jsonObject.put("order_status", order.getOrderStatus());

            jsonArray.add(jsonObject);
        }

        return jsonArray.toJSONString();
    }

    @Override
    public String cancellationUserOrder(int orderId, int userId) {
        // 申请退回订单
        // 先找到订单
        Order order = DaoFactory.getOrderDao().getByOrderId(orderId);
        if (order == null) {
            return "Order is not exist.";
        }

        // 核实权限
        if (order.getActiveId() != userId) {
            return "Order is not the user's.";
        }

        // 确定是否可以变为待确定退货状态
        if (order.getOrderStatus().equals(String.valueOf(OrderStatus.APPLY_RETURN))) {
            return "No need to perform this operation";
        }else if (order.getOrderStatus().equals(String.valueOf(OrderStatus.SURE_RETURN))) {
            return "No need to perform this operation";
        }
//        else if (order.getOrderStatus().equals(String.valueOf(OrderStatus.WAITING_FOR_RECEIPT))) {
//            return "No need to perform this operation";
//        }


        // 把订单变为待确定退货状态
        order.setOrderStatus(String.valueOf(OrderStatus.APPLY_RETURN));

        try {
            DaoFactory.getOrderDao().update(order);
        } catch (SQLException e) {
            return "Error" + e.getMessage();
        }


        return "Success";
//        // 将货物返回到商家处
//        // 找到货物
//        Product byProductId = DaoFactory.getProductDao().getByProductId(order.getProductId());
//        if (byProductId == null) {
//            return "Product is not exist.";
//        }
//
//        // 找到商家
//        Merchant byMerchantId = DaoFactory.getMerchantDao().getByMerchantId(order.getPassiveId());
//        if (byMerchantId == null) {
//            return "Merchant is not exist.";
//        }
//
//        // 退货
//        byProductId.setStock(String.valueOf(new BigDecimal(byProductId.getStock()).add(new BigDecimal(order.getProductNumber()))));

    }

    @Override
    public String paymentOrder(int orderId, int userId) {
        // 支付订单
        // 获取 订单 和 用户 对象
        Order order = DaoFactory.getOrderDao().getByOrderId(orderId);
        User user = DaoFactory.getUserDao().getById(userId);
        // 这里应该开启事务, 进行锁
        // 获取用户账户对象
        Fund activeFund = DaoFactory.getFundDao().getByUserId(userId);
        Merchant merchant = DaoFactory.getMerchantDao().getByMerchantId(order.getPassiveId());
        Fund passiveFund = DaoFactory.getFundDao().getByUserId(merchant.getUserId());
        // 确定钱够不够
        BigDecimal availableFund = new BigDecimal(activeFund.getAvailableFunds());
        BigDecimal amout = new BigDecimal(order.getProductTotalPrice());
        BigDecimal totleFund = new BigDecimal(activeFund.getTotalFunds());

        if (availableFund.compareTo(amout) > 0) {
            // 钱够

            // 修改付款账户
            availableFund = availableFund.subtract(amout);
            totleFund = totleFund.subtract(amout);
            activeFund.setAvailableFunds(String.valueOf(availableFund));
            activeFund.setTotalFunds(String.valueOf(totleFund));

            // 修改订单状态
            order.setOrderStatus(String.valueOf(OrderStatus.WAITING_FOR_RECEIPT));

            // 生成资金流
            FundFlow fundFlow = new FundFlow();
            fundFlow.setOrderId(orderId);
            fundFlow.setActiveId(activeFund.getFundId());
            fundFlow.setPassiveId(passiveFund.getFundId());
            fundFlow.setAmount(amout);
            fundFlow.setFundflowStatus(String.valueOf(FundFlowStatus.IN_ORDER));

            // 更新到数据库中
            try {
                DaoFactory.getFundFlowDao().save(fundFlow);
                DaoFactory.getFundDao().update(activeFund);
                DaoFactory.getOrderDao().update(order);
            } catch (SQLException e) {
                return "Error" + e.getMessage();
            }
            return "Success";
        }else {
            return "Insufficient funds";
        }
    }

    @Override
    public String viewUserActiveFundFlows(int userId) {
        // 查看个人转出资金流
        User user = DaoFactory.getUserDao().getById(userId);

        List<FundFlow> fundFlows = DaoFactory.getFundFlowDao().listByActiveId(user.getFundId());

        JSONArray jsonArray = new JSONArray();

        for (FundFlow fundFlow : fundFlows) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("fund_flow_id", fundFlow.getFundFlowId());
            jsonObject.put("order_id", fundFlow.getOrderId());
            jsonObject.put("active_id", fundFlow.getActiveId());
            jsonObject.put("passive_id", fundFlow.getPassiveId());
            jsonObject.put("amount", fundFlow.getAmount());
            jsonObject.put("change_type", fundFlow.getChangeType());
            jsonObject.put("fundflow_status", fundFlow.getFundflowStatus());

            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    @Override
    public String viewUserPassiveFundFlows(int userId) {
        // 查看个人转入资金流
        User user = DaoFactory.getUserDao().getById(userId);

        List<FundFlow> fundFlows = DaoFactory.getFundFlowDao().listByPassiveId(user.getFundId());

        JSONArray jsonArray = new JSONArray();

        for (FundFlow fundFlow : fundFlows) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("fund_flow_id", fundFlow.getFundFlowId());
            jsonObject.put("order_id", fundFlow.getOrderId());
            jsonObject.put("active_id", fundFlow.getActiveId());
            jsonObject.put("passive_id", fundFlow.getPassiveId());
            jsonObject.put("amount", fundFlow.getAmount());
            jsonObject.put("change_type", fundFlow.getChangeType());
            jsonObject.put("fundflow_status", fundFlow.getFundflowStatus());

            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    /**
     * 废案
     */
    public void cancellationFundFlow() {
        // 退回已完成订单
        // 废案
        // 原因: 资金依附于物品, 此处可以用退回订单来代替
    }

    @Override
    public String conductComment(int userId, int orderId, String commentDetail, int isPositive) {
        // 对订单进行评价
        Order order = DaoFactory.getOrderDao().getByOrderId(orderId);
        if (order == null) {
            return "Order is not exist.";
        }else if (order.getActiveId() != userId) {
            return "Order is not the user.";
        }

        Comment comment = new Comment();
        comment.setIsPositive(isPositive);
        comment.setUserId(userId);
        comment.setOrderId(orderId);
        comment.setProductId(order.getProductId());
        comment.setMerchantId(order.getPassiveId());
        comment.setCommentDetail(commentDetail);

        try {
            DaoFactory.getCommentDao().save(comment);
        } catch (SQLException e) {
            return "Error" + e.getMessage();
        }

        return "Success";

    }

    @Override
    public String viewUserCommentForOrder(int userId, int orderId) {
        // 查看个人对该订单评价
        List<Comment> comments = DaoFactory.getCommentDao().listByOrderId(orderId);
        Comment comment = null;
        for (Comment comment1 : comments) {
            if (comment1.getUserId() == userId) {
                comment = comment1;
                break;
            }
        }
        if (comment != null){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("comment_id", comment.getCommentId());
            jsonObject.put("comment_user_id", comment.getUserId());
            jsonObject.put("order_id", comment.getOrderId());
            jsonObject.put("product_id", comment.getProductId());
            jsonObject.put("merchant_id", comment.getMerchantId());
            jsonObject.put("comment_detail", comment.getCommentDetail());
            jsonObject.put("Is_positive", comment.getIsPositive());
            return jsonObject.toJSONString();
        }else {
            return "You haven't given a review yet";
        }
    }

    public void signIn(int userId) {
        // 签到
        // 找到user
        // 比对签到日期
        // 增加签到金额
        User user = DaoFactory.getUserDao().getById(userId);
    }

    @Override
    public String applyToIncreasePoints(int userId, String amount) {
        // 发起充值请求
        FundFlow fundFlow = new FundFlow();
        // 正则表达式
        String regex = "[+-]?(\\d+\\.?\\d*|\\.\\d+)([Ee][+-]?\\d+)?";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regex);
        // 创建 Matcher 对象
        Matcher matcher = pattern.matcher(amount);

        if (!matcher.matches()) {
            return "Amouut is wrong.";
        }

        User user = DaoFactory.getUserDao().getById(userId);

        fundFlow.setAmount(new BigDecimal(amount));
        fundFlow.setFundflowStatus(String.valueOf(FundFlowStatus.WAIT_FOR_ADMIN));
        fundFlow.setActiveId(0);
        fundFlow.setPassiveId(user.getFundId());
        fundFlow.setOrderId(null);
        fundFlow.setChangeType("Recharge");

        try {
            DaoFactory.getFundFlowDao().save(fundFlow);
        } catch (SQLException e) {
            return "Error" + e.getMessage();
        }

        return "Success";

    }

}
