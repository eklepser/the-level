package game.config;

public final class Paths {
    private Paths() {
        throw new UnsupportedOperationException();
    }

    // Internal paths:
    public static final String ATLAS = "ui/skin/ui.atlas";
    public static final String SKIN = "ui/skin/skin.json";

    public static final String TILESET = "tileset/tileset";
    public static final String COMMANDS_INFO = "text/commands-info.json";
    public static final String LEVEL_CONFIG = "world/level.json";

    // Local paths:
    public static final String USER_DATA = "data/user/userdata.json";
    public static final String BUILDER_DATA = "data/builder/";
}
