package com.pixcat.jellymonsters.game;

enum Direction {

    LEFT,
    RIGHT,
    UP,
    DOWN,
    NONE;

    public static Direction opposite(Direction direction) {
        switch (direction) {

            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            default:
                return NONE;
        }
    }
}
