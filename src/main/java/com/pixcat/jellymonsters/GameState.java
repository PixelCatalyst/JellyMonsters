package com.pixcat.jellymonsters;

import java.util.Collection;
import java.util.Set;

public interface GameState {

    Collection<DrawCommand> getDrawCommands();

    Set<KeyCode> getObservedKeys();

    GameState update(Seconds delta, InputState inputState);
}
