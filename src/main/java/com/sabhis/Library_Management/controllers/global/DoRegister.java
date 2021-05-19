/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sabhis.Library_Management.controllers.global;

import com.sabhis.Library_Management.models.CmnUserDetails;
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
@WebServlet(name = "DoRegister", urlPatterns = {"/DoRegister"})
public class DoRegister extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        JSONObject jsonobject = new JSONObject();
        PrintWriter out = response.getWriter();
        try {
            HttpSession httpsession = request.getSession(true);
            CmnUserDetails cmnuserdetails = new CmnUserDetails();
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String confpwd = request.getParameter("confpwd");
            cmnuserdetails.setEmailId(userName);
            cmnuserdetails.setPassword(password);

            if (password.equals(confpwd)) {
                out.print(DBServices.registerUser(cmnuserdetails));
            } else {
                jsonobject.put("responseCode", 0);
                jsonobject.put("doauth", false);
                out.println(jsonobject);
            }

        } catch (Exception ex) {
//            logger.error("err_auth - " + ex);
            out.println("{responseCode: 0}");
        }
    }

}
