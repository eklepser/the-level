package game.scene.builder.logic;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import game.common.tilemap.TileDefinition;
import game.resources.Assets;
import game.scene.level.logic.LevelConfiguration;

public final class Builder {
    private TileDefinition selectedTileDef;

    public Builder(LevelConfiguration config) {
        selectedTileDef = Assets.getTileset().getDefinitions().get(10);
    }

    // Getters & setters:
    public TileDefinition getSelectedTileDef() { return selectedTileDef; }

    public void setSelectedTileDef(TileDefinition selectedTileDef) {
        this.selectedTileDef = selectedTileDef;
    }

    public TextureRegion getSelectedTile() {
        return Assets.getTileset().getTile(selectedTileDef.id);
    }
}
