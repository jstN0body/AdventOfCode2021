package com.github.jstN0body.adventofcode.java.day12;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Pathfinder {

    final HashMap<Cave, Boolean> visited =  new HashMap<>();
    final Cave finish;
    final Cave start;

    public Pathfinder(Cave start, Cave finish) {
        this.start = start;
        this.finish = finish;

        for (Cave c : Cave.ALL_CAVES.values()) {
            visited.put(c, false);
        }

        Part1(start);
    }

    public List<LinkedList<Cave>> allPaths = new LinkedList<>();
    LinkedList<Cave> currentPath = new LinkedList<>();

    public void Part1(Cave location) {
        if (visited.getOrDefault(location, false)) return;

        if (location.type == CaveType.SMALL) {
            visited.put(location, true);
        }
        currentPath.add(location);

        if (location.equals(finish)) {
            printPath(currentPath);
            allPaths.add(currentPath);
            visited.put(location, false);
            currentPath.removeLast();
            return;
        }
        for (Cave c : location.destinations) {
            Part1(c);
        }
        currentPath.removeLast();
        visited.put(location, false);
    }

    public void Part2(Cave location, boolean extraVisit) {
        if (visited.get(location)) { // why doesn't this work :(
            if (!extraVisit && !location.equals(start)) {
                extraVisit = true;
            } else {
                return;
            }
        }

        if (location.type == CaveType.SMALL) visited.put(location, true);
        currentPath.add(location);

        if (location.equals(finish)) {
            printPath(currentPath);
            allPaths.add(currentPath);
            visited.put(location, false);
            currentPath.removeLast();
            return;
        }
        for (Cave c : location.destinations) {
            Part2(c, extraVisit);
        }
        currentPath.removeLast();
        visited.put(location, false);
    }

    public void printPath(LinkedList<Cave> path) {
        System.out.println();
        for (Cave c : path) {
            System.out.print(c.name + " ");
        }
    }
}
