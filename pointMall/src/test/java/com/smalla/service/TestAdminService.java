package com.smalla.service;

import com.smalla.factory.DaoFactory;
import com.smalla.factory.ServiceFactory;
import org.junit.Test;

public class TestAdminService {
    @Test
    public void viewUserByAdmin() {
        String s = ServiceFactory.getAdminService().viewUserByAdmin();
        System.out.println(s);
    }

    @Test
    public void updateUserByAdmin() {
        System.out.println(ServiceFactory.getAdminService().updateUserByAdmin(10003, "updateUserByAdmin", "updateUserByAdmin", "updateUserByAdmin"));
        System.out.println(DaoFactory.getUserDao().listById(10002).get(0));
    }

    @Test
    public void viewMerchantByAdmin() {
        String s = ServiceFactory.getAdminService().viewMerchantByAdmin();
        System.out.println(s);
    }

    @Test
    public void updateMerchantByAdmin() {
        System.out.println(ServiceFactory.getAdminService().updateMerchantByAdmin(2, "updateMerchantByAdmin", 0));
    }

    @Test
    public void viewProductByAdmin() {
        String s = ServiceFactory.getAdminService().viewProductByAdmin();
        System.out.println(s);
    }

    @Test
    public void updateProductByAdmin() {
        System.out.println(ServiceFactory.getAdminService().updateProductByAdmin(2, 0));
    }

    @Test
    public void viewCategoryByAdmin() {
        String s = ServiceFactory.getAdminService().viewCategoryByAdmin();
        System.out.println(s);
    }

    @Test
    public void insertCategoryByAdmin() {
        System.out.println(ServiceFactory.getAdminService().insertCategoryByAdmin("Music"));
    }

    @Test
    public void viewDailycheckinByAdmin() {
        System.out.println(ServiceFactory.getAdminService().viewDailycheckinByAdmin());
    }
}
