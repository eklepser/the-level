package com.eklepser.thelevel.graphics.screen.builder;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.eklepser.thelevel.graphics.render.TileSet;
import com.eklepser.thelevel.graphics.screen.TableLayout;

public class TilePalette extends TableLayout {
    private final TileSet tileSet;
    private final Builder builder;
    private final EditMode mode;

    private final int colsNum = 4;

    private int tileType;

    public TilePalette(BuilderScreen screen, TileSet tileSet, EditMode mode) {
        this.tileSet = tileSet;
        builder = screen.getBuilder();
        this.mode = mode;

        setup();
    }

    @Override
    public void setup() {
        for (int i = 0; i < 30; i++) {
            TextureRegion icon = tileSet.getTile(i);
            if (icon == null || icon.getTexture() == null) {
                continue;
            }

            int finalI = i;
            ImageButton btn = new ImageButton(new TextureRegionDrawable(icon));
            btn.addListener(new ClickListener() {
                @Override public void clicked(InputEvent event, float x, float y) {
                    builder.setMode(mode);
                    builder.setSelectedTileId(finalI);
                }
            });
            add(btn);

            if ((i + 1) % colsNum == 0) row();
        }
    }
}
