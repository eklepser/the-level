package game.scene.builder.logic;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import game.scene.builder.rendering.BuilderScreen;
import game.common.rendering.tilemap.TileDefinition;
import game.common.rendering.GameScreen;
import game.resources.Assets;

public class Builder {
    private final BuilderScreen screen;

    private TileDefinition selectedTileDef;

    public Builder(BuilderScreen screen) {
        this.screen = screen;

        selectedTileDef = Assets.getTileset().getDefinitions().get(10);
    }

    // Getters & setters:
    public GameScreen getScreen() {
        return screen;
    }

    public TileDefinition getSelectedTileDef() { return selectedTileDef; }

    public void setSelectedTileDef(TileDefinition selectedTileDef) {
        this.selectedTileDef = selectedTileDef;
    }

    public TextureRegion getSelectedTile() {
        return Assets.getTileset().getTile(selectedTileDef.id);
    }
}
