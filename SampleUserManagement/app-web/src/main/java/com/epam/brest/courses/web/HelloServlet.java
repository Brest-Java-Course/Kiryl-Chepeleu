package com.epam.brest.courses.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Created by kirill on 5.11.14.
 */
public class HelloServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameter = req.getParameter("name");
        resp.setContentType("text/plain");

        PrintWriter pw = resp.getWriter();
        try {
            pw.println("Hello ");
            pw.println(parameter);
        }finally {
            pw.close();
        }
    }
}
