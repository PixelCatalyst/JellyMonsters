package com.pixcat.jellymonsters;

import processing.core.PApplet;

public class Application extends PApplet {

    private final Graphics graphics = new ProcessingGraphics(this);
    private final Input input = new ProcessingInput(this);
    private final Engine engine = new Engine(graphics, input);
    private GameState gameState = new GameState();

    public static void main(String[] args) {
        PApplet.main("com.pixcat.jellymonsters.Application");
    }

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {
        surface.setTitle("Jelly Monsters");
    }

    @Override
    public void draw() {
        engine.draw(gameState);
        engine.handleInput();
        gameState = engine.update(gameState);
    }
}
