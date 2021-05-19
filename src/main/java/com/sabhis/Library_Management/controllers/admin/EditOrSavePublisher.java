/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sabhis.Library_Management.controllers.admin;

import com.sabhis.Library_Management.models.PublisherDetails;
import com.sabhis.Library_Management.services.DBServices;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "EditOrSavePublisher", urlPatterns = {"/EditOrSavePublisher"})
public class EditOrSavePublisher extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        try {
            
            HttpSession httpsession = request.getSession(true);
            int userId = Integer.parseInt(httpsession.getAttribute("UserId").toString());
            String publisherId = request.getParameter("publisherId").trim();
            String publisherName = request.getParameter("publisherName").trim();
            String description = request.getParameter("description").trim();
            
           
            
            PublisherDetails pd = new PublisherDetails();
            if (!publisherId.equalsIgnoreCase("0")) {
                pd.setPublisherId(Integer.parseInt(publisherId));
            }
            pd.setPublisherName(publisherName);
            pd.setDescription(description);
            pd.setModifiedOn(new Date());
            pd.setCreatedOn(new Date());
            
            out.println(DBServices.editPublisherByPublishId(pd,userId));
        } catch (Exception ex) {
            System.out.println(ex);
            out.println("{responseCode:0}");
        }
    }

}
