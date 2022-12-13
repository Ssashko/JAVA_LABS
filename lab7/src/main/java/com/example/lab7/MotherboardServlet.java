package com.example.lab7;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.omarianchuk.pcinfo.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.validation.ValidationException;

@WebServlet(name = "motherboardServlet", value = "/motherboard-servlet")
public class MotherboardServlet extends HttpServlet {
    final String DB_url = "jdbc:postgresql://localhost:5432/java";
    final String DB_login = "postgres";
    final String DB_pass = "root";
    final String JSP_location = "motherboard.jsp";
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        DBService db = new DBService(DB_url,DB_login,DB_pass);
        List<UniqueMotherboard> motherBoardList = null;

        try(Connection connect = db.getConnection()) {
            ServiceMotherboard.setConnection(connect);
            motherBoardList = ServiceMotherboard.getMotherboard();

        } catch (SQLException ex) {
            response.setStatus(400);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(ex);
            return;
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(JSP_location);
        request.setAttribute("listMotherBoard", motherBoardList);
        requestDispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

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
    public void doPostCpuPlug(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
            ServiceMotherboard.setConnection(connection);
            ServiceMotherboard.plugCPU(idMotherboard,idCpu);

        } catch (SQLException ex) {
            response.setStatus(400);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(ex);
            return;
        }
        doGet(request, response);
    }
    public void doPostCpuUnPlug(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
            ServiceMotherboard.setConnection(connection);
            ServiceMotherboard.unplugCPU(idMotherboard);

        } catch (SQLException ex) {
            response.setStatus(400);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(ex);
            return;
        }
        doGet(request, response);
    }
    public void _doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

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
            ServiceMotherboard.setConnection(connection);
            ServiceMotherboard.createMotherboard(motherboard);

        } catch (SQLException ex) {
            response.setStatus(400);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(ex);
            return;
        }
        doGet(request, response);
    }
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
            ServiceMotherboard.setConnection(connection);
            ServiceMotherboard.deleteMotherboard(id);

        } catch (SQLException ex) {
            response.setStatus(400);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(ex);
            return;
        }

        doGet(request, response);
    }
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
            ServiceMotherboard.setConnection(connection);
            ServiceMotherboard.updateMotherboard(motherboard);

        } catch (SQLException ex) {
            response.setStatus(400);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(ex);
            return;
        }
        doGet(request, response);

    }

}