package com.pixcat.jellymonsters.game;

import com.pixcat.jellymonsters.graphics.DrawCommand;
import com.pixcat.jellymonsters.graphics.Drawable;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ActiveLevel implements Drawable {

    private final LevelData levelData;

    private static final int DRAG_THRESHOLD = 40;

    private boolean mouseDragging = false;
    private int dragMouseX = 0;
    private int dragMouseY = 0;
    private Direction dragDirection = Direction.NONE;

    Monster activeMonster = null;

    public int getOrdinalNumber() {
        return levelData.getOrdinalNumber();
    }

    @Override
    public Collection<DrawCommand> getDrawCommands() {
        ArrayList<Drawable> drawables = new ArrayList<>();
        drawables.addAll(levelData.getGroundTiles());
        drawables.addAll(levelData.getStarTiles());
        drawables.addAll(levelData.getMonsters());
        drawables.addAll(levelData.getProps());
        return drawables.stream()
                .map(Drawable::getDrawCommands)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public void startMouseDrag(int mouseX, int mouseY) {
        TileCoord hitTile = TileCoord.ofScreenCoord(mouseX, mouseY);
        for (Monster m : levelData.getMonsters()) {
            if (m.collision(hitTile)) {
                mouseDragging = true;
                dragMouseX = mouseX;
                dragMouseY = mouseY;
                activeMonster = m;
            }
        }
    }

    public void updateMouseDrag(int mouseX, int mouseY) {
        if (mouseDragging) {
            int distanceX = dragMouseX - mouseX;
            int distanceY = dragMouseY - mouseY;

            if (Math.abs(distanceX) >= DRAG_THRESHOLD) {
                dragDirection = (distanceX < 0 ? Direction.RIGHT : Direction.LEFT);
                mouseDragging = false;
            } else if (Math.abs(distanceY) >= DRAG_THRESHOLD) {
                dragDirection = (distanceY < 0 ? Direction.DOWN : Direction.UP);
                mouseDragging = false;
            }
        }
    }

    public GameResult update() {
        GameResult result = null;

        if (dragDirection != Direction.NONE) {
            boolean collision = false;

            while (monsterNotFallingOff(activeMonster)) {
                activeMonster.move(dragDirection);
                for (Prop p : levelData.getProps()) {
                    if (activeMonster.collision(p.getCoord())) {
                        collision = true;
                    }
                }
                for (Monster m : levelData.getMonsters()) {
                    if (m != activeMonster && activeMonster.collision(m.getCoord())) {
                        collision = true;
                    }
                }
                if (collision) {
                    activeMonster.move(Direction.opposite(dragDirection));
                    break;
                }
            }

            abortMouseDrag();
            if (!monsterNotFallingOff(activeMonster)) {
                result = GameResult.FAILURE;
            }
            mergeMonsters();
        }
        return result;
    }

    private boolean monsterNotFallingOff(Monster monster) {
        TileCoord monsterCoord = monster.getCoord();
        return monsterCoord.getX() >= -1 &&
                monsterCoord.getX() <= 8 &&
                monsterCoord.getY() >= -1 &&
                monsterCoord.getY() <= 9;
    }

    public void mergeMonsters() {
        ArrayList<Monster> nonMerged = new ArrayList<>(levelData.getMonsters());
        ArrayList<Monster> merged = new ArrayList<>();
        for (int x = 0; x < 8; ++x) {
            for (int y = 0; y < 8; ++y) {
                TileCoord coord = TileCoord.of(x, y);
                for (Monster m : nonMerged) {
                    if (m.collision(coord)) {
                        Monster left = findAt(TileCoord.of(coord.getX() - 1, coord.getY()), nonMerged);
                        Monster right = findAt(TileCoord.of(coord.getX() + 1, coord.getY()), nonMerged);
                        Monster up = findAt(TileCoord.of(coord.getX(), coord.getY() - 1), nonMerged);
                        Monster down = findAt(TileCoord.of(coord.getX(), coord.getY() + 1), nonMerged);
                    }
                }
            }
        }
    }

    public Monster findAt(TileCoord coord, Collection<Monster> monsters) {
        for (Monster m : monsters) {
            if (m.collision(coord)) {
                return m;
            }
        }
        return null;
    }

    public void abortMouseDrag() {
        mouseDragging = false;
        dragDirection = Direction.NONE;
    }
}
