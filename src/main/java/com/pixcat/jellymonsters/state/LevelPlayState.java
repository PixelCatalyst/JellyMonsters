package com.pixcat.jellymonsters.state;

import com.pixcat.jellymonsters.game.Level;
import com.pixcat.jellymonsters.graphics.DrawCommand;
import com.pixcat.jellymonsters.input.InputState;
import com.pixcat.jellymonsters.input.KeyCode;
import com.pixcat.jellymonsters.input.KeyEvent;
import com.pixcat.jellymonsters.input.KeyState;
import com.pixcat.jellymonsters.time.Seconds;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Set;

@RequiredArgsConstructor
public class LevelPlayState implements GameState {

    private final Level level;

    @Override
    public Collection<DrawCommand> getDrawCommands() {
        //TODO
        return level.getDrawCommands();
    }

    @Override
    public Set<KeyCode> getObservedKeys() {
        return Set.of(KeyCode.ESCAPE);
    }

    @Override
    public GameState update(Seconds delta, InputState inputState) {
        //TODO
        for (KeyEvent ke : inputState.getKeyEvents()) {
            if (ke.getKeyCode() == KeyCode.ESCAPE && ke.getKeyState() == KeyState.RELEASED) {
                return null;
            }
        }
        return this;
    }
}
