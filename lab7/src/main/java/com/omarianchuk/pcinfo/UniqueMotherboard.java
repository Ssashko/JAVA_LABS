package com.omarianchuk.pcinfo;

public class UniqueMotherboard {
    public void setMotherboard(Motherboard motherboard) {
        this.motherboard = motherboard;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Motherboard getMotherboard() {
        return motherboard;
    }

    public int getId() {
        return id;
    }

    private Motherboard motherboard;
    private int id;


    public UniqueMotherboard(Motherboard motherboard, int id)
    {
        this.motherboard = motherboard;
        this.id = id;
    }
}