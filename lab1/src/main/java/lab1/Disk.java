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
abstract public class Disk {
    protected final String vendor;
    //vendor id
    protected final int vid;
    //disk id
    protected final int did;
    //
    protected final long capacity;

    protected final PCInterfaces interface_;
    protected final long linearSpeedOfWrite;

    protected final long linearSpeedOfRead;

    protected final int randomSpeedOfWrite;

    protected final int randomSpeedOfRead;

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

    public PCInterfaces getInterface_() {
        return interface_;
    }

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


    public String getVendor() {
        return vendor;
    }

    public int getVid() {
        return vid;
    }

    public int getDid() {
        return did;
    }

    public long getCapacity() {
        return capacity;
    }

    public long getLinearSpeedOfWrite() {
        return linearSpeedOfWrite;
    }

    public long getLinearSpeedOfRead() {
        return linearSpeedOfRead;
    }

    public int getRandomSpeedOfWrite() {
        return randomSpeedOfWrite;
    }

    public int getRandomSpeedOfRead() {
        return randomSpeedOfRead;
    }
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

        public BuilderDisk setVendor(String vendor) {
            this.vendor = vendor;
            return this;
        }

        public BuilderDisk setVid(int vid) {
            this.vid = vid;
            return this;
        }

        public BuilderDisk setDid(int did) {
            this.did = did;
            return this;
        }

        public BuilderDisk setCapacity(long capacity) {
            this.capacity = capacity;
            return this;
        }

        public BuilderDisk setInterface_(PCInterfaces interface_) {
            this.interface_ = interface_;
            return this;
        }

        public BuilderDisk setLinearSpeedOfWrite(long linearSpeedOfWrite) {
            this.linearSpeedOfWrite = linearSpeedOfWrite;
            return this;
        }

        public BuilderDisk setLinearSpeedOfRead(long linearSpeedOfRead) {
            this.linearSpeedOfRead = linearSpeedOfRead;
            return this;
        }

        public BuilderDisk setRandomSpeedOfWrite(int randomSpeedOfWrite) {
            this.randomSpeedOfWrite = randomSpeedOfWrite;
            return this;
        }

        public BuilderDisk setRandomSpeedOfRead(int randomSpeedOfRead) {
            this.randomSpeedOfRead = randomSpeedOfRead;
            return this;
        }

        public boolean validate (){
            boolean result = !vendor.isEmpty() && !vendor.trim().isEmpty();
            result = result && vid >= 0 && did >= 0 && capacity >= 0 && interface_ != PCInterfaces.None;
            result = result && linearSpeedOfWrite >= 0 && linearSpeedOfRead >= 0 && randomSpeedOfWrite >= 0 && randomSpeedOfRead >= 0;
            return result;
        }

    }
}
