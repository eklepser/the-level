package com.eklepser.thelevel.graphics.level.root;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.eklepser.thelevel.logic.decoder.command.Command;
import com.eklepser.thelevel.logic.world.entity.Entity;

import java.util.LinkedList;

public class StatusBar extends Table {
    private final LinkedList<Actor[]> itemGroups = new LinkedList<>();
    private final int itemsLimit = 999;
    private final Image startImage;
    private final Image winImage;

    public StatusBar() {
        startImage = new Image(new Texture(Gdx.files.internal("ui/icon/start.png")));
        winImage = new Image(new Texture(Gdx.files.internal("ui/icon/win.png")));
        clear();
    }

    public void update(Command command, Entity target) {
        int totalItems = itemGroups.stream()
            .mapToInt(arr -> arr.length).sum();
        if (totalItems >= itemsLimit) itemGroups.removeFirst();
        itemGroups.add(command.getIcons(target));
        clearChildren();
        for (Actor[] group : itemGroups) {
            add().padLeft(4);
            for (Actor item : group) {
                add(item).padRight(-10).size(32, 32).expand(false, false);
                if (getParent() instanceof ScrollPane scrollPane)
                {
                    Gdx.app.postRunnable(() -> {
                        scrollPane.setScrollPercentX(1f);
                    });
                }
            }
            add().padRight(10);
        }
    }

    public void clear() {
        clearChildren();
        itemGroups.clear();
        itemGroups.add(new Actor[] {startImage});
        add(startImage).size(32, 32).expand(false, false).padLeft(4);
    }

    public void win() {
        add(winImage).size(32, 32).expand(false, false).padLeft(4);
    }
}
