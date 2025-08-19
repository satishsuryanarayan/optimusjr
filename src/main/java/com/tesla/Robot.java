package com.tesla;

import java.util.*;

/*
    One implementation of IRobot interface
    This class has one implementation of the visit method
    There could be other Robot implementations with their own implementations of the visit method
 */
public class Robot implements IRobot {
    /*
        Class to maintain the state of the Robot's visit
     */
    private static final class State {
        private final IFloor floor;
        private boolean invertedMode;
        private boolean breakMode;
        private ITile currentTile;
        private final Direction[] compass;
        private int nextDirection;
        private final List<Direction> path;

        public State(final IFloor floor) {
            this.floor = floor;
            this.invertedMode = false;
            this.breakMode = false;
            this.currentTile = floor.getRoot();
            this.compass = new Direction[]{Direction.SOUTH, Direction.EAST, Direction.NORTH, Direction.WEST};
            this.nextDirection = 0;
            this.path = new ArrayList<>();
        }

        public ITile getNext() {
            ITile nextTile = getTile(nextDirection);
            while ((nextTile.isVisited() || (nextTile.getValue() == Constants.BREAKABLE && !breakMode)) && canProceed()) {
                changeDirection();
                nextTile = getTile(nextDirection);
            }

            recordPath();

            if (nextTile.getValue() == Constants.BREAKER)
                breakMode = !breakMode;
            else if (nextTile.getValue() == Constants.INVERTER)
                invertedMode = !invertedMode;
            else if (nextTile.getValue() == Constants.SOUTH)
                nextDirection = 0;
            else if (nextTile.getValue() == Constants.EAST)
                nextDirection = 1;
            else if (nextTile.getValue() == Constants.NORTH)
                nextDirection = 2;
            else if (nextTile.getValue() == Constants.WEST)
                nextDirection = 3;
            else if (nextTile.getValue() >= Constants.TELEPORTER_START && nextTile.getValue() <= Constants.TELEPORTER_END)
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
            if (!southVisited && south.getValue() == Constants.BREAKABLE && !breakMode)
                southVisited = true;

            if (currentTile.getValue() == Constants.SOUTH && southVisited)
                return false;

            ITile east = currentTile.getEast();
            boolean eastVisited = east.isVisited();
            if (!eastVisited && east.getValue() == Constants.BREAKABLE && !breakMode)
                eastVisited = true;

            if (currentTile.getValue() == Constants.EAST && eastVisited)
                return false;

            ITile north = currentTile.getNorth();
            boolean northVisited = north.isVisited();
            if (!northVisited && north.getValue() == Constants.BREAKABLE && !breakMode)
                northVisited = true;

            if (currentTile.getValue() == Constants.NORTH && northVisited)
                return false;

            ITile west = currentTile.getWest();
            boolean westVisited = west.isVisited();
            if (!westVisited && west.getValue() == Constants.BREAKABLE && !breakMode)
                westVisited = true;

            if (currentTile.getValue() == Constants.WEST && westVisited)
                return false;

            return (!(southVisited && eastVisited && northVisited && westVisited));
        }

        private void changeDirection() {
            if (!invertedMode)
                nextDirection = (nextDirection + 1) % compass.length;
            else
                nextDirection = (nextDirection - 1 < 0) ? compass.length - 1 : nextDirection - 1;
        }

        private ITile getTile(int direction) {
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

    private final IFloor floor;

    public Robot(final IFloor floor) {
        this.floor = floor;
    }

    /*
        Visitor that visits the Floor tiles
     */
    @Override
    public List<Direction> visit() {
        State state = new State(floor);
        ITile tile = state.getNext();
        while (tile.getValue() != Constants.END) {
            ((Tile) tile).setVisited(true);
            tile = state.getNext();
            if (tile.isVisited())
                return Collections.singletonList(Direction.LOOP);
        }
        return state.getPath();
    }
}
