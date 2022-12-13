package com.example.lab7;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

import com.omarianchuk.pcinfo.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.validation.ValidationException;

@WebServlet(name = "cpuServlet", value = "/cpu-servlet")
public class CPUServlet extends HttpServlet {
    final String DB_url = "jdbc:postgresql://localhost:5432/java";
    final String DB_login = "postgres";
    final String DB_pass = "root";
    final String JSP_location = "/Gradle___com_example___lab7_1_0_SNAPSHOT_war/cpu.jsp";
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>cpu servlet</h1>");
        out.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if(request.getParameter("http-method").equals("post"))
            _doPost(request,response);
        else if(request.getParameter("http-method").equals("delete"))
            doDelete(request,response);
        else if (request.getParameter("http-method").equals("put"))
            doPut(request,response);

    }
    public void _doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Cpu cpu = null;


        try
        {
            int vid = Integer.parseInt(request.getParameter("vidCpuCreate"));
            int pid = Integer.parseInt(request.getParameter("pidCpuCreate"));
            String vendor = request.getParameter("vendorCpuCreate");
            String name = request.getParameter("nameCpuCreate");
            SoketType soketType = SoketType.valueOf(request.getParameter("sokettypeIdCpuCreate"));

            int corecount = Integer.parseInt(request.getParameter("corecountCpuCreate"));
            long frequency = Long.parseLong(request.getParameter("frequencyCpuCreate"));
            boolean hyperThreading = Boolean.parseBoolean(request.getParameter("hyperthreadingCpuCreate"));
            int l1CacheCapacity = Integer.parseInt(request.getParameter("l1cachecapacityCpuCreate"));
            int l2CacheCapacity = Integer.parseInt(request.getParameter("l2cachecapacityCpuCreate"));
            int l3CacheCapacity = Integer.parseInt(request.getParameter("l3cachecapacityCpuCreate"));
            cpu = new Cpu.CpuBuilder()
                    .setVid(vid)
                    .setPip(pid)
                    .setVendor(vendor)
                    .setName(name)
                    .setCoreCount(corecount)
                    .setSoketType(soketType)
                    .setFrequency(frequency)
                    .setHyperThreading(hyperThreading)
                    .setL1CacheCapacity(l1CacheCapacity)
                    .setL2CacheCapacity(l2CacheCapacity)
                    .setL3CacheCapacity(l3CacheCapacity)
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
            ServiceCPU dbService = new ServiceCPU(connection);
            dbService.createCPU(cpu);

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
            ServiceCPU dbService = new ServiceCPU(connection);
            dbService.deleteCPU(id);

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
        UniqueCPU cpu = null;
        try
        {
            int vid = Integer.parseInt(request.getParameter("vidCpuUpdate"));
            int pid = Integer.parseInt(request.getParameter("pidCpuUpdate"));
            String vendor = request.getParameter("vendorCpuUpdate");
            String name = request.getParameter("nameCpuUpdate");

            int corecount = Integer.parseInt(request.getParameter("corecountCpuUpdate"));

            SoketType soketType = SoketType.valueOf(request.getParameter("sokettypeIdCpuUpdate"));
            long frequency = Long.parseLong(request.getParameter("frequencyCpuUpdate"));
            boolean hyperThreading = Boolean.parseBoolean(request.getParameter("hyperthreadingCpuUpdate"));
            int l1CacheCapacity = Integer.parseInt(request.getParameter("l1cachecapacityCpuUpdate"));
            int l2CacheCapacity = Integer.parseInt(request.getParameter("l2cachecapacityCpuUpdate"));
            int l3CacheCapacity = Integer.parseInt(request.getParameter("l3cachecapacityCpuUpdate"));
            int id = Integer.parseInt(request.getParameter("idCPUUpdate"));

            cpu = new UniqueCPU(new Cpu.CpuBuilder()
                    .setVid(vid)
                    .setPip(pid)
                    .setVendor(vendor)
                    .setName(name)
                    .setSoketType(soketType)
                    .setFrequency(frequency)
                    .setCoreCount(corecount)
                    .setHyperThreading(hyperThreading)
                    .setL1CacheCapacity(l1CacheCapacity)
                    .setL2CacheCapacity(l2CacheCapacity)
                    .setL3CacheCapacity(l3CacheCapacity)
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
            ServiceCPU dbService = new ServiceCPU(connection);
            dbService.updateCPU(cpu);

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