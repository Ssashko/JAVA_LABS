package lab4;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestLab4 {
    private Motherboard.buildMotherboard motherboardBuilder1;
    private Motherboard.buildMotherboard motherboardBuilder2;
    private Ssd.BuilderSsd ssdBuilder1;
    private Ssd.BuilderSsd ssdBuilder2;

    private Hdd.BuilderHdd hddBuilder1;
    private Hdd.BuilderHdd hddBuilder2;

    private Cpu.CpuBuilder cpuBuilder1;
    private Cpu.CpuBuilder cpuBuilder2;

    private Ram.RamBuilder ramBuilder1;
    private Ram.RamBuilder ramBuilder2;



    private TestLab4() {
        motherboardBuilder1 = new Motherboard.buildMotherboard()
                .setPid(12210)
                .setVid(2311)
                .setVendor("Asus");
        motherboardBuilder2 = new Motherboard.buildMotherboard()
                .setVendor("MSI")
                .setPid(23127)
                .setVid(4321);
        cpuBuilder1 = new Cpu.CpuBuilder()
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
                .setL3CacheCapacity(4 * 1000 * 1000);

        cpuBuilder2 = new Cpu.CpuBuilder()
                .setCoreCount(0)
                .setFrequency(3600L * 1000 * 1000)
                //.setHyperThreading(true)
                .setName("Core i7 8700K^&")
                .setPip(42340)
                .setVendor("Intel")
                .setSoketType(SoketType.Lga)
                .setVid(4300)
                .setL1CacheCapacity(1000 * 6)
                .setL2CacheCapacity(1000 * 256)
                .setL3CacheCapacity(12 * 1000 * 1000);

        ramBuilder1 = new Ram.RamBuilder()
                .setPid(97761)
                .setCapacity(4L * 1024 * 1024 * 1024)
                .setFrequency(-1600L * 1000 * 1000)
                .setRamFormFactor(RamFormFactor.DIMM)
                .setRank(2)
                .setVendor("Kingston")
                .setVid(2345)
                .setRamType(RamType.Ddr3);
        ramBuilder2 = new Ram.RamBuilder()
                .setPid(97763)
                .setCapacity(4L * 1024 * 1024 * 1024)
                .setFrequency(3200L * 1000 * 1000)
                .setRamFormFactor(RamFormFactor.DIMM)
                .setRank(2)
                .setVendor("Kingston")
                .setRamType(RamType.Ddr4)
                .setVid(2345);

        hddBuilder1 = (Hdd.BuilderHdd)new Hdd.BuilderHdd()
                //.setFormfactor(FormFactorHdd._2_5)
                .setRotationSpeed(7200)
                .setVid(1321)
                .setVendor("#$%*Hitachi")
                .setDid(14146)
                //.setCapacity(1024L * 1024 * 1024 * 1024)
                //.setLinearSpeedOfRead(1024 * 1024 * 130)
                //.setLinearSpeedOfWrite(1024 * 1024 * 100)
                //.setRandomSpeedOfRead(1024 * 1024 * 3)
                .setRandomSpeedOfWrite(1024 * 1024 * 3)
                .setInterface_(PCInterfaces.SATA);

        hddBuilder2 = (Hdd.BuilderHdd)new Hdd.BuilderHdd()
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
                .setInterface_(PCInterfaces.SATA);

        ssdBuilder1 = (Ssd.BuilderSsd)new Ssd.BuilderSsd()
                //.setMemoryType(MemoryTypeSsd.TLS)
                .setFormfactor(FormFactorSsd._2_5)
                .setVendor("Samsung")
                .setVid(1101)
                .setDid(3810987)
                .setCapacity(256L * 1024 * 1024 * 512)
                .setLinearSpeedOfRead(1024L * 1024 * 2048)
                .setLinearSpeedOfWrite(1024 * 1024 * 1400)
                .setRandomSpeedOfRead(1024 * 1024 * 550)
                .setRandomSpeedOfWrite(1024 * 1024 * 550)
                .setInterface_(PCInterfaces.SATA);

        ssdBuilder2 = (Ssd.BuilderSsd)new Ssd.BuilderSsd()
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
                .setInterface_(PCInterfaces.PCIE);

    }

    @DataProvider
    public Object[][] TestRamBuilderProvider() {

        return new Object[][] {
                {new Ram.RamBuilder(),true},
                {ramBuilder1,true},
                {ramBuilder2,false},
        };
    }

    @Test(dataProvider = "TestRamBuilderProvider", expectedExceptions = IllegalArgumentException.class )
    public void testRamBuilder(Ram.RamBuilder b, boolean expectException) {
        try {
            b.build();
        } catch (IllegalArgumentException ex)  {
            if(!expectException) {
                System.err.println("not expected IllegalArgumentException");
                return;
            }

            System.out.println(ex.getMessage());
            System.out.println();
            throw ex;
        }

        if(expectException) {
            System.err.println("expect IllegalArgumentException");
        }else {
            System.out.println("OK");
            throw new IllegalArgumentException();
        }

    }

    @DataProvider
    public Object[][] TestHddBuilderProvider() {

        return new Object[][] {
                {new Hdd.BuilderHdd(),true},
                {hddBuilder1,true},
                {hddBuilder2,false},
        };
    }

    @Test(dataProvider = "TestHddBuilderProvider", expectedExceptions = IllegalArgumentException.class )
    public void testHddBuilder(Hdd.BuilderHdd b, boolean expectException) {
        try {
            b.build();
        } catch (IllegalArgumentException ex)  {
            if(!expectException) {
                System.err.println("not expected IllegalArgumentException");
                return;
            }

            System.out.println(ex.getMessage());
            System.out.println();
            throw ex;
        }

        if(expectException) {
            System.err.println("expect IllegalArgumentException");
        }else {
            System.out.println("OK");
            throw new IllegalArgumentException();
        }

    }

    @DataProvider
    public Object[][] TestSsdBuilderProvider() {

        return new Object[][] {
                {new Ssd.BuilderSsd(),true},
                {ssdBuilder1,true},
                {ssdBuilder2,false},
        };
    }

    @Test(dataProvider = "TestSsdBuilderProvider", expectedExceptions = IllegalArgumentException.class )
    public void testSsdBuilder(Ssd.BuilderSsd b, boolean expectException) {
        try {
            b.build();
        } catch (IllegalArgumentException ex)  {
            if(!expectException) {
                System.err.println("not expected IllegalArgumentException");
                return;
            }

            System.out.println(ex.getMessage());
            System.out.println();
            throw ex;
        }

        if(expectException) {
            System.err.println("expect IllegalArgumentException");
        }else {
            System.out.println("OK");
            throw new IllegalArgumentException();
        }

    }


    @DataProvider
    public Object[][] TestCpuBuilderProvider() {

        return new Object[][] {
                {new Cpu.CpuBuilder(),true},
                {cpuBuilder1,false},
                {cpuBuilder2,true},
        };
    }

    @Test(dataProvider = "TestCpuBuilderProvider", expectedExceptions = IllegalArgumentException.class )
    public void testCpuBuilder(Cpu.CpuBuilder b, boolean expectException) {
        try {
            b.build();
        } catch (IllegalArgumentException ex)  {
            if(!expectException) {
                System.err.println("not expected IllegalArgumentException");
                return;
            }

            System.out.println(ex.getMessage());
            System.out.println();
            throw ex;
        }

        if(expectException) {
            System.err.println("expect IllegalArgumentException");
        }else {
            System.out.println("OK");
            throw new IllegalArgumentException();
        }

    }


    @DataProvider
    public Object[][] TestMotherboardBuilderProvider() {

        return new Object[][] {
                {new Motherboard.buildMotherboard(),true},
                {motherboardBuilder1,false},
                {motherboardBuilder2,false},
        };
    }

    @Test(dataProvider = "TestMotherboardBuilderProvider", expectedExceptions = IllegalArgumentException.class )
    public void testMotherboardBuilder(Motherboard.buildMotherboard b, boolean expectException) {
        try {
            b.build();
        } catch (IllegalArgumentException ex)  {
            if(!expectException) {
                System.err.println("not expected IllegalArgumentException");
                return;
            }

            System.out.println(ex.getMessage());
            System.out.println();
            throw ex;
        }

        if(expectException) {
            System.err.println("expect IllegalArgumentException");
        }else {
            System.out.println("OK");
            throw new IllegalArgumentException();
        }

    }
}