package com.sabhis.Library_Management.controllers.user;

import com.sabhis.Library_Management.models.CmnUserDetails;
import com.sabhis.Library_Management.services.DBServices;
import org.json.simple.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DoAuth", value = "/DoAuth")
public class DoAuth extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        JSONObject jsonobject = new JSONObject();
        PrintWriter out = response.getWriter();
        try {
            HttpSession httpsession = request.getSession(true);
            CmnUserDetails cmnuserdetails = new CmnUserDetails();
            String UserName = request.getParameter("userName");
            String Password = request.getParameter("password");
            cmnuserdetails.setEmailId(UserName);
            cmnuserdetails.setPassword(Password);
            cmnuserdetails = DBServices.doauth(cmnuserdetails);
            if (cmnuserdetails != null) {
                jsonobject.put("responseCode", 1);
                jsonobject.put("doauth", true);
                jsonobject.put("role", cmnuserdetails.getRoleId().getRoleName());
                httpsession.setAttribute("UserId", cmnuserdetails.getUserId());
                httpsession.setAttribute("ImagePath", cmnuserdetails.getImagePath());
                httpsession.setAttribute("UserName", cmnuserdetails.getName().trim());
                httpsession.setAttribute("Role", cmnuserdetails.getRoleId().getRoleName());
                httpsession.setAttribute("PrimaryEmail", cmnuserdetails.getEmailId());
                String ipAddress = request.getHeader("X-FORWARDED-FOR");
                if (ipAddress == null) {
                    ipAddress = request.getRemoteAddr();
                }

//                logger.info("LoginSuccess - " + "Ignite" + cmnuserdetails.getIgniteId() + " | " + cmnuserdetails.getPrimaryEmail() + " @ " + ipAddress);
            } else {
                jsonobject.put("responseCode", 0);
                jsonobject.put("doauth", false);
//                logger.warn("login_failed - " + UserName);
            }

            out.println(jsonobject);
        } catch (Exception ex) {
//            logger.error("err_auth - " + ex);
            out.println("{responseCode: 0}");
        }
    }
}
