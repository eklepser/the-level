package game.scene.level.window;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Json;
import game.config.Display;
import game.config.Paths;
import game.data.IO.Assets;
import game.scene.common.rendering.component.ColoredString;
import game.scene.common.rendering.component.TextLabel;
import game.scene.level.rendering.component.ExitLevelButton;

import java.util.ArrayList;

public class HelpWindow extends Window {
    private final Table commandsTable;
    private final ScrollPane scrollPane;
    private final ExitLevelButton exitLevelButton;
    private final ArrayList<CommandInfo> commandInfos;

    public HelpWindow() {
        super("", Assets.getSkin());
        commandsTable = new Table();
        scrollPane = new ScrollPane(commandsTable);
        scrollPane.setCancelTouchFocus(false);
        exitLevelButton = new ExitLevelButton();

        Json json = new Json();
        commandInfos = json.fromJson(ArrayList.class, CommandInfo.class,
            Gdx.files.internal(Paths.COMMANDS_INFO));

        setupLayout();
    }

    private void setupLayout() {
        float editorWidth = Display.VIEWPORT_WIDTH * Display.EDITOR_MENU_SCALE;
        float freeWidth = Display.VIEWPORT_WIDTH - editorWidth;

        setSize(freeWidth, Display.VIEWPORT_HEIGHT);
        setVisible(false);
        setColor(0.5f, 0, 0.75f, 0.9f);

        defaults().padLeft(16f);
        add(new ColoredString("/_2 Hotkeys")).padBottom(8).colspan(2).center().padLeft(2);
        row();
        add(new ColoredString("F1 /light-gray Increase execution speed")).left();
        add(new ColoredString("F4, CTRL+\\ /light-gray Reset execution")).left();
        row();
        add(new ColoredString("F2 /light-gray Decrease execution speed")).left();
        add(new ColoredString("F5, CTRL+ENTER /light-gray Run execution")).left();
        defaults().padLeft(0);

        row().colspan(2);
        add(new ColoredString("/_2 Commands")).padTop(12).center();
        row().colspan(2);

        for (CommandInfo info : commandInfos) {
            commandsTable.add(getCommandPanel(info)).pad(10).expand().left().fillX().row();
        }

        add(scrollPane).left();

        row().colspan(2);
        add(exitLevelButton).width(Display.VIEWPORT_WIDTH / 4.0f)
            .height(Display.VIEWPORT_HEIGHT / 12.0f).pad(30, 10, 30, 10);

        setPosition(editorWidth, 0);
    }

    public void invertPosition(boolean editorOnRight) {
        float editorWidth = Display.VIEWPORT_WIDTH * Display.EDITOR_MENU_SCALE;
        if (editorOnRight) {
            setPosition(0, 0);
        } else {
            setPosition(editorWidth, 0);
        }
    }

    private Table getCommandPanel(CommandInfo info) {
        Image icon = new Image(new Texture(Gdx.files.internal(info.iconPath)));
        ColoredString title = new ColoredString(info.titleText);
        String argsText = info.argsText;
        String exampleText = info.exampleText;
        TextLabel desc = new TextLabel(info.descriptionText);
        desc.setWrap(true);
        desc.setColor(0.65f, 0.65f, 0.65f, 1);

        Table table = new Table();
        HorizontalGroup group = new HorizontalGroup();
        group.space(5f);
        group.addActor(icon);
        group.addActor(title);

        table.row().colspan(2);
        table.add(group).left().expandX();

        if (!argsText.isEmpty()) {
            ColoredString args = new ColoredString(argsText);
            table.row().colspan(2);
            table.add(args).left().padTop(4);
        }

        if (!exampleText.isEmpty()) {
            ColoredString example = new ColoredString(exampleText);
            table.row().colspan(2);
            table.add(example).left().fillX();
        }

        table.row().colspan(2).padTop(4);
        table.add(desc).left().fillX();
        return table;
    }

    public void toggle() {
        boolean willBeVisible = !isVisible();
        setVisible(willBeVisible);
        if (willBeVisible && getStage() != null) {
            getStage().setScrollFocus(scrollPane);
        }
    }
}
