package Engine;

import Engine.graphics.Shader;
import Engine.graphics.Sprite;
import Engine.graphics.Texture;
import Engine.graphics.VertexArray;

public class Level {
    private int mapsize = 50;
    private Sprite background;
    private MidiHandler mh;
    private Bird bird;
    private LightBug[] bugs = new LightBug[200];
    private float birdSize = 0.1f;
    private float bugSize = 0.0125f;

    public Level(Sprite background) {
        this.background = background;
        mh = new MidiHandler();
        createBird();
        createBug();
    }

    public void draw() {
        background.draw();
        bird.draw();
        for (LightBug bug : bugs) {
            bug.draw();
        }
}

    public void update() {
        float degree = 0;
        boolean moved = false;
        for (int i = 0; i < mh.keys.length; i++) {
            boolean key = mh.keys[i];
            if (key) {
                moved = true;
                if (i < mh.keys.length/2) {
                    degree += 1;
                } else {
                    degree -= 1;
                }
            }
        }

        bird.rotate(degree);
        if (moved) {
            bird.setSpeed(10);
        }  else {
            bird.setSpeed(1);
        }
        bird.move();
    }

    public void close() {
        mh.close();
    }

    public void createBird() {
        float[] vertices = new float[] {
                -birdSize, -birdSize, -0.1f,
                -birdSize, birdSize, -0.1f,
                birdSize,  birdSize, -0.1f,
                birdSize, -birdSize, -0.1f
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

        Shader shader = new Shader("engineGraphics-master/Engine/shaders/bird.vert","engineGraphics-master/Engine/shaders/bird.frag");

        Texture tex = new Texture("engineGraphics-master/Engine/res/swift.png");

        bird = new Bird(shader,va,tex);
    }

    public void createBug() {
        float[] vertices = new float[] {
                -bugSize * 9.0f / 16.0f, -bugSize, -0.1f,
                -bugSize * 9.0f / 16.0f, bugSize, -0.1f,
                bugSize * 9.0f / 16.0f,  bugSize, -0.1f,
                bugSize * 9.0f / 16.0f, -bugSize, -0.1f
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

        Shader shader = new Shader("engineGraphics-master/Engine/shaders/bug.vert","engineGraphics-master/Engine/shaders/bug.frag");

        Texture tex = new Texture("engineGraphics-master/Engine/res/one_ball.png");

        for (int i = 0; i < bugs.length; i++) {
            LightBug bug;
            bug = new LightBug(shader,va,tex,bird,i);
            bugs[i] = bug;
        }
    }
}