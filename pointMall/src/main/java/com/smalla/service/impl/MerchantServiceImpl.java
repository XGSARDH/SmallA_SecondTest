package com.smalla.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smalla.config.FundFlowStatus;
import com.smalla.config.OrderStatus;
import com.smalla.factory.DaoFactory;
import com.smalla.po.*;
import com.smalla.service.MerchantService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Sardh
 */
public class MerchantServiceImpl implements MerchantService {

    private static volatile MerchantServiceImpl instance;

    public static MerchantServiceImpl getInstance() {
        if (instance == null) {
            synchronized (ClientServiceImpl.class) {
                if (instance == null) {
                    instance = new MerchantServiceImpl();
                }
            }
        }
        return instance;
    }

    public MerchantServiceImpl() {
    }

    @Override
    public String insertMerchantProduct(int merchantId, String productName, int categoryId, String price, String stock, int productHealth){
        // 增加商户商品
        Product product = new Product();

        product.setMerchantId(merchantId);
        product.setProductName(productName);
        product.setCategoryId(categoryId);
        product.setPrice(price);
        product.setStock(stock);
        product.setProductHealth(productHealth);
        try {
            product.setProductId(DaoFactory.getProductDao().save(product));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Success";

    }

    @Override
    public String viewMerchantProduct(int merchantId){
        // 查看商户商品
        List<Product> products = DaoFactory.getProductDao().listByMerchantId(merchantId);

        JSONArray jsonArray = new JSONArray();
        for (Product product : products) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("product_id", product.getProductId());
            jsonObject.put("merchant_id", product.getMerchantId());
            jsonObject.put("product_name", product.getProductName());
            jsonObject.put("category_id", product.getCategoryId());
            jsonObject.put("price", product.getPrice());
            jsonObject.put("stock", product.getStock());
            jsonObject.put("product_health", product.getProductHealth());
            jsonObject.put("sales_volume", product.getSalesVolume());
            jsonObject.put("positive_reviews", product.getPositiveReviews());

            jsonArray.add(jsonObject);
        }

        return jsonArray.toJSONString();
    }

    @Override
    public String updateProduct(int productId, int merchantId, String productName, int categoryId, String price, String stock, int productHealth){
        // 更新商户商品
        Product product = DaoFactory.getProductDao().getByProductId(productId);
        if (product == null) {
            return "Product is not exist";
        }else if (product.getMerchantId() != merchantId) {
            return "Product is not the merchant";
        } else {
            product.setProductName(productName);
            product.setCategoryId(categoryId);
            product.setPrice(price);
            product.setStock(stock);
            product.setProductHealth(productHealth);
            try {
                DaoFactory.getProductDao().update(product);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return "Success";
        }
    }

    @Override
    public String viewMerchantInformation(int merchantId){
        // 查看商户信息
        Merchant merchant = DaoFactory.getMerchantDao().getByMerchantId(merchantId);

        if (merchant == null){
            return "Merchant is not exist";
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("merchant_id", merchant.getMerchantId());
        jsonObject.put("user_id", merchant.getUserId());
        jsonObject.put("fund_id", merchant.getFundId());
        jsonObject.put("merchant_name", merchant.getMerchantName());
        jsonObject.put("merchant_health", merchant.getMerchantHealth());

        return jsonObject.toJSONString();
    }

    @Override
    public String updateMerchantInformation(int merchantId, String merchantName){
        // 修改商户信息
        Merchant merchant = DaoFactory.getMerchantDao().getByMerchantId(merchantId);
        if (merchant == null) {
            return "Merchant is not exist";
        }else {
            merchant.setMerchantName(merchantName);
            try {
                DaoFactory.getMerchantDao().update(merchant);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return "Success";
        }
    }

    @Override
    public String viewCancellationOrder(int merchantId, int userId){
        // 查看退货请求
        Merchant byMerchantId = DaoFactory.getMerchantDao().getByMerchantId(merchantId);
        if (byMerchantId == null) {
            return "Merchant is not exist.";
        } else if(byMerchantId.getUserId() != userId) {
            return "Merchant is not the User.";
        }
        List<Order> orders = DaoFactory.getOrderDao().listByPassiveIdAndOrderStatus(merchantId, String.valueOf(OrderStatus.APPLY_RETURN));
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
    public String agreeCancellationOrder(int orderId, int merchantId, int userId){
        // 同意退货请求
        // 对商铺和操作者权限进行检验
        Merchant byMerchantId = DaoFactory.getMerchantDao().getByMerchantId(merchantId);
        if (byMerchantId == null) {
            return "Merchant is not exist.";
        } else if(byMerchantId.getUserId() != userId) {
            return "Merchant is not the User.";
        }
        Order order = DaoFactory.getOrderDao().getByOrderId(orderId);
        // 核对是否是申请退款状态
        if (!order.getOrderStatus().equals(String.valueOf(OrderStatus.APPLY_RETURN))) {
            return "The order needn't to handle";
        }
        // 查看是否有对应资金流
        FundFlow fundFlow = DaoFactory.getFundFlowDao().getByOrderId(orderId);

        // 如果有, 对资金流进行操作
        if (fundFlow != null) {
            if (fundFlow.getFundflowStatus().equals(String.valueOf(FundFlowStatus.IN_ORDER))) {
                // 资金流如果 在订单, 则从订单中退回

                // 退还转账人账户
                Fund activeFoud = DaoFactory.getFundDao().getByFundId(fundFlow.getActiveId());
                BigDecimal activeAvailableFunds = new BigDecimal(activeFoud.getAvailableFunds());
                BigDecimal activeTotleFunds = new BigDecimal(activeFoud.getTotalFunds());

                activeAvailableFunds = activeAvailableFunds.add(fundFlow.getAmount());
                activeTotleFunds = activeTotleFunds.add(fundFlow.getAmount());

                activeFoud.setAvailableFunds(String.valueOf(activeAvailableFunds));
                activeFoud.setTotalFunds(String.valueOf(activeTotleFunds));

                // 更改订单状态
                order.setOrderStatus(String.valueOf(OrderStatus.SURE_RETURN));

                try {
                    DaoFactory.getFundDao().update(activeFoud);
                    DaoFactory.getOrderDao().update(order);
                } catch (SQLException e) {
                    return "Error" + e.getMessage();
                }

            }else if (fundFlow.getFundflowStatus().equals(String.valueOf(FundFlowStatus.SOLVE))) {
                // 资金流如果 已经成功, 则从自己的资金中退回, 如果自己的资金不足, 就返回资金不足, 无法处理

                Fund passiveFoud = DaoFactory.getFundDao().getByFundId(fundFlow.getPassiveId());
                BigDecimal passiveAvailableFunds = new BigDecimal(passiveFoud.getAvailableFunds());
                BigDecimal passiveTotleFunds = new BigDecimal(passiveFoud.getTotalFunds());

                // 资金不足, 无法处理
                if (passiveAvailableFunds.compareTo(fundFlow.getAmount()) < 0) {
                    return "Insufficient funds, unable to refund";
                }

                // 转出收款人账户
                passiveAvailableFunds = passiveAvailableFunds.subtract(fundFlow.getAmount());
                passiveTotleFunds = passiveTotleFunds.subtract(fundFlow.getAmount());

                passiveFoud.setAvailableFunds(String.valueOf(passiveAvailableFunds));
                passiveFoud.setTotalFunds(String.valueOf(passiveTotleFunds));


                // 退还转账人账户
                Fund activeFoud = DaoFactory.getFundDao().getByFundId(fundFlow.getActiveId());
                BigDecimal activeAvailableFunds = new BigDecimal(activeFoud.getAvailableFunds());
                BigDecimal activeTotleFunds = new BigDecimal(activeFoud.getTotalFunds());

                activeAvailableFunds = activeAvailableFunds.add(fundFlow.getAmount());
                activeTotleFunds = activeTotleFunds.add(fundFlow.getAmount());

                activeFoud.setAvailableFunds(String.valueOf(activeAvailableFunds));
                activeFoud.setTotalFunds(String.valueOf(activeTotleFunds));

                // 更改订单状态
                order.setOrderStatus(String.valueOf(OrderStatus.SURE_RETURN));

                try {
                    DaoFactory.getFundDao().update(activeFoud);
                    DaoFactory.getFundDao().update(passiveFoud);
                    DaoFactory.getOrderDao().update(order);
                } catch (SQLException e) {
                    return "Error" + e.getMessage();
                }

            }
        }else {
            return "No need to handle";
        }

        // 货物退回到商户商品库存中
        Product byProductId = DaoFactory.getProductDao().getByProductId(order.getProductId());
        Integer stock = Integer.parseInt(byProductId.getStock()) + order.getProductNumber();
        byProductId.setStock(String.valueOf(stock));

        try {
            DaoFactory.getProductDao().update(byProductId);
        } catch (SQLException e) {
            return "Error" + e.getMessage();
        }

        return "Success";

    }

    @Override
    public String disagreeCancellationOrder(int orderId, int merchantId, int userId){
        // 拒绝退货请求
        // 对商铺和操作者权限进行检验
        Merchant byMerchantId = DaoFactory.getMerchantDao().getByMerchantId(merchantId);
        if (byMerchantId == null) {
            return "Merchant is not exist.";
        } else if(byMerchantId.getUserId() != userId) {
            return "Merchant is not the User.";
        }
        Order order = DaoFactory.getOrderDao().getByOrderId(orderId);
        // 核对是否是申请退款状态
        if (!order.getOrderStatus().equals(String.valueOf(OrderStatus.APPLY_RETURN))) {
            return "The order needn't to handle";
        }
        // 查看是否有对应资金流
        FundFlow fundFlow = DaoFactory.getFundFlowDao().getByOrderId(orderId);

        if (fundFlow == null) {
            order.setOrderStatus(String.valueOf(OrderStatus.DOING));
            try {
                DaoFactory.getOrderDao().update(order);
            } catch (SQLException e) {
                return "Error" + e.getMessage();
            }
            return "Success";
        } else if (fundFlow.getFundflowStatus().equals(String.valueOf(FundFlowStatus.IN_ORDER))) {
            // 如果资金还在订单中, 将订单状态恢复为等待收货
            order.setOrderStatus(String.valueOf(OrderStatus.WAITING_FOR_RECEIPT));
            try {
                DaoFactory.getOrderDao().update(order);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return "Success";
        }else if (fundFlow.getFundflowStatus().equals(String.valueOf(FundFlowStatus.SOLVE))) {
            // 如果资金已经成功交易, 将订单状态恢复为成功交易
            order.setOrderStatus(String.valueOf(OrderStatus.SOLVE));
            try {
                DaoFactory.getOrderDao().update(order);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return "Success";
        }else {
            return "No need to handle";
        }
    }

}
