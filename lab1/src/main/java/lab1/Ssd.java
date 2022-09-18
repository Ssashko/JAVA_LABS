package lab1;
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
final public class Ssd extends Disk {
    private final MemoryTypeSsd memoryType;
    private final FormFactorSsd formfactor;

    Ssd(BuilderSsd bs){
        super(bs);
        this.memoryType = bs.memoryType;
        this.formfactor = bs.formfactor;
    }

    public MemoryTypeSsd getMemoryType() {
        return memoryType;
    }

    public FormFactorSsd getFormfactor() {
        return formfactor;
    }
    @Override
    public String toString(){
        String result = super.toString();
        result += "\nmemory type: ";
        switch (memoryType)
        {
            case SLS -> result += "SLS";
            case MLS -> result += "MLS";
            case TLS -> result += "TLS";
            case QLS -> result += "QLS";
        }
        result += "\nform factor: ";
        switch (formfactor)
        {
            case _2_5 -> result += "2.5\"";
            case M_2 -> result += "M.2\"";
        }
        return result;
    }
    final static public class BuilderSsd extends Disk.BuilderDisk {

        private MemoryTypeSsd memoryType = MemoryTypeSsd.None;
        private FormFactorSsd formfactor = FormFactorSsd.None;

        public BuilderSsd setMemoryType(MemoryTypeSsd memoryType) {
            this.memoryType = memoryType;
            return this;
        }

        public BuilderSsd setFormfactor(FormFactorSsd formfactor) {
            this.formfactor = formfactor;
            return this;
        }
        BuilderSsd() {
            super();
        }

        public Ssd build()
        {
            return new Ssd(this);
        }
        @Override
        public boolean validate () {
            return super.validate() && memoryType != MemoryTypeSsd.None && formfactor != FormFactorSsd.None;
        }

    }

}
