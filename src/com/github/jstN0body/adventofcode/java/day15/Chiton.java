package com.github.jstN0body.adventofcode.java.day15;

import java.util.ArrayList;
import java.util.LinkedList;

public class Chiton {

    public static void main(String[] args) {
        int[][] grid = new int[args.length][args[0].length()];
        Point.ALL_POINTS = new Point[args.length][args[0].length()];
        for (int y = 0; y < args.length; y++) {
            for (int x =  0; x < args[0].length(); x++) {
                int i = Character.getNumericValue(args[y].charAt(x));
                grid[y][x] = i;
                Point.ALL_POINTS[y][x] = new Point(x, y, i);
            }
        }
        for (Point[] ar : Point.ALL_POINTS) {
            for (Point p : ar) {
                p.addNeighbors();
            }
        }
        System.out.println(safestPath());
    }

    static int lowestRisk;
    public static int safestPath() {

        lowestRisk = 999999999;
        DFS(Point.ALL_POINTS[0][0], Point.ALL_POINTS[Point.ALL_POINTS.length-1][Point.ALL_POINTS[0].length-1], new LinkedList<>());
        return lowestRisk;
    }

    public static void DFS(Point location, Point end, LinkedList<Point> currentPath) {
        if (currentPath.contains(location)) return;

        currentPath.add(location);
        if (location.equals(end)) {
            int risk = getRisk(currentPath);
            lowestRisk = Math.min(lowestRisk, risk);
            System.out.println(lowestRisk + "   " + risk);
            currentPath.removeLast();
            return;
        }

        for (Point p : location.neighbors) {
            DFS(p, end, currentPath);
        }
        currentPath.removeLast();
    }

    public static int getRisk(LinkedList<Point> path) {
        int risk = 0;
        for (int i = 1; i < path.size(); i++) {
            Point p = path.get(i);
            risk += p.value;
        }
        return risk;
    }
}

class Point {

    public static Point[][] ALL_POINTS;

    public final int x, y, value;
    public final ArrayList<Point> neighbors = new ArrayList<>();

    public Point(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.value = val;
    }

    public void addNeighbors() {
        //if (x > 0) neighbors.add(ALL_POINTS[y][x-1]);
        //if (y > 0) neighbors.add(ALL_POINTS[y-1][x]);
        if (y < ALL_POINTS.length-1) neighbors.add(ALL_POINTS[y+1][x]);
        if (x < ALL_POINTS[0].length-1) neighbors.add(ALL_POINTS[y][x+1]);
    }

    public int sum() {
        int sum = 0;
        for (Point p : neighbors) {
            sum += p.value;
        }
        if (neighbors.size() == 1) sum += 9;
        return sum;
    }
}
