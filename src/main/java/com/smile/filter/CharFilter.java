package com.smile.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class CharFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        //处理post请求的乱码问题
        request.setCharacterEncoding("utf-8");
        //设置响应数据类型
        response.setContentType("text/html;charset=utf-8");
        //放行
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
