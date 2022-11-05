package lab3;

import java.util.Objects;

enum FormFactorSsd {
    _2_5,
    M_2,
    None
}
enum MemoryTypeSsd {
    SLS,
    MLS,
    TLS,
    QLS,
    None
}
/**
 * It's a class that describes a Ssd disk's parameters.
 * It inherit Disk
 * @author Oleksandr Marianchuk
 * */
final public class Ssd extends Disk implements Comparable<Ssd>  {
    private final MemoryTypeSsd memoryType;
    private final FormFactorSsd formfactor;
    /**
     * constructor that accepts class-builder.
     * Each field of class-builder must be filled
     * */
    Ssd(BuilderSsd bs){
        super(bs);
        if(!bs.validate())
            throw new IllegalArgumentException("incorrect Ssd builder");
        this.memoryType = bs.memoryType;
        this.formfactor = bs.formfactor;
    }
    /**
     * getter that returns an enum type of disk's memory type.
     * */
    public MemoryTypeSsd getMemoryType() {
        return memoryType;
    }
    /**
     * getter that returns an enum type of disk's form factor.
     * */
    public FormFactorSsd getFormfactor() {
        return formfactor;
    }
    @Override
    public String toString(){
        String result = super.toString();
        result = String.join("",result,"\nmemory type: ");
        switch (memoryType)
        {
            case SLS -> result = String.join("",result,"SLS");
            case MLS -> result = String.join("",result,"MLS");
            case TLS -> result = String.join("",result,"TLS");
            case QLS -> result = String.join("",result,"QLS");
        }
        result = String.join("",result,"\nform factor: ");
        switch (formfactor)
        {
            case _2_5 -> result = String.join("",result,"2.5\"");
            case M_2 -> result = String.join("",result,"M.2\"");
        }
        return result;
    }
    @Override
    public boolean equals(Object obj){
        if(obj == this)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        if(!super.equals(obj))
            return false;
        Ssd dsk = (Ssd)obj;

        if(!Objects.equals(memoryType,dsk.memoryType) || !Objects.equals(formfactor,dsk.formfactor))
            return false;
        return true;
    }
    @Override
    public int hashCode(){
        int result = super.hashCode();
        result = result*31 + memoryType.hashCode();
        result = result*31 + formfactor.hashCode();
        return result;
    }
    private int getMemoryTypePriority(MemoryTypeSsd type) {
        return switch (type) {
            case SLS -> 4;
            case MLS -> 3;
            case TLS -> 2;
            case QLS -> 1;
            default -> -1;
        };
    }
    @Override
    public int compareTo(Ssd o) {
        int DiskComp = super.compareTo(o);
        if(DiskComp == 0)
            return Integer.compare(getMemoryTypePriority(this.memoryType), getMemoryTypePriority(o.memoryType));
        return DiskComp;
    }

    /**
     * It's a builder class that describes a Hdd disk's parameters
     * It inherit Disk.BuilderDisk
     * @author Oleksandr Marianchuk
     * */
    final static public class BuilderSsd extends Disk.BuilderDisk {

        private MemoryTypeSsd memoryType = MemoryTypeSsd.None;
        private FormFactorSsd formfactor = FormFactorSsd.None;
        /**
         * setter that assigns an enum type of disk's memory type.
         * */
        public BuilderSsd setMemoryType(MemoryTypeSsd memoryType) {
            this.memoryType = memoryType;
            return this;
        }
        /**
         * setter that assigns an enum type of disk's form factor.
         * */
        public BuilderSsd setFormfactor(FormFactorSsd formfactor) {
            this.formfactor = formfactor;
            return this;
        }
        BuilderSsd() {
            super();
        }
        /**
         * a method that builds a complete object
         * */
        @Override
        public Ssd build()
        {
            return new Ssd(this);
        }
        /**
         * a method that checks the correctness of fields and their completeness
         * */
        @Override
        public boolean validate () {
            return super.validate() && memoryType != MemoryTypeSsd.None && formfactor != FormFactorSsd.None;
        }

    }

}
