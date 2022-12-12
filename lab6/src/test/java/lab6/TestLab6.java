package lab6;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestLab6 {

    @Test
    public void testCreating() throws SQLException {
        DBService test = new DBService("jdbc:postgresql://localhost:5432/java","postgres","root");
        test.deleteTables();
        test.createTables();
    }
    @Test
    public void testCPU() throws SQLException {
        DBService test = new DBService("jdbc:postgresql://localhost:5432/java","postgres","root");

        Connection connect = test.getConnection();

        ServiceCPU.setConnection(connect);

        Cpu CoreI7_4770K = new Cpu.CpuBuilder()
                .setCoreCount(4)
                .setFrequency(4L * 1000 * 1000 * 1000)
                .setHyperThreading(true)
                .setName("Core i7 4770K")
                .setPip(12340)
                .setVendor("Intel")
                .setSoketType(SoketType.Lga)
                .setVid(4300)
                .setL1CacheCapacity(1000 * 4)
                .setL2CacheCapacity(1000 * 128)
                .setL3CacheCapacity(4 * 1000 * 1000)
                .build();

        Cpu CoreI7_8700K = new Cpu.CpuBuilder()
                .setCoreCount(6)
                .setFrequency(3600L * 1000 * 1000)
                .setHyperThreading(true)
                .setName("Core i7 8700K")
                .setPip(42340)
                .setVendor("Intel")
                .setSoketType(SoketType.Lga)
                .setVid(4300)
                .setL1CacheCapacity(1000 * 6)
                .setL2CacheCapacity(1000 * 256)
                .setL3CacheCapacity(12 * 1000 * 1000)
                .build();

        for(int i = 0;i < 10;i++) {
            ServiceCPU.createCPU(CoreI7_4770K);
            ServiceCPU.createCPU(CoreI7_8700K);
        }
        List<UniqueCPU> set = ServiceCPU.getCPU();

        for(UniqueCPU cpu : set) {

            ServiceCPU.updateCPU(new UniqueCPU(new Cpu.CpuBuilder()
                    .setCoreCount(8)
                    .setFrequency(4100L * 1000 * 1000)
                    .setHyperThreading(true)
                    .setName("Core i9 9900K")
                    .setPip(42340)
                    .setVendor("Intel")
                    .setSoketType(SoketType.Lga)
                    .setVid(4300)
                    .setL1CacheCapacity(1000 * 6)
                    .setL2CacheCapacity(1000 * 256)
                    .setL3CacheCapacity(12 * 1000 * 1000)
                    .build(), cpu.getId()));
        }

        for(UniqueCPU el : set)
            ServiceCPU.deleteCPU(el.getId());

        connect.close();
    }


    @Test
    public void testRam() throws SQLException {
        DBService test = new DBService("jdbc:postgresql://localhost:5432/java","postgres","root");

        Connection connect = test.getConnection();

        ServiceRAM.setConnection(connect);

        Ram ram = new Ram.RamBuilder()
                .setPid(97763)
                .setCapacity(4L * 1024 * 1024 * 1024)
                .setFrequency(3200L * 1000 * 1000)
                .setRamFormFactor(RamFormFactor.DIMM)
                .setRank(2)
                .setVendor("Kingston")
                .setRamType(RamType.Ddr4)
                .setVid(2345)
                .build();

        for(int i = 0;i < 10;i++) {
            ServiceRAM.createRAM(ram);
        }
        List<UniqueRAM> set = ServiceRAM.getRAM();

        for(UniqueRAM cpu : set) {

            ServiceRAM.updateRAM(new UniqueRAM(new Ram.RamBuilder()
                    .setPid(97763)
                    .setCapacity(4L * 1024 * 1024 * 1024)
                    .setFrequency(3200L * 1000 * 1000)
                    .setRamFormFactor(RamFormFactor.DIMM)
                    .setRank(2)
                    .setVendor("Kingston")
                    .setRamType(RamType.Ddr4)
                    .setVid(2345)
                    .build(), cpu.getId()));
        }

        for(UniqueRAM el : set)
            ServiceRAM.deleteRAM(el.getId());
        connect.close();
    }

    @Test
    public void testHDD() throws SQLException {
        DBService test = new DBService("jdbc:postgresql://localhost:5432/java","postgres","root");

        Connection connect = test.getConnection();

        ServiceHDD.setConnection(connect);

        Hdd hdd1 = (Hdd) new Hdd.BuilderHdd()
                .setFormfactor(FormFactorHdd._2_5)
                .setRotationSpeed(7200)
                .setVid(1321)
                .setVendor("Hitachi")
                .setDid(14146)
                .setCapacity(1024L * 1024 * 1024 * 1024)
                .setLinearSpeedOfRead(1024 * 1024 * 130)
                .setLinearSpeedOfWrite(1024 * 1024 * 100)
                .setRandomSpeedOfRead(1024 * 1024 * 3)
                .setRandomSpeedOfWrite(1024 * 1024 * 3)
                .setInterface_(PCInterfaces.SATA)
                .build();

        Hdd hdd2 = (Hdd) new Hdd.BuilderHdd()
                .setFormfactor(FormFactorHdd._2_5)
                .setRotationSpeed(7200)
                .setVid(1321)
                .setVendor("Hitachi")
                .setDid(14148)
                .setCapacity(1024L * 1024 * 1024 * 1024)
                .setLinearSpeedOfRead(1024 * 1024 * 130)
                .setLinearSpeedOfWrite(1024 * 1024 * 100)
                .setRandomSpeedOfRead(1024 * 1024 * 3)
                .setRandomSpeedOfWrite(1024 * 1024 * 3)
                .setInterface_(PCInterfaces.SATA)
                .build();

        for(int i = 0;i < 5;i++) {
            ServiceHDD.createHDD(hdd1);
            ServiceHDD.createHDD(hdd2);
        }
        List<UniqueHDD> set = ServiceHDD.getHDD();


        for(UniqueHDD hdd : set) {

            ServiceHDD.updateHDD(new UniqueHDD((Hdd) new Hdd.BuilderHdd()
                    .setFormfactor(FormFactorHdd._2_5)
                    .setRotationSpeed(7200)
                    .setVid(1321)
                    .setVendor("Hitachi")
                    .setDid(14148)
                    .setCapacity(1024L * 1024 * 1024 * 1024)
                    .setLinearSpeedOfRead(1024 * 1024 * 130)
                    .setLinearSpeedOfWrite(1024 * 1024 * 100)
                    .setRandomSpeedOfRead(1024 * 1024 * 3)
                    .setRandomSpeedOfWrite(1024 * 1024 * 3)
                    .setInterface_(PCInterfaces.SATA)
                    .build(), hdd.getId()));
        }

        for(UniqueHDD el : set)
            ServiceRAM.deleteRAM(el.getId());
        connect.close();
    }

    @Test
    public void testSSD() throws SQLException {
        DBService test = new DBService("jdbc:postgresql://localhost:5432/java","postgres","root");

        Connection connect = test.getConnection();

        ServiceSSD.setConnection(connect);

        Ssd ssd1 = (Ssd) new Ssd.BuilderSsd()
                .setMemoryType(MemoryTypeSsd.TLS)
                .setFormfactor(FormFactorSsd._2_5)
                .setVendor("Samsung")
                .setVid(1101)
                .setDid(38107)
                .setCapacity(256L * 1024 * 1024 * 512)
                .setLinearSpeedOfRead(1024L * 1024 * 2048)
                .setLinearSpeedOfWrite(1024 * 1024 * 1400)
                .setRandomSpeedOfRead(1024 * 1024 * 550)
                .setRandomSpeedOfWrite(1024 * 1024 * 550)
                .setInterface_(PCInterfaces.SATA)
                .build();

        Ssd ssd2 = (Ssd)new Ssd.BuilderSsd()
                .setMemoryType(MemoryTypeSsd.SLS)
                .setFormfactor(FormFactorSsd.M_2)
                .setVendor("Kingston")
                .setVid(1101)
                .setDid(38109)
                .setCapacity(256L * 1024 * 1024 * 512)
                .setLinearSpeedOfRead(1024L * 1024 * 2048)
                .setLinearSpeedOfWrite(1024 * 1024 * 1400)
                .setRandomSpeedOfRead(1024 * 1024 * 550)
                .setRandomSpeedOfWrite(1024 * 1024 * 550)
                .setInterface_(PCInterfaces.PCIE)
                .build();

        for(int i = 0;i < 5;i++) {
            ServiceSSD.createSSD(ssd1);
            ServiceSSD.createSSD(ssd2);
        }
        List<UniqueSSD> set = ServiceSSD.getSSD();

        for(UniqueSSD ssd : set) {

            ServiceSSD.updateSSD(new UniqueSSD((Ssd)new Ssd.BuilderSsd()
                    .setMemoryType(MemoryTypeSsd.SLS)
                    .setFormfactor(FormFactorSsd.M_2)
                    .setVendor("Kingston")
                    .setVid(1101)
                    .setDid(38109)
                    .setCapacity(256L * 1024 * 1024 * 512)
                    .setLinearSpeedOfRead(1024L * 1024 * 2048)
                    .setLinearSpeedOfWrite(1024 * 1024 * 1400)
                    .setRandomSpeedOfRead(1024 * 1024 * 550)
                    .setRandomSpeedOfWrite(1024 * 1024 * 550)
                    .setInterface_(PCInterfaces.PCIE)
                    .build(), ssd.getId()));
        }

        for(UniqueSSD el : set)
            ServiceRAM.deleteRAM(el.getId());
        connect.close();
    }

    @Test
    public void testMotherBoard() throws SQLException {
        DBService test = new DBService("jdbc:postgresql://localhost:5432/java","postgres","root");

        Connection connect = test.getConnection();

        ServiceMotherboard.setConnection(connect);

        Motherboard motherboard1 = new Motherboard.buildMotherboard()
                .setPid(12210)
                .setVid(2311)
                .setVendor("Asus")
                .build();

        Motherboard motherboard2 = new Motherboard.buildMotherboard()
                .setVendor("MSI")
                .setPid(23127)
                .setVid(4321)
                .build();


        List<UniqueMotherboard> set = ServiceMotherboard.getMotherboard();

        for(UniqueMotherboard motherboard : set) {

            ServiceMotherboard.updateMotherboard(new UniqueMotherboard(
                    new Motherboard.buildMotherboard()
                            .setPid(12210)
                            .setVid(2311)
                            .setVendor("Asus")
                            .build(), motherboard.getId()));
        }

        connect.close();
    }
}