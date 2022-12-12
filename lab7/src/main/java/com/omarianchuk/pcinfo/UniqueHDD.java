package com.omarianchuk.pcinfo;

public class UniqueHDD {
    public void setHdd(Hdd hdd) {
        this.hdd = hdd;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Hdd getHdd() {
        return hdd;
    }

    public int getId() {
        return id;
    }

    private Hdd hdd;
    private int id;


    UniqueHDD(Disk hdd, int id)
    {
        this.hdd = (Hdd)hdd;
        this.id = id;
    }
}
