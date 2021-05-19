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

/**
 *
 * @author sabhis231
 */
@WebServlet(name = "RenewBook", urlPatterns = {"/RenewBook"})
public class RenewBook extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        HttpSession httpsession = request.getSession(true);
        int userId = Integer.parseInt(httpsession.getAttribute("UserId").toString());

        int bookId = Integer.parseInt(request.getParameter("bookId"));

        try {
            out.println(DBServices.renewBook(userId, bookId));
        } catch (Exception ex) {
            System.out.println(ex);
            out.println("{responseCode:0}");
        }
    }
}
