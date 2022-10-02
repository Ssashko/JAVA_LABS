package lab1;

import java.util.Objects;

enum FormFactorHdd {
    _3_5,
    _2_5,
    None
}
/**
 * It's a class that describes a HDD disk's parameters.
 * It inherit Disk
 * @author Oleksandr Marianchuk
 * */
public class Hdd extends Disk {
    private final int rotationSpeed;
    private final FormFactorHdd formfactor;
    /**
     * constructor that accepts class-builder.
     * Each field of class-builder must be filled
     * */
    Hdd(BuilderHdd bh) {
        super(bh);
        if(!bh.validate())
            throw new IllegalArgumentException("incorrect Hdd builder");
        this.rotationSpeed = bh.rotationSpeed;
        this.formfactor = bh.formfactor;
    }
    /**
     * getter that returns an enum type of disk's form factor.
     * */
    public FormFactorHdd getFormfactor() {
        return formfactor;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == this)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        if(!super.equals(obj))
            return false;
        Hdd dsk = (Hdd)obj;

        return Objects.equals(rotationSpeed, dsk.rotationSpeed) && Objects.equals(formfactor, dsk.formfactor);
    }
    @Override
    public int hashCode(){

        int result = super.hashCode();
        result = result*31 + rotationSpeed;
        result = result*31 + formfactor.hashCode();
        return result;
    }
    /**
     * getter that returns a disk's rotation speed of read-head above hard platters.
     * */
    public int getRotationSpeed() {
        return rotationSpeed;
    }
    @Override
    public String toString(){
        String result = super.toString();
        result = String.join("",result,"\nrotation speed: ", String.valueOf(rotationSpeed), " RPM");

        result = result + "\nform factor: ";
        switch (formfactor)
        {
            case _2_5 -> result = String.join("",result,"2.5\"");
            case _3_5 -> result = String.join("",result,"3.5\"");
        }
        return result;
    }
    /**
     * It's a builder class that describes a Hdd disk's parameters
     * It inherit Disk.BuilderDisk
     * @author Oleksandr Marianchuk
     * */
    final static public class BuilderHdd extends Disk.BuilderDisk {

        private int rotationSpeed = -1;
        private FormFactorHdd formfactor = FormFactorHdd.None;
        /**
         * setter that assigns a disk's rotation speed of read-head above hard platters.
         * */
        public BuilderHdd setRotationSpeed(int rotationSpeed) {
            this.rotationSpeed = rotationSpeed;
            return this;
        }
        /**
         * setter that assigns an enum type of disk's form factor.
         * */
        public BuilderHdd setFormfactor(FormFactorHdd formfactor) {
            this.formfactor = formfactor;
            return this;
        }

        BuilderHdd() {
            super();
        }
        /**
         * a method that builds a complete object
         * */
        @Override
        public Hdd build()
        {
            return new Hdd(this);
        }
        /**
         * a method that checks the correctness of fields and their completeness
         * */
        @Override
        public boolean validate () {
            return super.validate() && rotationSpeed >= 0 && formfactor != FormFactorHdd.None;
        }

    }
}
