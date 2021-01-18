package com.pixcat.jellymonsters.game;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pixcat.jellymonsters.graphics.DrawCommand;
import com.pixcat.jellymonsters.graphics.Drawable;
import com.pixcat.jellymonsters.gui.button.Button;
import lombok.Value;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Value
public class Level implements Drawable {

    int ordinalNumber;
    String title;
    Set<GameResult> possibleResults;
    Collection<GroundTile> groundTiles;
    Collection<StarTile> starTiles;
    Collection<Prop> props;
    Collection<Monster> monsters;

    @JsonCreator
    public Level(
            @JsonProperty("ordinalNumber") int ordinalNumber,
            @JsonProperty("title") String title,
            @JsonProperty("possibleResults") Set<GameResult> possibleResults,
            @JsonProperty("groundTiles") Collection<GroundTile> groundTiles,
            @JsonProperty("starTiles") Collection<StarTile> starTiles,
            @JsonProperty("props") Collection<Prop> props,
            @JsonProperty("monsters") Collection<Monster> monsters) {
        this.ordinalNumber = ordinalNumber;
        this.title = title;
        this.possibleResults = possibleResults;
        this.groundTiles = groundTiles;
        this.starTiles = starTiles;
        this.props = props;
        this.monsters = monsters;
    }

    @Override
    public Collection<DrawCommand> getDrawCommands() {
        ArrayList<Drawable> drawables = new ArrayList<>();
        drawables.addAll(groundTiles);
        drawables.addAll(starTiles);
        drawables.addAll(monsters);
        drawables.addAll(props);
        return drawables.stream()
                .map(Drawable::getDrawCommands)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
