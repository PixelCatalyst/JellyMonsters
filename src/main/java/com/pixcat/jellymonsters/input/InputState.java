package com.pixcat.jellymonsters.input;

import lombok.Value;

import java.util.Queue;

@Value
public class InputState {

    Queue<KeyEvent> keyEvents;
    int currentMouseX;
    int currentMouseY;
}
