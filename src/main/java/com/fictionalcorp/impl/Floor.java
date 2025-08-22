package com.fictionalcorp.impl;

import com.fictionalcorp.IFloor;
import com.fictionalcorp.ITile;
import com.fictionalcorp.utils.Token;

import java.util.*;

/*
    Class that represents the Floor map
 */
public class Floor implements IFloor {
    private final int numRows;
    private final int numCols;
    private final ITile[][] matrix;
    private ITile start;
    private final Map<Character, Set<ITile>> teleportationMap;

    public Floor(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.matrix = new Tile[numRows][numCols];
        this.start = null;
        this.teleportationMap = new HashMap<>();
    }

    @Override
    public void readMap(Scanner input) {
        for (int x = 0; x < numRows; x++) {
            String line = input.nextLine();
            for  (int y = 0; y < numCols; y++) {
                char c = line.charAt(y);
                this.matrix[x][y] = new Tile(c, x, y);
                if (c == Token.START) {
                    this.start = this.matrix[x][y];
                    ((Tile) this.start).setVisited(true);
                } else if (c >= Token.TELEPORTER_START && c <= Token.TELEPORTER_END) {
                    this.teleportationMap.putIfAbsent(c, new HashSet<>());
                    this.teleportationMap.get(c).add(this.matrix[x][y]);
                } else if (c == Token.UNBREAKABLE)
                    ((Tile) this.matrix[x][y]).setVisited(true);
            }
        }

        /*
            Set tile neighbors to the north, south, east and west
         */
        for (int x = 0; x < numRows; x++) {
            for  (int y = 0; y < numCols; y++) {
                ITile tile = this.matrix[x][y];
                ((Tile) tile).setNorth(getNorth(x, y));
                ((Tile) tile).setEast(getEast(x, y));
                ((Tile) tile).setWest(getWest(x, y));
                ((Tile) tile).setSouth(getSouth(x, y));
            }
        }
    }

    @Override
    public ITile teleport(final ITile tile) {
        Set<ITile> tiles = this.teleportationMap.get(tile.getValue());
        ITile result = null;
        for (ITile n : tiles) {
            if (!n.equals(tile)) {
                result = n;
                break;
            }
        }
        return result;
    }

    private ITile getNorth(int x, int y) {
        if (x - 1 < 0)
            return null;
        else
            return this.matrix[x - 1][y];
    }

    private ITile getEast(int x, int y) {
        if (y + 1 > numCols - 1)
            return null;
        else
            return this.matrix[x][y + 1];
    }

    private ITile getWest(int x, int y) {
        if (y - 1 < 0)
            return null;
        else
            return this.matrix[x][y - 1];
    }

    private ITile getSouth(int x, int y) {
        if (x + 1 > numRows - 1)
            return null;
        else
            return this.matrix[x + 1][y];
    }

    public ITile getStart() {
        return start;
    }
}
