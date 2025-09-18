package com.wither.simplymartians.entities;

import java.util.Arrays;
import java.util.Comparator;

public enum MartianVariant {
    RED(0),
    GREEN(1),
    BLUE(2),
    GRAY(3);


    private static final MartianVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(MartianVariant::getId)).toArray(MartianVariant[]::new);
    private final int id;

    MartianVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static MartianVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}