package com.tesla;

import java.io.Serializable;
import java.util.Objects;

/*
    Class representing the tiles on the Floor
    Each tile has a value and 4 neighbors (think of this as a QuadNode in a QuadTree)
 */
public class Tile implements ITile {
    private Character value;
    private final int x;
    private final int y;
    private Tile north;
    private Tile east;
    private Tile west;
    private Tile south;
    private boolean visited;

    public Tile(final Character value, final int x, final int y) {
        this.value = value;
        this.x = x;
        this.y = y;
        this.north = null;
        this.east = null;
        this.west = null;
        this.south = null;
        this.visited = false;
    }

    @Override
    public Character getValue() {
        return value;
    }

    public void setValue(final Character value) {
        this.value = value;
    }

    @Override
    public Tile getNorth() {
        return north;
    }

    public void setNorth(final Tile north) {
        this.north = north;
    }

    @Override
    public Tile getEast() {
        return east;
    }

    public void setEast(final Tile east) {
        this.east = east;
    }

    @Override
    public Tile getWest() {
        return west;
    }

    public void setWest(final Tile west) {
        this.west = west;
    }

    @Override
    public Tile getSouth() {
        return south;
    }

    public void setSouth(final Tile south) {
        this.south = south;
    }

    @Override
    public boolean isVisited() {
        return visited;
    }

    public void setVisited(final boolean visited) {
        this.visited = visited;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Tile tile))
            return false;
        return x == tile.x && y == tile.y && Objects.equals(value, tile.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, x, y);
    }

    @Override
    public String toString() {
        return "Node{" +
                "value='" + value + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
