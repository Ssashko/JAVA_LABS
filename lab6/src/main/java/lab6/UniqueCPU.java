package lab6;

public class UniqueCPU {

    public void setCpu(Cpu cpu) {
        this.cpu = cpu;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cpu getCpu() {
        return cpu;
    }

    public int getId() {
        return id;
    }

    private Cpu cpu;
    private int id;


    UniqueCPU(Cpu cpu, int id)
    {
        this.cpu = cpu;
        this.id = id;
    }

}
