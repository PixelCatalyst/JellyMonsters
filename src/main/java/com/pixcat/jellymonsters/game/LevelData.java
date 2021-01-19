package com.pixcat.jellymonsters.game;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.Collection;
import java.util.Set;

@Value
public class LevelData {

    int ordinalNumber;
    String title;
    Set<GameResult> possibleResults;
    Collection<GroundTile> groundTiles;
    Collection<StarTile> starTiles;
    Collection<Prop> props;
    Collection<Monster> monsters;

    @JsonCreator
    public LevelData(
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
}
