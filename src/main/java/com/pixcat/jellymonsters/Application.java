package com.pixcat.jellymonsters;

import com.pixcat.jellymonsters.graphics.ProcessingGraphics;
import com.pixcat.jellymonsters.input.ProcessingInput;
import com.pixcat.jellymonsters.resource.ProcessingResourceLoader;
import com.pixcat.jellymonsters.resource.ResourceLoader;
import com.pixcat.jellymonsters.state.GameState;
import com.pixcat.jellymonsters.state.TitleScreenState;
import processing.core.PApplet;

public class Application extends PApplet {

    private static ResourceLoader resourceLoader;

    private final ProcessingInput processingInput = new ProcessingInput();
    private final Engine engine = new Engine(
            new ProcessingGraphics(this),
            processingInput);

    private GameState gameState;

    public static void main(String[] args) {
        PApplet.main("com.pixcat.jellymonsters.Application");
    }

    public static ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    @Override
    public void settings() {
        size(ApplicationProperties.WINDOW_WIDTH, ApplicationProperties.WINDOW_HEIGHT);
    }

    @Override
    public void setup() {
        surface.setTitle(ApplicationProperties.WINDOW_TITLE);

        //resourceLoader and gameState are created here because Processing demands external resources to be loaded
        //during or after executing setup()
        resourceLoader = new ProcessingResourceLoader(this);

        gameState = new TitleScreenState();
        processingInput.registerObservedKeys(gameState.getObservedKeys());
    }

    @Override
    public void keyPressed() {
        processingInput.onKeyPressed(keyCode, mouseX, mouseY);
        disableAppQuitOnEscape();
    }

    private void disableAppQuitOnEscape() {
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

        if (gameState == null) {
            super.exit();
        }
    }
}
