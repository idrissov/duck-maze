package com.epam.duckmaze.dataprovider;

import com.epam.duckmaze.CellTypeEnum;
import com.epam.duckmaze.MazeCell;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by .
 */
public class FileMazeDataProvider implements MazeCellDataProvider {

    private String filePath;

    public FileMazeDataProvider(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public MazeCell loadMaze() {
        List<String> maze = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                maze.add(line);
            }
            return convertArrayToMaze(maze);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private MazeCell convertArrayToMaze(List<String> mazeFromFile) {
        MazeCell[][] mazeCells = converStringArrayToMazaCell(mazeFromFile);
        return convertToMazeCell(mazeCells);

    }

    private MazeCell[][] converStringArrayToMazaCell(List<String> mazeFromFile) {
        MazeCell[][] temp2Result = new MazeCell[mazeFromFile.size()][mazeFromFile.get(0).length()];
        for (int i = mazeFromFile.size() - 1; i >= 0; i--) {
            String innerArray = mazeFromFile.get(i);
            ArrayList<MazeCell> thisLine = new ArrayList<>();
            for (int j = innerArray.length() - 1; j >= 0; j--) {
                if (innerArray.charAt(j) == '#') {
                    temp2Result[i][j] = null;
                }
                if (innerArray.charAt(j) == '.') {
                    temp2Result[i][j] = new MazeCell();
                }
                if (innerArray.charAt(j) == 'I') {
                    temp2Result[i][j] = new MazeCell(CellTypeEnum.INPUT);
                }
                if (innerArray.charAt(j) == 'O') {
                    temp2Result[i][j] = new MazeCell(CellTypeEnum.OUTPUT);
                }
            }
        }
        return temp2Result;
    }

    private MazeCell convertToMazeCell(MazeCell[][] mazeCells) {
        MazeCell input = null;
        for (int i = mazeCells.length - 1; i >= 0; i--) {
            MazeCell[] innerCellArray = mazeCells[i];
            for (int j = innerCellArray.length - 1; j >= 0; j--) {
                MazeCell mazeCell = innerCellArray[j];
                if (mazeCell != null) {
                    if (CellTypeEnum.INPUT.equals(mazeCell.getCellType())) {
                        input = mazeCell;
                    }
                    List<NeighborCells> availableIndexes = getAvailableIndexes(i, j, mazeCells);
                    for (NeighborCells availableIndex : availableIndexes) {
                        linkMazeCell(availableIndex, mazeCell, mazeCells);
                    }
                }
            }
        }
        return input;
    }

    private void linkMazeCell(NeighborCells neighborCells, MazeCell core, MazeCell[][] mazeCells) {
        switch (neighborCells.getNeighborType()) {
            case NORTH:
                core.setGoNorth(mazeCells[neighborCells.getI()][neighborCells.getJ()]);
                break;
            case EAST:
                core.setGoEast(mazeCells[neighborCells.getI()][neighborCells.getJ()]);
                break;
            case SOUTH:
                core.setGoSouth(mazeCells[neighborCells.getI()][neighborCells.getJ()]);
                break;
            case WEST:
                core.setGoWest(mazeCells[neighborCells.getI()][neighborCells.getJ()]);
                break;
        }
    }

    private List<NeighborCells> getAvailableIndexes(int i, int j, final MazeCell[][] mazeCells) {
        List<NeighborCells> allIndexes = new ArrayList<>();
        allIndexes.add(new NeighborCells(i - 1, j, NeighborTypeEnum.NORTH));
        allIndexes.add(new NeighborCells(i, j + 1, NeighborTypeEnum.EAST));
        allIndexes.add(new NeighborCells(i + 1, j, NeighborTypeEnum.SOUTH));
        allIndexes.add(new NeighborCells(i, j - 1, NeighborTypeEnum.WEST));
        return allIndexes.stream().filter(entry -> checkArrayBorder(entry, mazeCells)).collect(Collectors.toList());
    }

    private boolean checkArrayBorder(NeighborCells coordinates, MazeCell[][] mazeCells) {
        Integer i = coordinates.getI();
        Integer j = coordinates.getJ();
        return !(i < 0 || j < 0) && !(i >= mazeCells.length || j >= mazeCells[i].length);
    }
}