package com.example.WebApps;

import com.example.WebApps.domain.User;
import com.example.WebApps.services.PhotoServer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "delPhoto", value = "/delete")
public class PhotoDel extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect("login.jsp");
        }
        PhotoServer photoServer = new PhotoServer();
        photoServer.delete(Long.parseLong(req.getParameter("id")));
        resp.sendRedirect("photos.jsp");
    }
}
