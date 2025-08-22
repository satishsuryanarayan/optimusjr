package com.fictionalcorp.impl;

import com.fictionalcorp.*;
import com.fictionalcorp.utils.Direction;
import com.fictionalcorp.utils.State;
import com.fictionalcorp.utils.Token;

import java.util.*;

/*
    One implementation of IRobot interface
    This class has one implementation of the visit method
    There could be other Robot implementations with their own implementations of the visit method
 */
public class Robot implements IRobot {
    public Robot() {
    }

    /*
        Visitor that visits the Floor tiles
     */
    @Override
    public List<Direction> visit(final IFloor floor) {
        State state = new State(floor);
        ITile tile = state.getNext();
        while (tile.getValue() != Token.END) {
            ((Tile) tile).setVisited(true);
            tile = state.getNext();
            if (tile.isVisited())
                return Collections.singletonList(Direction.LOOP);
        }
        return state.getPath();
    }
}
