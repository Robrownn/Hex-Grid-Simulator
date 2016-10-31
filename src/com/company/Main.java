package com.company;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("grid.txt"));
        int size = in.nextInt();
        Sensor[] sensors = new Sensor[size];
        Grid grid = new Grid(sensors);
    }
}
