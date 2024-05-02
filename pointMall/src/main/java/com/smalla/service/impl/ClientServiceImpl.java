package com.smalla.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smalla.factory.DaoFactory;
import com.smalla.po.*;
import com.smalla.service.ClientService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

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
        order.setOrderStatus("1");
        order.setDescription("Not yet paid");
        return order;
    }

    public void viewUserOrder() {
        // 查看个人正在进行订单

    }

    public void cancellationUserOrder() {
        // 退回订单

    }

    public void viewUserFundFlows() {
        // 查看个人完成订单和资金流

    }

    public void cancellationFundFlow() {
        // 退回已完成订单

    }

    public void conductComment() {
        // 对订单进行评价

    }

    public void viewUserCommentForOrder() {
        // 查看个人对该订单评价

    }

    public void signIn() {
        // 签到

    }

}
