package Engine;

import Engine.graphics.Shader;
import Engine.graphics.Sprite;
import Engine.graphics.Texture;
import Engine.graphics.VertexArray;

public class Game {
    private Sprite background;
    private Level level;

    public Game() {

        initBackground();
        level = new Level(background);
    }

    public void initBackground() {
        Shader shader = new Shader("engineGraphics-master/Engine/shaders/bg.vert","engineGraphics-master/Engine/shaders/bg.frag");

        float[] vertices = new float[] {
                -11.0f, -11.0f * 9.0f / 16.0f, 0.0f,
                -11.0f,  11.0f * 9.0f / 16.0f, 0.0f,
                11.0f,  11.0f * 9.0f / 16.0f, 0.0f,
                11.0f, -11.0f * 9.0f / 16.0f, 0.0f
        };

        byte[] indices = new byte[] {
                0, 1, 2,
                2, 3, 0
        };

        float[] tcs = new float[] {
                0, 1,
                0, 0,
                1, 0,
                1, 1
        };

        VertexArray va = new VertexArray(vertices,indices,tcs);

        Texture tex = new Texture("engineGraphics-master/Engine/res/background.png");

        background = new Sprite(shader,va,tex);

    }

    public void render() {
        level.draw();
    }

    public void update() {
        level.update();
    }

    public void close() {
        level.close();
    }
}
