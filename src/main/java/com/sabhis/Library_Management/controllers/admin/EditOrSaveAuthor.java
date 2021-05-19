/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sabhis.Library_Management.controllers.admin;

import com.sabhis.Library_Management.models.AuthorDetails;
import com.sabhis.Library_Management.models.PublisherDetails;
import com.sabhis.Library_Management.services.DBServices;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
@WebServlet(name = "EditOrSaveAuthor", urlPatterns = {"/EditOrSaveAuthor"})
public class EditOrSaveAuthor extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        try {
//            authorId = 1 & authorName = Author % 201 % 20 & description = Test
            HttpSession httpsession = request.getSession(true);
            int userId = Integer.parseInt(httpsession.getAttribute("UserId").toString());
            String authorId = request.getParameter("authorId").trim();
            String authorName = request.getParameter("authorName").trim();
            String description = request.getParameter("description").trim();

            AuthorDetails ad = new AuthorDetails();
            if (!authorId.equalsIgnoreCase("0")) {
                ad.setAuthorId(Integer.parseInt(authorId));
            }
            ad.setAuthorName(authorName);
            ad.setDescription(description);
            ad.setModifiedOn(new Date());
            ad.setCreatedOn(new Date());

            out.println(DBServices.editAuthorByAuthorId(ad, userId));
        } catch (Exception ex) {
            System.out.println(ex);
            out.println("{responseCode:0}");
        }

    }
}
