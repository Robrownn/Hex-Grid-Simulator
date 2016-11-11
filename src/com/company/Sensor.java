package com.company;

/**
 * Created by Rob on 10/31/2016.
 */
public class Sensor extends Cell {

    int status;

    public Sensor() {
        super();
        this.status = 1;
    }

    public Sensor(int status) {
        this.status = status;
    }

    public Sensor(Cell NW, Cell NE, Cell w, Cell e, Cell SW, Cell SE, int status) {
        super(NW, NE, w, e, SW, SE);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setNeighbours(Sensor[] neighbours) {
        this.setNW(neighbours[0]);
        this.setN(neighbours[1]);
        this.setNE(neighbours[2]);
        this.setSW(neighbours[3]);
        this.setS(neighbours[4]);
        this.setSE(neighbours[5]);
    }

    public Sensor[] getNeighbours() {
        Sensor NW = (Sensor)this.getNW();
        Sensor N  = (Sensor)this.getN();
        Sensor NE = (Sensor)this.getNE();
        Sensor SW = (Sensor)this.getSW();
        Sensor S  = (Sensor)this.getS();
        Sensor SE = (Sensor)this.getSE();

        Sensor[] neighbours = {NW,N,NE,SW,S,SE};
        return neighbours;
    }


}
