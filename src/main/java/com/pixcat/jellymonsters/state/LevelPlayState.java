package com.pixcat.jellymonsters.state;

import com.pixcat.jellymonsters.Application;
import com.pixcat.jellymonsters.ApplicationProperties;
import com.pixcat.jellymonsters.game.ActiveLevel;
import com.pixcat.jellymonsters.game.GameResult;
import com.pixcat.jellymonsters.game.LevelData;
import com.pixcat.jellymonsters.graphics.DrawCommand;
import com.pixcat.jellymonsters.gui.button.ActionableButton;
import com.pixcat.jellymonsters.gui.button.Button;
import com.pixcat.jellymonsters.gui.button.ImageButton;
import com.pixcat.jellymonsters.input.InputState;
import com.pixcat.jellymonsters.input.KeyCode;
import com.pixcat.jellymonsters.input.KeyEvent;
import com.pixcat.jellymonsters.input.KeyState;
import com.pixcat.jellymonsters.time.Seconds;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class LevelPlayState implements GameState {

    private static final int NAV_BUTTON_SIZE = 64;
    private static final int NAV_BUTTON_MARGIN = 26;

    private final ActiveLevel level;

    private final Button backButton = ImageButton.of(
            ActionableButton.builderForAction(() -> backButtonPressed = true)
                    .width(NAV_BUTTON_SIZE)
                    .height(NAV_BUTTON_SIZE)
                    .x(NAV_BUTTON_MARGIN)
                    .y(ApplicationProperties.WINDOW_HEIGHT - NAV_BUTTON_MARGIN - NAV_BUTTON_SIZE)
                    .build(),
            Application.getResourceLoader().getImage("button_back_small.png"));

    private final Button reloadButton = ImageButton.of(
            ActionableButton.builderForAction(() -> reloadButtonPressed = true)
                    .width(NAV_BUTTON_SIZE)
                    .height(NAV_BUTTON_SIZE)
                    .x(NAV_BUTTON_SIZE + 2 * NAV_BUTTON_MARGIN)
                    .y(ApplicationProperties.WINDOW_HEIGHT - NAV_BUTTON_MARGIN - NAV_BUTTON_SIZE)
                    .build(),
            Application.getResourceLoader().getImage("button_reload.png"));

    private boolean backButtonPressed = false;
    private boolean reloadButtonPressed = false;

    public LevelPlayState(LevelData levelData) {
        this.level = new ActiveLevel(levelData);
    }

    @Override
    public Collection<DrawCommand> getDrawCommands() {
        ArrayList<DrawCommand> commands = new ArrayList<>();
        commands.addAll(level.getDrawCommands());
        commands.addAll(backButton.getDrawCommands());
        commands.addAll(reloadButton.getDrawCommands());
        return commands;
    }

    @Override
    public Set<KeyCode> getObservedKeys() {
        return Set.of(KeyCode.MOUSE_LEFT);
    }

    @Override
    public GameState update(Seconds delta, InputState inputState) {
        for (KeyEvent ke : inputState.getKeyEvents()) {
            if (ke.getKeyCode() == KeyCode.MOUSE_LEFT && ke.getKeyState() == KeyState.PRESSED) {
                level.startMouseDrag(ke.getMouseX(), ke.getMouseY());
            }

            if (ke.getKeyCode() == KeyCode.MOUSE_LEFT && ke.getKeyState() == KeyState.RELEASED) {
                level.abortMouseDrag();

                backButton.onClick(ke.getMouseX(), ke.getMouseY());
                reloadButton.onClick(ke.getMouseX(), ke.getMouseY());
            }
        }
        level.updateMouseDrag(inputState.getCurrentMouseX(), inputState.getCurrentMouseY());

        GameResult gameResult = level.update();
        if (gameResult != null) {
            if (gameResult == GameResult.FAILURE) {
                return new LevelLoadState(level.getOrdinalNumber());
            }
        }

        if (reloadButtonPressed) {
            return new LevelLoadState(level.getOrdinalNumber());
        } else if (backButtonPressed) {
            return new LevelSelectState();
        }
        return this;
    }
}
