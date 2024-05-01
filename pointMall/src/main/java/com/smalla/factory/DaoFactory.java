package com.smalla.factory;

import com.smalla.dao.*;
import com.smalla.dao.impl.*;
import com.smalla.po.User;

/**
 * @author Sardh
 */
public class DaoFactory {

    private final AddressDao addressDao = AddressDaoImpl.getInstance();
    private final CartDao cartDao = CartDaoImpl.getInstance();
    private final FundDao fundDao = FundDaoImpl.getInstance();
    private final MerchantDao merchantDao = MerchantDaoImpl.getInstance();
    private final OrderDao orderDao = OrderDaoImpl.getInstance();
    private final ProductDao productDao = ProductDaoImpl.getInstance();
    private final UserDao userDao = UserDaoImpl.getInstance();

    public AddressDao getAddressDao() {
        return addressDao;
    }

    public CartDao getCartDao() {
        return cartDao;
    }

    public FundDao getFundDao() {
        return fundDao;
    }

    public MerchantDao getMerchantDao() {
        return merchantDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}
