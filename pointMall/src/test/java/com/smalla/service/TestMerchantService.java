package com.smalla.service;

import com.smalla.factory.ServiceFactory;
import org.junit.Test;

public class TestMerchantService {
    private final MerchantService merchantService = ServiceFactory.getMerchantService();
    @Test
    public void Test_insertMerchantProduct(){
//        insertMerchantProduct
        System.out.println(merchantService.insertMerchantProduct(2, "Test_insertMerchantProduct", 2, "100", "100", 1));
    }

    @Test
    public void Test_viewMerchantProduct() {
//        viewMerchantProduct
        System.out.println(merchantService.viewMerchantProduct(2));
    }

    @Test
    public void Test_updateProduct() {
//        updateProduct
        System.out.println(merchantService.updateProduct(10, 2, "updateProduct", 2, "88", "88", 1));
    }

    @Test
    public void Test_viewMerchantInformation() {
//        viewMerchantInformation
        System.out.println(merchantService.viewMerchantInformation(2));
    }

    @Test
    public void Test_updateMerchantInformation() {
//        updateMerchantInformation
        System.out.println(merchantService.updateMerchantInformation(2, "updateMerchantInformation"));
    }

}
