package lab6;

public class UniqueRAM {
    public void setRam(Ram ram) {
        this.ram = ram;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ram getRam() {
        return ram;
    }

    public int getId() {
        return id;
    }

    private Ram ram;
    private int id;


    UniqueRAM(Ram ram, int id)
    {
        this.ram = ram;
        this.id = id;
    }
}