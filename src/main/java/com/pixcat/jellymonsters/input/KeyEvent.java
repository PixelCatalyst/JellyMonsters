package com.pixcat.jellymonsters.input;

import lombok.Value;

@Value
public class KeyEvent {

    KeyCode keyCode;
    KeyState keyState;

    int mouseX;
    int mouseY;
}
