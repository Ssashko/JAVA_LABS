package lab6;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.stream.Collectors;

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
    final private int vid;
    final private int pid;
    final private String Vendor;
    final private long capacity;
    final private int rank;
    final private long frequency;
    final private RamType ramType;
    final private RamFormFactor ramFormFactor;

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

        @NotNull
        @Min(value = 1000, message = "vendor id must be 4-digits number")
        @Max(value = 9999, message = "vendor id must be 4-digits number")
        private Integer vid;
        @NotNull
        @Min(value = 10000, message = "product id must be 5-digits number")
        @Max(value = 99999, message = "product id must be 5-digits number")
        private Integer pid;
        @NotNull
        @Pattern(regexp = "[A-Z][A-Za-z]{2,}", message = "incorrect vendor name")
        private String Vendor;
        @NotNull
        @Min(value = 0, message = "capacity must be positive number")
        private Long capacity;
        @NotNull
        @Min(value = 0, message = "rank must be positive number")
        private Integer rank;
        @NotNull
        @Min(value = 0, message = "frequency must be positive number")
        private Long frequency;
        @NotNull
        private RamType ramType;
        @NotNull
        private RamFormFactor ramFormFactor;

        public RamBuilder() {
            vid = null;
        }

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
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set<ConstraintViolation<Ram.RamBuilder>> constraintViolations = validator.validate(this);
            if(constraintViolations.size() > 0) {
                String errors = constraintViolations.stream().map(constraintViolation -> {
                    String fieldName = constraintViolation.getPropertyPath().toString().toUpperCase();
                    String message = constraintViolation.getMessage();
                    return String.join(" : ", fieldName, message);
                }).collect(Collectors.joining("\n"));
                throw new IllegalArgumentException(errors);
            }
            return new Ram(vid, pid, Vendor, capacity, rank, frequency, ramType, ramFormFactor);
        }
    }
}

