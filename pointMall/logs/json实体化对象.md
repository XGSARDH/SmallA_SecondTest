

### User类的json化
```java
class UserJson{
    public String userJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_id", user.getUserId());
        jsonObject.put("fund_id", user.getFundId());
        jsonObject.put("username", user.getUsername());
        jsonObject.put("password", user.getPassword());
        jsonObject.put("phone", user.getPhone());
        jsonObject.put("user_health", user.getUserHealth());
        jsonObject.put("is_merchant", user.getIsMerchant());
        jsonObject.put("default_address", user.getDefaultAddress());
    }
}
```

### Merchant类的json化
```java
class MerchantJson{
    public String merchantJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("merchant_id", merchant.getMerchantId());
        jsonObject.put("user_id", merchant.getUserId());
        jsonObject.put("fund_id", merchant.getFundId());
        jsonObject.put("merchant_name", merchant.getMerchantName());
        jsonObject.put("merchant_health", merchant.getMerchantHealth());
    }
}
```

### Product类的json化
```java
class ProductJson{
    public String productJson(){
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
    }
}
```

### Category类的json化
```java
class CategoryJson{
    public String CategoryJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("product_id", product.getProductId());
        jsonObject.put("merchant_id", product.getMerchantId());
        jsonObject.put("product_name", product.getProductName());
        jsonObject.put("category_id", product.getCategoryId());
        jsonObject.put("price", product.getPrice());
        jsonObject.put("stock", product.getStock());
        jsonObject.put("product_health", product.getProductHealth());
    }
}
```

### Dailycheckin类的json化
```java
class DailycheckinJson{
    public String dailycheckinJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("check_in_date", allCheckIn.getCheckInDate());
        jsonObject.put("number_of_people", allCheckIn.getNumberOfPeople());
    }
}
```

### Address类的json化
```java
class AddressJson{
    public String addressJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("address_id", address.getAddressId());
        jsonObject.put("user_id", address.getUserId());
        jsonObject.put("phone", address.getPhone());
        jsonObject.put("address", address.getAddress());
    }
}
```

### Comment类的json化
```java
class CommentJson{
    public String commentJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("comment_id", comment.getCommentId());
        jsonObject.put("comment_user_id", comment.getCommentId());
        jsonObject.put("order_id", comment.getCommentId());
        jsonObject.put("product_id", comment.getCommentId());
        jsonObject.put("merchant_id", comment.getCommentId());
        jsonObject.put("comment_detail", comment.getCommentId());
        jsonObject.put("Is_positive", comment.getCommentId());
    }
}
```

### Order类的json化
```java
class OrderJson{
    public String orderJson(){
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
    }
}
```

### Comment类的json化
```java
class CommentJson{
    public String commentJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("comment_id", comment.getCommentId());
        jsonObject.put("comment_user_id", comment.getUserId());
        jsonObject.put("order_id", comment.getOrderId());
        jsonObject.put("product_id", comment.getProductId());
        jsonObject.put("merchant_id", comment.getMerchantId());
        jsonObject.put("comment_detail", comment.getCommentDetail());
        jsonObject.put("Is_positive", comment.getIsPositive());
    }
}
```

### Merchant类的json化
```java
class MerchantJson{
    public String merchantJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("merchant_id", merchant.getMerchantId());
        jsonObject.put("user_id", merchant.getUserId());
        jsonObject.put("fund_id", merchant.getFundId());
        jsonObject.put("merchant_name", merchant.getMerchantName());
        jsonObject.put("merchant_health", merchant.getMerchantHealth());
    }
}
```

### FundFlow类的json化
```java
class FundFlowJson{
    public String fundFlowJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fund_flow_id", fundFlow.getFundFlowId());
        jsonObject.put("order_id", fundFlow.getOrderId());
        jsonObject.put("active_id", fundFlow.getActiveId());
        jsonObject.put("passive_id", fundFlow.getPassiveId());
        jsonObject.put("amount", fundFlow.getAmount());
        jsonObject.put("change_type", fundFlow.getChangeType());
        jsonObject.put("fundflow_status", fundFlow.getFundflowStatus());
    }
}
```

