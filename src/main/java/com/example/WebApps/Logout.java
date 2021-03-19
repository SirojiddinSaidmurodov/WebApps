package com.example.WebApps;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "logout", value = "/logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        resp.addCookie(new Cookie("user", null));
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/session");
        requestDispatcher.forward(req, resp);
    }
}
