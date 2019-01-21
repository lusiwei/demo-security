package com.lusiwei.web.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author: lusiwei
 * @Date: 2019/1/20 20:20
 * @Description:
 */
//@Component
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter init 方法执行");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long startTime = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("filter执行时间"+ (System.currentTimeMillis()-startTime));
    }

    @Override
    public void destroy() {
        System.out.println("filter destroy 执行");
    }
}
