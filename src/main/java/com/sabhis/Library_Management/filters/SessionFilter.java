/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sabhis.Library_Management.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sabhis231
 */
public class SessionFilter implements Filter {
//        System.out.println("dbfjh");

    @Override
    public void init(FilterConfig config) throws ServletException {
        // If you have any <init-param> in web.xml, then you could get them
        // here by config.getInitParameter("name") and assign it as field.
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession httpsession = request.getSession(true);
        String url = request.getRequestURL().toString();
      
        String[] Roles = {"admin", "user", "ta"};
        String Role = String.valueOf(httpsession.getAttribute("Role"));
        boolean authreq = false;

        if (Role != null) {
            for (int i = 0; i < Roles.length; i++) {
                if (url.contains("/views/" + Roles[i]) && Role.equals(Roles[i])) {
                    authreq = true;
                    break;
                }
            }
        }

        //Logout Filtering
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setDateHeader("Expires", 0); // Proxies
        if (!authreq) {
            response.sendRedirect(request.getContextPath());

            return;
        }

        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
    }

}
