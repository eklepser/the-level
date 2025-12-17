package game.scene.level.rendering;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.Matrix4;

public class BackgroundEffect {
    public enum Shape {
        CIRCLE,
        SQUARE
    }

    private static class Particle {
        float x, y;
        float size;
        float targetSize;
        float rotation;
        float rotSpeed;
        float speedX, speedY;
        float driftSpeedX, driftSpeedY;
        Color color;
        float alpha;
        float age = 0f;
        float lifetime = 0f;
        float appearTime;
    }

    private final Shape shape;
    private final Array<Particle> particles = new Array<>();
    private final ShapeRenderer shapeRenderer;
    private float spawnTimer = 0f;
    private static final int MIN_COUNT = 20;
    private static final int MAX_COUNT = 30;

    public BackgroundEffect(Shape shape) {
        this.shape = shape;
        this.shapeRenderer = new ShapeRenderer();
    }

    public void update(float delta, float worldWidth, float worldHeight) {
        for (int i = particles.size - 1; i >= 0; i--) {
            Particle p = particles.get(i);

            p.x += p.speedX * delta;
            p.y += p.speedY * delta;
            p.x += p.driftSpeedX * delta;
            p.y += p.driftSpeedY * delta;
            p.age += delta;

            if (shape == Shape.SQUARE) {
                p.rotation += p.rotSpeed * delta;
            }

            if (p.age <= p.appearTime) {
                float t = p.age / p.appearTime;
                p.size = p.targetSize * t;
                p.alpha = t;
            } else if (p.age <= p.lifetime) {
                float t = (p.age - p.appearTime) / (p.lifetime - p.appearTime);
                p.size = p.targetSize * (1.0f - t);
                p.alpha = 1.0f - t;
            } else {
                particles.removeIndex(i);
                continue;
            }

            if (p.x < 20) {
                p.x = 20;
                p.speedX = Math.abs(p.speedX) * 0.8f;
            } else if (p.x > worldWidth - 20) {
                p.x = worldWidth - 20;
                p.speedX = -Math.abs(p.speedX) * 0.8f;
            }

            if (p.y < 20) {
                p.y = 20;
                p.speedY = Math.abs(p.speedY) * 0.8f;
            } else if (p.y > worldHeight - 20) {
                p.y = worldHeight - 20;
                p.speedY = -Math.abs(p.speedY) * 0.8f;
            }
        }

        float currentCount = particles.size;
        float spawnRate;

        if (currentCount < MIN_COUNT) {
            spawnRate = 5.0f;
        } else if (currentCount > MAX_COUNT) {
            spawnRate = 0.0f;
        } else {
            float ratio = (currentCount - MIN_COUNT) / (MAX_COUNT - MIN_COUNT);
            spawnRate = 1.0f + (0.2f - 1.0f) * ratio;
        }

        if (spawnRate > 0) {
            spawnTimer += delta;
            float interval = 1.0f / spawnRate;
            if (spawnTimer >= interval) {
                spawnTimer -= interval;
                spawnParticle(worldWidth, worldHeight);
            }
        } else {
            spawnTimer = 0f;
        }
    }

    private void spawnParticle(float worldWidth, float worldHeight) {
        Particle p = new Particle();

        p.x = MathUtils.random(50, worldWidth - 50);
        p.y = MathUtils.random(50, worldHeight - 50);

        p.driftSpeedX = MathUtils.random(-0.02f, 0.02f);
        p.driftSpeedY = MathUtils.random(-0.02f, 0.02f);
        p.speedX = MathUtils.random(-0.4f, 0.4f);
        p.speedY = MathUtils.random(-0.2f, 0.2f);

        if (shape == Shape.SQUARE) {
            p.rotSpeed = MathUtils.random(-20f, 20f);
            p.rotation = MathUtils.random(360f);
        }

        float base = MathUtils.random(0.02f, 0.05f);
        float purpleTint = MathUtils.random(0.03f, 0.07f);
        float blueTint = MathUtils.random(0.01f, 0.03f);
        p.color = new Color(
            base + purpleTint * 0.7f,
            base + purpleTint * 0.3f,
            base + purpleTint + blueTint,
            1.0f
        );

        float baseSize = shape == Shape.CIRCLE
            ? MathUtils.random(60f, 130f)
            : MathUtils.random(40f, 100f);
        float scale = MathUtils.random(0.5f, 2.0f);
        p.targetSize = baseSize * scale;

        p.lifetime = MathUtils.random(80f, 160f);
        p.appearTime = p.lifetime * MathUtils.random(0.4f, 0.5f);

        p.size = 0f;
        p.alpha = 0f;
        p.age = 0f;

        particles.add(p);
    }

    public void draw(Matrix4 screenMatrix) {
        if (particles.isEmpty()) return;

        shapeRenderer.setProjectionMatrix(screenMatrix);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        switch (shape) {
            case CIRCLE:
                drawCircles();
                break;
            case SQUARE:
                drawSquares();
                break;
        }

        shapeRenderer.end();
    }

    private void drawCircles() {
        for (Particle p : particles) {
            if (p.size <= 0.1f || p.alpha <= 0f) continue;

            float r = p.size;
            float a = p.alpha;

            int steps = 8;
            for (int i = 0; i < steps; i++) {
                float t = (float) i / (steps - 1);
                float stepRadius = r * (1.0f - t * t * 0.65f);
                float stepAlpha = a * (1.0f - t * 0.75f);
                float bright = 1.0f + t * 0.35f;
                Color c = new Color(
                    Math.min(1f, p.color.r * bright),
                    Math.min(1f, p.color.g * bright),
                    Math.min(1f, p.color.b * bright),
                    stepAlpha
                );
                shapeRenderer.setColor(c);
                shapeRenderer.circle(p.x, p.y, stepRadius);
            }
        }
    }

    private void drawSquares() {
        for (Particle p : particles) {
            if (p.size <= 0.1f || p.alpha <= 0f) continue;

            float half = p.size * 0.5f;

            shapeRenderer.identity();
            shapeRenderer.translate(p.x, p.y, 0);
            shapeRenderer.rotate(0, 0, 1, p.rotation);
            shapeRenderer.translate(-p.x, -p.y, 0);
            shapeRenderer.setProjectionMatrix(shapeRenderer.getProjectionMatrix());

            shapeRenderer.setColor(p.color.r, p.color.g, p.color.b, p.alpha);
            shapeRenderer.rect(p.x - half, p.y - half, p.size, p.size);

            float innerSize = p.size * 0.6f;
            float innerHalf = innerSize * 0.5f;
            float innerAlpha = p.alpha * 0.6f;
            float bright = 1.3f;
            shapeRenderer.setColor(
                Math.min(1f, p.color.r * bright),
                Math.min(1f, p.color.g * bright),
                Math.min(1f, p.color.b * bright),
                innerAlpha
            );
            shapeRenderer.rect(p.x - innerHalf, p.y - innerHalf, innerSize, innerSize);
        }
    }

    public void dispose() {
        shapeRenderer.dispose();
    }

    public void clear() {
        particles.clear();
    }
}
