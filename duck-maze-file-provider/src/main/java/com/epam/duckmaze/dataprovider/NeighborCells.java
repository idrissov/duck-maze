package com.epam.duckmaze.dataprovider;

/**
 * Created by .
 */
public class NeighborCells {

    private int i;
    private int j;
    private NeighborTypeEnum neighborType;

    public NeighborCells(int i, int j, NeighborTypeEnum neighborType) {
        this.i = i;
        this.j = j;
        this.neighborType = neighborType;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public NeighborTypeEnum getNeighborType() {
        return neighborType;
    }
}
