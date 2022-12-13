package com.example.lab7;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

import com.omarianchuk.pcinfo.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.validation.ValidationException;

@WebServlet(name = "motherboardServlet", value = "/motherboard-servlet")
public class MotherboardServlet extends HttpServlet {
    final String DB_url = "jdbc:postgresql://localhost:5432/java";
    final String DB_login = "postgres";
    final String DB_pass = "root";
    final String JSP_location = "/Gradle___com_example___lab7_1_0_SNAPSHOT_war/";
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>motherboard servlet</h1>");
        out.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if(request.getParameter("http-method").equals("post"))
            _doPost(request,response);
        else if(request.getParameter("http-method").equals("delete"))
            doDelete(request,response);
        else if (request.getParameter("http-method").equals("put"))
            doPut(request,response);
        else if (request.getParameter("http-method").equals("post-cpu-plug"))
            doPostCpuPlug(request,response);
        else if (request.getParameter("http-method").equals("post-cpu-unplug"))
            doPostCpuUnPlug(request,response);

    }
    public void doPostCpuPlug(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idCpu = 0;
        int idMotherboard = 0;
        try
        {
            idCpu  = Integer.parseInt(request.getParameter("idPluggedCPU"));
            idMotherboard = Integer.parseInt(request.getParameter("idMotherBoardPlugCPU"));

        } catch (Exception ex) {
            response.setStatus(400);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(ex);
            return;
        }


        DBService db = new DBService(DB_url,DB_login,DB_pass);

        try(Connection connection = db.getConnection()) {
            ServiceMotherboard dbService = new ServiceMotherboard(connection);
            dbService.plugCPU(idMotherboard,idCpu);

        } catch (SQLException ex) {
            response.setStatus(400);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(ex);
            return;
        }
        response.sendRedirect(JSP_location);
    }
    public void doPostCpuUnPlug(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idMotherboard = 0;
        try
        {
            idMotherboard = Integer.parseInt(request.getParameter("idMotherBoardPlugCPU"));

        } catch (Exception ex) {
            response.setStatus(400);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(ex);
            return;
        }


        DBService db = new DBService(DB_url,DB_login,DB_pass);

        try(Connection connection = db.getConnection()) {
            ServiceMotherboard dbService = new ServiceMotherboard(connection);
            dbService.unplugCPU(idMotherboard);

        } catch (SQLException ex) {
            response.setStatus(400);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(ex);
            return;
        }
        response.sendRedirect(JSP_location);
    }
    public void _doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Motherboard motherboard = null;
        try
        {
            int vid  = Integer.parseInt(request.getParameter("vidMotherboardCreate"));
            int pid = Integer.parseInt(request.getParameter("pidMotherboardCreate"));
            String vendor = request.getParameter("vendorMotherboardCreate");
            motherboard = new Motherboard.buildMotherboard()
                    .setVid(vid)
                    .setPid(pid)
                    .setVendor(vendor)
                    .build();
        } catch (Exception ex) {
            response.setStatus(400);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(ex);
            return;
        }


        DBService db = new DBService(DB_url,DB_login,DB_pass);

        try(Connection connection = db.getConnection()) {
            ServiceMotherboard dbService = new ServiceMotherboard(connection);
            dbService.createMotherboard(motherboard);

        } catch (SQLException ex) {
            response.setStatus(400);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(ex);
            return;
        }
        response.sendRedirect(JSP_location);
    }
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = 0;
        try
        {
            id  = Integer.parseInt(request.getParameter("idDelete"));
        } catch (Exception ex) {
            response.setStatus(400);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(ex);
            return;
        }

        DBService db = new DBService(DB_url,DB_login,DB_pass);

        try(Connection connection = db.getConnection()) {

            ServiceMotherboard dbService = new ServiceMotherboard(connection);
            dbService.deleteMotherboard(id);

        } catch (SQLException ex) {
            response.setStatus(400);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(ex);
            return;
        }

        response.sendRedirect(JSP_location);
    }
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UniqueMotherboard motherboard = null;
        try
        {
            int vid  = Integer.parseInt(request.getParameter("vidMotherboardUpdate"));
            int pid = Integer.parseInt(request.getParameter("pidMotherboardUpdate"));
            String vendor = request.getParameter("vendorMotherboardUpdate");
            int id = Integer.parseInt(request.getParameter("idMotherboardUpdate"));

            motherboard = new UniqueMotherboard(new Motherboard.buildMotherboard()
                    .setVid(vid)
                    .setPid(pid)
                    .setVendor(vendor)
                    .build(),id);

        } catch (Exception ex) {
            response.setStatus(400);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(ex);
            return;
        }


        DBService db = new DBService(DB_url,DB_login,DB_pass);

        try(Connection connection = db.getConnection()) {
            ServiceMotherboard dbService = new ServiceMotherboard(connection);
            dbService.updateMotherboard(motherboard);

        } catch (SQLException ex) {
            response.setStatus(400);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(ex);
            return;
        }
        response.sendRedirect(JSP_location);

    }

}