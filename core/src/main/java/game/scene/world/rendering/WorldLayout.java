package game.scene.world.rendering;

import game.common.rendering.TableLayout;
import game.common.rendering.GameScreen;
import game.common.rendering.component.ColoredString;
import game.scene.world.rendering.component.SelectingLayout;
import game.scene.world.logic.World;

public class WorldLayout extends TableLayout {
    private final SelectingLayout selectingLayout;
    private final ColoredString levelIdString;

    public WorldLayout() {
        selectingLayout = new SelectingLayout();
        levelIdString = new ColoredString();

        setup();
    }

    @Override
    public void setup() {
        setFillParent(true);

        add(selectingLayout).left().padLeft(20).expandX();
        row();
        add().expandY();
        row();
        add(levelIdString).left().pad(0, 20, 15, 0).expandX();
    }

    //Getters:
    public SelectingLayout getSelectingLayout() {
        return selectingLayout;
    }

    public ColoredString getLevelIdString() {
        return levelIdString;
    }
}
