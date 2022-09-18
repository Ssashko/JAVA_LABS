package lab1;
enum FormFactorHdd {
    _3_5,
    _2_5,
    None
}
public class Hdd extends Disk {
    private final int rotationSpeed;
    private final FormFactorHdd formfactor;

    Hdd(BuilderHdd bh) {
        super(bh);
        this.rotationSpeed = bh.rotationSpeed;
        this.formfactor = bh.formfactor;
    }

    public FormFactorHdd getFormfactor() {
        return formfactor;
    }

    public int getRotationSpeed() {
        return rotationSpeed;
    }
    @Override
    public String toString(){
        String result = super.toString();
        result += "\nrotation speed: " + rotationSpeed + " RPM";

        result += "\nform factor: ";
        switch (formfactor)
        {
            case _2_5 -> result += "2.5\"";
            case _3_5 -> result += "3.5\"";
        }
        return result;
    }
    final static public class BuilderHdd extends Disk.BuilderDisk {

        private int rotationSpeed = -1;
        private FormFactorHdd formfactor = FormFactorHdd.None;

        public BuilderHdd setRotationSpeed(int rotationSpeed) {
            this.rotationSpeed = rotationSpeed;
            return this;
        }

        public BuilderHdd setFormfactor(FormFactorHdd formfactor) {
            this.formfactor = formfactor;
            return this;
        }

        BuilderHdd() {
            super();
        }

        public Hdd build()
        {
            return new Hdd(this);
        }
        @Override
        public boolean validate () {
            return super.validate() && rotationSpeed >= 0 && formfactor != FormFactorHdd.None;
        }

    }
}
