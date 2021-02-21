package com.example.WebApps;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/form")
public class FormServlet extends HttpServlet {
    @Override
    public void init() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("<h1>ToDo list:<h1>");
        String[] items = req.getParameterValues("items");
        writer.println("<ul>");
        for (String item : items) {
            writer.println("<li>" + item + "</li>");
        }
        writer.println("</ul>");
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        String name = req.getParameter("username");
        String age = req.getParameter("userage");
        String gender = req.getParameter("gender");
        String country = req.getParameter("country");
        String[] courses = req.getParameterValues("courses");

        writer.println("<p>Name: " + name + "</p>");
        writer.println("<p>Age: " + age + "</p>");
        writer.println("<p>Gender: " + gender + "</p>");
        writer.println("<p>Country: " + country + "</p>");
        writer.println("<h4>Courses</h4>");
        for (String course : courses)
            writer.println("<li>" + course + "</li>");

        writer.close();

    }

    @Override
    public void destroy() {

    }
}
