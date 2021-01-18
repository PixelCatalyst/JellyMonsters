package com.pixcat.jellymonsters.state;

import com.pixcat.jellymonsters.Application;
import com.pixcat.jellymonsters.ApplicationProperties;
import com.pixcat.jellymonsters.graphics.DrawCommand;
import com.pixcat.jellymonsters.graphics.DrawImage;
import com.pixcat.jellymonsters.gui.Menu;
import com.pixcat.jellymonsters.gui.button.ActionableButton;
import com.pixcat.jellymonsters.gui.button.ImageButton;
import com.pixcat.jellymonsters.gui.layout.CenteredLayout;
import com.pixcat.jellymonsters.input.InputState;
import com.pixcat.jellymonsters.input.KeyCode;
import com.pixcat.jellymonsters.input.KeyEvent;
import com.pixcat.jellymonsters.input.KeyState;
import com.pixcat.jellymonsters.resource.Image;
import com.pixcat.jellymonsters.time.Seconds;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class TitleScreenState implements GameState {

    private final Image background = Application.getResourceLoader().getImage("background.png");
    private final Image logo = Application.getResourceLoader().getImage("logo.png");

    private final Menu mainMenu = Menu.builderForViewport(ApplicationProperties.WINDOW_WIDTH)
            .topMargin(310)
            .addButtonsWithLayout(CenteredLayout.of(
                    ImageButton.of(
                            ActionableButton.builderForAction(() -> levelSelectButtonPressed = true)
                                    .width(200)
                                    .height(80)
                                    .build(),
                            Application.getResourceLoader().getImage("button_level_select.png")
                    )))
            .elementSpacing(50)
            .addButtonsWithLayout(CenteredLayout.of(
                    ImageButton.of(
                            ActionableButton.builderForAction(() -> exitButtonPressed = true)
                                    .width(200)
                                    .height(80)
                                    .build(),
                            Application.getResourceLoader().getImage("button_exit.png")
                    )))
            .build();

    private boolean levelSelectButtonPressed = false;
    private boolean exitButtonPressed = false;

    @Override
    public Collection<DrawCommand> getDrawCommands() {
        DrawCommand drawBackground = new DrawImage(0, 0, background);
        DrawCommand drawLogo = new DrawImage(100, 50, logo);
        ArrayList<DrawCommand> commands = new ArrayList<>();
        commands.add(drawBackground);
        commands.add(drawLogo);
        commands.addAll(mainMenu.getDrawCommands());
        return commands;
    }

    @Override
    public Set<KeyCode> getObservedKeys() {
        return Set.of(KeyCode.MOUSE_LEFT);
    }

    @Override
    public GameState update(Seconds delta, InputState inputState) {
        for (KeyEvent ke : inputState.getKeyEvents()) {
            if ((ke.getKeyCode() == KeyCode.MOUSE_LEFT) && (ke.getKeyState() == KeyState.RELEASED)) {
                mainMenu.onClick(ke.getMouseX(), ke.getMouseY());
            }
        }

        if (levelSelectButtonPressed) {
            return new LevelSelectState();
        } else if (exitButtonPressed) {
            return null;
        }
        return this;
    }
}
