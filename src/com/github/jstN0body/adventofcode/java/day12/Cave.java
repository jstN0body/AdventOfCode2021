package com.github.jstN0body.adventofcode.java.day12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cave {

    public static final HashMap<String, Cave> ALL_CAVES = new HashMap<>();

    public final String name;
    public final CaveType type;
    public final List<Cave> destinations = new ArrayList<>();

    public Cave(String name, CaveType type) {
        this.name = name;
        this.type = type;

        ALL_CAVES.putIfAbsent(name, this);
    }

}
