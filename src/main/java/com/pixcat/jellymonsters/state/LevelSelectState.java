package com.pixcat.jellymonsters.state;

import com.pixcat.jellymonsters.Application;
import com.pixcat.jellymonsters.ApplicationProperties;
import com.pixcat.jellymonsters.graphics.DrawCommand;
import com.pixcat.jellymonsters.graphics.DrawImage;
import com.pixcat.jellymonsters.gui.Menu;
import com.pixcat.jellymonsters.gui.button.ActionableButton;
import com.pixcat.jellymonsters.gui.button.ImageButton;
import com.pixcat.jellymonsters.gui.layout.CenteredLayout;
import com.pixcat.jellymonsters.gui.layout.GridLayout;
import com.pixcat.jellymonsters.input.InputState;
import com.pixcat.jellymonsters.input.KeyCode;
import com.pixcat.jellymonsters.input.KeyEvent;
import com.pixcat.jellymonsters.input.KeyState;
import com.pixcat.jellymonsters.resource.Image;
import com.pixcat.jellymonsters.time.Seconds;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class LevelSelectState implements GameState {

    private final static int NUMBER_OF_LEVELS = 10;

    private final Image background = Application.getResourceLoader().getImage("background.png");
    private final Image levelSelectHeader = Application.getResourceLoader().getImage("level_select.png");
    private final Menu levelMenu;

    private int levelSelected = 0;
    private boolean backButtonPressed = false;

    public LevelSelectState() {
        final var levelGridBuilder = GridLayout.builder()
                .cellSize(60)
                .cellSpacing(15)
                .rowCells(5);
        for (int i = 1; i <= NUMBER_OF_LEVELS; ++i) {
            final int levelNumber = i;
            levelGridBuilder.addButton(
                    ImageButton.of(
                            ActionableButton.builderForAction(() -> levelSelected = levelNumber)
                                    .width(60)
                                    .height(60)
                                    .build(),
                            Application.getResourceLoader().getImage(String.format("level%d.png", i))
                    ));
        }

        levelMenu = Menu.builderForViewport(ApplicationProperties.WINDOW_WIDTH)
                .topMargin(140)
                .addButtonsWithLayout(levelGridBuilder.build())
                .elementSpacing(200)
                .addButtonsWithLayout(CenteredLayout.of(
                        ImageButton.of(
                                ActionableButton.builderForAction(() -> backButtonPressed = true)
                                        .width(200)
                                        .height(80)
                                        .build(),
                                Application.getResourceLoader().getImage("button_back.png"))
                ))
                .build();
    }

    @Override
    public Collection<DrawCommand> getDrawCommands() {
        DrawCommand drawBackground = new DrawImage(0, 0, background);
        DrawCommand drawHeader = new DrawImage(150, 50, levelSelectHeader);
        ArrayList<DrawCommand> commands = new ArrayList<>();
        commands.add(drawBackground);
        commands.add(drawHeader);
        commands.addAll(levelMenu.getDrawCommands());
        return commands;
    }

    @Override
    public Set<KeyCode> getObservedKeys() {
        return Set.of(KeyCode.ESCAPE, KeyCode.MOUSE_LEFT);
    }

    @Override
    public GameState update(Seconds delta, InputState inputState) {
        for (KeyEvent ke : inputState.getKeyEvents()) {
            if ((ke.getKeyCode() == KeyCode.ESCAPE) && (ke.getKeyState() == KeyState.RELEASED)) {
                backButtonPressed = true;
            } else if ((ke.getKeyCode() == KeyCode.MOUSE_LEFT) && (ke.getKeyState() == KeyState.RELEASED)) {
                levelMenu.onClick(ke.getMouseX(), ke.getMouseY());
            }
        }

        if (levelSelected > 0) {
            return new LevelLoadState(levelSelected);
        } else if (backButtonPressed) {
            return new TitleScreenState();
        }
        return this;
    }
}
