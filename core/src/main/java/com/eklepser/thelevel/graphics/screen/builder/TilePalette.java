package com.eklepser.thelevel.graphics.screen.builder;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.eklepser.thelevel.graphics.render.TileDefinition;
import com.eklepser.thelevel.graphics.render.Tileset;
import com.eklepser.thelevel.graphics.screen.TableLayout;

import java.util.*;

public class TilePalette extends TableLayout {
    private final Tileset tileSet;
    private final Builder builder;
    private final int idStart;
    private final int idEnd;

    private final int colsNum = 4;

    public TilePalette(BuilderScreen screen, Tileset tileSet, int idStart, int idEnd) {
        this.tileSet = tileSet;
        builder = screen.getBuilder();

        this.idStart = idStart;
        this.idEnd = idEnd;

        setup();
    }

    @Override
    public void setup() {
        Map<Integer, TileDefinition> defs = tileSet.getDefinitions();

        int count = 1;
        for (int id : defs.keySet()) {

            if (id < idStart || id > idEnd) continue;

            TextureRegion icon = tileSet.getTile(id);
            if (icon == null || icon.getTexture() == null) {
                continue;
            }

            ImageButton btn = new ImageButton(new TextureRegionDrawable(icon));
            btn.addListener(new ClickListener() {
                @Override public void clicked(InputEvent event, float x, float y) {
                    builder.setSelectedTileDef(defs.get(id));
                }
            });
            add(btn);

            if ((count) % colsNum == 0) row();
            count++;
        }
    }
}
