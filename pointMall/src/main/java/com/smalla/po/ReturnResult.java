package com.smalla.po;

import com.alibaba.fastjson.JSONObject;

/**
 * 统一返回结果集, 用于返回给页面结果
 * @author sardh
 */
public class ReturnResult {
    /**
     * 返回状态码
     */
    private Integer status;
    /**
     * 返回说明信息
     */
    private String message;
    /**
     * 返回数据
     */
    private String returnData;

    /**
     * 将统一返回结果集转化为json字符串
     * @param returnResult
     * @return
     */
    private String returnResultToJsonString(ReturnResult returnResult) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", returnResult.getStatus());
        jsonObject.put("message", returnResult.getMessage());
        jsonObject.put("returnData", returnResult.getReturnData());
        return jsonObject.toJSONString();
    }

    /**
     * 成功的返回结果
     * @param returnData
     * @return
     */
    public String successResult(String returnData){
        ReturnResult returnResult = new ReturnResult();
        returnResult.setStatus(200);
        returnResult.setMessage("处理成功");
        returnResult.setReturnData(returnData);
        return returnResultToJsonString(returnResult);
    }

    /**
     * 失败的返回结果
     * @param returnData
     * @return
     */
    public String errorResult(String returnData){
        ReturnResult returnResult = new ReturnResult();
        returnResult.setStatus(201);
        returnResult.setMessage("处理失败");
        returnResult.setReturnData(returnData);
        return returnResultToJsonString(returnResult);
    }

    public ReturnResult() {
    }

    public ReturnResult(Integer status, String message, String returnData) {
        this.status = status;
        this.message = message;
        this.returnData = returnData;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReturnData() {
        return returnData;
    }

    public void setReturnData(String returnData) {
        this.returnData = returnData;
    }
}
