package com.pixcat.jellymonsters;

import lombok.Value;

import java.util.Queue;

@Value
public class InputState {

    Queue<KeyEvent> keyEvents;
    int currentMouseX;
    int currentMouseY;
}
