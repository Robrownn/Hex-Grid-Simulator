package com.company;

/**
 * Created by Rob on 10/31/2016.
 */
public class Cell {

    Cell N,NE,NW,S,SW,SE;

    public Cell() {
        N = null;
        NE = null;
        NW = null;
        S = null;
        SW = null;
        SE = null;
    }

    public Cell(Cell n, Cell NE, Cell NW, Cell s, Cell SW, Cell SE) {
        N = n;
        this.NE = NE;
        this.NW = NW;
        S = s;
        this.SW = SW;
        this.SE = SE;
    }

    public Cell getN() {
        return N;
    }

    public void setN(Cell n) {
        N = n;
    }

    public Cell getNE() {
        return NE;
    }

    public void setNE(Cell NE) {
        this.NE = NE;
    }

    public Cell getNW() {
        return NW;
    }

    public void setNW(Cell NW) {
        this.NW = NW;
    }

    public Cell getS() {
        return S;
    }

    public void setS(Cell s) {
        S = s;
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
