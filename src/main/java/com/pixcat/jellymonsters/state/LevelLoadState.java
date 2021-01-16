package com.pixcat.jellymonsters.state;

import com.pixcat.jellymonsters.graphics.DrawCommand;
import com.pixcat.jellymonsters.input.InputState;
import com.pixcat.jellymonsters.input.KeyCode;
import com.pixcat.jellymonsters.input.KeyEvent;
import com.pixcat.jellymonsters.input.KeyState;
import com.pixcat.jellymonsters.time.Seconds;

import java.util.Collection;
import java.util.Set;

public class LevelLoadState implements GameState {

    @Override
    public Collection<DrawCommand> getDrawCommands() {
        //TODO
        return null;
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
