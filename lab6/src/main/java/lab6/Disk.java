package lab6;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
        if(bd == null)
            throw new IllegalArgumentException("incorrect disk builder");
        bd.validate();
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
        result = String.join("",result, "vendor: ", vendor);
        result = String.join("",result,"\nvendor id: ", String.valueOf(vid));
        result = String.join("",result,"\ndisk id: ", String.valueOf(did));
        result = String.join("",result,"\ncapacity: ", String.valueOf(capacity), " bytes");
        result = String.join("",result,"\ninterface: ");
        switch (interface_)
        {
            case IDE -> result = String.join("",result,"IDE");
            case USB -> result = String.join("",result,"USB");
            case PCIE -> result = String.join("",result,"PCI-express");
            case PCI -> result = String.join("",result,"PCI");
            case SATA -> result = String.join("",result,"SATA");
        }
        result = String.join("",result,"\nlinearSpeedOfWrite: ", String.valueOf(linearSpeedOfWrite), " bytes per second");
        result = String.join("",result,"\nlinearSpeedOfRead: ", String.valueOf(linearSpeedOfRead), " bytes per second");
        result = String.join("",result,"\nrandomSpeedOfWrite: ", String.valueOf(randomSpeedOfWrite), " bytes per second");
        result = String.join("",result,"\nrandomSpeedOfRead: ", String.valueOf(randomSpeedOfRead), " bytes per second");
        return result;
    }

    public int compareTo(Disk o) {
        int capacityComp = Long.compare(this.capacity, o.capacity);
        if(capacityComp == 0)
        {
            int randomSpeedOfReadComp = Long.compare(this.randomSpeedOfRead, o.randomSpeedOfRead);
            if(randomSpeedOfReadComp == 0)
            {
                int linearSpeedOfReadComp = Long.compare(this.linearSpeedOfRead, o.linearSpeedOfRead);
                if(linearSpeedOfReadComp == 0)
                {
                    int randomSpeedOfWriteComp = Long.compare(this.randomSpeedOfWrite, o.randomSpeedOfWrite);
                    if(randomSpeedOfWriteComp == 0)
                        return Long.compare(this.linearSpeedOfWrite, o.linearSpeedOfWrite);
                    return randomSpeedOfWriteComp;
                }
                return linearSpeedOfReadComp;
            }
            return randomSpeedOfReadComp;
        }
        return capacityComp;
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
    public long getRandomSpeedOfWrite() {
        return randomSpeedOfWrite;
    }
    /**
     * getter that returns a disk's random speed of read.
     * */
    public long getRandomSpeedOfRead() {
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
        @NotNull
        @Pattern(regexp = "[A-Z][A-Za-z]{2,}", message = "incorrect vendor name")
        protected String vendor;
        @NotNull
        @Min(value = 1000, message = "vendor id must be 4-digits number")
        @Max(value = 9999, message = "vendor id must be 4-digits number")
        protected Integer vid;
        @NotNull
        @Min(value = 10000, message = "disk id must be 5-digits number")
        @Max(value = 99999, message = "disk id must be 5-digits number")
        protected Integer did;
        @NotNull
        @Min(value = 0, message = "capacity must be positive number")
        protected Long capacity;
        @NotNull
        protected PCInterfaces interface_;
        @NotNull
        @Min(value = 0, message = "linear speed of write must be positive number")
        protected Long linearSpeedOfWrite;

        @NotNull
        @Min(value = 0, message = "linear speed of read must be positive number")
        protected Long linearSpeedOfRead;
        @NotNull
        @Min(value = 0, message = "random speed of write must be positive number")
        protected Integer randomSpeedOfWrite;

        @NotNull
        @Min(value = 0, message = "random speed of read must be positive number")
        protected Integer randomSpeedOfRead;

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
        public void validate (){

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set<ConstraintViolation<BuilderDisk>> constraintViolations = validator.validate(this);
            if(constraintViolations.size() > 0) {
                String errors = constraintViolations.stream().map(constraintViolation -> {
                    String fieldName = constraintViolation.getPropertyPath().toString().toUpperCase();
                    String message = constraintViolation.getMessage();
                    return String.join(" : ", fieldName, message);
                }).collect(Collectors.joining("\n"));
                throw new IllegalArgumentException(errors);
            }
        }
        abstract public Disk build();

    }
}
