package com.smalla.service;

import com.smalla.config.ConditionType;
import com.smalla.config.LoginMode;
import com.smalla.config.SortType;
import com.smalla.factory.ServiceFactory;
import org.junit.Test;

public class TestTouristService {

    @Test
    public void TestLogin(){
        String loginMode = String.valueOf(LoginMode.PHONE);
        String loginCredentials = "2";
        String password = "testPassword";
        System.out.println(ServiceFactory.getTouristService().login(loginMode, loginCredentials, password));
    }

    @Test
    public void TestRegister(){
        String username = "TestRegister";
        String password = "TestRegister";
        String phone = "TestRegister1";
        String defaultAddress = "TestRegister";
        System.out.println(ServiceFactory.getTouristService().register(username, password, phone, defaultAddress));
    }

    @Test
    public void TestViewProductByConditionAndSorting() {
        String conditionType = String.valueOf(ConditionType.NULL);
        String conditionDetail = "";
        String sortType = String.valueOf(SortType.SALES_VOLUME);
        System.out.println(ServiceFactory.getTouristService().viewProductByConditionAndSorting(conditionType, conditionDetail, sortType, 0, 9));
    }

    @Test
    public void TestViewProductDetails() {
        System.out.println(ServiceFactory.getTouristService().viewProductDetails(2));
    }

}
