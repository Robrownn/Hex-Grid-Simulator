package com.company;

/**
 * Created by Rob on 10/31/2016.
 */
public class Grid {

    Cell[][] grid;

    public Grid() {
        grid = null;
    }
    public Grid(Cell[][] grid) {
        this.grid = grid;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public void setGrid(Cell[][] grid) {
        this.grid = grid;
    }
}
