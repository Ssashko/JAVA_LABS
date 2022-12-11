package lab6;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceRAM {

    private static UniqueRAM fillRAM (ResultSet res) throws SQLException {
        int _id = res.getInt(1);
        int vid = res.getInt(2);
        int pid = res.getInt(3);
        String Vendor = res.getString(4);
        long capacity = res.getLong(5);
        int rank = res.getInt(6);
        long frequency  = res.getLong(7);
        RamType ramType =  RamType.values()[res.getInt(8) - 1];
        RamFormFactor ramFormFactor = RamFormFactor.values()[res.getInt(9) - 1];

        return new UniqueRAM(new Ram.RamBuilder()
                .setVid(vid)
                .setPid(pid)
                .setVendor(Vendor)
                .setCapacity(capacity)
                .setRank(rank)
                .setFrequency(frequency)
                .setRamType(ramType)
                .setRamFormFactor(ramFormFactor)
                .build(), _id);
    }
    public static List<UniqueRAM> getRAM (Connection connection) throws SQLException {
        Statement statement = connection.createStatement();

        ResultSet resultSQL = statement.executeQuery("SELECT " +
                "_id, vid, pid, vendor, capacity, _rank, frequency, _ramtype, _ramformfactor FROM ram");
        List<UniqueRAM> result = new ArrayList<>();
        while(resultSQL.next())
            result.add(fillRAM(resultSQL));

        return result;
    }

    public static List<UniqueRAM> getRAM (Connection connection, int id_motherboard) throws SQLException {
        Statement statement = connection.createStatement();

        ResultSet resultSQL = statement.executeQuery("SELECT " +
                "_id, vid, pid, vendor, capacity, _rank, frequency, _ramtype, _ramformfactor FROM ram WHERE " +
                "motherboard_id = " + id_motherboard);
        List<UniqueRAM> result = new ArrayList<>();
        while(resultSQL.next())
            result.add(fillRAM(resultSQL));

        return result;
    }

    public static void deleteRAM (Connection connection, int id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM Ram WHERE _id = " + Integer.toString(id));
    }

    public static void createRAM (Connection connection, Ram ram) throws SQLException {
        Statement statement = connection.createStatement();

        statement.execute(String.format(
                "INSERT INTO Ram (vid, pid, vendor, capacity, _rank, frequency, _ramtype, _ramformfactor) " +
                        "VALUES (%d,%d,'%s',%d,%d,%d,%d,%d);",
                ram.getVid(), ram.getPid(), ram.getVendor(), ram.getCapacity(), ram.getRank(), ram.getFrequency(),
                ram.getRamType().ordinal() + 1, ram.getRamFormFactor().ordinal() + 1));
    }

    public static void updateRAM (Connection connection, UniqueRAM uram) throws SQLException {
        Statement statement = connection.createStatement();
        Ram ram = uram.getRam();
        statement.execute(String.format("UPDATE Ram SET vid = %d, pid = %d, vendor = '%s', capacity = %d, _rank = %d, " +
                        "frequency  = %d, _ramtype  = %d, _ramformfactor  = %d WHERE _id = %d",
                ram.getVid(), ram.getPid(), ram.getVendor(), ram.getCapacity(), ram.getRank(),
                ram.getFrequency(), ram.getRamType().ordinal() + 1, ram.getRamFormFactor().ordinal() + 1,
                uram.getId()));
    }

    public static void bindToMotherboard(Connection connection,int ram_id, int motherboard_id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(String.format("UPDATE Ram SET motherboard_id = %d WHERE _id = %d",
                motherboard_id, ram_id));

    }

}
