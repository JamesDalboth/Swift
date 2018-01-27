package Engine;

import Engine.graphics.Shader;
import Engine.graphics.Sprite;
import Engine.graphics.Texture;
import Engine.graphics.VertexArray;
import Engine.maths.Matrix4f;
import Engine.maths.Vector3f;

import java.util.Random;

public class LightBug{


    protected Shader shader;
    protected VertexArray vertexArray;
    protected Texture tex;

    private static Bird bird;
    private Vector3f position = new Vector3f();
    private float speed;
    private float dir = 0;

    public LightBug(Shader shader, VertexArray vertexArray, Texture texture, Bird bird,int seed) {
        this.shader = shader;
        this.vertexArray = vertexArray;
        this.tex = texture;

        this.shader.setUniform1i("tex", 1);

        Random randnum;
        randnum = new Random();
        randnum.setSeed((long) (seed * 170.022));
        position.x = (randnum.nextInt(50) - 25);
        System.out.println(position.x);
        position.y = (randnum.nextInt(50) - 25);
        System.out.println(position.y);
        this.bird = bird;
    }

    public void draw() {
        tex.bind();
        shader.enable();
        shader.setUniformMat4f("ml_matrix", Matrix4f.translate(bird.getPos().negate().add(position))
                .multiply(Matrix4f.rotate(dir)));
        vertexArray.render();
        shader.disable();
        tex.unbind();
    }

}
