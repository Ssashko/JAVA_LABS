package lab3;
enum RamType {
    Ddr1,
    Ddr2,
    Ddr3,
    Ddr4,
    Ddr5
}

enum RamFormFactor {
    DIMM,
    SO_DIMM
}
public class Ram implements Comparable<Ram> {
    final int vid;
    final int pid;
    final String Vendor;
    final long capacity;
    final int rank;
    final long frequency;
    final RamType ramType;
    final RamFormFactor ramFormFactor;

    public Ram(int vid, int pid, String Vendor, long capacity, int rank, long frequency, RamType ramType,
    RamFormFactor ramFormFactor) {
        this.vid = vid;
        this.pid = pid;
        this.Vendor = Vendor;
        this.capacity = capacity;
        this.rank = rank;
        this.frequency = frequency;
        this.ramType = ramType;
        this.ramFormFactor = ramFormFactor;
    }

    public int getVid() {
        return vid;
    }

    public int getPid() {
        return pid;
    }

    public String getVendor() {
        return Vendor;
    }

    public long getCapacity() {
        return capacity;
    }

    public int getRank() {
        return rank;
    }

    public long getFrequency() {
        return frequency;
    }

    public RamType getRamType() {
        return ramType;
    }

    public RamFormFactor getRamFormFactor() {
        return ramFormFactor;
    }


    @Override
    public int compareTo(Ram o) {
        int capacityComp = Long.compare(this.capacity, o.capacity);
        if(capacityComp == 0)
            return Long.compare(this.frequency, o.frequency);
        return capacityComp;
    }

    public static class RamBuilder{
        int vid;
        int pid;
        String Vendor;
        long capacity;
        int rank;
        long frequency;
        RamType ramType;
        RamFormFactor ramFormFactor;

        public RamBuilder setVid(int vid) {
            this.vid = vid;
            return this;
        }

        public RamBuilder setPid(int pid) {
            this.pid = pid;
            return this;
        }

        public RamBuilder setVendor(String vendor) {
            Vendor = vendor;
            return this;
        }

        public RamBuilder setCapacity(long capacity) {
            this.capacity = capacity;
            return this;
        }

        public RamBuilder setRank(int rank) {
            this.rank = rank;
            return this;
        }

        public RamBuilder setFrequency(long frequency) {
            this.frequency = frequency;
            return this;
        }

        public RamBuilder setRamType(RamType ramType) {
            this.ramType = ramType;
            return this;
        }

        public RamBuilder setRamFormFactor(RamFormFactor ramFormFactor) {
            this.ramFormFactor = ramFormFactor;
            return this;
        }
        public Ram build() {
            return new Ram(vid, pid, Vendor, capacity, rank, frequency, ramType, ramFormFactor);
        }
    }
}
