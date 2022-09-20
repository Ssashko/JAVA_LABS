package lab1;

import java.util.Objects;

enum PCInterfaces {
    SATA,
    IDE,
    USB,
    PCIE,
    PCI,
    None
}
/**
 * It's an abstract class that describes a common disk's parameters
 * @author Oleksandr Marianchuk
 * */
abstract public class Disk {
    protected final String vendor;
    //vendor id
    protected final int vid;
    //disk id
    protected final int did;
    protected final long capacity;

    protected final PCInterfaces interface_;
    protected final long linearSpeedOfWrite;

    protected final long linearSpeedOfRead;

    protected final int randomSpeedOfWrite;

    protected final int randomSpeedOfRead;
    /**
     * constructor that accepts class-builder.
     * Each field of class-builder must be filled
     * */
    Disk(BuilderDisk bd){
        if(bd == null || !bd.validate())
            throw new IllegalArgumentException("incorrect disk builder");
        this.vendor = bd.vendor;
        this.vid = bd.vid;
        this.did = bd.did;
        this.capacity = bd.capacity;
        this.interface_ = bd.interface_;
        this.linearSpeedOfWrite = bd.linearSpeedOfWrite;
        this.linearSpeedOfRead = bd.linearSpeedOfWrite;
        this.randomSpeedOfWrite = bd.randomSpeedOfWrite;
        this.randomSpeedOfRead = bd.randomSpeedOfWrite;
    }
    /**
     * getter that returns an enum type of connecting interface.
     * */
    public PCInterfaces getInterface_() {
        return interface_;
    }
    @Override
    public String toString(){
        String result = "";
        result += "vendor: " + vendor;
        result += "\nvendor id: " + String.valueOf(vid);
        result += "\ndisk id: " + String.valueOf(did);
        result += "\ncapacity: " + String.valueOf(capacity) + " bytes";
        result += "\ninterface: ";
        switch (interface_)
        {
            case IDE -> result += "IDE";
            case USB -> result += "USB";
            case PCIE -> result += "PCI-express";
            case PCI -> result += "PCI";
            case SATA -> result += "SATA";
        }
        result += "\nlinearSpeedOfWrite: " + String.valueOf(linearSpeedOfWrite) + " bytes per second";
        result += "\nlinearSpeedOfRead: " + String.valueOf(linearSpeedOfRead) + " bytes per second";
        result += "\nrandomSpeedOfWrite: " + String.valueOf(randomSpeedOfWrite) + " bytes per second";
        result += "\nrandomSpeedOfRead: " + String.valueOf(randomSpeedOfRead) + " bytes per second";
        return result;
    }

    /**
     * getter that returns vendor a name string.
     * */
    public String getVendor() {
        return vendor;
    }
    /**
     * getter that returns a vendor id.
     * */
    public int getVid() {
        return vid;
    }
    /**
     * getter that returns a disk id.
     * */
    public int getDid() {
        return did;
    }
    /**
     * getter that returns a capacity of disk.
     * */
    public long getCapacity() {
        return capacity;
    }
    /**
     * getter that returns a disk's linear speed of write.
     * */
    public long getLinearSpeedOfWrite() {
        return linearSpeedOfWrite;
    }
    /**
     * getter that returns a disk's linear speed of read.
     * */
    public long getLinearSpeedOfRead() {
        return linearSpeedOfRead;
    }
    /**
     * getter that returns a disk's random speed of write.
     * */
    public int getRandomSpeedOfWrite() {
        return randomSpeedOfWrite;
    }
    /**
     * getter that returns a disk's random speed of read.
     * */
    public int getRandomSpeedOfRead() {
        return randomSpeedOfRead;
    }
    @Override
    public boolean equals(Object obj){
        Disk dsk = (Disk)obj;

        if(!Objects.equals(vendor, dsk.vendor) || !Objects.equals(vid,dsk.vid) || !Objects.equals(did,dsk.did))
            return false;
        if(!Objects.equals(capacity, dsk.capacity) || !Objects.equals(interface_,dsk.interface_))
            return false;
        if(!Objects.equals(linearSpeedOfWrite, dsk.linearSpeedOfWrite) || !Objects.equals(linearSpeedOfRead,dsk.linearSpeedOfRead))
            return false;
        if(!Objects.equals(randomSpeedOfWrite, dsk.randomSpeedOfWrite) || !Objects.equals(randomSpeedOfRead, dsk.randomSpeedOfRead))
            return false;
        return true;
    }
    @Override
    public int hashCode(){

        int result = vendor.hashCode();
        result = result*31 + vid;
        result = result*31 + did;
        result = result*31 + (int)capacity;
        result = result*31 + interface_.hashCode();
        result = result*31 + (int)linearSpeedOfWrite;
        result = result*31 + (int)linearSpeedOfRead;
        result = result*31 + randomSpeedOfWrite;
        result = result*31 + randomSpeedOfRead;
        return result;
    }
    /**
     * It's an abstract builder class that describes a common disk's parameters
     * @author Oleksandr Marianchuk
     * */
    static abstract public class BuilderDisk {
        protected String vendor = "";
        protected int vid = -1;
        protected int did = -1;
        protected long capacity = -1;
        protected PCInterfaces interface_ = PCInterfaces.None;
        protected long linearSpeedOfWrite = -1;
        protected long linearSpeedOfRead = -1;
        protected int randomSpeedOfWrite = -1;
        protected int randomSpeedOfRead = -1;

        BuilderDisk(){
            super();
        }
        /**
         * setter that assigns a vendor string.
         * */
        public BuilderDisk setVendor(String vendor) {
            this.vendor = vendor;
            return this;
        }
        /**
         * setter that assigns a vendor id.
         * */
        public BuilderDisk setVid(int vid) {
            this.vid = vid;
            return this;
        }
        /**
         * setter that assigns a disk id.
         * */
        public BuilderDisk setDid(int did) {
            this.did = did;
            return this;
        }
        /**
         * setter that assigns a capacity of disk.
         * */
        public BuilderDisk setCapacity(long capacity) {
            this.capacity = capacity;
            return this;
        }
        /**
         * setter that assigns an enum of connecting interface.
         * */
        public BuilderDisk setInterface_(PCInterfaces interface_) {
            this.interface_ = interface_;
            return this;
        }
        /**
         * setter that assigns a disk's linear speed of write.
         * */
        public BuilderDisk setLinearSpeedOfWrite(long linearSpeedOfWrite) {
            this.linearSpeedOfWrite = linearSpeedOfWrite;
            return this;
        }
        /**
         * setter that assigns a disk's linear speed of read.
         * */
        public BuilderDisk setLinearSpeedOfRead(long linearSpeedOfRead) {
            this.linearSpeedOfRead = linearSpeedOfRead;
            return this;
        }
        /**
         * setter that assigns a disk's random speed of write.
         * */
        public BuilderDisk setRandomSpeedOfWrite(int randomSpeedOfWrite) {
            this.randomSpeedOfWrite = randomSpeedOfWrite;
            return this;
        }
        /**
         * setter that assigns a disk's random speed of read.
         * */
        public BuilderDisk setRandomSpeedOfRead(int randomSpeedOfRead) {
            this.randomSpeedOfRead = randomSpeedOfRead;
            return this;
        }
        /**
         * a method that checks the correctness of fields and their completeness
         * */
        abstract public Disk build();
        public boolean validate (){
            boolean result = !vendor.isEmpty() && !vendor.trim().isEmpty();
            result = result && vid >= 0 && did >= 0 && capacity >= 0 && interface_ != PCInterfaces.None;
            result = result && linearSpeedOfWrite >= 0 && linearSpeedOfRead >= 0 && randomSpeedOfWrite >= 0 && randomSpeedOfRead >= 0;
            return result;
        }

    }
}
