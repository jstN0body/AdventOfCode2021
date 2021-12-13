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

        clearVisited();
        part1(start);
        System.out.println(allPaths.size());
        allPaths.clear();
        currentPath.clear();
        clearVisited();
        part2(start, false);
        System.out.println(allPaths.size());
    }

    public void clearVisited() {
        for (Cave c : Cave.ALL_CAVES.values()) {
            visited.put(c, false);
        }
    }

    public List<LinkedList<Cave>> allPaths = new LinkedList<>();
    LinkedList<Cave> currentPath = new LinkedList<>();

    public void part1(Cave location) {
        if (visited.get(location)) return;

        if (location.type == CaveType.SMALL) {
            visited.put(location, true);
        }
        currentPath.add(location);

        if (location.equals(finish)) {
            allPaths.add(currentPath);
            visited.put(location, false);
            currentPath.removeLast();
            return;
        }
        for (Cave c : location.destinations) {
            part1(c);
        }
        currentPath.removeLast();
        visited.put(location, false);
    }

    public void part2(Cave location, boolean extraVisit) {
        if (currentPath.contains(location) && location.type == CaveType.SMALL) {
            if (!extraVisit && !location.equals(start)) {
                extraVisit = true;
            } else return;
        }
        currentPath.add(location);

        if (location.equals(finish)) {
            allPaths.add(currentPath);
            visited.put(location, false);
            currentPath.removeLast();
            return;
        }
        for (Cave c : location.destinations) {
            part2(c, extraVisit);
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
