package game.scene.level.rendering.component.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import game.scene.common.rendering.TableLayout;
import game.scene.common.rendering.component.TextLabel;
import game.scene.level.logic.command.Instruction;

import java.util.List;

public final class CommandsLayout extends TableLayout {
    private final List<Instruction> allowedInstructions;
    private final int itemsPerRow = 4;
    private boolean showLabels = false;

    public CommandsLayout(List<Instruction> allowedInstructions) {
        this.allowedInstructions = allowedInstructions;
        setup();
    }

    @Override
    public void setup() {
        int count = 0;
        for (Instruction instruction : allowedInstructions) {
            String iconPath = instruction.iconPath + ".png";
            Image iconImage = new Image(new Texture(Gdx.files.internal(iconPath)));
            add(iconImage).padRight(5);
            if (showLabels) {
                TextLabel label = new TextLabel("(" + instruction.name + ")");
                add(label).padRight(10);
            }
            if (++count % itemsPerRow == 0) row();
        }
    }

    // Class logic:
    public void update(boolean showLabels) {
        clearChildren();
        this.showLabels = showLabels;
        setup();
    }
}
