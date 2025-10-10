package game.scene.level.rendering.component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import game.scene.common.rendering.CommandIconFactory;
import game.scene.common.rendering.TableLayout;
import game.resources.Assets;
import game.scene.level.logic.command.Command;

import java.util.LinkedList;

public final class LevelStatusbar extends TableLayout {
    private final LinkedList<Actor[]> itemGroups;
    private final int itemsLimit = 999;
    private final Image startImage;
    private final Image winImage;

    public LevelStatusbar() {
        itemGroups = new LinkedList<>();
        startImage = Assets.getImage("ui/icon/start.png");
        winImage = Assets.getImage("ui/icon/win.png");
    }

    @Override
    public void setup() { }

    // Class logic:
    public void update(Command command) {
        int totalItems = itemGroups.stream()
            .mapToInt(arr -> arr.length).sum();
        if (totalItems >= itemsLimit) itemGroups.removeFirst();

        itemGroups.add(CommandIconFactory.commandIcon(command));
        clearChildren();
        for (Actor[] group : itemGroups) {
            add().padLeft(4);
            for (Actor item : group) {
                add(item).padRight(-10).size(32, 32).expand(false, false);
                if (getParent() instanceof ScrollPane scrollPane) {
                    Gdx.app.postRunnable(() -> scrollPane.setScrollPercentX(1f));
                }
            }
            add().padRight(10);
        }
    }

    public void clear() {
        clearChildren();
        itemGroups.clear();
    }

    public void start() {
        itemGroups.add(new Image[] {startImage});
        add(startImage).size(32, 32).expand(false, false).padLeft(4);
    }

    public void win() {
        add(winImage).size(32, 32).expand(false, false).padLeft(4);
    }
}
