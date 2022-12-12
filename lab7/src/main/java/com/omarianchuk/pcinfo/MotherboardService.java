package com.omarianchuk.pcinfo;

import java.util.List;
import java.util.function.Supplier;

enum SpeedOfDiskTypes {
    Linear,
    Random
}
enum SpeedOfDiskDirections {
    Write,
    Read
}
/**
 * It's a service class that provides several useful methods to process Motherboard's parameters.
 * @author Oleksandr Marianchuk
 * */
public class MotherboardService {

    /**
     * get simple total capacity of disks
     * @author Oleksandr Marianchuk
     * */
    public static long getSummaryCapacityOfDisks(Motherboard m) {
        long result = 0;
        for(Disk disk : m.getDisks())
            result += disk.getCapacity();
        return result;
    }
    /**
     * get simple total capacity of disks
     * @author Oleksandr Marianchuk
     * */
    private static Supplier<Long> getReferenceToSpecificGetterOfSpeed(Disk d,SpeedOfDiskTypes types, SpeedOfDiskDirections directions) {
        return switch (directions) {
            case Write -> switch (types) {
                case Linear -> d::getLinearSpeedOfWrite;
                case Random -> d::getRandomSpeedOfWrite;
            };
            case Read -> switch (types) {
                case Linear -> d::getLinearSpeedOfRead;
                case Random -> d::getRandomSpeedOfRead;
            };
        };
    }

    /**
     * get average specific speed corresponding to capacity
     * @author Oleksandr Marianchuk
     * */
    public static long getAverageSpecificSpeed(Motherboard m, SpeedOfDiskTypes types, SpeedOfDiskDirections directions) {
        long summaryCapacity = getSummaryCapacityOfDisks(m);
        double result = 0;
        for(Disk disk : m.getDisks())
            result += (disk.getCapacity()/(double)summaryCapacity)*getReferenceToSpecificGetterOfSpeed(disk,types,directions).get();
        return (long)result;
    }
    public static long getAverageLinearSpeedOfWrite(Motherboard m) {
        return getAverageSpecificSpeed(m,SpeedOfDiskTypes.Linear, SpeedOfDiskDirections.Write);
    }
    public static long getAverageRandomSpeedOfWrite(Motherboard m) {
        return getAverageSpecificSpeed(m,SpeedOfDiskTypes.Random, SpeedOfDiskDirections.Write);
    }
    public static long getAverageLinearSpeedOfRead(Motherboard m) {
        return getAverageSpecificSpeed(m,SpeedOfDiskTypes.Linear, SpeedOfDiskDirections.Read);
    }
    public static long getAverageRandomSpeedOfRead(Motherboard m) {
        return getAverageSpecificSpeed(m,SpeedOfDiskTypes.Random, SpeedOfDiskDirections.Read);
    }

    /**
     * get minimum frequency of rams
     * @author Oleksandr Marianchuk
     * */
    public static long getFrequencyOfRams(Motherboard m)
    {
        if(m.getRams().isEmpty())
            return 0;
        long min = m.getRams().get(0).getFrequency();
        for(Ram ram : m.getRams())
            min = Math.min(min, ram.getFrequency());
        return min;
    }
    /**
     * get total capacity of rams
     * @author Oleksandr Marianchuk
     * */
    public static long getCapacityOfRams(Motherboard m) {
        long capacity = 0;
        for(Ram ram : m.getRams())
            capacity += ram.getCapacity();

        return capacity;
    }
    /**
     * get all SSD
     * @author Oleksandr Marianchuk
     * */
    public static List<Ssd> getAllSsd(Motherboard m) {
        return m.getDisks().stream().filter( disk -> disk instanceof Ssd).map( disk -> (Ssd)disk).toList();
    }

    /**
     * get all HDD
     * @author Oleksandr Marianchuk
     * */
    public static List<Hdd> getAllHdd(Motherboard m) {
        return m.getDisks().stream().filter( disk -> disk instanceof Hdd).map( disk -> (Hdd)disk).toList();
    }
    /**
     * check compatible of ram
     * @author Oleksandr Marianchuk
     * */
    public static boolean isRamModulesCompatible(Motherboard m) {
        return !m.getRams().isEmpty() && m.getRams().stream().noneMatch(ram -> ram.getRamType() != m.getRams().get(0).getRamType());
    }

}
