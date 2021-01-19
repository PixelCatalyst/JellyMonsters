package com.pixcat.jellymonsters.game;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pixcat.jellymonsters.graphics.DrawCircle;
import com.pixcat.jellymonsters.graphics.DrawCommand;
import com.pixcat.jellymonsters.graphics.Drawable;
import lombok.Getter;

import java.util.Collection;
import java.util.List;

public class Monster implements Drawable {

    @Getter
    private TileCoord coord;

    @JsonCreator
    public Monster(@JsonProperty("coord") TileCoord coord) {
        this.coord = coord;
    }

    @Override
    public Collection<DrawCommand> getDrawCommands() {
        return List.of(new DrawCircle(coord.getScreenX() + 20, coord.getScreenY() + 20, 20));
    }

    public boolean collision(TileCoord otherCoord) {
        return coord.equals(otherCoord);
    }

    public void move(Direction direction) {
        switch (direction) {
            case LEFT:
                coord = TileCoord.of(coord.getX() - 1, coord.getY());
                break;
            case RIGHT:
                coord = TileCoord.of(coord.getX() + 1, coord.getY());
                break;
            case UP:
                coord = TileCoord.of(coord.getX(), coord.getY() - 1);
                break;
            case DOWN:
                coord = TileCoord.of(coord.getX(), coord.getY() + 1);
                break;
        }
    }
}
