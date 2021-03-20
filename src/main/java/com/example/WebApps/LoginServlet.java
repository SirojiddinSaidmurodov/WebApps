package com.example.WebApps;

import com.example.WebApps.services.DBProps;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

@WebServlet(name = "Login", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            DBProps props = new DBProps();
            Connection connection = DriverManager.getConnection(
                    props.getProperty("url"),
                    props.getProperty("user"),
                    props.getProperty("password"));
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT login,password from `user` where login=?");
            preparedStatement.setString(1, req.getParameter("login"));
            ResultSet resultSet = preparedStatement.executeQuery();
            PrintWriter writer = resp.getWriter();

            if (resultSet.next()) {
                if (resultSet.getString("password").equals(req.getParameter("password"))) {
                    Cookie cookie = new Cookie("user", req.getParameter("login"));
                    resp.addCookie(cookie);
                    HttpSession session = req.getSession();
                    session.setAttribute("URL", req.getRequestURL());
                    session.setAttribute("user", req.getParameter("login"));
                    writer.println("User: " + resultSet.getString("login") + "<br>");
                    writer.println("Creation time: " + new Date(session.getCreationTime()) + "<br>");
                    writer.println("Last access time: " + new Date(session.getLastAccessedTime()) + "<br>");
                    writer.println("Session id: " + session.getId() + "<br>");
                    writer.println("Your url: " + req.getRequestURL() + "<br>");
                    session.setMaxInactiveInterval(60 * 60);

                    writer.flush();
                    writer.close();
                }
            } else {
                writer.println("Error!");
                writer.flush();
                writer.close();
            }
            connection.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
