package com.eklepser.thelevel.graphics.game.level.window;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Json;
import com.eklepser.thelevel.graphics.game.level.ExitLevelButton;
import com.eklepser.thelevel.graphics.utils.ColoredString;
import com.eklepser.thelevel.graphics.utils.TextLabel;
import com.eklepser.thelevel.graphics.Layout;
import com.eklepser.thelevel.util.Resources;

import java.util.ArrayList;

public class HelpWindow extends Window {
    private final Table commandsTable;
    private final ScrollPane scrollPane;
    private final ExitLevelButton exitLevelButton;
    private final ArrayList<CommandInfo> commandInfos;

    public HelpWindow(Game game) {
        super("", Resources.getSkin());
        commandsTable = new Table();
        scrollPane = new ScrollPane(commandsTable);
        exitLevelButton = new ExitLevelButton(game);

        Json json = new Json();
        commandInfos = json.fromJson(ArrayList.class, CommandInfo.class,
            Gdx.files.internal(Resources.COMMANDS_INFO));

        setupLayout();
    }

    private void setupLayout() {
        float editorWidth = Layout.VIEWPORT_WIDTH * Layout.EDITOR_MENU_SCALE;
        setSize(Layout.VIEWPORT_WIDTH - editorWidth, Layout.VIEWPORT_HEIGHT);
        setPosition(editorWidth, 0);

        for (CommandInfo info : commandInfos) {
            commandsTable.add(getCommandPanel(info)).pad(10).expand().left().row();
        }

        add(scrollPane);

        row();
        add(exitLevelButton).width(Layout.VIEWPORT_WIDTH / 4.0f)
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
        group.space(5f);
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
