package com.company;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("grid11.txt"));
        int rows = in.nextInt();
        int cols = in.nextInt();
        int[][] directions = {
                {-1,-1},{-1,0},{-1,1},
                {0,-1},{0,0},{0,1},
                {1,-1},{1,0},{1,1}
        };

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
                }
            }
        }

        // We must count the amount of sensors in the grid to know when the algorithm should stop: when all sensors are dead
        int sensorCount = 0;
        int awakeSensor = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (gridArray[i][j] instanceof Sensor)
                    sensorCount++;
            }
        }

        System.out.println(sensorCount); //Test statement to check how many sensors are in the network

        for (int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if (gridArray[i][j] instanceof Sensor) {
                    Sensor sensor = (Sensor)gridArray[i][j];
                    if (sensor.getStatus() == 1)
                        awakeSensor++;
                }
            }
        }

        System.out.println(awakeSensor);




        // We initialize a counter and increment it every time a sensor dies.
        int deadCount = 0;
        int totalCoverage = 0;

        int t = 0;
        // The algorithm stops when all sensors are dead
        while (deadCount < sensorCount) {
            int cellsCovered = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (gridArray[i][j] instanceof Sensor) {
                        Sensor sensor = (Sensor) gridArray[i][j];
                        if (!sensor.isDead && sensor.getStatus() == 1) {

                            int awakeCount = countAwakeNeighbours(sensor); // count number of awake neighbours


                            //radiusOne(sensor, awakeCount); // run radius one algorithm

                            // sleepCrit is the value in which the sensor determines if it should go to sleep or not
                            double sleepCrit = Math.random();
                            double forceSleep = Math.random();

                            // if the sensor is on and its probability exceeds the randomly generated value then it goes to sleep and resets its sleep probability
                            if (sensor.getStatus() == 1) {
                                sensor.battery -= 0.0165;
                                if (sensor.battery <= 0) {
                                    sensor.isDead = true;
                                    deadCount++;
                                    sensor.setUpdateStatus(0);
                                }
                                else if (sensor.sleepProbability > sleepCrit) {
                                    sensor.setUpdateStatus(0);
                                    sensor.sleepProbability = 0;
                                }
                                else if (forceSleep <= 0.2 && awakeCount >= 1) {
                                    sensor.setUpdateStatus(0);
                                    sensor.sleepProbability = 0;
                                }
                                // otherwise we increment its probability of going to sleep by 0.05.
                                else
                                    sensor.sleepProbability += 0.05;
                            }
                            else
                                sensor.battery -= 0.00006;

                            // count cells being covered.
                            //cellsCovered += countSurroundings(directions,gridArray,i,j);
                            //totalCoverage += cellsCovered;
                        }
                        else if (!sensor.isDead && sensor.getStatus() == 0) {
                            int awakeCount = countAwakeNeighbours(sensor);
                            double forceWake = Math.random();


                            if (forceWake <= 0.2 && awakeCount < 1) {
                                sensor.setUpdateStatus(1);

                                sensor.battery -= 0.00006;

                                if (sensor.battery <= 0) {
                                    sensor.isDead = true;
                                    deadCount++;
                                    sensor.setUpdateStatus(0);
                                }
                            }
                        }

                    }
                }
            }
            update(rows,cols,gridArray); // Update the status of each sensor
            t++;
            int coverage = 0;
            for (int m = 0; m < rows; m++) {
                for (int n = 0; n < cols; n++) {
                    if(gridArray[m][n] instanceof Sensor) {

                        Sensor sensor = (Sensor)gridArray[m][n];

                        if (sensor.getStatus() == 1 || countAwakeNeighbours(sensor) >= 1 || countAwakeNeighboursHelper(sensor.getNeighbours()))
                            coverage++;
                    }

                }
            }

            System.out.println(coverage + " ");
            //System.out.print("(" + t + ")\n");

            awakeSensor = 0;
            for (int k = 0; k < rows; k++) {
                for(int l = 0; l < cols; l++) {
                    if (gridArray[k][l] instanceof Sensor) {
                        Sensor sensor = (Sensor)gridArray[k][l];
                        if (sensor.getStatus() == 1)
                            awakeSensor++;
                    }
                }
            }

            //System.out.println(awakeSensor);
        }
        System.out.println("[" + totalCoverage + "]");
        System.out.println("{" + t + "}");


    }
    /*public static void radiusOne(Sensor sensor, int awakeCount) {
        Sensor[] neighbours = sensor.getNeighbours();

        int k;
        for (k = 0; k < neighbours.length; k++) {
            Sensor neighbour = neighbours[k];
            if (neighbour == null)
                continue;

            if (neighbour.getStatus() == 1) {
                sensor.setStatus(0);
                awakeCount++;
                break;
            }
        }

        if (awakeCount < 1)
            sensor.setUpdateStatus(1);
        else
            sensor.setUpdateStatus(0);
    }*/

    /*static int countSurroundings(int[][] directions, Cell[][] matrix, int x, int y) {
        int count = 0;
        for (int[] direction : directions) {
            int cx = x + direction[0];
            int cy = y + direction[1];
            if (cy >= 0 && cy < matrix.length)
                if (cx >= 0 && cx < matrix[cy].length && matrix[cy][cx] instanceof Sensor)
                    count++;
        }

        return count;
    }*/

    static void update(int rows, int cols, Cell[][] gridArray) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (gridArray[i][j] instanceof Sensor) {
                    Sensor sensor = (Sensor)gridArray[i][j];
                    sensor.update();
                }
            }
        }
    }

    public static int countAwakeNeighbours(Sensor sensor) {
        Sensor[] neighbours = sensor.getNeighbours();
        int awakeCount = 0;

        for (int i = 0; i < neighbours.length; i++) {

            if (neighbours[i] == null)
                continue;

            if (neighbours[i].getStatus() == 1)
                awakeCount++;
        }

        return awakeCount;
    }

    /* This is a helper method to facilitate the use of counting a sensors neighbours neighbours.
       it takes in a sensor array which would be the source sensors list of neighbours,
       and then iterates through that list of neighbours to count the amount of awake neighbours that that sensor has.

       If the number of awake sensors is greater than 0 then I can return true (because that means at least one sensor is covering the source)
       otherwise we continue until a neighbour has an awake neighbour.
       If no neighbours are awake then we return false which means there is no sensor a radius of 2 away covering the source node.
     */
    public static boolean countAwakeNeighboursHelper(Sensor[] sensors) {

        for (int i =0; i < sensors.length; i++) {
            int awakeCount = countAwakeNeighbours(sensors[i]);

            if (awakeCount > 0)
                return true;
        }

        return false;
    }

}
