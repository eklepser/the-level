package com.eklepser.thelevel.logic.decoder;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.eklepser.thelevel.graphics.ui.code_editor.CodeLine;
import com.eklepser.thelevel.logic.Cat;

import java.util.ArrayList;
import java.util.List;

public class Translator {
    private final List<CodeLine> codeLines;
    private final List<String> allowedInstructions;

    public Translator(List<CodeLine> codeLines, Cat target, Actor actor) {
        this.codeLines = codeLines;
        allowedInstructions = Instruction.defaultAllowed().stream().map(
            instruction -> instruction.name).toList();
    }

    public TranslationResult translateAll() {
        List<Command> commands = new ArrayList<>();
        for (int i = 0; i < codeLines.size(); i++) {
            String text = codeLines.get(i).getText();
            if (text.isBlank()) continue;
            String[] words = text.split("\\s+");

            // Instruction existing check:
            String instructionName = words[0].toLowerCase();
            Instruction instruction;
            if (allowedInstructions.contains(instructionName)) {
                instruction = Instruction.fromName(instructionName);
            }
            else {
                String message = String.format("LINE %d: INSTRUCTION %s IS NOT ALLOWED", i + 1, instructionName);
                return new TranslationResult(false, message);
            }

            // Instruction format check:
            if (instruction.expectedArgs != words.length - 1) {
                String message = String.format("LINE %d: INSTRUCTION FORMAT IS NOT CORRECTED", i + 1);
                return new TranslationResult(false, message);
            }

            // Instruction arguments check:
            switch (instruction) {
                case MOVE:
                    if (!instruction.allowedArgs.contains(words[1].toLowerCase())) {
                        String message = String.format("LINE %d: ARGUMENT %s IS NOT ALLOWED", i + 1, words[1]);
                        return new TranslationResult(false, message);
                    }
                    break;
                case GOTO:
                    try {
                        int lineNum = Integer.parseInt(words[1]);
                        if ((lineNum < 1) || (lineNum > codeLines.size())) {
                            String message = String.format("LINE %d: ARGUMENT %d OUT OF RANGE", i + 1, lineNum);
                            return new TranslationResult(false, message);
                        }
                    }
                    catch (NumberFormatException e) {
                        String message = String.format("LINE %d: ARGUMENT %s IS NOT ALLOWED", i + 1, words[1]);
                        return new TranslationResult(false, message);
                    }
                    break;
            }

            // Creating new command-object
            Command command = new Command(instruction);

            // Adding command-object to all-commands list
            commands.add(command);
        }
        return new TranslationResult(true, "TRANSLATION: SUCCESS", commands);
    }
}
