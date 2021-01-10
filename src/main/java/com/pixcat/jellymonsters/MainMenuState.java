package com.pixcat.jellymonsters;

import java.util.Collection;
import java.util.Set;

public class MainMenuState implements GameState {

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
