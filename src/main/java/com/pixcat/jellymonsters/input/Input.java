package com.pixcat.jellymonsters.input;

import java.util.Set;

public interface Input {

    void registerObservedKeys(Set<KeyCode> keys);

    InputState fetchState();
}
