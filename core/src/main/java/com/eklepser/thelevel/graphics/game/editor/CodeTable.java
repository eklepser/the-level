package com.eklepser.thelevel.graphics.game.editor;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.eklepser.thelevel.graphics.common.TextLabel;
import com.eklepser.thelevel.logic.world.level.LevelConfiguration;
import com.eklepser.thelevel.util.Direction;

import java.util.ArrayList;
import java.util.List;

public class CodeTable extends Table {
    private final Table root;
    private final LevelConfiguration conf;
    private ScrollPane codeScrollPane;
    private final List<CodeLine> codeLines = new ArrayList<>();
    private int selectedLine = -1;

    public CodeTable(Table root, LevelConfiguration conf) {
        this.conf = conf;
        this.root = root;
    }

    public void setupLayout(ScrollPane scrollPane) {
        codeScrollPane = scrollPane;
        createCodeLines(conf.codeLinesNum());
    }

    private void createCodeLines(int linesAmount) {
        for (int i = 0; i < linesAmount; i++)
        {
            row();
            CodeLine codeline = new CodeLine();
            TextLabel label = new TextLabel((String.valueOf(i + 1)));
            int finalI = i;
            codeline.addListener(new FocusListener() {
                @Override
                public void keyboardFocusChanged(FocusEvent event, Actor actor, boolean focused) {
                    if (focused) selectedLine = finalI;
                    codeScrollPane.scrollTo(codeline.getX(), codeline.getY() + codeline.getHeight(),
                        codeline.getWidth(), codeline.getHeight(), true, true);
                }
            });
            add(label).width(20).height(20).left().fillX().padRight(10);
            add(codeline).height(20).right().padBottom(1).colspan(3).fillX().expand().colspan(3);
            codeLines.add(codeline);
        }
    }

    public void clearCode() {
        codeLines.forEach(codeLine -> codeLine.setText(""));
    }

    public void setSelectedLine(Direction direction) {
        switch (direction)
        {
            case UP -> setSelectedLine(selectedLine - 1);
            case DOWN -> setSelectedLine(selectedLine + 1);
        }
    }

    public void setSelectedLine(int lineNum) {
        if ((lineNum >= 0) && (lineNum < codeLines.size())) {
            root.getStage().setKeyboardFocus(codeLines.get(lineNum));
        }
    }

    public List<CodeLine> getCodeLines() { return codeLines; }
}
