package com.epam.duckmaze;

import com.epam.duckmaze.actionprovider.KeyboardActionProvider;
import com.epam.duckmaze.dataprovider.FileMazeDataProvider;

/**
 * Created by .
 */
public class Main {

    public static void main(String[] args) {
        Maze maze = new Maze(new FileMazeDataProvider(""), new KeyboardActionProvider());
        maze.playMaze();
    }
}
