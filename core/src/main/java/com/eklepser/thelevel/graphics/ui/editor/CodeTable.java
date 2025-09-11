package com.eklepser.thelevel.graphics.ui.editor;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.eklepser.thelevel.graphics.ui.common.TextLabel;
import com.eklepser.thelevel.util.Direction;

import java.util.ArrayList;
import java.util.List;

public class CodeTable extends Table {
    private final Table root;
    private final List<CodeLine> codeLines = new ArrayList<>();
    private int selectedLine = -1;

    public CodeTable(Table root, int linesAmount) {
        this.root = root;
        createCodeLines(linesAmount);
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
                }
            });

            add(label).width(20).height(20).left().fillX().padRight(10);
            add(codeline).height(20).right().padBottom(1).colspan(3).fillX().expand().colspan(3);
            codeLines.add(codeline);
        }
    }

    public List<CodeLine> getCodeLines() { return codeLines; }

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
}
