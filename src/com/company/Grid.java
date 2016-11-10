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

    public void printGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                if (grid[i][j] instanceof Sensor) {
                    Sensor sensor = (Sensor)grid[i][j];
                    System.out.print(sensor.getStatus() + " ");
                }
                else
                    System.out.print("  ");
            }
            System.out.println();
        }
    }
}
