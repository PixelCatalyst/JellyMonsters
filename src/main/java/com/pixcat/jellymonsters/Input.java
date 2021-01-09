package com.pixcat.jellymonsters;

import java.util.Set;

public interface Input {

    void registerObservedKeys(Set<KeyCode> keys);

    InputState fetchState();
}
