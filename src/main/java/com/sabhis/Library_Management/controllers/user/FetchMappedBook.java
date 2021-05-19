package com.sabhis.Library_Management.controllers.user;

import com.sabhis.Library_Management.services.DBServices;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "FetchMappedBook", value = "/FetchMappedBook")
public class FetchMappedBook extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        HttpSession httpsession = request.getSession(true);
        int userId = Integer.parseInt(httpsession.getAttribute("UserId").toString());
        try {
            out.println(DBServices.fetchMappedBook(userId));
        } catch (Exception ex) {
            System.out.println(ex);
            out.println("{responseCode:0}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
