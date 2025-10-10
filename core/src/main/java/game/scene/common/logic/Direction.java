package game.scene.common.logic;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public enum Direction {
    UP(0, 1, "up"),
    DOWN(0, -1, "down"),
    LEFT(-1, 0, "left"),
    RIGHT(1, 0, "right"),
    FORWARD(0, 0, "forward");

    public static final List<Direction> orderedDirections = new ArrayList<>(List.of(
        UP, RIGHT, DOWN, LEFT));

    public final Vector2 vector;
    public final String name;


    Direction(float x, float y, String name) {
        this.vector = new Vector2(x, y);
        this.name = name;
    }

    public static Direction getByName(String name) {
        return switch (name) {
            case "u", "up" -> UP;
            case "d", "down" -> DOWN;
            case "l", "left" -> LEFT;
            case "r", "right" -> RIGHT;
            default -> FORWARD;
        };
    }

    public static float getDegrees(Direction direction) {
        return switch (direction) {
            case DOWN -> 180;
            case LEFT -> 90;
            case RIGHT -> 270;
            default -> 0;
        };
    }

    public static Direction getRotatedDirection(Direction facingDirection,Direction rotateDirection) {
        int i = orderedDirections.indexOf(facingDirection);
        if (rotateDirection.equals(RIGHT)) {
            return orderedDirections.get((i + 1) % 4);
        }
        else if (rotateDirection.equals(LEFT)) {
            return orderedDirections.get((i + 3) % 4);
        }
        else return facingDirection;
    }
}
