package com.eklepser.thelevel.logic.decoder;

import com.eklepser.thelevel.graphics.ui.code_editor.CodeLine;

public class Command {
    private final String cmd;
    private final CodeLine codeLine;

    public Command(String cmd, CodeLine codeLine) {
        this.cmd = cmd;
        this.codeLine = codeLine;
    }

    public String getCmd() {
        return cmd;
    }

    public CodeLine getCodeLine() {
        return codeLine;
    }
}
