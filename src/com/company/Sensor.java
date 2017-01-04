package com.company;

/**
 * Created by Rob on 10/31/2016.
 */
public class Sensor extends Cell {

    int status;
    int updateStatus;
    double battery = 0.8;
    double sleepProbability = 0;
    double wakeProbability = 0;
    boolean isDead = false;

    public Sensor() {
        super();
        this.status = 1;
        this.updateStatus =1;
    }

    public Sensor(int status) {
        this.status = status;
    }

    public Sensor(Cell NW, Cell NE, Cell w, Cell e, Cell SW, Cell SE, int status) {
        super(NW, NE, w, e, SW, SE);
        this.status = status;
        this.updateStatus = 1;
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

    public void setUpdateStatus(int status) {
        this.updateStatus = status;
    }
    public void update() {
        this.status = updateStatus;
    }


}
