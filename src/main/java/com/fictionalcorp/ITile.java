package com.fictionalcorp;

/*
    Interface representing a Floor tile
 */
public interface ITile {
    ITile getSouth();
    ITile getEast();
    ITile getNorth();
    ITile getWest();
    boolean isVisited();
    Character getValue();
}
