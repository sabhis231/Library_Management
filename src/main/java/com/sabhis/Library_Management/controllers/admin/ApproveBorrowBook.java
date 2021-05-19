/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sabhis.Library_Management.controllers.admin;

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
@WebServlet(name = "ApproveBorrowBook", urlPatterns = {"/ApproveBorrowBook"})
public class ApproveBorrowBook extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        HttpSession httpsession = request.getSession(true);

        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            int bookId = Integer.parseInt(request.getParameter("bookId"));

            out.println(DBServices.approveBorrowBook(userId, bookId));
        } catch (Exception ex) {
            System.out.println(ex);
            out.println("{responseCode:0}");
        }
    }

}
