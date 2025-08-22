package com.fictionalcorp;

import com.fictionalcorp.utils.Direction;

import java.util.List;

/*
    Interface representing a Robot
 */
public interface IRobot {
    List<Direction> visit(final IFloor floor);
}
