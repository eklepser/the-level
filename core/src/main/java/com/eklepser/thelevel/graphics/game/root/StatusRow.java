package com.eklepser.thelevel.graphics.game.root;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.eklepser.thelevel.graphics.common.TextLabel;
import com.eklepser.thelevel.logic.decoder.command.Command;
import com.eklepser.thelevel.logic.world.collision.Entity;
import com.eklepser.thelevel.util.Layout;

import java.util.LinkedList;

public class StatusRow extends Table {

    private final LinkedList<Actor[]> itemGroups = new LinkedList<>();
    private final int itemsLimit = 100;
    private final float fadingSpeed = 0.005f;
    private final Image startImage;

    public StatusRow() {
        setHeight(32);
        setFillParent(true);
        add(new TextLabel("")).height(32);
        startImage = new Image(new Texture(Gdx.files.internal("ui/icon/start.png")));
    }

    public void update(Command command, Entity target) {
        itemGroups.add(command.getIcons(target));
        clearChildren();

        for (int i = 0; i < itemGroups.size(); i++) {
            Actor[] group = itemGroups.get(i);
            for (Actor actor : group) {
                float coef = 1 - (float) (itemGroups.size() - i) * fadingSpeed;
                actor.setColor(actor.getColor().r, actor.getColor().g, actor.getColor().b, coef);
            }
        }


        add(startImage).size(32, 32).expand(false, false);
        for (Actor[] group : itemGroups) {
            add().padLeft(4);
            for (Actor item : group) {
                add(item).padRight(-10).size(32, 32).expand(false, false);
            }
            add().padRight(10);
            if (getParent() instanceof ScrollPane scrollPane)  scrollPane.setScrollPercentX(1f);
        }
    }

    public void clear() {
        clearChildren();
        itemGroups.clear();
        add(new TextLabel("")).height(32);
    }
}
