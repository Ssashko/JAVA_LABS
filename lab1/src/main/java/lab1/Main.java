package lab1;

public class Main {
    public static void main (String[] args)
    {
        Hdd.BuilderHdd bhbuilder = new Hdd.BuilderHdd();
        bhbuilder.setFormfactor(FormFactorHdd._2_5)
                .setRotationSpeed(7200)
                .setVendor("Hitachi")
                .setVid(1321)
                .setDid(1414)
                .setCapacity(1024L*1024*1024*1024)
                .setLinearSpeedOfRead(1024*1024*130)
                .setLinearSpeedOfWrite(1024*1024*100)
                .setRandomSpeedOfRead(1024*1024*3)
                .setRandomSpeedOfWrite(1024*1024*3)
                .setInterface_(PCInterfaces.SATA);
        Hdd bd = bhbuilder.build();
        System.out.print("\n\nHDD drive:\n\n");
        System.out.printf("\nHDD drive hash: %d\n", bd.hashCode());
        System.out.print(bd);

        Ssd.BuilderSsd ssdbuilder = new Ssd.BuilderSsd();
        ssdbuilder.setFormfactor(FormFactorSsd.M_2)
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
        Ssd ssd = ssdbuilder.build();
        System.out.print("\n\nSSD drive:\n\n");
        System.out.printf("\nSSD drive hash: %d\n", ssd.hashCode());
        System.out.print(ssd);
    }

}
