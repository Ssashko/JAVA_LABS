package lab3;

import java.util.List;
import java.util.Objects;

enum SoketType {
    Lga,
    Pga
}
/**
 * It's a class that describes a Cpu parameters.
 * @author Oleksandr Marianchuk
 * */
public class Cpu implements Comparable<Cpu> {
    final int vid;
    final String vendor;
    final int pip;
    final String name;
    final SoketType soketType;
    final long frequency;
    final int coreCount;
    final boolean hyperThreading;
    final int l1CacheCapacity;
    final int l2CacheCapacity;
    final int l3CacheCapacity;

    public int getVid() {
        return vid;
    }

    public String getVendor() {
        return vendor;
    }

    public int getPip() {
        return pip;
    }

    public String getName() {
        return name;
    }

    public SoketType getSoketType() {
        return soketType;
    }

    public long getFrequency() {
        return frequency;
    }

    public int getCoreCount() {
        return coreCount;
    }

    public boolean isHyperThreading() {
        return hyperThreading;
    }

    public int getL1CacheCapacity() {
        return l1CacheCapacity;
    }

    public int getL2CacheCapacity() {
        return l2CacheCapacity;
    }

    public int getL3CacheCapacity() {
        return l3CacheCapacity;
    }

    Cpu(int vid, String vendor, int pip, String name, SoketType soketType, long frequency, int coreCount,
        boolean hyperThreading, int l1CacheCapacity, int l2CacheCapacity, int l3CacheCapacity)
    {
        this.vid = vid;
        this.vendor = vendor;
        this.pip = pip;
        this.name = name;
        this.soketType = soketType;
        this.frequency = frequency;
        this.coreCount = coreCount;
        this.hyperThreading = hyperThreading;
        this.l1CacheCapacity = l1CacheCapacity;
        this.l2CacheCapacity = l2CacheCapacity;
        this.l3CacheCapacity = l3CacheCapacity;
    }
    private static class CompareablePair<T extends Comparable<T>> {
        public T first;
        public T second;

        CompareablePair(T first, T second){
            this.first = first;
            this.second = second;
        }
        int compare() {
            return first.compareTo(second);
        }

    }
    private int compareRec(List<CompareablePair<?>> params, int head)
    {
        if (head == params.size())
            return 0;
        int comp = params.get(head).compare();
        if(comp == 0)
            compareRec(params,head+1);
        return comp;
    }
    @Override
    public int compareTo(Cpu o) {
        return compareRec(List.of(
                new CompareablePair<Integer>(this.coreCount, o.coreCount),
                new CompareablePair<Long>(this.frequency, o.frequency),
                new CompareablePair<Boolean>(this.hyperThreading, o.hyperThreading),
                new CompareablePair<Integer>(this.l1CacheCapacity, o.l1CacheCapacity),
                new CompareablePair<Integer>(this.l2CacheCapacity, o.l2CacheCapacity),
                new CompareablePair<Integer>(this.l3CacheCapacity, o.l3CacheCapacity)
        ),0);
    }

    /**
     * It's a builder class that describes Cpu parameters
     * @author Oleksandr Marianchuk
     * */
    public static class CpuBuilder {
        int vid;
        String vendor;
        int pip;
        String name;
        SoketType soketType;
        long frequency;
        int coreCount;
        boolean hyperThreading;
        int l1CacheCapacity;
        int l2CacheCapacity;
        int l3CacheCapacity;

        public CpuBuilder setVid (int vid) {
            this.vid = vid;
            return this;
        }
        public CpuBuilder setVendor (String vendor) {
            this.vendor = vendor;
            return this;
        }
        public CpuBuilder setPip (int pip) {
            this.pip = pip;
            return this;
        }
        public CpuBuilder setName (String name) {
            this.name = name;
            return this;
        }
        public CpuBuilder setSoketType (SoketType soketType) {
            this.soketType = soketType;
            return this;
        }
        public CpuBuilder setFrequency (long frequency) {
            this.frequency = frequency;
            return this;
        }


        public CpuBuilder setCoreCount (int coreCount) {
            this.coreCount = coreCount;
            return this;
        }
        public CpuBuilder setHyperThreading(boolean hyperThreading) {
            this.hyperThreading = hyperThreading;
            return this;
        }
        public CpuBuilder setL1CacheCapacity (int l1CacheCapacity) {
            this.l1CacheCapacity = l1CacheCapacity;
            return this;
        }
        public CpuBuilder setL2CacheCapacity (int l2CacheCapacity) {
            this.l2CacheCapacity = l2CacheCapacity;
            return this;
        }
        public CpuBuilder setL3CacheCapacity (int l3CacheCapacity) {
            this.l3CacheCapacity = l3CacheCapacity;
            return this;
        }
        public Cpu build() {
            return new Cpu(vid, vendor, pip, name, soketType, frequency, coreCount, hyperThreading, l1CacheCapacity,
                    l2CacheCapacity, l3CacheCapacity);
        }
    }

}
