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

    // Local paths:
    public static final String USER_DATA = "data/user/userdata.json";
    public static final String WORLD_DATA = "data/world/";
    public static final String WORLD_ONE = "data/world/world_one.json";
    public static final String BUILDER_DATA = "data/builder/";
}
