package com.eklepser.thelevel.graphics.screen.builder;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.eklepser.thelevel.graphics.render.TileSet;
import com.eklepser.thelevel.graphics.screen.Layout;
import com.eklepser.thelevel.util.Resources;

public class TilePalette extends Layout {
    private final TileSet tileSet;
    private final Builder builder;
    private final int colsNum = 4;

    public TilePalette(BuilderScreen screen) {
        tileSet = Resources.getTileSet();
        builder = screen.getBuilder();

        setup();
    }

    @Override
    protected void setup() {
        for (int i = 0; i < 20; i++) {
            TextureRegion icon = tileSet.getTile(i);
            if (icon == null || icon.getTexture() == null) {
                continue;
            }

            int finalI = i;
            ImageButton btn = new ImageButton(new TextureRegionDrawable(icon));
            btn.addListener(new ClickListener() {
                @Override public void clicked(InputEvent event, float x, float y) {
                    builder.setSelectedTile(finalI);
                }
            });
            add(btn);

            if (i % colsNum == 0) row();
        }
    }
}
