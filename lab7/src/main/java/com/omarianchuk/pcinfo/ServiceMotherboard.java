package com.omarianchuk.pcinfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceMotherboard {

    private static Connection connection;

    public static void setConnection(Connection connection) {

        ServiceMotherboard.connection =  connection;
    }
    private static UniqueMotherboard fillMotherboard (ResultSet resultSQLBASE, ResultSet resultSQLSSD,
                                                      ResultSet resultSQLHHD, ResultSet resultSQLRAM)
            throws SQLException {

        /* motherboard part*/
        int _id = resultSQLBASE.getInt(1);
        int vid = resultSQLBASE.getInt(2);
        int pid = resultSQLBASE.getInt(3);
        String vendor = resultSQLBASE.getString(4);

        UniqueMotherboard motherboard = new UniqueMotherboard(new Motherboard.buildMotherboard()
                .setVid(vid)
                .setPid(pid)
                .setVendor(vendor)
                .build()
                ,_id);

        /* motherboard part*/
        fillCPUMotherboard(resultSQLBASE, motherboard);

        while(resultSQLSSD.next())
        {
            if(resultSQLSSD.getInt("motherboard_id") != motherboard.getId()) {
                resultSQLSSD.previous();
                break;
            }
            else
                fillSSDMotherboard(resultSQLSSD,motherboard);
        }

        while(resultSQLHHD.next())
        {
            if(resultSQLHHD.getInt("motherboard_id") != motherboard.getId()) {
                resultSQLHHD.previous();
                break;
            }
            else
                fillHDDMotherboard(resultSQLHHD,motherboard);
        }

        while(resultSQLRAM.next())
        {
            if(resultSQLRAM.getInt("motherboard_id") != motherboard.getId()) {
                resultSQLRAM.previous();
                break;
            }
            else
                fillRAMMotherboard(resultSQLRAM,motherboard);
        }
        return motherboard;
    }

    private static void fillRAMMotherboard (ResultSet res, UniqueMotherboard motherboard) throws SQLException {
        int _id = res.getInt(6);
        if(_id == 0)
            return;
        int vid = res.getInt(7);
        int pid = res.getInt(8);
        String Vendor = res.getString(9);
        long capacity = res.getLong("capacity");
        int rank = res.getInt("_rank");
        long frequency  = res.getLong("frequency");
        RamType ramType =  RamType.values()[res.getInt("_ramtype") - 1];
        RamFormFactor ramFormFactor = RamFormFactor.values()[res.getInt("_ramformfactor") - 1];

        motherboard.getMotherboard().addRam(new Ram.RamBuilder()
                .setVid(vid)
                .setPid(pid)
                .setVendor(Vendor)
                .setCapacity(capacity)
                .setRank(rank)
                .setFrequency(frequency)
                .setRamType(ramType)
                .setRamFormFactor(ramFormFactor)
                .build());
    }
    private static void fillHDDMotherboard (ResultSet res, UniqueMotherboard motherboard) throws SQLException {
        int _id = res.getInt(6);
        if(_id == 0)
            return;
        String vendor = res.getString(9);
        int vid = res.getInt(7);
        int did = res.getInt(8);
        long capacity = res.getLong("capacity");
        PCInterfaces interface_ = PCInterfaces.values()[res.getInt("interface_")-1];
        long linearSpeedOfWrite = res.getLong("linearSpeedOfWrite");
        long linearSpeedOfRead = res.getLong("linearSpeedOfWrite");
        int randomSpeedOfWrite = res.getInt("randomSpeedOfWrite");
        int randomSpeedOfRead = res.getInt("randomSpeedOfRead");
        int rotationSpeed = res.getInt("_rotationspeed");
        FormFactorHdd formfactor = FormFactorHdd.values()[res.getInt("_formfactorhdd")-1];

        motherboard.getMotherboard().addDisk(new Hdd.BuilderHdd()
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
                .build());
    }
    private static void fillSSDMotherboard (ResultSet res, UniqueMotherboard motherboard) throws SQLException {
        int _id = res.getInt(6);
        if(_id == 0)
            return;
        String vendor = res.getString(9);
        int vid = res.getInt(7);
        int did = res.getInt(8);
        long capacity = res.getLong("capacity");
        PCInterfaces interface_ = PCInterfaces.values()[res.getInt("interface_")-1];
        long linearSpeedOfWrite = res.getLong("linearSpeedOfWrite");
        long linearSpeedOfRead = res.getLong("linearSpeedOfWrite");
        int randomSpeedOfWrite = res.getInt("randomSpeedOfWrite");
        int randomSpeedOfRead = res.getInt("randomSpeedOfRead");
        MemoryTypeSsd memoryType = MemoryTypeSsd.values()[res.getInt("_memorytypessd")-1];
        FormFactorSsd formfactor = FormFactorSsd.values()[res.getInt("_formfactorssd")-1];

        motherboard.getMotherboard().addDisk(new Ssd.BuilderSsd()
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
                .build());
    }
    private static void fillCPUMotherboard (ResultSet res, UniqueMotherboard motherboard) throws SQLException {
        if(res.getInt(6) == 0)
            return;
        int vid = res.getInt(7);
        String vendor = res.getString(8);
        int pip = res.getInt(9);
        String name = res.getString("_name");
        SoketType soketType = SoketType.values()[res.getInt("sokettype_id")-1];
        long frequency = res.getLong("frequency");
        int coreCount = res.getInt("corecount");
        boolean hyperThreading = res.getBoolean("hyperthreading");
        int l1CacheCapacity = res.getInt("l1cachecapacity");
        int l2CacheCapacity = res.getInt("l2cachecapacity");
        int l3CacheCapacity = res.getInt("l3cachecapacity");
        motherboard.getMotherboard().plugCpu(new Cpu.CpuBuilder()
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
                .build());
    }



    public static List<UniqueMotherboard> getMotherboard () throws SQLException {
        Statement statement = connection.createStatement();
        Statement[] add_statement = {
                connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY),
                connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY),
                connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
        };
        final String SQLBASE = "SELECT * FROM motherboard " +
                "LEFT OUTER JOIN cpu ON cpu._id = motherboard.cpu_id ORDER BY motherboard._id";
        final String SQLSSD = "SELECT * FROM motherboard " +
                "INNER JOIN ssd ON ssd.motherboard_id = motherboard._id ORDER BY motherboard._id";
        final String SQLHHD = "SELECT * FROM motherboard " +
                "INNER JOIN hdd ON hdd.motherboard_id = motherboard._id ORDER BY motherboard._id";
        final String SQLRAM = "SELECT * FROM motherboard " +
                "INNER JOIN ram ON ram.motherboard_id = motherboard._id ORDER BY motherboard._id";


        ResultSet resultSQLBASE = statement.executeQuery(SQLBASE);
        ResultSet resultSQLSSD = add_statement[0].executeQuery(SQLSSD);
        ResultSet resultSQLHHD = add_statement[1].executeQuery(SQLHHD);
        ResultSet resultSQLRAM = add_statement[2].executeQuery(SQLRAM);
        List<UniqueMotherboard> result = new ArrayList<>();

        while(resultSQLBASE.next())
            result.add(fillMotherboard(resultSQLBASE,resultSQLSSD,resultSQLHHD,resultSQLRAM));
        return result;
    }

    public static void deleteMotherboard (int id) throws SQLException {
        final String SQL = "DELETE FROM Motherboard WHERE _id = ?";
        PreparedStatement pStatement = connection.prepareStatement(SQL);
        pStatement.setInt(1,id);
        pStatement.execute();
    }

    public static void createMotherboard (Motherboard motherboard)   throws SQLException {
        final String SQL = "INSERT INTO Motherboard (vid, pid, vendor) VALUES (?, ?, ?)";

        PreparedStatement pStatement = connection.prepareStatement(SQL);
        pStatement.setInt(1, motherboard.getVid());
        pStatement.setInt(2, motherboard.getPid());
        pStatement.setString(3, motherboard.getVendor());

        pStatement.execute();
    }

    public static void updateMotherboard (UniqueMotherboard umotherboard) throws SQLException {
        final String SQL = "UPDATE Motherboard SET vid = ?, pid = ?, vendor = ? WHERE _id = ?";

        PreparedStatement pStatement = connection.prepareStatement(SQL);
        Motherboard motherboard = umotherboard.getMotherboard();

        pStatement.setInt(1,motherboard.getVid());
        pStatement.setInt(2, motherboard.getPid());
        pStatement.setString(3, motherboard.getVendor());
        pStatement.setInt(4, umotherboard.getId());

        pStatement.execute();
    }

    public static void plugCPU(int motherboardId,int cpuId) throws SQLException {
        final String SQL = "UPDATE Motherboard SET cpu_id = ? WHERE _id = ?";

        PreparedStatement pStatement = connection.prepareStatement(SQL);

        pStatement.setInt(1, cpuId);
        pStatement.setInt(2, motherboardId);

        pStatement.execute();
    }

    public static void unplugCPU(int motherboardId) throws SQLException {
        final String SQL = "UPDATE Motherboard SET cpu_id = null WHERE _id = ?";

        PreparedStatement pStatement = connection.prepareStatement(SQL);

        pStatement.setInt(1, motherboardId);

        pStatement.execute();
    }
}
