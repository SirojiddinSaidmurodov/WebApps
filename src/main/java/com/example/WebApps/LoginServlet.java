package com.example.WebApps;

import com.example.WebApps.services.DBProps;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

@WebServlet(name = "Login", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
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
                    HttpSession session = req.getSession();
                    session.setAttribute("URL", req.getRequestURL());
                    writer.println("User: " + resultSet.getString("login") + "<br>");
                    writer.println("Creation time: " + new Date(session.getCreationTime()) + "<br>");
                    writer.println("Last access time: " + new Date(session.getLastAccessedTime()) + "<br>");
                    writer.println("Session id: " + session.getId() + "<br>");
                    writer.println("Your url: " + req.getRequestURL() + "<br>");
                    session.setMaxInactiveInterval(60 * 60);

                    writer.flush();
                    writer.close();
                }
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
