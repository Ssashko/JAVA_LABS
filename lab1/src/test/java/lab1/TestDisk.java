package lab1;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Objects;

public class TestDisk {

    @Test(dataProvider = "equalDisk")
    public void equalTest(Disk obj1, Disk obj2, boolean result) {
        assertEquals(Objects.equals(obj1, obj2), result);
    }

    @DataProvider
    public Object[][] equalDisk() {
        Hdd.BuilderHdd hddb1 = new Hdd.BuilderHdd();
        Hdd.BuilderHdd hddb2 = new Hdd.BuilderHdd();
        Hdd.BuilderHdd hddb3 = new Hdd.BuilderHdd();
        hddb1.setFormfactor(FormFactorHdd._2_5)
                .setRotationSpeed(7200)
                .setVid(1321)
                .setVendor("Hitachi")
                .setDid(1414)
                .setCapacity(1024L*1024*1024*1024)
                .setLinearSpeedOfRead(1024*1024*130)
                .setLinearSpeedOfWrite(1024*1024*100)
                .setRandomSpeedOfRead(1024*1024*3)
                .setRandomSpeedOfWrite(1024*1024*3)
                .setInterface_(PCInterfaces.SATA);
        Hdd hdd1 = hddb1.build();
        hddb2.setFormfactor(FormFactorHdd._2_5)
                .setRotationSpeed(7200)
                .setVid(1321)
                .setVendor("Hitachi")
                .setDid(1414)
                .setCapacity(1024L*1024*1024*1024)
                .setLinearSpeedOfRead(1024*1024*130)
                .setLinearSpeedOfWrite(1024*1024*100)
                .setRandomSpeedOfRead(1024*1024*3)
                .setRandomSpeedOfWrite(1024*1024*3)
                .setInterface_(PCInterfaces.SATA);
        Hdd hdd2 = hddb2.build();
        hddb3.setFormfactor(FormFactorHdd._3_5)
                .setRotationSpeed(7200)
                .setVid(1450)
                .setVendor("Samsung")
                .setDid(5600)
                .setCapacity(1024L*1024*1024*1024)
                .setLinearSpeedOfRead(1024*1024*130)
                .setLinearSpeedOfWrite(1024*1024*100)
                .setRandomSpeedOfRead(1024*1024*3)
                .setRandomSpeedOfWrite(1024*1024*3)
                .setInterface_(PCInterfaces.IDE);
        Hdd hdd3 = hddb3.build();

        Ssd.BuilderSsd ssdb1 = new Ssd.BuilderSsd();
        Ssd.BuilderSsd ssdb2 = new Ssd.BuilderSsd();
        Ssd.BuilderSsd ssdb3 = new Ssd.BuilderSsd();
        ssdb1.setFormfactor(FormFactorSsd.M_2)
                .setMemoryType(MemoryTypeSsd.TLS)
                .setVendor("Samsung")
                .setVid(1101)
                .setDid(3810)
                .setCapacity(1024L*1024*1024*512)
                .setLinearSpeedOfRead(1024L*1024*2048)
                .setLinearSpeedOfWrite(1024*1024*1400)
                .setRandomSpeedOfRead(1024*1024*550)
                .setRandomSpeedOfWrite(1024*1024*550)
                .setInterface_(PCInterfaces.PCIE);
        Ssd ssd1 = ssdb1.build();
        ssdb2.setFormfactor(FormFactorSsd.M_2)
                .setMemoryType(MemoryTypeSsd.TLS)
                .setVendor("Samsung")
                .setVid(1101)
                .setDid(3810)
                .setCapacity(1024L*1024*1024*512)
                .setLinearSpeedOfRead(1024L*1024*2048)
                .setLinearSpeedOfWrite(1024*1024*1400)
                .setRandomSpeedOfRead(1024*1024*550)
                .setRandomSpeedOfWrite(1024*1024*550)
                .setInterface_(PCInterfaces.PCIE);
        Ssd ssd2 = ssdb2.build();
        ssdb3.setFormfactor(FormFactorSsd.M_2)
                .setMemoryType(MemoryTypeSsd.SLS)
                .setVendor("Kingston")
                .setVid(4556)
                .setDid(1112)
                .setCapacity(1024L*1024*1024*512)
                .setLinearSpeedOfRead(512L*1024*2048)
                .setLinearSpeedOfWrite(512L*1024*1400)
                .setRandomSpeedOfRead(1024*1024*550)
                .setRandomSpeedOfWrite(1024*1024*550)
                .setInterface_(PCInterfaces.SATA);
        Ssd ssd3 = ssdb3.build();
        return new Object[][] {{hdd1, hdd2, true}, {hdd1, hdd3, false}, {hdd1, hdd1, true},
                {ssd1, ssd2, true}, {ssd1, ssd3, false}, {ssd1, ssd1, true}, {hdd1, ssd1, false}
        };
    }
    @Test(dataProvider = "hashEqualDisk")
    public void hashEqualTest(Disk obj1, Disk obj2) {
        assertEquals(obj2.hashCode(), obj1.hashCode());
    }

    @DataProvider
    public Object[][] hashEqualDisk() {
        Ssd.BuilderSsd ssdb1 = new Ssd.BuilderSsd();
        Ssd.BuilderSsd ssdb2 = new Ssd.BuilderSsd();
        ssdb1.setFormfactor(FormFactorSsd.M_2)
                .setMemoryType(MemoryTypeSsd.TLS)
                .setVendor("Samsung")
                .setVid(1101)
                .setDid(3810)
                .setCapacity(1024L*1024*1024*512)
                .setLinearSpeedOfRead(1024L*1024*2048)
                .setLinearSpeedOfWrite(1024*1024*1400)
                .setRandomSpeedOfRead(1024*1024*550)
                .setRandomSpeedOfWrite(1024*1024*550)
                .setInterface_(PCInterfaces.PCIE);
        Ssd ssd1 = ssdb1.build();
        ssdb2.setFormfactor(FormFactorSsd.M_2)
                .setMemoryType(MemoryTypeSsd.TLS)
                .setVendor("Samsung")
                .setVid(1101)
                .setDid(3810)
                .setCapacity(1024L*1024*1024*512)
                .setLinearSpeedOfRead(1024L*1024*2048)
                .setLinearSpeedOfWrite(1024*1024*1400)
                .setRandomSpeedOfRead(1024*1024*550)
                .setRandomSpeedOfWrite(1024*1024*550)
                .setInterface_(PCInterfaces.PCIE);
        Ssd ssd2 = ssdb2.build();
        return new Object[][] {{ssd1, ssd2}};
    }
    @Test(dataProvider = "BuilderDataException" ,expectedExceptions  = IllegalArgumentException.class)
    public void BuilderTestException(Disk.BuilderDisk builder) {
        Disk dsk = builder.build();
    }
    @Test(dataProvider = "BuilderData")
    public void BuilderTest(Disk.BuilderDisk builder) {
        Disk dsk = builder.build();
    }
    @DataProvider
    public Object[][] BuilderDataException() {
        Ssd.BuilderSsd ssdb1 = new Ssd.BuilderSsd();
        Ssd.BuilderSsd ssdb2 = new Ssd.BuilderSsd();
        ssdb1.setFormfactor(FormFactorSsd.M_2)
                .setMemoryType(MemoryTypeSsd.TLS)
                .setVendor("Samsung")
                .setVid(1101)
                .setDid(3810)
                .setCapacity(1024L*1024*1024*512)
                .setLinearSpeedOfRead(1024L*1024*2048)
                .setLinearSpeedOfWrite(1024*1024*1400)
                .setRandomSpeedOfWrite(1024*1024*550)
                .setInterface_(PCInterfaces.PCIE);
        ssdb2.setFormfactor(FormFactorSsd.M_2)
                .setMemoryType(MemoryTypeSsd.TLS)
                .setVendor("Samsung")
                .setVid(1101)
                .setDid(3810)
                .setCapacity(1024L*1024*1024*512)
                .setLinearSpeedOfRead(1024L*1024*2048)
                .setLinearSpeedOfWrite(1024*1024*1400)
                .setRandomSpeedOfRead(1024*1024*550);
        return new Object[][] {{ssdb1}, {ssdb2}};
    }
    @DataProvider
    public Object[][] BuilderData() {
        Ssd.BuilderSsd ssdb1 = new Ssd.BuilderSsd();
        Ssd.BuilderSsd ssdb2 = new Ssd.BuilderSsd();
        ssdb1.setFormfactor(FormFactorSsd.M_2)
                .setMemoryType(MemoryTypeSsd.TLS)
                .setVendor("Samsung")
                .setVid(1101)
                .setDid(3810)
                .setCapacity(1024L*1024*1024*512)
                .setLinearSpeedOfRead(1024L*1024*2048)
                .setLinearSpeedOfWrite(1024*1024*1400)
                .setRandomSpeedOfRead(1024*1024*550)
                .setRandomSpeedOfWrite(1024*1024*550)
                .setInterface_(PCInterfaces.PCIE);
        ssdb2.setFormfactor(FormFactorSsd.M_2)
                .setMemoryType(MemoryTypeSsd.TLS)
                .setVendor("Samsung")
                .setVid(1101)
                .setDid(3810)
                .setCapacity(1024L*1024*1024*512)
                .setLinearSpeedOfRead(1024L*1024*2048)
                .setLinearSpeedOfWrite(1024*1024*1400)
                .setRandomSpeedOfRead(1024*1024*550)
                .setRandomSpeedOfWrite(1024*1024*550)
                .setInterface_(PCInterfaces.PCIE);
        return new Object[][] {{ssdb1}, {ssdb2}};
    }

}
