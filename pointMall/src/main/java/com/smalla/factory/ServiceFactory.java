package com.smalla.factory;

import com.smalla.service.AdminService;
import com.smalla.service.ClientService;
import com.smalla.service.impl.AdminServiceImpl;
import com.smalla.service.impl.ClientServiceImpl;
import com.smalla.service.impl.MerchantServiceImpl;
import com.smalla.service.impl.TouristServiceImpl;
import com.smalla.service.MerchantService;
import com.smalla.service.TouristService;

/**
 * @author Sardh
 */
public class ServiceFactory {
    private static final AdminService adminService = AdminServiceImpl.getInstance();
    private static final TouristService touristService = TouristServiceImpl.getInstance();
    private static final ClientService clientService = ClientServiceImpl.getInstance();
    private static final MerchantService merchantService = MerchantServiceImpl.getInstance();

    static public AdminService getAdminService() {
        return adminService;
    }

    static public TouristService getTouristService() {
        return touristService;
    }

    static public ClientService getClientService() {
        return clientService;
    }

    static public MerchantService getMerchantService() {
        return merchantService;
    }
}
