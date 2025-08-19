package com.tesla;

import java.util.Scanner;

/*
    Interface representing the Floor
 */
public interface IFloor {
    void readMap(Scanner input);
    ITile getRoot();
    ITile teleport(ITile tile);
}
