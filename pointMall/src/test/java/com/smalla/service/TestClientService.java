package com.smalla.service;

import com.smalla.factory.ServiceFactory;
import org.junit.Test;

public class TestClientService {

    private ClientService clientService = ServiceFactory.getClientService();

    @Test
    public void Test_viewUserInformation() {
        System.out.println(clientService.viewUserInformation(2));
    }

    @Test
    public void Test_updateUserInformation() {
        System.out.println(clientService.updateUserInformation(2, "Test_updateUserInformation", "Test_updateUserInformation", "Test_updateUserInformation"));
    }

    @Test
    public void Test_viewUserAddress() {
        System.out.println(clientService.viewUserAddress(10017));
    }

    @Test
    public void Test_updateUserAddress() {
        System.out.println(clientService.updateUserAddress(2, "Test_updateUserAddress", "1111111"));
    }

    @Test
    public void Test_insertUserAddress() {
        System.out.println(clientService.insertUserAddress(10017, "Test_updateUserAddress", "1111111"));
    }

    @Test
    public void Test_setDefaultAddress() {

        System.out.println(clientService.setDefaultAddress(10017, 11));
    }

    @Test
    public void Test_addProductToCart() {
        // 将商品添加到购物车
        System.out.println(clientService.addProductToCart(10017, 3, 10));
    }

    @Test
    public void Test_viewUserCart() {
        // 查看个人购物车
        System.out.println(clientService.viewUserCart(10017));
    }

    @Test
    public void Test_deleteProductFromCart() {
        // 删除个人购物车, 注意只是作废该购物车, 后续可以查找记录
        System.out.println(clientService.deleteProductFromCart(9, 10017));
    }

    @Test
    public void Test_updateProductFromCart() {
        // 修改个人购物车

        System.out.println(clientService.updateProductFromCart(10, 10017, 1));
    }

    @Test
    public void Test_settlementUserAllCart() {
        // 结算个人全部购物车

        System.out.println(clientService.settlementUserAllCart(10017));
    }



}
