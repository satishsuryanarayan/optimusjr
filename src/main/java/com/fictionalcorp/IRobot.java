package com.fictionalcorp;

import java.util.List;

/*
    Interface representing a Robot
 */
public interface IRobot {
    List<Direction> visit(final IFloor floor);
}
