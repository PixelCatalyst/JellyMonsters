package com.pixcat.jellymonsters;

import processing.core.PApplet;

public class Application extends PApplet {

    private static final String WINDOW_TITLE = "Jelly Monsters";

    private final ProcessingInput processingInput = new ProcessingInput();
    private final Engine engine = new Engine(
            new ProcessingGraphics(this),
            processingInput);

    private GameState gameState = new GameState();

    public Application() {
        processingInput.registerObservedKeys(gameState.getObservedKeys());
    }

    public static void main(String[] args) {
        PApplet.main("com.pixcat.jellymonsters.Application");
    }

    @Override
    public void settings() {
        size(500, 600);
    }

    @Override
    public void setup() {
        surface.setTitle(WINDOW_TITLE);
    }

    @Override
    public void keyPressed() {
        processingInput.onKeyPressed(keyCode, mouseX, mouseY);
        disableQuitOnEscape();
    }

    private void disableQuitOnEscape() {
        if (key == ESC) {
            key = 0;
        }
    }

    @Override
    public void keyReleased() {
        processingInput.onKeyReleased(keyCode, mouseX, mouseY);
    }

    @Override
    public void mousePressed() {
        processingInput.onMousePressed(mouseButton, mouseX, mouseY);
    }

    @Override
    public void mouseReleased() {
        processingInput.onMouseReleased(mouseButton, mouseX, mouseY);
    }

    @Override
    public void mouseMoved() {
        processingInput.onMouseMoved(mouseX, mouseY);
    }

    @Override
    public void mouseDragged() {
        // Processing mouseDrag event is treated as normal mouseMove.
        // We prefer to let GameState handle mouse drag (if needed) by itself.
        processingInput.onMouseMoved(mouseX, mouseY);
    }

    @Override
    public void draw() {
        engine.draw(gameState);
        gameState = engine.update(gameState);
    }
}
