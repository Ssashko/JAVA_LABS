package com.omarianchuk.pcinfo;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * It's a class that describes a Cpu parameters.
 * @author Oleksandr Marianchuk
 * */
public class Cpu implements Comparable<Cpu> {
    final private int vid;
    final private String vendor;
    final private int pip;
    final private String name;
    final private SoketType soketType;
    final private long frequency;
    final private int coreCount;
    final private boolean hyperThreading;
    final private int l1CacheCapacity;
    final private int l2CacheCapacity;
    final private int l3CacheCapacity;

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
        @NotNull
        @Min(value = 1000, message = "vendor id must be 4-digits number")
        @Max(value = 9999, message = "vendor id must be 4-digits number")
        private Integer vid;
        @NotNull
        @Pattern(regexp = "[A-Z][A-Za-z]{2,}", message = "incorrect vendor name")
        private String vendor;
        @NotNull
        @Min(value = 10000, message = "product id must be 5-digits number")
        @Max(value = 99999, message = "product id must be 5-digits number")
        private Integer pip;
        @NotNull
        @Pattern(regexp = "[A-Z][A-Za-z0-9\\s]{4,}", message = "incorrect vendor name")
        private String name;
        @NotNull
        private SoketType soketType;
        @NotNull
        @Min(value = 0, message = "frequency must be positive number")
        Long frequency;
        @NotNull
        @Min(value = 0, message = "frequency must be positive number")
        Integer coreCount;
        @NotNull
        Boolean hyperThreading;
        @NotNull
        @Min(value = 0, message = "l1 cache capacity must be positive number")
        Integer l1CacheCapacity;
        @NotNull
        @Min(value = 0, message = "l2 cache capacity must be positive number")
        Integer l2CacheCapacity;
        @NotNull
        @Min(value = 0, message = "l3 cache capacity must be positive number")
        Integer l3CacheCapacity;

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
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set<ConstraintViolation<CpuBuilder>> constraintViolations = validator.validate(this);
            if(constraintViolations.size() > 0) {
                String errors = constraintViolations.stream().map(constraintViolation -> {
                    String fieldName = constraintViolation.getPropertyPath().toString().toUpperCase();
                    String message = constraintViolation.getMessage();
                    return String.join(" : ", fieldName, message);
                }).collect(Collectors.joining("\n"));
                throw new IllegalArgumentException(errors);
            }
            return new Cpu(vid, vendor, pip, name, soketType, frequency, coreCount, hyperThreading, l1CacheCapacity,
                    l2CacheCapacity, l3CacheCapacity);
        }
    }

}
