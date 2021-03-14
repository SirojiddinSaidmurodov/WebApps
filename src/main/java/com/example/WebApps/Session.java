package com.example.WebApps;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "session", value = "/session")
public class Session extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        HttpSession session = req.getSession(true);
        PrintWriter out = resp.getWriter();
        StringBuffer url = req.getRequestURL();
        session.setAttribute("URL", url);
        out.write("<br> Creation Time : "
                + new Date(session.getCreationTime()));
        out.write("<br> Time of last access : "
                + new Date(session.getLastAccessedTime()));
        out.write("<br> session ID : " + session.getId());
        out.write("<br> Your URL: " + url);
        int timeLive = 60 * 30;
        session.setMaxInactiveInterval(timeLive);
        out.write("<br>Set max inactive interval : " + timeLive + "sec");
        out.flush();
        out.close();
    }
}
