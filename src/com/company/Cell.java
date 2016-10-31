package com.company;

/**
 * Created by Rob on 10/31/2016.
 */
public class Cell {

    int id;
    Cell NW,NE,W,E,SW,SE;

    public Cell() {
        id = 0;
        NW = null;
        NE = null;
        W = null;
        E = null;
        SW = null;
        SE = null;
    }

    public Cell(int id, Cell NW, Cell NE, Cell w, Cell e, Cell SW, Cell SE) {
        this.id = id;
        this.NW = NW;
        this.NE = NE;
        W = w;
        E = e;
        this.SW = SW;
        this.SE = SE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cell getNW() {
        return NW;
    }

    public void setNW(Cell NW) {
        this.NW = NW;
    }

    public Cell getNE() {
        return NE;
    }

    public void setNE(Cell NE) {
        this.NE = NE;
    }

    public Cell getW() {
        return W;
    }

    public void setW(Cell w) {
        W = w;
    }

    public Cell getE() {
        return E;
    }

    public void setE(Cell e) {
        E = e;
    }

    public Cell getSW() {
        return SW;
    }

    public void setSW(Cell SW) {
        this.SW = SW;
    }

    public Cell getSE() {
        return SE;
    }

    public void setSE(Cell SE) {
        this.SE = SE;
    }
}
