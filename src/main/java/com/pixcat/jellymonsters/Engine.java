package com.pixcat.jellymonsters;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Engine {
    private final Graphics graphics;

    public void draw(GameState gameState) {
        graphics.draw();
    }

    public void update() {

    }
}
