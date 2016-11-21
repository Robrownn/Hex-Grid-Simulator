package com.company;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("grid.txt"));
        int rows = in.nextInt();
        int cols = in.nextInt();

        Cell[][] gridArray = new Cell[rows][cols];
        Grid grid = new Grid();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int status = in.nextInt();
                if (status == -1)
                    gridArray[i][j] = new Cell();
                else
                    gridArray[i][j] = new Sensor(status);
            }
        }

        grid.setGrid(gridArray);

        grid.printGrid();

        // This section gathers neighbours for each sensor. If the neighbouring area does not have a sensor there it will be a null entry
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (gridArray[i][j] instanceof Sensor) {
                    Sensor NW = null;
                    if (!(i-1 < 0) && !(j-1 < 0))
                        NW = (Sensor) gridArray[i - 1][j - 1];

                    Sensor N = null;
                    if (!(i-2 < 0))
                        N  = (Sensor) gridArray[i - 2][j];

                    Sensor NE = null;
                    if (!(i-1 < 0) && !(j+1 >= cols))
                        NE = (Sensor) gridArray[i - 1][j + 1];

                    Sensor SW = null;
                    if (!(i+1 >= rows) && !(j-1 < 0))
                        SW = (Sensor) gridArray[i + 1][j - 1];

                    Sensor S = null;
                    if (!(i+2 >= rows))
                        S  = (Sensor) gridArray[i + 2][j];

                    Sensor SE = null;
                    if (!(i+1 >= rows) && !(j+1 >= cols))
                        SE = (Sensor) gridArray[i + 1][j + 1];

                    Sensor[] neighbours = {NW, N, NE, SW, S, SE};
                    Sensor sensor = (Sensor)gridArray[i][j];
                    sensor.setNeighbours(neighbours);
                } else {}
            }
        }

        // We must count the amount of sensors in the grid to know when the algorithm should stop: when all sensors are dead
        int sensorCount = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (gridArray[i][j] instanceof Sensor)
                    sensorCount++;
            }
        }

        // We initialize a counter and increment it every time a sensor dies.
        int deadCount = 0;

        // The algorithm stops when all sensors are dead
        while (deadCount < sensorCount) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (gridArray[i][j] instanceof Sensor) {
                        Sensor sensor = (Sensor) gridArray[i][j];
                        if (!sensor.isDead) {                               // if the sensor is dead the algorithm does not run
                            Sensor[] neighbours = sensor.getNeighbours();

                            int k;
                            for (k = 0; k < neighbours.length; k++) {
                                Sensor neighbour = neighbours[k];
                                if (neighbour == null)
                                    continue;

                                if (neighbour.getStatus() == 1) {
                                    sensor.setStatus(0);
                                    break;
                                }
                            }

                            if (k < neighbours.length)
                                break;
                            else
                                sensor.setStatus(1);

                            if (sensor.getStatus() == 1)
                                sensor.battery -= 0.0165;
                            else
                                sensor.battery -= 0.00006;

                            if (sensor.battery <= 0) {
                                sensor.isDead = true;
                                deadCount++;
                                sensor.setStatus(0);
                            }


                            // sleepCrit is the value in which the sensor determines if it should go to sleep or not
                            double sleepCrit = Math.random();

                            // if the sensor is on and its probability exceeds the randomly generated value then it goes to sleep and resets its sleep probability
                            if (sensor.getStatus() == 1 && sensor.sleepProbability > sleepCrit) {
                                sensor.setStatus(0);
                                sensor.sleepProbability = 0;
                            } else if (sensor.getStatus() == 1 && sensor.sleepProbability < sleepCrit) // otherwise we increment its probability of going to sleep by 0.05.
                                sensor.sleepProbability += 0.05;
                        }
                    }
                }
            }
        }

        System.out.println("Algorithm says...");
        grid.printGrid();


    }
}
