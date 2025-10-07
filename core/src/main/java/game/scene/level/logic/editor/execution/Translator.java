package game.scene.level.logic.editor.execution;

import game.scene.level.rendering.component.editor.CodeLine;
import game.scene.level.logic.editor.command.Command;
import game.scene.level.logic.editor.command.Instruction;
import game.scene.level.logic.editor.condition.ConditionPattern;

import java.util.*;

public final class Translator {
    private final Executor executor;
    private final Map<Integer, Command> codeMap;
    private final List<String> allowedInstructionNames;

    public Translator(List<Instruction> allowedInstructions, Executor executor) {
        this.executor = executor;
        allowedInstructionNames = allowedInstructions.stream()
            .map(instruction -> instruction.name).toList();
        codeMap = new HashMap<>();
    }

    public TranslationResult translateAll(List<String> inputLines) {
        codeMap.clear();

        for (int i = 0; i < inputLines.size(); i++) {
            String currentLine = inputLines.get(i);
            codeMap.put(i, null);

            String text = uncomment(currentLine);
            if (text.isEmpty()) continue;

            TranslationResult transResult = translate(text, i, false, currentLine, inputLines);
            if (!transResult.success()) return transResult;
        }

        if (codeMap.values().stream().allMatch(Objects::isNull))
            return new TranslationResult(false, "Code field is empty");
        return new TranslationResult(true, "Successfully running");
    }

    public TranslationResult translate(String text, int lineNum, boolean isConditionCommand, String codeLine, List<String> inputLines) {
        // Instruction existing check:
        String[] words = text.split("\\s+");
        String instructionName = words[0].toLowerCase();
        Instruction instruction;
        String[] args;

        if (allowedInstructionNames.contains(instructionName)) {
            instruction = Instruction.from(instructionName);
            args = Arrays.copyOfRange(words, 1, words.length);
        }
        else {
            String message = String.format("Line %d: instruction %s is not allowed", lineNum + 1, words[0]);
            return new TranslationResult(false, message);
        }

        // Instruction format check:
        if (instruction.equals(Instruction.IF) && isConditionCommand) {
            String message = String.format("Line %d: instruction format is not valid", lineNum + 1);
            return new TranslationResult(false, message);
        }
        if ((instruction.argsNum != args.length)
            && !instruction.equals(Instruction.IF)) {
            String message = String.format("Line %d: instruction format is not valid", lineNum + 1);
            return new TranslationResult(false, message);
        }

        // Instruction arguments check:
        if (instruction.argsType.equals("word") &&
            !instruction.allowedArgs.contains(args[0].toLowerCase())) {
            String message = String.format("Line %d: argument %s is not allowed", lineNum + 1, args[0]);
            return new TranslationResult(false, message);
        }

        if (instruction.argsType.equals("num")) {
            for (int i = 0; i < instruction.argsNum; i++)
            {
                try {
                    int num = Integer.parseInt(args[i]);
                    if ((num < 1) || (num > inputLines.size())) {
                        String message = String.format("Line %d: argument %d is out of range", lineNum + 1, num);
                        return new TranslationResult(false, message);
                    }
                }
                catch (NumberFormatException e) {
                    String message = String.format("Line %d: argument %s is not allowed", lineNum + 1, args[i]);
                    return new TranslationResult(false, message);
                }
            }
        }

        if (instruction.argsType.equals("cond")) {
            String conditionName = args[0].toLowerCase();
            String conditionArg = args[1].toLowerCase();
            if (!instruction.allowedArgs.contains(conditionName)) {
                String message = String.format("Line %d: condition %s is not allowed", lineNum + 1, args[0]);
                return new TranslationResult(false, message);
            }
            ConditionPattern cond = ConditionPattern.from(conditionName);
            String commandText = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
            TranslationResult result = translate(commandText, lineNum, true, codeLine, inputLines);
            if (!result.success()) return result;
        }

        Command command = Command.from(instructionName, args, executor);
        codeMap.put(lineNum, command);

        return new TranslationResult(true, "Successfully translated");
    }

    private String uncomment(String text) {
        if (text.isBlank() || text.startsWith(";")) return "";
        if (!text.contains(";")) return text;
        String[] commentarySplited = text.split(";");
        if (commentarySplited.length > 1) {
            return commentarySplited[0];
        }
        else return "";
    }

    public Map<Integer, Command> getCodeMap() { return codeMap; }
}
