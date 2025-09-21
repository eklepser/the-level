package com.eklepser.thelevel.graphics.game.root;

public final class CommandInfo {
    private String titleText;
    private String iconPath;
    private String argsText;
    private String descriptionText;

    public CommandInfo() { }

    public String getTitleText() { return titleText; }

    public String getIconPath() { return iconPath; }

    public String getArgsText() { return argsText; }

    public String getDescriptionText() { return descriptionText; }

    public void setTitleText(String titleText) { this.titleText = titleText; }

    public void setIconPath(String iconPath) { this.iconPath = iconPath; }

    public void setArgsText(String argsText) { this.argsText = argsText; }

    public void setDescriptionText(String descriptionText) { this.descriptionText = descriptionText; }
}
