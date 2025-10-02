package com.eklepser.thelevel.graphics.screen.builder;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eklepser.thelevel.graphics.render.TileMap;
import com.eklepser.thelevel.graphics.screen.TableLayout;
import com.eklepser.thelevel.graphics.util.InputField;
import com.eklepser.thelevel.graphics.util.TextLabel;
import com.eklepser.thelevel.util.Resources;

import static com.eklepser.thelevel.util.Util.tryParseInt;

public class ResizeTable extends TableLayout {
    private final BuilderScreen screen;
    private final TileMap map;

    private final TextLabel widthLabel;
    private final TextLabel heightLabel;

    private final InputField inputWidth;
    private final InputField inputHeight;

    private final InputField inputOffsetX;
    private final InputField inputOffsetY;

    private final TextButton applyButton;

    public ResizeTable(BuilderScreen screen) {
        this.screen = screen;
        map = screen.getMap();

        widthLabel = new TextLabel(String.format("X(%s)", map.width));
        heightLabel = new TextLabel(String.format("Y(%s)", map.height));

        inputWidth = new InputField("", 3);
        inputHeight = new InputField("", 3);

        inputOffsetX = new InputField("", 2);
        inputOffsetY = new InputField("", 2);

        applyButton = new TextButton("Apply", Resources.getSkin());
        applyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                resizeMap();
            }
        });

        setup();
    }

    @Override
    public void setup() {
        add(new TextLabel("Resize:")).padRight(10);

        add(widthLabel).center().padRight(10);
        add(heightLabel).center();

        row();

        add(new TextLabel("size")).padRight(10).left();
        add(inputWidth).width(32).padRight(10);
        add(inputHeight).width(32);

        row();

        add(new TextLabel("offset")).padRight(10).padTop(4).left();
        add(inputOffsetX).width(32).padRight(10);
        add(inputOffsetY).width(32);

        row();

        add();
        add(applyButton).colspan(2).fillX();
    }

    // Class logic:
    private void resizeMap() {
        int width = parseSize(map.width, inputWidth.getText());
        int height = parseSize(map.width, inputHeight.getText());
        int offsetX = tryParseInt(inputOffsetX.getText(), 0);
        int offsetY = tryParseInt(inputOffsetY.getText(), 0);

        map.resize(width, height);
        map.offset(offsetX, offsetY);

        screen.getGridActor().update();
        updateLabels();
    }

    private int parseSize(int startSize, String sizeText) {
        int newSize;

        if (sizeText.startsWith("-")) {
            newSize = startSize - Math.abs(tryParseInt(sizeText, startSize));
        } else if (sizeText.startsWith("+")) {
            newSize = startSize + Math.abs(tryParseInt(sizeText, startSize));
        } else {
            newSize = tryParseInt(sizeText, startSize);
        }

        if (newSize > 0) {
            return newSize;
        } else return startSize;
    }

    private void updateLabels() {
        widthLabel.setText(String.format("X(%s)", map.width));
        heightLabel.setText(String.format("Y(%s)", map.height));
    }
}
