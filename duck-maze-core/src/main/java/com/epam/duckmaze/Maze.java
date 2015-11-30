package com.epam.duckmaze;

import com.epam.duckmaze.actionprovider.MazeActionProvider;
import com.epam.duckmaze.dataprovider.MazeCellDataProvider;

import java.util.Set;

/**
 * Created by .
 */
public class Maze {
    private MazeCell mazeCell;
    private MazeActionProvider actionProvider;


    public Maze(MazeCellDataProvider dataProvider, MazeActionProvider actionProvider) {
        mazeCell = dataProvider.loadMaze();
        this.actionProvider = actionProvider;
    }

    public void playMaze() {
        MazeCell currentCell = mazeCell;
        while (true) {
            Set<ActionEnum> availableActions = currentCell.getAvailableActions();
            ActionEnum action = actionProvider.getAction(availableActions);
            currentCell = performAction(currentCell, action);
            if (CellTypeEnum.OUTPUT.equals(currentCell.getCellType())) {
                break;
            }
        }
        System.out.println("Congratulations!!!");
    }

    private MazeCell performAction(MazeCell cell, ActionEnum action) {
        switch (action) {
            case GO_NORTH:
                return cell.getGoNorth();
            case GO_EAST:
                return cell.getGoEast();
            case GO_SOUTH:
                return cell.getGoSouth();
            case GO_WEST:
                return cell.getGoWest();
        }
        throw new IllegalArgumentException("Not supported action");
    }



}
