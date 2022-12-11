package lab6;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceMotherboard {

    private static UniqueMotherboard fillMotherboard (ResultSet res) throws SQLException {

        int _id = res.getInt(1);
        int vid = res.getInt(2);
        int pid = res.getInt(3);
        String vendor = res.getString(4);

        return new UniqueMotherboard(new Motherboard.buildMotherboard()
                .setVid(vid)
                .setPid(pid)
                .setVendor(vendor)
                .build()
                ,_id);
    }


    public static List<UniqueMotherboard> getMotherboard (Connection connection) throws SQLException {
        Statement statement = connection.createStatement();

        ResultSet resultSQL = statement.executeQuery("SELECT _id, vid, pid, vendor FROM motherboard");
        List<UniqueMotherboard> result = new ArrayList<>();

        while(resultSQL.next())
            result.add(fillMotherboard(resultSQL));

        for(UniqueMotherboard el : result)
        {
            el.getMotherboard().plugCpu(ServiceCPU.getCPU(connection,el.getId()).getCpu());
            List<Ssd> disksSsd = ServiceSSD.getSSD(connection, el.getId()).stream().map(UniqueSSD::getSsd).toList();
            List<Hdd> disksHdd = ServiceHDD.getHDD(connection, el.getId()).stream().map(UniqueHDD::getHdd).toList();
            List<Ram> rams = ServiceRAM.getRAM(connection, el.getId()).stream().map(UniqueRAM::getRam).toList();
            for(Disk dsk : disksSsd)
                el.getMotherboard().addDisk(dsk);
            for(Disk dsk : disksHdd)
                el.getMotherboard().addDisk(dsk);
            for(Ram ram : rams)
                el.getMotherboard().addRam(ram);
        }

        return result;
    }

    public static void deleteMotherboard (Connection connection, int id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM Motherboard WHERE _id = " + Integer.toString(id));
    }

    public static void createMotherboard (Connection connection, Motherboard motherboard) throws SQLException {
        Statement statement = connection.createStatement();

        statement.execute(String.format(
                "INSERT INTO Motherboard (vid, pid, vendor)"+
                        " VALUES (%d,%d,'%s');",
                motherboard.getVid(), motherboard.getPid(), motherboard.getVendor()));
    }

    public static void updateMotherboard (Connection connection, UniqueMotherboard umotherboard) throws SQLException {
        Statement statement = connection.createStatement();
        Motherboard motherboard = umotherboard.getMotherboard();
        statement.execute(String.format("UPDATE Motherboard SET vid = %d, pid = %d, vendor = '%s' WHERE _id = %d",
                motherboard.getVid(), motherboard.getPid(), motherboard.getVendor(),
                umotherboard.getId()));
    }
}
