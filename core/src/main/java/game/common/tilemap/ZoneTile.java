package game.common.tilemap;

public class ZoneTile {
    public int id;
    public int x;
    public int y;
    public String type;
    public String[] properties;

    public ZoneTile() { }

    public ZoneTile(int id, int x, int y, String type, String[] properties) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.type = type;
        this.properties = properties;
    }
}
