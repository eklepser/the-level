package com.eklepser.thelevel.logic.decoder;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.eklepser.thelevel.graphics.ui.code_editor.CodeLine;
import com.eklepser.thelevel.logic.decoder.commands.Command;
import com.eklepser.thelevel.logic.world.Entity;

import java.util.List;
import java.util.Map;

public class Executor {
    private final Translator translator;
    private final List<CodeLine> codeLines;
    private Map<CodeLine, Command> codeMap;
    private final Actor target;

    public Executor(List<CodeLine> codeLines, Entity target) {
        this.codeLines = codeLines;
        this.target = target;
        translator = new Translator(this, codeLines, target);
    }

    public void executeAll(int start, Map<CodeLine, Command> codeMap) {
        System.out.println("Running");
        target.clearActions();
        SequenceAction sequence = new SequenceAction();
        for (int i = start; i < codeLines.size(); i++) {
            CodeLine currentLine = codeLines.get(i);
            Command currentCmd = codeMap.get(currentLine);
            if (currentCmd == null) continue;

            sequence.addAction(Actions.run(() -> currentLine.setCompleting(true)));
            sequence.addAction(Actions.delay(0.25f));
            sequence.addAction(Actions.run(() -> currentLine.setCompleting(false)));
            sequence.addAction(Actions.run(currentCmd::execute));
        }
        target.addAction(sequence);
    }

    public String translateAll() {
        TranslationResult result = translator.translateAll();
        if (result.success()) {
            codeMap = result.getCodeMap();
            System.out.println(result.message());
            executeAll(0, codeMap);
        }
        else {
            System.out.println(result.message());
        }
        return result.message();
    }

    public void stop() {
        target.clearActions();
    }

    public Map<CodeLine, Command> getCodeMap() {
        return codeMap;
    }
}
