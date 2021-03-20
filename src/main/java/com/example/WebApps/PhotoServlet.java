package com.example.WebApps;

import com.example.WebApps.domain.User;
import com.example.WebApps.services.PhotoServer;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "PhotoServlet", value = "/photo")
@MultipartConfig
public class PhotoServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect("login.jsp");
        }
        resp.setContentType("image");
        ServletOutputStream outputStream = resp.getOutputStream();
        PhotoServer photoServer = new PhotoServer();
        InputStream photo = photoServer.getPhoto(Long.parseLong(req.getParameter("id")));
        byte[] buffer = new byte[1024];
        while (photo.read(buffer) > 0) {
            outputStream.write(buffer);
        }
        photo.close();
        photo.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part photo = req.getPart("photo");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        String fileName = getFileName(photo);
        InputStream inputStream = photo.getInputStream();
        PhotoServer photoServer = new PhotoServer();
        if (inputStream.available() > 0) {
            photoServer.upload(user, fileName, inputStream);
        }
        resp.sendRedirect("photos.jsp");
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
