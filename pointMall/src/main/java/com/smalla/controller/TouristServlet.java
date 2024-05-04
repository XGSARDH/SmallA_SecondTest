package com.smalla.controller;

import com.alibaba.fastjson.JSONObject;
import com.smalla.config.LoginMode;
import com.smalla.config.ServiceReturn;
import com.smalla.factory.ServiceFactory;
import com.smalla.po.ReturnResult;
import com.smalla.util.JwtUtils.JwtUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.smalla.util.getDataFromRequest.GetDataFromRequest.getRequestPostStr;

/**
 * @author Sardh
 */
@WebServlet("/TouristServlet")
public class TouristServlet extends BaseServlet{

    public void loginById(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String jsonObject = getRequestParameter(request).toJSONString();
        System.out.println(jsonObject);
        String userId = null;
//        userId = jsonObject.getString("login_credentials");
        String password = null;
//        password = jsonObject.getString("password");

        response.setContentType("application/json;charset=UTF-8");
        String status = ServiceFactory.getTouristService().login(String.valueOf(LoginMode.ID), userId, password);

        if (String.valueOf(ServiceReturn.Success).equals(status)) {
            // 生成JWT
            Map<String, Object> claims = new HashMap<>();
            claims.put("userId", userId);
            String token = JwtUtils.generateJwt(claims);

            // 将JWT发送给前端
            response.getWriter().write(ReturnResult.successResult(token));
        } else {
            response.getWriter().write(ReturnResult.errorResult(status));
        }

    }

}
