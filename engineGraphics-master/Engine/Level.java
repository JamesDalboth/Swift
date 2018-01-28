package Engine;

import Engine.graphics.Shader;
import Engine.graphics.Sprite;
import Engine.graphics.Texture;
import Engine.graphics.VertexArray;
import Engine.maths.Vector3f;

public class Level {
    private int mapsize = 100;
    private Sprite background;
    private MidiHandler mh;
    private Bird bird;
    private LightBug[] bugs = new LightBug[400];
    private Sun sun;
    private float birdSize = 0.1f;
    private float bugSize = 0.02f;
    private float sunSize = 0.3f;

    public Level(Sprite background) {
        this.background = background;
        mh = new MidiHandler();
        createBird();
        createBug();
        createSun();
    }

    public void draw() {
        background.draw(bird.getPos());
        sun.draw();
        bird.draw();
        LightBug.bind();
        for (LightBug bug : bugs) {
            if (bug != null) {
                bug.render();
            }

        }
        LightBug.unbind();

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

        for (int i = 0; i < bugs.length; i++) {
            Vector3f disp = new Vector3f();

            if (bugs[i] != null) {
                disp = bugs[i].position.add(bird.getPos().negate());
            }

            if (disp.mag() < 0.1) {
                bugs[i] = null;
            }
        }
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
                -bugSize * 9.0f / 16.0f, -bugSize, -0.2f,
                -bugSize * 9.0f / 16.0f, bugSize, -0.2f,
                bugSize * 9.0f / 16.0f,  bugSize, -0.2f,
                bugSize * 9.0f / 16.0f, -bugSize, -0.2f
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

    public void createSun() {
        float[] vertices = new float[] {
            -sunSize * 9.0f / 16.0f, -sunSize, -0.05f,
            -sunSize* 9.0f / 16.0f, sunSize, -0.05f,
            sunSize* 9.0f / 16.0f,  sunSize, -0.05f,
            sunSize* 9.0f / 16.0f, -sunSize, -0.05f
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

        Shader shader = new Shader("engineGraphics-master/Engine/shaders/sun.vert","engineGraphics-master/Engine/shaders/sun.frag");

        Texture tex = new Texture("engineGraphics-master/Engine/res/one_ball.png");

        sun = new Sun(shader,va,tex, bird);
    }
}