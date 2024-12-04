package com.lbms.config;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class CORSFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        // res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        res.setHeader("Access-Control-Allow-Headers", "Content-Type");
        chain.doFilter(request, response);
    }
}
