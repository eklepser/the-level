package game.common.logic.collision.zone;

import game.common.logic.entity.Entity;

public class ColoredZone extends Zone {
    private final String colorName;

    public ColoredZone(int x, int y, String colorName) {
        super(x, y);
        this.colorName = colorName;
    }

    @Override
    public void onCollision(Entity entity) {
        System.out.println("Colored zone collision!");
    }

    public String getColorName() {
        return colorName;
    }
}
