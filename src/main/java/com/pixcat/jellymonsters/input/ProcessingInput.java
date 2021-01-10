package com.pixcat.jellymonsters.input;

import processing.core.PConstants;

import java.util.*;

public class ProcessingInput implements Input {

    private Set<KeyCode> observedKeys = Collections.emptySet();

    private final Queue<KeyEvent> keyEvents = new LinkedList<>();
    private int mouseX;
    private int mouseY;

    @Override
    public void registerObservedKeys(Set<KeyCode> keys) {
        if (keys != null) {
            observedKeys = new HashSet<>(keys);
        }
    }

    public void onKeyPressed(int keyCode, int mouseX, int mouseY) {
        onKeyEvent(KeyState.PRESSED, keyCode, mouseX, mouseY);
    }

    public void onKeyReleased(int keyCode, int mouseX, int mouseY) {
        onKeyEvent(KeyState.RELEASED, keyCode, mouseX, mouseY);
    }

    private void onKeyEvent(KeyState keyState, int keyCode, int mouseX, int mouseY) {
        KeyCode mappedKey = mapKeyboardKey(keyCode);
        if (observedKeys.contains(mappedKey)) {
            keyEvents.add(new KeyEvent(mappedKey, keyState, mouseX, mouseY));
        }
    }

    private KeyCode mapKeyboardKey(int keyCode) {
        if (keyCode == PConstants.ESC) {
            return KeyCode.ESCAPE;
        }
        return KeyCode.UNKNOWN;
    }

    public void onMousePressed(int keyCode, int mouseX, int mouseY) {
        onMouseEvent(KeyState.PRESSED, keyCode, mouseX, mouseY);
    }

    public void onMouseReleased(int keyCode, int mouseX, int mouseY) {
        onMouseEvent(KeyState.RELEASED, keyCode, mouseX, mouseY);
    }

    private void onMouseEvent(KeyState keyState, int keyCode, int mouseX, int mouseY) {
        KeyCode mappedKey = mapMouseKey(keyCode);
        if (observedKeys.contains(mappedKey)) {
            keyEvents.add(new KeyEvent(mappedKey, keyState, mouseX, mouseY));
        }
    }

    private KeyCode mapMouseKey(int keyCode) {
        if (keyCode == PConstants.LEFT) {
            return KeyCode.MOUSE_LEFT;
        }
        return KeyCode.UNKNOWN;
    }

    public void onMouseMoved(int mouseX, int mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    @Override
    public InputState fetchState() {
        InputState inputState = new InputState(new LinkedList<>(keyEvents), mouseX, mouseY);
        keyEvents.clear();
        return inputState;
    }
}
