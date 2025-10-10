package game.scene.world.rendering.component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import game.common.rendering.TableLayout;
import game.common.rendering.component.ColoredString;
import game.common.rendering.component.TextLabel;
import game.scene.level.logic.LevelConfigurationOld;
import game.scene.level.logic.command.Instruction;

public final class LevelStatusLayout extends TableLayout {
    private final ColoredString levelString;
    private final ColoredString limitString;
    private final ColoredString commandsString;
    private final Table commandsTable;
    private final int itemsPerRow = 2;

    public LevelStatusLayout() {
        levelString = new ColoredString();
        limitString = new ColoredString();
        commandsString = new ColoredString();
        commandsTable = new Table();

        setup();
    }

    @Override
    public void setup() {
        add(levelString).left();
        row();
        add(limitString).left();
        row();
        add(commandsString).left();
        row();
        add(commandsTable).left().padTop(10);
    }

    //Class logic:
    public void setStatus(LevelConfigurationOld config) {
        levelString.setText("/_3 " + config.title);
        limitString.setText("/gray_1.5 Lines limit: /white_2 " + config.codeLinesNum);
        commandsString.setText("/gray_1.5 Allowed commands:");

        commandsTable.clear();
        int count = 0;
        for (Instruction instruction : config.allowedInstructions) {
            String iconPath = instruction.iconPath + ".png";
            Image iconImage = new Image(new Texture(Gdx.files.internal(iconPath)));
            commandsTable.add(iconImage).padRight(5);
            TextLabel label = new TextLabel("(" + instruction.name + ")");
            commandsTable.add(label).padRight(10);
            if (++count % itemsPerRow == 0) commandsTable.row().padTop(10);
        }
    }
}
