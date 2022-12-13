package com.omarianchuk.pcinfo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceSSD {

    private final Connection connection;

    ServiceSSD(Connection connection) {
        this.connection =  connection;
    }


    private UniqueSSD fillSSD (ResultSet res) throws SQLException {
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
        MemoryTypeSsd memoryType = MemoryTypeSsd.values()[res.getInt(11)-1];
        FormFactorSsd formfactor = FormFactorSsd.values()[res.getInt(12)-1];

        return new UniqueSSD(new Ssd.BuilderSsd()
                .setMemoryType(memoryType)
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


    public List<UniqueSSD> getSSD () throws SQLException {
        Statement statement = connection.createStatement();

        ResultSet resultSQL = statement.executeQuery("SELECT " +
                "_id, vendor, vid, pid, capacity, interface_, linearSpeedOfWrite, linearSpeedOfRead," +
                "randomSpeedOfWrite, randomSpeedOfRead, _memoryTypeSsd, _formFactorSsd  FROM ssd");
        List<UniqueSSD> result = new ArrayList<>();
        while(resultSQL.next())
            result.add(fillSSD(resultSQL));

        return result;
    }

    public  List<UniqueSSD> getSSD (int id_motherboard) throws SQLException {

        Statement statement = connection.createStatement();

        ResultSet resultSQL = statement.executeQuery("SELECT " +
                "_id, vendor, vid, pid, capacity, interface_, linearSpeedOfWrite, linearSpeedOfRead," +
                "randomSpeedOfWrite, randomSpeedOfRead, _memoryTypeSsd, _formFactorSsd  FROM ssd WHERE motherboard_id = "
                + id_motherboard);
        List<UniqueSSD> result = new ArrayList<>();
        while(resultSQL.next())
            result.add(fillSSD(resultSQL));

        return result;

    }

    public void deleteSSD (int id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM Ssd WHERE _id = " + Integer.toString(id));
    }

    public void createSSD (Ssd ssd) throws SQLException {
        Statement statement = connection.createStatement();

        statement.execute(String.format(
                "INSERT INTO Ssd (vendor, vid, pid, capacity, interface_, linearSpeedOfWrite, linearSpeedOfRead," +
                        "randomSpeedOfWrite, randomSpeedOfRead, _memoryTypeSsd, _formFactorSsd)"+
                        " VALUES ('%s','%d',%d,'%d',%d, %d, %d, %d, %d, %d, %d);",
                ssd.getVendor(), ssd.getVid(), ssd.getDid(), ssd.getCapacity(), ssd.getInterface_().ordinal() + 1,
                ssd.getLinearSpeedOfWrite(), ssd.getLinearSpeedOfRead(), ssd.getRandomSpeedOfWrite(),
                ssd.getRandomSpeedOfRead(), ssd.getMemoryType().ordinal() + 1, ssd.getFormfactor().ordinal() + 1));
    }

    public void updateSSD (UniqueSSD ussd) throws SQLException {
        Statement statement = connection.createStatement();
        Ssd ssd = ussd.getSsd();
        statement.execute(String.format("UPDATE Ssd SET vendor = '%s', " +
                        "vid = %d, pid  = %d, capacity  = %d, interface_  = %d, linearSpeedOfWrite  = %d, " +
                        "linearSpeedOfRead  = %d, randomSpeedOfWrite  = %d, randomSpeedOfRead  = %d, " +
                        "_memoryTypeSsd  = %d, _formFactorSsd = %d WHERE _id = %d",
                ssd.getVendor(), ssd.getVid(), ssd.getDid(), ssd.getCapacity(), ssd.getInterface_().ordinal() + 1,
                ssd.getLinearSpeedOfWrite(), ssd.getLinearSpeedOfRead(), ssd.getRandomSpeedOfWrite(),
                ssd.getRandomSpeedOfRead(), ssd.getMemoryType().ordinal() + 1, ssd.getFormfactor().ordinal() + 1,
                ussd.getId()));
    }

    public void bindToMotherboard(int ssd_id, int motherboard_id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(String.format("UPDATE Ssd SET motherboard_id = %d WHERE _id = %d",
                motherboard_id, ssd_id));

    }
}
