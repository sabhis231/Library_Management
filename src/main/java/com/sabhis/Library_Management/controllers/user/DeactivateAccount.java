/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sabhis.Library_Management.controllers.user;

import com.sabhis.Library_Management.services.DBServices;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;

/**
 *
 * @author sabhis231
 */
@WebServlet(name = "DeactivateAccount", urlPatterns = {"/DeactivateAccount"})
public class DeactivateAccount extends HttpServlet {

    JSONObject object = new JSONObject();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        HttpSession httpsession = request.getSession(true);
        int userId = Integer.parseInt(httpsession.getAttribute("UserId").toString());
        try {
            int result = DBServices.deactivateAccount(userId);
            if (result == 1) {
                response.setHeader("Cache-Control", "no-cache");
                response.setHeader("Pragma", "no-cache");
                response.setDateHeader("Expires", 0);
                response.setDateHeader("Expires", 0);
                request.getSession(false).invalidate();
//                response.sendRedirect(request.getContextPath());
                object.put("responseCode", 1);
                object.put("path", request.getContextPath());
                out.println(object);
            } else if (result == 2) {
                object.put("responseCode", 2);
                object.put("msg", "Please return book before Deactivation");
                out.println(object);
            } else {
//                out.println("{responseCode:3}");
                object.put("responseCode", 3);
                object.put("msg", "Please try after some time");
                out.println(object);
            }

        } catch (Exception ex) {
            System.out.println(ex);
            object.put("responseCode", 0);
            object.put("msg", "Please try after some time");
            out.println(object);
//            out.println("{responseCode:0}");
        }

    }
}
