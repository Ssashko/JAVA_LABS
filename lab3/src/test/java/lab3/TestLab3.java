package lab3;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestLab3 {
    private Motherboard motherboard1;
    private Motherboard motherboard2;
    private Motherboard motherboard3;

    private TestLab3() {
         motherboard1 = new Motherboard.buildMotherboard()
                .setPid(1221)
                .setVid(2311)
                .setVendor("Asus")
                .build();
        motherboard2 = new Motherboard.buildMotherboard()
                .setVendor("MSI")
                .setPid(2312)
                .setVid(4321)
                .build();
        motherboard3 = new Motherboard.buildMotherboard()
                .setVendor("Gigabyte")
                .setPid(3431)
                .setVid(4321)
                .build();
        motherboard3.plugCpu(new Cpu.CpuBuilder()
                .setCoreCount(4)
                .setFrequency(4L * 1000 * 1000 * 1000)
                .setHyperThreading(true)
                .setName("Core i7 4770K")
                .setPip(1234)
                .setVendor("Intel")
                .setSoketType(SoketType.Lga)
                .setVid(4300)
                .setL1CacheCapacity(1000 * 4)
                .setL2CacheCapacity(1000 * 128)
                .setL3CacheCapacity(4 * 1000 * 1000)
                .build()
        );
        motherboard2.plugCpu(new Cpu.CpuBuilder()
                .setCoreCount(6)
                .setFrequency(3600L * 1000 * 1000)
                .setHyperThreading(true)
                .setName("Core i7 8700K")
                .setPip(4234)
                .setVendor("Intel")
                .setSoketType(SoketType.Lga)
                .setVid(4300)
                .setL1CacheCapacity(1000 * 6)
                .setL2CacheCapacity(1000 * 256)
                .setL3CacheCapacity(12 * 1000 * 1000)
                .build()
        );
        motherboard1.plugCpu(new Cpu.CpuBuilder()
                .setCoreCount(4)
                .setFrequency(3600L * 1000 * 1000)
                .setHyperThreading(false)
                .setName("Ryzen 3 1200")
                .setPip(1233)
                .setVendor("AMD")
                .setSoketType(SoketType.Lga)
                .setVid(2234)
                .setL1CacheCapacity(1000 * 4)
                .setL2CacheCapacity(1000 * 128)
                .setL3CacheCapacity(4 * 1000 * 1000)
                .build()
        );
        motherboard1.addRam(new Ram.RamBuilder()
                .setPid(9776)
                .setCapacity(4L * 1024 * 1024 * 1024)
                .setFrequency(1600L * 1000 * 1000)
                .setRamFormFactor(RamFormFactor.DIMM)
                .setRank(2)
                .setVendor("Kingston")
                .setVid(2345)
                .setRamType(RamType.Ddr3)
                .build());
        motherboard1.addRam(new Ram.RamBuilder()
                .setPid(9776)
                .setCapacity(4L * 1024 * 1024 * 1024)
                .setFrequency(1333L * 1000 * 1000)
                .setRamFormFactor(RamFormFactor.DIMM)
                .setRank(2)
                .setVendor("Kingston")
                .setVid(2345)
                .setRamType(RamType.Ddr3)
                .build());
        motherboard2.addRam(new Ram.RamBuilder()
                .setPid(9776)
                .setCapacity(4L * 1024 * 1024 * 1024)
                .setFrequency(3200L * 1000 * 1000)
                .setRamFormFactor(RamFormFactor.DIMM)
                .setRank(2)
                .setVendor("Kingston")
                .setRamType(RamType.Ddr4)
                .setVid(2345)
                .build());
        motherboard3.addRam(new Ram.RamBuilder()
                .setPid(9776)
                .setCapacity(4L * 1024 * 1024 * 1024)
                .setFrequency(3200L * 1000 * 1000)
                .setRamFormFactor(RamFormFactor.DIMM)
                .setRank(2)
                .setVendor("Kingston")
                .setVid(2345)
                .setRamType(RamType.Ddr4)
                .build());
        motherboard3.addDisk(new Hdd.BuilderHdd()
                .setFormfactor(FormFactorHdd._2_5)
                .setRotationSpeed(7200)
                .setVid(1321)
                .setVendor("Hitachi")
                .setDid(1414)
                .setCapacity(1024L * 1024 * 1024 * 1024)
                .setLinearSpeedOfRead(1024 * 1024 * 130)
                .setLinearSpeedOfWrite(1024 * 1024 * 100)
                .setRandomSpeedOfRead(1024 * 1024 * 3)
                .setRandomSpeedOfWrite(1024 * 1024 * 3)
                .setInterface_(PCInterfaces.SATA)
                .build());
        motherboard2.addDisk(new Ssd.BuilderSsd()
                .setMemoryType(MemoryTypeSsd.TLS)
                .setFormfactor(FormFactorSsd.M_2)
                .setVendor("Samsung")
                .setVid(1101)
                .setDid(3810)
                .setCapacity(256L * 1024 * 1024 * 512)
                .setLinearSpeedOfRead(1024L * 1024 * 2048)
                .setLinearSpeedOfWrite(1024 * 1024 * 1400)
                .setRandomSpeedOfRead(1024 * 1024 * 550)
                .setRandomSpeedOfWrite(1024 * 1024 * 550)
                .setInterface_(PCInterfaces.PCIE)
                .build());
        motherboard1.addDisk(new Hdd.BuilderHdd()
                .setFormfactor(FormFactorHdd._2_5)
                .setRotationSpeed(7200)
                .setVid(1321)
                .setVendor("Hitachi")
                .setDid(1414)
                .setCapacity(1024L * 1024 * 1024 * 1024)
                .setLinearSpeedOfRead(1024 * 1024 * 130)
                .setLinearSpeedOfWrite(1024 * 1024 * 100)
                .setRandomSpeedOfRead(1024 * 1024 * 3)
                .setRandomSpeedOfWrite(1024 * 1024 * 3)
                .setInterface_(PCInterfaces.SATA)
                .build());
        motherboard1.addDisk(new Ssd.BuilderSsd()
                .setMemoryType(MemoryTypeSsd.TLS)
                .setFormfactor(FormFactorSsd._2_5)
                .setVendor("Samsung")
                .setVid(1101)
                .setDid(3810)
                .setCapacity(256L * 1024 * 1024 * 512)
                .setLinearSpeedOfRead(1024L * 1024 * 2048)
                .setLinearSpeedOfWrite(1024 * 1024 * 1400)
                .setRandomSpeedOfRead(1024 * 1024 * 550)
                .setRandomSpeedOfWrite(1024 * 1024 * 550)
                .setInterface_(PCInterfaces.PCIE)
                .build());
    }
    @Test(dataProvider = "TestCompareToProvider")
    public void testCompareTo(Motherboard motherboard1, Motherboard motherboard2, int val) {
        assertEquals(new MotherboardComparator().compare(motherboard1, motherboard2),val);
    }
    @DataProvider
    public Object[][] TestCompareToProvider() {

        return new Object[][] {
                {motherboard1,motherboard2,-1},
                {motherboard2,motherboard3, 1},
                {motherboard3,motherboard3, 0},
        };
    }


    @Test(dataProvider = "TestCapacityOfRamProvider")
    public void testCapacityOfRam(Motherboard motherboard, long val) {

        assertEquals(MotherboardService.getCapacityOfRams(motherboard)/1024/1024,val);
    }

    @DataProvider
    public Object[][] TestCapacityOfRamProvider() {

        return new Object[][] {
                {motherboard1, 8L*1024},
                {motherboard2, 4L*1024},
                {motherboard3, 4L*1024},
        };
    }

    @Test(dataProvider = "TestFrequencyOfRamsProvider")
    public void testFrequencyOfRams(Motherboard motherboard, long val) {
        assertEquals(MotherboardService.getFrequencyOfRams(motherboard)/1000/1000,val);
    }

    @DataProvider
    public Object[][] TestFrequencyOfRamsProvider() {

        return new Object[][] {
                {motherboard1, 1333L},
                {motherboard2, 3200L},
                {motherboard3, 3200L},
        };
    }

    @Test(dataProvider = "TestIsRamModulesCompatibleProvider")
    public void testIsRamModulesCompatible(Motherboard motherboard, boolean val) {
        assertEquals(MotherboardService.isRamModulesCompatible(motherboard),val);
    }

    @DataProvider
    public Object[][] TestIsRamModulesCompatibleProvider() {

        return new Object[][] {
                {motherboard1, true},
                {motherboard2, true},
                {motherboard3, true},
        };
    }


    @Test(dataProvider = "TestSsdCountProvider")
    public void testSsdCount(Motherboard motherboard, int val) {
        assertEquals(MotherboardService.getAllSsd(motherboard).size(),val);
    }

    @DataProvider
    public Object[][] TestSsdCountProvider() {

        return new Object[][] {
                {motherboard1, 1},
                {motherboard2, 1},
                {motherboard3, 0},
        };
    }


    @Test(dataProvider = "TestSummaryCapacityOfDisksProvider")
    public void testSummaryCapacityOfDisks(Motherboard motherboard, long val) {
        assertEquals(MotherboardService.getSummaryCapacityOfDisks(motherboard)/1024/1024/1024,val);
    }

    @DataProvider
    public Object[][] TestSummaryCapacityOfDisksProvider() {

        return new Object[][] {
                {motherboard1, 1152},
                {motherboard2, 128},
                {motherboard3, 1024},
        };
    }

}