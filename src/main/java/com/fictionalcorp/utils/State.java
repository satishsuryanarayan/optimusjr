package com.fictionalcorp.utils;

import com.fictionalcorp.IFloor;
import com.fictionalcorp.ITile;

import java.util.ArrayList;
import java.util.List;

public final class State {
    private final IFloor floor;
    private boolean invertedMode;
    private boolean breakMode;
    private ITile currentTile;
    private final Direction[] compass;
    private int nextDirection;
    private final List<Direction> path;

    public State(final IFloor floor) {
        this.floor = floor;
        this.compass = new Direction[]{Direction.SOUTH, Direction.EAST, Direction.NORTH, Direction.WEST};
        this.invertedMode = false;
        this.breakMode = false;
        this.currentTile = floor.getStart();
        this.nextDirection = 0;
        this.path = new ArrayList<>();
    }

    public ITile getNext() {
        ITile nextTile = getTile(nextDirection);
        while ((nextTile.isVisited() || (nextTile.getValue() == Token.BREAKABLE && !breakMode)) && canProceed()) {
            changeDirection();
            nextTile = getTile(nextDirection);
        }

        if (nextTile.getValue() == Token.BREAKABLE && !breakMode)
            return currentTile;

        recordPath();

        if (nextTile.getValue() == Token.BREAKER)
            breakMode = !breakMode;
        else if (nextTile.getValue() == Token.INVERTER)
            invertedMode = !invertedMode;
        else if (nextTile.getValue() == Token.SOUTH)
            nextDirection = 0;
        else if (nextTile.getValue() == Token.EAST)
            nextDirection = 1;
        else if (nextTile.getValue() == Token.NORTH)
            nextDirection = 2;
        else if (nextTile.getValue() == Token.WEST)
            nextDirection = 3;
        else if (nextTile.getValue() >= Token.TELEPORTER_START && nextTile.getValue() <= Token.TELEPORTER_END)
            nextTile = floor.teleport(nextTile);

        currentTile = nextTile;

        return nextTile;
    }

    public List<Direction> getPath() {
        return path;
    }

    private void recordPath() {
        path.add(compass[nextDirection]);
    }

    private boolean canProceed() {
        ITile south = currentTile.getSouth();
        boolean southVisited = south.isVisited();
        if (!southVisited && south.getValue() == Token.BREAKABLE && !breakMode)
            southVisited = true;

        if (currentTile.getValue() == Token.SOUTH && southVisited)
            return false;

        ITile east = currentTile.getEast();
        boolean eastVisited = east.isVisited();
        if (!eastVisited && east.getValue() == Token.BREAKABLE && !breakMode)
            eastVisited = true;

        if (currentTile.getValue() == Token.EAST && eastVisited)
            return false;

        ITile north = currentTile.getNorth();
        boolean northVisited = north.isVisited();
        if (!northVisited && north.getValue() == Token.BREAKABLE && !breakMode)
            northVisited = true;

        if (currentTile.getValue() == Token.NORTH && northVisited)
            return false;

        ITile west = currentTile.getWest();
        boolean westVisited = west.isVisited();
        if (!westVisited && west.getValue() == Token.BREAKABLE && !breakMode)
            westVisited = true;

        if (currentTile.getValue() == Token.WEST && westVisited)
            return false;

        return (!(southVisited && eastVisited && northVisited && westVisited));
    }

    private void changeDirection() {
        if (!invertedMode)
            nextDirection = (nextDirection + 1) % compass.length;
        else
            nextDirection = (nextDirection - 1 < 0) ? compass.length - 1 : nextDirection - 1;
    }

    private ITile getTile(final int direction) {
        if (direction == 0)
            return currentTile.getSouth();
        else if (direction == 1)
            return currentTile.getEast();
        else if (direction == 2)
            return currentTile.getNorth();
        else
            return currentTile.getWest();
    }
}
