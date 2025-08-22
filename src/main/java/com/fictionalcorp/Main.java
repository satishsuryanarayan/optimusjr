package com.fictionalcorp;

import java.util.List;
import java.util.Scanner;

/*
    Driver class with the main method
 */
public class Main {
    public static void main(String[] args) {
        run(new Scanner(System.in));
    }

    public static void run(Scanner input) {
        String line = input.nextLine();
        String[] tokens = line.split("\\s+");
        IFloor floor = new Floor(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
        floor.readMap(input);
        IRobot robot = new Robot();
        List<Direction> path = robot.visit(floor);
        for (Direction d : path)
            System.out.println(d.name());
    }
}