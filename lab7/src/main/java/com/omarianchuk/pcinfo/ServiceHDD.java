package com.omarianchuk.pcinfo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceHDD {

    private static Connection connection;

    public static void setConnection(Connection connection) {

        ServiceHDD.connection =  connection;
    }
    private static UniqueHDD fillHDD (ResultSet res) throws SQLException {
        int _id = res.getInt(1);
        String vendor = res.getString(2);
        int vid = res.getInt(3);
        int did = res.getInt(4);
        long capacity = res.getLong(5);
        PCInterfaces interface_ = PCInterfaces.values()[res.getInt(6)-1];
        long linearSpeedOfWrite = res.getLong(7);
        long linearSpeedOfRead = res.getLong(8);
        int randomSpeedOfWrite = res.getInt(9);
        int randomSpeedOfRead = res.getInt(10);
        int rotationSpeed = res.getInt(11);
        FormFactorHdd formfactor = FormFactorHdd.values()[res.getInt(12)-1];

        return new UniqueHDD(new Hdd.BuilderHdd()
                .setRotationSpeed(rotationSpeed)
                .setFormfactor(formfactor)
                .setVid(vid)
                .setVendor(vendor)
                .setDid(did)
                .setCapacity(capacity)
                .setDid(did)
                .setLinearSpeedOfRead(linearSpeedOfRead)
                .setLinearSpeedOfWrite(linearSpeedOfWrite)
                .setRandomSpeedOfRead(randomSpeedOfRead)
                .setRandomSpeedOfWrite(randomSpeedOfWrite)
                .setInterface_(interface_)
                .build(),_id);
    }


    public static List<UniqueHDD> getHDD () throws SQLException {
        Statement statement = connection.createStatement();

        ResultSet resultSQL = statement.executeQuery("SELECT " +
                "_id, vendor, vid, pid, capacity, interface_, linearSpeedOfWrite, linearSpeedOfRead," +
                "randomSpeedOfWrite, randomSpeedOfRead, _rotationspeed, _formfactorhdd  FROM hdd");
        List<UniqueHDD> result = new ArrayList<>();
        while(resultSQL.next())
            result.add(fillHDD(resultSQL));

        return result;
    }

    public static List<UniqueHDD> getHDD (int motherboardId) throws SQLException {
        Statement statement = connection.createStatement();

        ResultSet resultSQL = statement.executeQuery("SELECT " +
                "_id, vendor, vid, pid, capacity, interface_, linearSpeedOfWrite, linearSpeedOfRead," +
                "randomSpeedOfWrite, randomSpeedOfRead, _rotationspeed, _formfactorhdd  FROM hdd WHERE motherboard_id = " +
                motherboardId);
        List<UniqueHDD> result = new ArrayList<>();
        while(resultSQL.next())
            result.add(fillHDD(resultSQL));

        return result;
    }

    public static void deleteHDD (int id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM Hdd WHERE _id = " + Integer.toString(id));
    }

    public static void createHDD (Hdd hdd) throws SQLException {
        Statement statement = connection.createStatement();

        statement.execute(String.format(
                "INSERT INTO Hdd (vendor, vid, pid, capacity, interface_, linearSpeedOfWrite, linearSpeedOfRead," +
                        "randomSpeedOfWrite, randomSpeedOfRead, _rotationspeed, _formfactorhdd)"+
                        " VALUES ('%s',%d,%d,%d,%d, %d, %d, %d, %d, %d, %d);",
                hdd.getVendor(), hdd.getVid(), hdd.getDid(), hdd.getCapacity(), hdd.getInterface_().ordinal() + 1,
                hdd.getLinearSpeedOfWrite(), hdd.getLinearSpeedOfRead(), hdd.getRandomSpeedOfWrite(),
                hdd.getRandomSpeedOfRead(), hdd.getRotationSpeed(), hdd.getFormfactor().ordinal() + 1));
    }

    public static void updateHDD (UniqueHDD uhdd) throws SQLException {
        Statement statement = connection.createStatement();
        Hdd hdd = uhdd.getHdd();
        statement.execute(String.format("UPDATE Hdd SET vendor = '%s', " +
                        "vid = %d, pid  = %d, capacity  = %d, interface_  = %d, linearSpeedOfWrite  = %d, " +
                        "linearSpeedOfRead  = %d, randomSpeedOfWrite  = %d, randomSpeedOfRead  = %d, " +
                        "_rotationspeed  = %d, _formfactorhdd = %d WHERE _id = %d",
                hdd.getVendor(), hdd.getVid(), hdd.getDid(), hdd.getCapacity(), hdd.getInterface_().ordinal() + 1,
                hdd.getLinearSpeedOfWrite(), hdd.getLinearSpeedOfRead(), hdd.getRandomSpeedOfWrite(),
                hdd.getRandomSpeedOfRead(), hdd.getRotationSpeed(), hdd.getFormfactor().ordinal() + 1,
                uhdd.getId()));
    }



    public static void bindToMotherboard(int hddId, int motherboardId) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(String.format("UPDATE Hdd SET motherboard_id = %d WHERE _id = %d",
                motherboardId, hddId));

    }
}
