package com.smile.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
            多个Servlet的访问，都要走当前的Service方法
            为了找到对应Servlet中的方法执行，我们首先要在这里，得到当前访问的Servlet的字节码对象Class
         */
        Class<? extends BaseServlet> clazz = this.getClass();
        //获取func的方法名
        String func = request.getParameter("func");
        try {
            //获取到要执行的方法对象
            Method method = clazz.getDeclaredMethod(func, HttpServletRequest.class, HttpServletResponse.class);
            //私有方法，要暴力反射，打通访问权限
            method.setAccessible(true);
            //调用方法
            method.invoke(this, request, response);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}








