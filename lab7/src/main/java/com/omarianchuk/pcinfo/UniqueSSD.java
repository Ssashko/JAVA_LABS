package com.omarianchuk.pcinfo;

public class UniqueSSD {
    public void setSsd(Ssd ssd) {
        this.ssd = ssd;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ssd getSsd() {
        return ssd;
    }

    public int getId() {
        return id;
    }

    private Ssd ssd;
    private int id;


    UniqueSSD(Disk ssd, int id)
    {
        this.ssd = (Ssd)ssd;
        this.id = id;
    }
}
