package com.smalla.factory;

import com.smalla.dao.*;
import com.smalla.dao.impl.*;
import com.smalla.po.User;

/**
 * @author Sardh
 */
public class DaoFactory {

    private static final AddressDao addressDao = AddressDaoImpl.getInstance();
    private static final CartDao cartDao = CartDaoImpl.getInstance();
    private static final FundDao fundDao = FundDaoImpl.getInstance();
    private static final MerchantDao merchantDao = MerchantDaoImpl.getInstance();
    private static final OrderDao orderDao = OrderDaoImpl.getInstance();
    private static final ProductDao productDao = ProductDaoImpl.getInstance();
    private static final UserDao userDao = UserDaoImpl.getInstance();
    private static final CategoryDao categoryDao = CategoryDaoImpl.getInstance();
    private static final CommentDao commentDao = CommentDaoImpl.getInstance();
    private static final DailyCheckInDao dailyCheckInDao = DailyCheckInDaoImpl.getInstance();
    private static final FundFlowDao fundFlowDao = FundFlowDaoImpl.getInstance();

    static public CategoryDao getCategoryDao() {
        return categoryDao;
    }

    static public CommentDao getCommentDao() {
        return commentDao;
    }

    static public DailyCheckInDao getDailyCheckInDao() {
        return dailyCheckInDao;
    }

    static public FundFlowDao getFundFlowDao() {
        return fundFlowDao;
    }

    static public AddressDao getAddressDao() {
        return addressDao;
    }

    static public CartDao getCartDao() {
        return cartDao;
    }

    static public FundDao getFundDao() {
        return fundDao;
    }

    static public MerchantDao getMerchantDao() {
        return merchantDao;
    }

    static public OrderDao getOrderDao() {
        return orderDao;
    }

    static public ProductDao getProductDao() {
        return productDao;
    }

    static public UserDao getUserDao() {
        return userDao;
    }
}
