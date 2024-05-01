package com.smalla.po;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestReturnResult {
    @Test
    public void testSuccessResult() {
        ReturnResult result = new ReturnResult();
        String successData = "这是成功的测试数据";
        String successResponse = result.successResult(successData);

        System.out.println(successResponse);

        assertTrue(successResponse.contains("\"status\":200"));
        assertTrue(successResponse.contains("\"message\":\"处理成功\""));
        assertTrue(successResponse.contains("\"returnData\":\"这是成功的测试数据\""));
    }

    @Test
    public void testErrorResult() {
        ReturnResult result = new ReturnResult();
        String errorData = "这是失败的测试数据";
        String errorResponse = result.errorResult(errorData);

        System.out.println(errorResponse);

        assertTrue(errorResponse.contains("\"status\":201"));
        assertTrue(errorResponse.contains("\"message\":\"处理失败\""));
        assertTrue(errorResponse.contains("\"returnData\":\"这是失败的测试数据\""));
    }

    @Test
    public void testSetAndGetMethods() {
        ReturnResult result = new ReturnResult();
        result.setStatus(200);
        result.setMessage("测试信息");
        result.setReturnData("测试数据");

        assertEquals(Integer.valueOf(200), result.getStatus());
        assertEquals("测试信息", result.getMessage());
        assertEquals("测试数据", result.getReturnData());
    }
}
