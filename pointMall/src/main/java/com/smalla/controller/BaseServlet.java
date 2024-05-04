package com.smalla.controller;


import com.alibaba.fastjson.JSONObject;
import com.smalla.util.getDataFromRequest.GetDataFromRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

import static com.smalla.util.getDataFromRequest.GetDataFromRequest.getRequestPostStr;

/**
 * @author Sardh
 */
@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try {
            // 获取请求标识
            JSONObject jsonObject = getRequestParameter(request);
            System.out.println(jsonObject);
            String methodName = jsonObject.getString("method");
            System.out.println("methodName: " + methodName);

            // 获取当前实例的类对象
            Class<? extends BaseServlet> cls = this.getClass();

            // 通过类的字节码对象获取方法的字节码对象
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            // 让方法执行
            method.invoke(this, request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * 便捷获取请求头中的数据
     * @param request
     * @return
     */
    public JSONObject getRequestParameter(HttpServletRequest request) {
        try {
            String requestPostStr = GetDataFromRequest.getRequestJsonString(request);
            JSONObject jsonObject = JSONObject.parseObject(requestPostStr);
            return jsonObject;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //这个方法在测试login时会发生异常报错
}

