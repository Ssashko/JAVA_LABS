package lab5;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * It's a class that describes a Motherboard parameters.
 * @author Oleksandr Marianchuk
 * */
public class Motherboard {
    final private int vid;
    final private int pid;
    final private String vendor;
    private List<Disk> disks;
    private Cpu cpu;

    public Cpu getCpu() {
        return cpu;
    }

    public List<Ram> getRams() {
        return rams;
    }

    public List<Disk> getDisks() {
        return disks;
    }

    private List<Ram> rams;

    Motherboard(int vid, int pid, String vendor)
    {
        this.vid = vid;
        this.pid = pid;
        this.vendor = vendor;
        disks = new ArrayList<>();
        rams = new ArrayList<>();
    }

    public int getVid() {
        return vid;
    }

    public int getPid() {
        return pid;
    }

    public String getVendor() {
        return vendor;
    }

    public boolean addDisk(Disk dsk) {
        return disks.add(dsk);
    }

    public boolean addRam (Ram dsk) {
        return rams.add(dsk);
    }

    public Disk getDisk (int index) {
        return disks.get(index);
    }

    public Ram getRam (int index) {
        return rams.get(index);
    }

    public boolean plugCpu (Cpu cpu) {
        if(this.cpu != null)
            return false;
        this.cpu = cpu;
        return true;
    }
    public boolean unPlugCpu() {
        if(this.cpu == null)
            return false;
        this.cpu = null;
        return true;
    }

    /**
     * It's a builder class that describes Motherboard parameters
     * @author Oleksandr Marianchuk
     * */
    public static class buildMotherboard {
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
        private String vendor;

        public buildMotherboard setVid(int vid) {
            this.vid = vid;
            return this;
        }

        public buildMotherboard setPid(int pid) {
            this.pid = pid;
            return this;
        }

        public buildMotherboard setVendor(String vendor) {
            this.vendor = vendor;
            return this;
        }
        public Motherboard build () {
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set<ConstraintViolation<buildMotherboard>> constraintViolations = validator.validate(this);
            if(constraintViolations.size() > 0) {
                String errors = constraintViolations.stream().map(constraintViolation -> {
                    String fieldName = constraintViolation.getPropertyPath().toString().toUpperCase();
                    String message = constraintViolation.getMessage();
                    return String.join(" : ", fieldName, message);
                }).collect(Collectors.joining("\n"));
                throw new IllegalArgumentException(errors);
            }
            return new Motherboard(this.vid, this.pid, this.vendor);
        }
    }
}
