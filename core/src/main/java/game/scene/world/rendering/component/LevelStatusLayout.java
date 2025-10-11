package game.scene.world.rendering.component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import game.data.level.LevelData;
import game.data.user.LevelStatus;
import game.scene.common.rendering.TableLayout;
import game.scene.common.rendering.component.ColoredString;
import game.scene.common.rendering.component.TextLabel;
import game.scene.level.logic.command.Instruction;

public final class LevelStatusLayout extends TableLayout {
    private final ColoredString levelString;
    private final ColoredString limitString;
    private final ColoredString commandsString;

    private final Table commandsTable;
    private final int itemsPerRow = 2;

    private final ColoredString completedString;

    public LevelStatusLayout() {
        levelString = new ColoredString();
        limitString = new ColoredString();
        commandsString = new ColoredString();

        commandsTable = new Table();

        completedString = new ColoredString();

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
        row();
        add(completedString).left();
    }

    //Class logic:
    public void setStatus(LevelData levelData, LevelStatus levelStatus) {
        if (levelStatus.equals(LevelStatus.LOCKED)) {
            levelString.setText("/_3 ???");
            return;
        }

        levelString.setText("/_3 " + levelData.metadata.title);
        limitString.setText("/gray_1.5 Lines limit: /white_2 " + levelData.metadata.codeLinesAmount);
        commandsString.setText("/gray_1.5 Allowed commands:");

        commandsTable.clear();
        int count = 0;
        for (Instruction instruction : levelData.metadata.allowedInstructions) {
            String iconPath = instruction.iconPath + ".png";
            Image iconImage = new Image(new Texture(Gdx.files.internal(iconPath)));
            commandsTable.add(iconImage).padRight(5);
            TextLabel label = new TextLabel("(" + instruction.name + ")");
            commandsTable.add(label).padRight(10);
            if (++count % itemsPerRow == 0) commandsTable.row().padTop(10);
        }

        if (levelStatus.equals(LevelStatus.COMPLETED)) {
            completedString.setText("/green_1.5 Completed");
        }
        else {
            completedString.setText("/red_1.5 Uncompleted");
        }
    }
}
