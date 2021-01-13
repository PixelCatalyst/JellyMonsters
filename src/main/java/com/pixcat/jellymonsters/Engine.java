package com.pixcat.jellymonsters;

import com.pixcat.jellymonsters.graphics.Color;
import com.pixcat.jellymonsters.graphics.DrawCommand;
import com.pixcat.jellymonsters.graphics.Graphics;
import com.pixcat.jellymonsters.input.Input;
import com.pixcat.jellymonsters.input.InputState;
import com.pixcat.jellymonsters.state.GameState;
import com.pixcat.jellymonsters.time.Seconds;
import com.pixcat.jellymonsters.time.Timer;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@RequiredArgsConstructor
class Engine {

    private final Graphics graphics;
    private final Input input;

    private final Timer timer = new Timer();

    public void draw(GameState gameState) {
        graphics.clear(Color.white());

        Collection<DrawCommand> drawCommands = gameState.getDrawCommands();
        if (drawCommands != null) {
            for (DrawCommand command : drawCommands) {
                if (command != null) {
                    command.execute(graphics);
                }
            }
        }
    }

    public GameState update(GameState gameState) {
        Seconds delta = timer.getElapsedSeconds();
        InputState inputState = input.fetchState();
        GameState newGameState = gameState.update(delta, inputState);

        if (newGameState != gameState) {
            gameState = newGameState;
            if (gameState != null) {
                gameState.getObservedKeys();
                input.registerObservedKeys(gameState.getObservedKeys());
            }
        }
        return gameState;
    }
}
