package com.pixcat.jellymonsters.state;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pixcat.jellymonsters.Application;
import com.pixcat.jellymonsters.game.Level;
import com.pixcat.jellymonsters.graphics.DrawCommand;
import com.pixcat.jellymonsters.graphics.DrawImage;
import com.pixcat.jellymonsters.input.InputState;
import com.pixcat.jellymonsters.input.KeyCode;
import com.pixcat.jellymonsters.resource.Image;
import com.pixcat.jellymonsters.time.Seconds;
import lombok.SneakyThrows;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class LevelLoadState implements GameState {

    private final ObjectMapper mapper = new ObjectMapper();

    private final int levelOrdinalNumber;
    private Level level;
    private boolean levelLoadScheduled = false;
    private boolean levelLoaded = false;

    private final Image background = Application.getResourceLoader().getImage("background_blank.png");
    private final Image loadProgress = Application.getResourceLoader().getImage("level_load.png");

    public LevelLoadState(int levelOrdinalNumber) {
        this.levelOrdinalNumber = levelOrdinalNumber;
    }

    @Override
    public Collection<DrawCommand> getDrawCommands() {
        return List.of(
                new DrawImage(0, 0, background),
                new DrawImage(150, 275, loadProgress));
    }

    @Override
    public Set<KeyCode> getObservedKeys() {
        return null;
    }

    @Override
    public GameState update(Seconds delta, InputState inputState) {
        scheduleLevelLoad();
        if (levelLoaded) {
            return new LevelPlayState(level);
        }
        return this;
    }

    private void scheduleLevelLoad() {
        if (!levelLoadScheduled) {
            Thread loadThread = new Thread(this::loadLevel);
            loadThread.start();
            levelLoadScheduled = true;
        }
    }

    @SneakyThrows
    private void loadLevel() {
        File jsonFile = new File(String.format("level_%d.json", levelOrdinalNumber));
        level = mapper.readValue(jsonFile, Level.class);
        levelLoaded = true;
    }
}
