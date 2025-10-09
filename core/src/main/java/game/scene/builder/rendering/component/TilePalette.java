package game.scene.builder.rendering.component;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import game.common.rendering.TableLayout;
import game.common.rendering.tilemap.TileDefinition;
import game.common.rendering.tilemap.Tileset;
import game.scene.builder.logic.Builder;

import java.util.Map;

public final class TilePalette extends TableLayout {
    private final Tileset tileset;
    private final Builder builder;
    private final int idStart;
    private final int idEnd;

    private final int colsNum = 4;

    public TilePalette(Builder builder, Tileset tileset, int idStart, int idEnd) {
        this.tileset = tileset;
        this.builder = builder;

        this.idStart = idStart;
        this.idEnd = idEnd;

        setup();
    }

    @Override
    public void setup() {
        Map<Integer, TileDefinition> defs = tileset.getDefinitions();

        int count = 1;
        for (int id : defs.keySet()) {

            if (id < idStart || id > idEnd) continue;

            TextureRegion icon = tileset.getTile(id);
            if (icon == null || icon.getTexture() == null) {
                continue;
            }

            ImageButton btn = new ImageButton(new TextureRegionDrawable(icon));
            btn.addListener(new ClickListener() {
                @Override public void clicked(InputEvent event, float x, float y) {
                    TileDefinition def = defs.get(id);
                    builder.setSelectedTileDef(def);
                }
            });
            add(btn);

            if ((count) % colsNum == 0) row();
            count++;
        }
    }
}
