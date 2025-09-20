package com.eklepser.thelevel.graphics.game.root;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.eklepser.thelevel.graphics.common.TextLabel;
import com.eklepser.thelevel.logic.decoder.command.Command;
import com.eklepser.thelevel.logic.world.collision.Entity;
import com.eklepser.thelevel.util.Layout;

import java.util.LinkedList;

public class StatusRow extends Table {
    private final LinkedList<Actor[]> itemGroups = new LinkedList<>();
    private final int itemsLimit = Layout.VIEWPORT_WIDTH / Layout.TILE_SIZE;
    private final float fadingSpeed = 0.036f;

    public StatusRow() {
        setWidth(Layout.VIEWPORT_WIDTH);
        add(new TextLabel("")).height(32);
    }

    public void update(Command command, Entity target) {
        int totalItems = itemGroups.stream()
            .mapToInt(arr -> arr.length).sum();
        if (totalItems >= itemsLimit) itemGroups.removeFirst();

        itemGroups.add(command.getIcons(target));
        clearChildren();

        for (int i = 0; i < itemGroups.size(); i++) {
            Actor[] group = itemGroups.get(i);
            for (Actor actor : group) {
                float coef = 1 - (float) (itemGroups.size() - i) * fadingSpeed;
                actor.setColor(actor.getColor().r, actor.getColor().g, actor.getColor().b, coef);
            }
        }

        for (Actor[] group : itemGroups) {
            add().padLeft(4);
            for (Actor item : group) {
                add(item).padRight(-16).size(32, 32).expand(false, false);
            }
            add().padRight(16);
        }
    }

    public void clear() {
        clearChildren();
        itemGroups.clear();
        add(new TextLabel("")).height(32);
    }
}
