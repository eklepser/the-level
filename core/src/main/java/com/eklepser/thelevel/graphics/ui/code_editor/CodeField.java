package com.eklepser.thelevel.graphics.ui.code_editor;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.eklepser.thelevel.graphics.ui.common.TextLabel;

import java.util.ArrayList;
import java.util.List;

public class CodeField {
    private final Table root;
    private final List<CodeLine> codeLines = new ArrayList<>();

    public CodeField(Table root, int linesAmount) {
        this.root = root;
        createCodeLines(linesAmount);
    }

    private void createCodeLines(int linesAmount) {
        for (int i = 0; i < linesAmount; i++)
        {
            root.row();
            CodeLine codeline = new CodeLine();
            root.add(new TextLabel(String.valueOf(i + 1))).width(20).height(20).left().fillX();
            root.add(codeline).width(150).height(20).right().padBottom(2);
            codeLines.add(codeline);
        }
    }

    public List<CodeLine> getCodeLines() { return codeLines; }
}
