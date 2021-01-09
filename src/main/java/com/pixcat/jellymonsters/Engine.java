package com.pixcat.jellymonsters;

import lombok.RequiredArgsConstructor;

import java.util.Collection;

@RequiredArgsConstructor
public class Engine {

    private final Graphics graphics;
    private final Input input;

    private final Timer timer = new Timer();

    public void draw(GameState gameState) {
        graphics.clear(Color.white());

        Collection<DrawCommand> drawCommands = gameState.getDrawCommands();
        for (DrawCommand command : drawCommands) {
            if (command != null) {
                command.execute(graphics);
            }
        }
    }

    public GameState update(GameState gameState) {
        Seconds delta = timer.getElapsedSeconds();
        InputState inputState = input.fetchState();
        GameState newGameState = gameState.update(delta, inputState);

        if (newGameState != gameState) {
            gameState = newGameState;
            gameState.getObservedKeys();
            input.registerObservedKeys(gameState.getObservedKeys());
        }
        return gameState;
    }
}
