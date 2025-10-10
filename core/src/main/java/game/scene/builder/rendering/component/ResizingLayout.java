package game.scene.builder.rendering.component;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import game.resources.Assets;
import game.scene.builder.logic.Builder;
import game.scene.common.rendering.TableLayout;
import game.scene.common.rendering.component.InputField;
import game.scene.common.rendering.component.TextLabel;
import game.scene.common.rendering.tilemap.TileMap;

import static game.utils.NumberUtils.tryParseInt;

public final class ResizingLayout extends TableLayout {
    private final TileMap map;

    private final TextLabel widthLabel;
    private final TextLabel heightLabel;

    private final InputField inputWidth;
    private final InputField inputHeight;

    private final InputField inputOffsetX;
    private final InputField inputOffsetY;

    private final TextButton applyButton;

    public ResizingLayout(Builder builder) {
        map = builder.getMap();

        widthLabel = new TextLabel(String.format("X(%s)", map.width));
        heightLabel = new TextLabel(String.format("Y(%s)", map.height));

        inputWidth = new InputField("", 3);
        inputHeight = new InputField("", 3);

        inputOffsetX = new InputField("", 2);
        inputOffsetY = new InputField("", 2);

        applyButton = new TextButton("Apply", Assets.getSkin());
        applyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int width = parseSize(map.width, inputWidth.getText());
                int height = parseSize(map.height, inputHeight.getText());
                int offsetX = tryParseInt(inputOffsetX.getText(), 0);
                int offsetY = tryParseInt(inputOffsetY.getText(), 0);

                builder.resizeMap(width, height, offsetX, offsetY);

                widthLabel.setText(String.format("X(%s)", map.width));
                heightLabel.setText(String.format("Y(%s)", map.height));
            }
        });

        setup();
    }

    @Override
    public void setup() {
        add(new TextLabel("Resize:")).padRight(10);

        add(widthLabel).center().padRight(10);
        add(heightLabel).center();

        row();

        add(new TextLabel("size")).padRight(10);
        add(inputWidth).width(32).padRight(10);
        add(inputHeight).width(32);

        row();

        add(new TextLabel("offset")).padRight(10).padTop(4);
        add(inputOffsetX).width(32).padRight(10);
        add(inputOffsetY).width(32);

        row();

        add();
        add(applyButton).colspan(2).fillX();
    }

    private int parseSize(int startSize, String sizeText) {
        int newSize;

        if (sizeText.startsWith("-")) {
            newSize = startSize - Math.abs(tryParseInt(sizeText, startSize));
        } else if (sizeText.startsWith("+")) {
            newSize = startSize + Math.abs(tryParseInt(sizeText, startSize));
        } else {
            newSize = tryParseInt(sizeText, startSize);
        }

        if (newSize > 0) {
            return newSize;
        } else return startSize;
    }
}
