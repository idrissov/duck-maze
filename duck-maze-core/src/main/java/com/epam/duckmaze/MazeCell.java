package com.epam.duckmaze;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by .
 */
public class MazeCell {

    public static final int MAX_COUNT_ACTIONS = 4;

    private MazeCell goNorth;
    private MazeCell goEast;
    private MazeCell goSouth;
    private MazeCell goWest;
    private boolean finish;
    private CellTypeEnum cellType;

    public MazeCell() {
    }

    public MazeCell(CellTypeEnum cellType) {
        this.cellType = cellType;
    }

    public Set<ActionEnum> getAvailableActions() {
        Set<ActionEnum> result = new HashSet<>(MAX_COUNT_ACTIONS);
        if (finish) {
            result.add(ActionEnum.FINISH);
            return result;
        }
        if (goNorth != null) {
            result.add(ActionEnum.GO_NORTH);
        }
        if (goEast != null) {
            result.add(ActionEnum.GO_EAST);
        }
        if (goSouth != null) {
            result.add(ActionEnum.GO_SOUTH);
        }
        if (goWest != null) {
            result.add(ActionEnum.GO_WEST);
        }
        return result;
    }

    public MazeCell getMazeCellByAction(ActionEnum action) {
        switch (action) {
            case GO_NORTH:
                return goNorth;
            case GO_EAST:
                return goEast;
            case GO_WEST:
                return goWest;
            case GO_SOUTH:
                return goSouth;
            default:
                throw new IllegalArgumentException("Not supported action");
        }
    }

    public CellTypeEnum getCellType() {
        return cellType;
    }

    public MazeCell getGoNorth() {
        return goNorth;
    }

    public void setGoNorth(MazeCell goNorth) {
        this.goNorth = goNorth;
    }

    public MazeCell getGoEast() {
        return goEast;
    }

    public void setGoEast(MazeCell goEast) {
        this.goEast = goEast;
    }

    public MazeCell getGoSouth() {
        return goSouth;
    }

    public void setGoSouth(MazeCell goSouth) {
        this.goSouth = goSouth;
    }

    public MazeCell getGoWest() {
        return goWest;
    }

    public void setGoWest(MazeCell goWest) {
        this.goWest = goWest;
    }
}
