package com.pixcat.jellymonsters;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Engine {
    private final Graphics graphics;
    private final Input input;

    private final Timer timer = new Timer();

    public void draw(GameState gameState) {
        float delta = timer.elapsedSeconds();

        graphics.text(delta + "s");
    }

    public void handleInput() {
        input.fetchKeyboardState();
    }

    public GameState update(GameState gameState) {

        return gameState;
    }
}
