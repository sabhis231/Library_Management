/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sabhis.Library_Management.controllers.admin;

import com.sabhis.Library_Management.models.AuthorDetails;
import com.sabhis.Library_Management.models.BookDetails;
import com.sabhis.Library_Management.models.PublisherDetails;
import com.sabhis.Library_Management.services.DBServices;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "EditOrSaveBook", urlPatterns = {"/EditOrSaveBook"})
public class EditOrSaveBook extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        try {
            
            HttpSession httpsession = request.getSession(true);
            int userId = Integer.parseInt(httpsession.getAttribute("UserId").toString());
            String bookId = request.getParameter("bookId").trim();
            String booktitle = request.getParameter("booktitle").trim();
            String authorId = request.getParameter("authorList").trim();
            String publisherId = request.getParameter("publisherList").trim();
            String publishedYear = request.getParameter("publishedYear").trim();
            String totalPages = request.getParameter("totalPages").trim();
            String description = request.getParameter("description").trim();
            
            DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            Date publishedDate = format.parse(publishedYear);
            
            BookDetails bd = new BookDetails();
            if (!bookId.equalsIgnoreCase("0")) {
                bd.setBookId(Integer.parseInt(bookId));
            }
            bd.setBookTitle(booktitle);
            bd.setAuthorId(new AuthorDetails(Integer.parseInt(authorId)));
            bd.setPublisherId(new PublisherDetails(Integer.parseInt(publisherId)));
            bd.setPublishedYear(publishedDate);
            bd.setTotalPage(Integer.parseInt(totalPages));
            bd.setDescriptiion(description);
            bd.setModifiedOn(new Date());
            
            out.println(DBServices.editBookByBookId(bd, userId));
        } catch (Exception ex) {
            System.out.println(ex);
            out.println("{responseCode:0}");
        }
    }
}
