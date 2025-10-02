package com.eklepser.thelevel.graphics.screen.builder;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eklepser.thelevel.graphics.render.TileMap;
import com.eklepser.thelevel.graphics.screen.TableLayout;
import com.eklepser.thelevel.graphics.util.InputField;
import com.eklepser.thelevel.graphics.util.TextLabel;
import com.eklepser.thelevel.util.Resources;

public class ResizeTable extends TableLayout {
    private final BuilderScreen screen;

    private final InputField inputWidth;
    private final InputField inputHeight;

    private final InputField inputOffsetX;
    private final InputField inputOffsetY;

    private final TextButton applyButton;

    public ResizeTable(BuilderScreen screen) {
        this.screen = screen;

        inputWidth = new InputField("", 2);
        inputHeight = new InputField("", 2);

        inputOffsetX = new InputField("", 2);
        inputOffsetY = new InputField("", 2);

        applyButton = new TextButton("Apply", Resources.getSkin());
        applyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                resize();
            }
        });

        setup();
    }

    @Override
    public void setup() {
        add(new TextLabel("'x'.'y':")).padRight(10);
        add(inputWidth).width(32);
        add(new TextLabel("."));
        add(inputHeight).width(32);

        row();

        add(new TextLabel("offset:")).padRight(10).padTop(4);
        add(inputOffsetX).width(32);
        add(new TextLabel("."));
        add(inputOffsetY).width(32);

        row();

        add();
        add(applyButton).colspan(3);
    }

    // Class logic:
    private void resize() {
        int width = Integer.parseInt(inputWidth.getText());
        int height = Integer.parseInt(inputHeight.getText());

        screen.getMap().resize(width, height);
        screen.getGridActor().update();

    }
}
