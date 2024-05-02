package com.smalla.service;

public interface ClientService {
    String viewUserInformation(int userId);

    String updateUserInformation(int userId, String username, String phone, String password);

    String viewUserAddress(int userId);

    String updateUserAddress(int addressId, String addressDetail, String phone);

    String insertUserAddress(int userId, String addressDetail, String phone);

    String setDefaultAddress(int userId, int addressId);

    String addProductToCart(int userId, int productId, int productQuantity);

    public String viewUserCart(int userId) ;

    String deleteProductFromCart(int cartId, int userId);

    String updateProductFromCart(int cartId, int userId, int productQuantity);

    String settlementUserAllCart(int userId);
}
