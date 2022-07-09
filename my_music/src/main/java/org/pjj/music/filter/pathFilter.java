package org.pjj.music.filter;

import org.pjj.music.utils.Const;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤所有请求, 判断是否登录, 登录则允许访问, 否则不许访问
 * @author PengJiaJun
 * @Date 2021/11/16 19:02
 */
//@WebFilter(filterName = "pathFilter", urlPatterns = "/*")
public class pathFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("##############过滤器初始化##############");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if(request.getRequestURI().equals("/admin/login/status")){
            chain.doFilter(request,servletResponse);//放行
        }
        if(request.getRequestURI().equals("/user/login/status")){
            chain.doFilter(request,servletResponse);//放行
        }

        if(request.getSession().getAttribute(Const.NAME) == null || "".equals(request.getSession().getAttribute(Const.NAME))){
            System.out.println("没登录的请求" + request.getRequestURL().toString());
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            //response.sendRedirect("http://localhost:8080/");//登录页面
            //... 这怎么跳转页面...
        }else{
            chain.doFilter(request,servletResponse);//放行
        }

    }

    @Override
    public void destroy() {
        System.out.println("##############过滤器销毁##############");
    }

}
