package com.fictionalcorp;

import java.util.Scanner;

/*
    Interface representing the Floor
 */
public interface IFloor {
    void readMap(Scanner input);
    ITile getStart();
    ITile teleport(ITile tile);
}
