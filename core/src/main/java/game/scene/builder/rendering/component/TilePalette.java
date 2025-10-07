package game.scene.builder.rendering.component;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import game.scene.builder.logic.Builder;
import game.scene.builder.rendering.BuilderScreen;
import game.common.tilemap.TileDefinition;
import game.common.tilemap.Tileset;
import game.common.rendering.TableLayout;

import java.util.*;

public final class TilePalette extends TableLayout {
    private final Tileset tileSet;
    private final Builder builder;
    private final int idStart;
    private final int idEnd;

    private final int colsNum = 4;

    public TilePalette(Builder builder, Tileset tileSet, int idStart, int idEnd) {
        this.tileSet = tileSet;
        this.builder = builder;

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
