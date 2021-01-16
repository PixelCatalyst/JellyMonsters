package com.pixcat.jellymonsters.gui.layout;

import com.pixcat.jellymonsters.gui.button.Button;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GridLayout implements Layout {

    private final int cellSize;
    private final int cellSpacing;
    private final int rowCells;
    private final List<Button> buttons;

    @Override
    public void setViewport(int width, int originY) {
        final int rowWidth = rowCells * (cellSize + cellSpacing) - cellSpacing;
        final int originX = (width - rowWidth) / 2;

        int currentX = originX;
        int currentY = originY;
        for (int i = 0; i < buttons.size(); ++i) {
            Button b = buttons.get(i);
            b.setX(currentX);
            b.setY(currentY);
            currentX += cellSize + cellSpacing;
            if (((i + 1) % rowCells) == 0) {
                currentX = originX;
                currentY += cellSize + cellSpacing;
            }
        }
    }

    @Override
    public Collection<Button> getButtons() {
        return List.copyOf(buttons);
    }

    @Override
    public int getHeight() {
        final int numberOfRows = (int) Math.ceil(buttons.size() / (double) rowCells);
        return numberOfRows * (cellSize + cellSpacing) - cellSpacing;
    }

    public static GridLayoutBuilder builder() {
        return new GridLayoutBuilder();
    }

    public static class GridLayoutBuilder {

        private int cellSize;
        private int cellSpacing;
        private int rowCells;
        private final List<Button> buttons = new ArrayList<>();

        private GridLayoutBuilder() {
        }

        public GridLayoutBuilder cellSize(int cellSize) {
            this.cellSize = cellSize;
            return this;
        }

        public GridLayoutBuilder cellSpacing(int cellSpacing) {
            this.cellSpacing = cellSpacing;
            return this;
        }

        public GridLayoutBuilder rowCells(int rowCells) {
            this.rowCells = rowCells;
            return this;
        }

        public GridLayoutBuilder addButton(Button button) {
            this.buttons.add(button);
            return this;
        }

        public GridLayout build() {
            return new GridLayout(cellSize, cellSpacing, rowCells, List.copyOf(buttons));
        }
    }
}
