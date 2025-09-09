package com.eklepser.thelevel.graphics.ui.code_editor;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eklepser.thelevel.logic.world.Entity;
import com.eklepser.thelevel.util.Resources;
import com.eklepser.thelevel.graphics.ui.common.TextLabel;
import com.eklepser.thelevel.graphics.screen.GameField;
import com.eklepser.thelevel.util.Direction;
import com.eklepser.thelevel.logic.decoder.Executor;

public class Editor {
    private final Table root;
    private final CodeField codeField;
    private final InputProcessor keyboardProcessor;
    private final Executor executor;
    private final TextLabel statusLabel;

    public Editor(Entity entity, GameField gameField) {
        root = new Table();
        root.add(new TextLabel("Code:")).padBottom(10);
        codeField = new CodeField(root, 12);
        keyboardProcessor = createKeyboardProcessor(codeField);
        executor = new Executor(codeField.getCodeLines(), gameField, entity);
        statusLabel = new TextLabel("Status:");
        statusLabel.setWrap(true);

        root.row().padTop(10).padBottom(10);
        root.add(new TextLabel("Action:"));
        root.add(createRunButton());
        root.add(createResetButton());
        root.add(createClearButton());
        root.row();
        root.add(statusLabel).colspan(3).fillX().height(40).top();

        setDefault();
    }

    private InputAdapter createKeyboardProcessor(CodeField codeField) {
        return new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.DOWN || keycode == Input.Keys.ENTER) {
                    codeField.setSelectedLine(Direction.DOWN);
                    return true;
                } else if (keycode == Input.Keys.UP) {
                    codeField.setSelectedLine(Direction.UP);
                    return true;
                }
                return false;
            }
        };
    }

    private Button createRunButton() {
        Button button = new TextButton("Run", Resources.getSkin());
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String status = executor.translateAll();
                statusLabel.setText(status);
            }
        });
        return button;
    }

    private Button createResetButton() {
        Button button = new TextButton("Stop", Resources.getSkin());
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Stopping");
                codeField.getCodeLines().forEach(codeLine -> codeLine.setCompleting(false));
                executor.stop();
                statusLabel.setText("Status: ");
            }
        });
        return button;
    }

    private Button createClearButton() {
        Button button = new TextButton("Clear", Resources.getSkin());
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Clearing");
                executor.stop();
                codeField.getCodeLines().forEach(codeLine -> codeLine.setCompleting(false));
                codeField.getCodeLines().forEach(codeLine -> codeLine.setText(""));
                statusLabel.setText("Status: ");
            }
        });
        return button;
    }

    public Table getTable() { return root; }

    public InputProcessor getKeyboardProcessor() { return keyboardProcessor; }

    private void setDefault() {
        for (int i = 0; i < codeField.getCodeLines().size(); i++) {
            if (i==0) codeField.getCodeLines().get(i).setText(";My app");
            if (i==1) codeField.getCodeLines().get(i).setText("MOVE UP ;go up!");
            if (i==2) codeField.getCodeLines().get(i).setText(";boo ;boo ;boo");
            if (i==3) codeField.getCodeLines().get(i).setText("MOVE RIGHT");
            if (i==4) codeField.getCodeLines().get(i).setText("");
            if (i==5) codeField.getCodeLines().get(i).setText("MOVE RIGHT");
            if (i==6) codeField.getCodeLines().get(i).setText("MOVE DOWN");
            if (i==7) codeField.getCodeLines().get(i).setText(";yoo");
            if (i==8) codeField.getCodeLines().get(i).setText("MOVE LEFT");
            if (i==9) codeField.getCodeLines().get(i).setText("MOVE UP");
            if (i==10) codeField.getCodeLines().get(i).setText("");
            if (i==11) codeField.getCodeLines().get(i).setText("GOTO 5");
        }
    }
}
