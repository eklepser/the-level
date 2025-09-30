package com.eklepser.thelevel.graphics.screen.builder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.eklepser.thelevel.graphics.screen.TableLayout;
import com.eklepser.thelevel.graphics.utils.TextLabel;

public class StatusBar extends TableLayout {
    private final BuilderScreen screen;
    private final Builder builder;
    private final Image selectedTileImage;
    private final TextLabel selectedTileLabel;
    private final TextLabel cursorPositionLabel;
    private final TextLabel cameraPositionLabel;

    public StatusBar(BuilderScreen screen) {
        this.screen = screen;
        builder = screen.getBuilder();

        selectedTileImage = new Image();
        selectedTileLabel = new TextLabel();
        cursorPositionLabel = new TextLabel();
        cameraPositionLabel = new TextLabel();

        setup();
    }

    @Override
    public void setup() {
        add(new TextLabel("Selected:")).padRight(20);
        add(selectedTileImage).padRight(20);
        add(selectedTileLabel);
        add().expandX();
        add(cursorPositionLabel).padRight(20);
        add(cameraPositionLabel);

        update();
    }

    public void update() {
        TextureRegion icon = builder.getSelectedTile();
        if (icon == null || icon.getTexture() == null) return;

        Image image = new Image(icon);
        selectedTileImage.setDrawable(new TextureRegionDrawable(icon));

        String selectedText = String.format("%s (%s)", builder.getMode(), builder.getSelectedTileId());
        selectedTileLabel.setText(selectedText);

        Vector2 screenPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        Vector2 gridPos = screen.getGridStage().screenToStageCoordinates(screenPos);
        String cursorText = String.format("Cursor: %.2f, %.2f", gridPos.x / 32f, gridPos.y / 32f);
        cursorPositionLabel.setText(cursorText);

        Vector3 cameraPos = screen.getCamera().position;
        String cameraText = String.format("Cam: %.2f, %.2f", cameraPos.x / 32f, cameraPos.y / 32f);
        cameraPositionLabel.setText(cameraText);
    }
}
