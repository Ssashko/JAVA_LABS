package com.omarianchuk.pcinfo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceCPU {

    final private Connection connection;

    public ServiceCPU(Connection connection) {
        this.connection = connection;
    }

    private UniqueCPU fillCPU (ResultSet res) throws SQLException {
        int vid = res.getInt(1);
        String vendor = res.getString(2);
        int pip = res.getInt(3);
        String name = res.getString(4);
        SoketType soketType = SoketType.values()[res.getInt(5)-1];
        long frequency = res.getLong(6);
        int coreCount = res.getInt(7);
        boolean hyperThreading = res.getBoolean(8);
        int l1CacheCapacity = res.getInt(9);
        int l2CacheCapacity = res.getInt(10);
        int l3CacheCapacity = res.getInt(11);
        int _id = res.getInt(12);
        return new UniqueCPU(new Cpu.CpuBuilder()
                .setVid(vid)
                .setVendor(vendor)
                .setPip(pip)
                .setName(name)
                .setSoketType(soketType)
                .setFrequency(frequency)
                .setCoreCount(coreCount)
                .setHyperThreading(hyperThreading)
                .setL1CacheCapacity(l1CacheCapacity)
                .setL2CacheCapacity(l2CacheCapacity)
                .setL3CacheCapacity(l3CacheCapacity)
                .build(), _id);
    }
    public List<UniqueCPU> getCPU () throws SQLException {
        Statement statement = connection.createStatement();

        ResultSet resultSQL = statement.executeQuery("SELECT " +
                "vid, vendor, pid, _name, sokettype_id, frequency, corecount, hyperthreading," +
                "l1cachecapacity, l2cachecapacity, l3cachecapacity, _id  FROM cpu");
        List<UniqueCPU> result = new ArrayList<>();
        while(resultSQL.next())
            result.add(fillCPU(resultSQL));

        return result;
    }
    public UniqueCPU getCPU ( int id) throws SQLException {
        Statement statement = connection.createStatement();

        ResultSet resultSQL = statement.executeQuery("SELECT " +
                "vid, vendor, pid, _name, sokettype_id, frequency, corecount, hyperthreading," +
                "l1cachecapacity, l2cachecapacity, l3cachecapacity, _id  FROM cpu WHERE _id = " + id);
        if(!resultSQL.next())
            return new UniqueCPU(null,0);
        return fillCPU(resultSQL);
    }
    public void deleteCPU (int id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM Cpu WHERE _id = " + Integer.toString(id));
    }

    public void createCPU (Cpu cpu) throws SQLException {
        Statement statement = connection.createStatement();

        statement.execute(String.format(
                "INSERT INTO Cpu (vid, vendor, pid, _name, " +
                        "sokettype_id, frequency, corecount, hyperthreading, l1cachecapacity, " +
                        "l2cachecapacity, l3cachecapacity) VALUES (%d,'%s',%d,'%s',%d,%d,%d,%b,%d,%d,%d);",
                cpu.getVid(), cpu.getVendor(), cpu.getPip(), cpu.getName(), cpu.getSoketType().ordinal() + 1,
                cpu.getFrequency(), cpu.getCoreCount(), cpu.isHyperThreading(), cpu.getL1CacheCapacity(),
                cpu.getL2CacheCapacity(), cpu.getL3CacheCapacity() ));
    }

    public void updateCPU (UniqueCPU ucpu) throws SQLException {
        Statement statement = connection.createStatement();
        Cpu cpu = ucpu.getCpu();
        statement.execute(String.format("UPDATE Cpu SET vid = %d, vendor = '%s', pid = %d, _name = '%s', sokettype_id = %d, " +
                "frequency = %d, corecount = %d, hyperthreading = %b, l1cachecapacity = %d, l2cachecapacity = %d, " +
                "l3cachecapacity = %d WHERE _id = %d",cpu.getVid(), cpu.getVendor(), cpu.getPip(), cpu.getName(), cpu.getSoketType().ordinal() + 1,
                cpu.getFrequency(), cpu.getCoreCount(), cpu.isHyperThreading(), cpu.getL1CacheCapacity(),
                cpu.getL2CacheCapacity(), cpu.getL3CacheCapacity(), ucpu.getId()));
    }


}
