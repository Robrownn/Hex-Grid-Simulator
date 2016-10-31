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

    public Sensor(int id, Cell NW, Cell NE, Cell w, Cell e, Cell SW, Cell SE, int status) {
        super(id, NW, NE, w, e, SW, SE);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
