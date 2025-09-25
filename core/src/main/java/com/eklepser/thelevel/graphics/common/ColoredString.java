package com.eklepser.thelevel.graphics.common;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.eklepser.thelevel.util.Resources;

import java.util.ArrayList;
import java.util.List;

public class ColoredString extends HorizontalGroup {
    private final String text;
    private final BitmapFont font;
    private final List<TextLabel> labels;

    public ColoredString(String text) {
        this.text = text;
        this.font = Resources.getSkin().getFont("default");
        labels = parseText(text);
        update();
    }

    public void update() {
        for (Label label : labels) {
            addActor(label);
        }
    }

    private List<TextLabel> parseText(String text) {
        String[] parts = text.split("/");
        List<TextLabel> labels = new ArrayList<>();
        for (String part : parts) {
            String cmd;
            String[] words = part.split("\\s+");
            if (words.length > 0) cmd = words[0];
            else continue;

            String colorName = "white";
            float scale = 1.0f;
            String[] args = cmd.split("_");
            if (args.length > 0) colorName = args[0];
            if (args.length > 1) scale = Float.parseFloat(args[1]);

            Color color = parseColor(colorName);
            TextLabel textLabel = new TextLabel(part.replaceFirst(cmd + " ", ""));
            textLabel.setColor(color);
            textLabel.setFontScale(scale);
            labels.add(textLabel);
        }
        return labels;
    }

    private Color parseColor(String colorName) {
        switch (colorName) {
            case "red": return Color.RED;
            case "green": return Color.GREEN;
            case "blue": return Color.BLUE;
            case "black": return Color.BLACK;
            case "yellow": return Color.YELLOW;
            case "cyan": return Color.CYAN;
            case "magenta": return Color.MAGENTA;
            case "gray": return Color.GRAY;
            case "orange": return Color.ORANGE;
            case "pink": return Color.PINK;
            case "brown": return Color.BROWN;
            default: return Color.WHITE;
        }
    }

    public String getText() { return text; }
}
