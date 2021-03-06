package com.pixcat.jellymonsters.state;

import com.pixcat.jellymonsters.graphics.Drawable;
import com.pixcat.jellymonsters.input.InputState;
import com.pixcat.jellymonsters.input.KeyCode;
import com.pixcat.jellymonsters.time.Seconds;

import java.util.Set;

public interface GameState extends Drawable {

    Set<KeyCode> getObservedKeys();

    GameState update(Seconds delta, InputState inputState);
}
