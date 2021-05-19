/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sabhis.Library_Management.controllers.user;

import com.sabhis.Library_Management.models.CmnUserDetails;
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
import org.json.simple.JSONObject;

/**
 *
 * @author sabhis231
 */
@WebServlet(name = "UpdateProfile", urlPatterns = {"/UpdateProfile"})
public class UpdateProfile extends HttpServlet {

    JSONObject object = new JSONObject();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        HttpSession httpsession = request.getSession(true);
        int userId = Integer.parseInt(httpsession.getAttribute("UserId").toString());
        try {
            String name = request.getParameter("name").trim();
            String emailId = request.getParameter("emailId").trim();
            String dob = request.getParameter("dob").trim();
            String city = request.getParameter("city").trim();
            String country = request.getParameter("country").trim();
            String zipCode = request.getParameter("zipCode").trim();
            String about = request.getParameter("about").trim();
            String gender = request.getParameter("gender").trim();

            DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            Date dobDate = format.parse(dob);

            CmnUserDetails cud = new CmnUserDetails();

            cud.setName(name);
            cud.setEmailId(emailId);
            cud.setDateOfBirth(dobDate);
            cud.setCity(city);
            cud.setCounty(country);
            cud.setZipcode(zipCode);
            cud.setAbout(about);
            cud.setGender(gender);

            cud = DBServices.updateProfile(cud, userId);
            httpsession.setAttribute("UserId", cud.getUserId());
            httpsession.setAttribute("ImagePath", cud.getImagePath());
            httpsession.setAttribute("UserName", cud.getName().trim());
            httpsession.setAttribute("Role", cud.getRoleId().getRoleName());
            httpsession.setAttribute("PrimaryEmail", cud.getEmailId());
            object.put("responseCode", 1);
            out.println(object);
        } catch (Exception ex) {
            System.out.println(ex);
            object.put("responseCode", 0);
            out.println(object);
        }

    }
}
