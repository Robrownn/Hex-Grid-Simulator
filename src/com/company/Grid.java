package com.company;

/**
 * Created by Rob on 10/31/2016.
 */
public class Grid {

    Sensor[] sensors;

    public Grid() {
        sensors = null;
    }
    public Grid(Sensor[] sensors) {
        this.sensors = sensors;
    }

    public Sensor[] getSensors() {
        return sensors;
    }

    public void setSensors(Sensor[] sensors) {
        this.sensors = sensors;
    }
}
