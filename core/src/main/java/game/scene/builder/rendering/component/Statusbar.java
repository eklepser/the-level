package game.scene.builder.rendering.component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import game.config.Display;
import game.scene.builder.logic.Builder;
import game.scene.builder.rendering.BuilderScreen;
import game.common.rendering.tilemap.TileDefinition;
import game.common.rendering.TableLayout;
import game.common.rendering.component.ColoredString;
import game.common.rendering.component.TextLabel;

public final class Statusbar extends TableLayout {
    private final BuilderScreen screen;
    private final Builder builder;

    private final Image selectedTileImage;
    private final TextLabel selectedTileLabel;
    private final ColoredString actionLabel;
    private final TextLabel cursorPositionLabel;
    private final TextLabel cameraPositionLabel;

    public Statusbar(BuilderScreen screen) {
        this.screen = screen;
        builder = screen.getBuilder();

        selectedTileImage = new Image();
        selectedTileLabel = new TextLabel();

        actionLabel = new ColoredString("");

        cursorPositionLabel = new TextLabel();
        cameraPositionLabel = new TextLabel();

        setup();
    }

    @Override
    public void setup() {
        int zoneWidth = Display.VIEWPORT_WIDTH / 3;

        add(new TextLabel("Selected:")).width(zoneWidth / 4.0f).bottom();
        add(selectedTileImage).width(Display.TILE_SIZE).padRight(10).bottom();
        add(selectedTileLabel).width(3 * zoneWidth / 4.0f - Display.TILE_SIZE - 10).bottom();
        add().expandX();

        add(actionLabel).center();
        add().expandX();

        add(cursorPositionLabel).width(zoneWidth / 2.0f).bottom();
        add(cameraPositionLabel).width(zoneWidth / 2.0f).bottom();

        update();
    }

    public void update() {
        TextureRegion icon = builder.getSelectedTile();
        if (icon == null || icon.getTexture() == null) return;

        selectedTileImage.setDrawable(new TextureRegionDrawable(icon));

        TileDefinition def = builder.getSelectedTileDef();
        String selectedText = String.format("%s (%s)", def.type, def.id );
        selectedTileLabel.setText(selectedText);

        Vector2 screenPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        Vector2 gridPos = screen.getGridStage().screenToStageCoordinates(screenPos);
        String cursorText = String.format("Cursor: %.2f, %.2f", gridPos.x / 32f, gridPos.y / 32f);
        cursorPositionLabel.setText(cursorText);

        Vector3 cameraPos = screen.getCamera().position;
        String cameraText = String.format("Cam: %.2f, %.2f", cameraPos.x / 32f, cameraPos.y / 32f);
        cameraPositionLabel.setText(cameraText);
    }

    public void setActionText(String text) {
        actionLabel.setText(text);
    }
}
