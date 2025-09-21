package com.eklepser.thelevel.graphics.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Json;
import com.eklepser.thelevel.graphics.common.ColoredString;
import com.eklepser.thelevel.graphics.common.TextLabel;
import com.eklepser.thelevel.graphics.game.root.CommandInfo;
import com.eklepser.thelevel.graphics.selection.buttons.LevelButton;
import com.eklepser.thelevel.logic.world.level.LevelConfiguration;
import com.eklepser.thelevel.util.Layout;
import com.eklepser.thelevel.util.Resources;

import java.util.ArrayList;

public class HelpWindow extends Window {
    public HelpWindow(Game game) {
        super("", Resources.getSkin());
        float editorWidth = Layout.VIEWPORT_WIDTH * Layout.EDITOR_MENU_SCALE;
        setSize(Layout.VIEWPORT_WIDTH - editorWidth, Layout.VIEWPORT_HEIGHT);
        setPosition(editorWidth, 0);
        Json json = new Json();

        Table table = new Table();
        ScrollPane scrollPane = new ScrollPane(table);
        ArrayList<CommandInfo> infos = json.fromJson(ArrayList.class, CommandInfo.class,
            Gdx.files.internal("ui/text/commands-info.json"));
        for (CommandInfo info : infos) {
            table.add(getCommandPanel(info)).pad(10).expand().left().row();
        }

        add(scrollPane);

        row();
        add(new ExitLevelButton(game)).width(Layout.VIEWPORT_WIDTH / 4.0f)
            .height(Layout.VIEWPORT_HEIGHT / 16.0f).padBottom(40);
        setVisible(false);
        setColor(0.5f, 0, 0.75f, 0.9f);
    }

    private Table getCommandPanel(CommandInfo info) {
        ColoredString title = new ColoredString(info.getTitleText());
        Image icon = new Image(new Texture(Gdx.files.internal(info.getIconPath())));

        TextLabel desc = new TextLabel(info.getDescriptionText());

        Table table = new Table();

        HorizontalGroup group = new HorizontalGroup();
        group.addActor(icon);
        group.addActor(title);

        table.row().colspan(2);
        table.add(group).left();

        String argsText = info.getArgsText();
        if (!argsText.isEmpty()) {
            ColoredString args = new ColoredString(info.getArgsText());
            table.row().colspan(2);
            table.add(args).left();
        }

        table.row().colspan(2);
        table.add(desc).left();
        return table;
    }

    public void toggle() { setVisible(!isVisible()); }
}
