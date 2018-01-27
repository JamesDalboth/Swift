package Engine;

import Engine.graphics.Shader;
import Engine.graphics.Sprite;
import Engine.graphics.Texture;
import Engine.graphics.VertexArray;
import Engine.maths.Matrix4f;
import Engine.maths.Vector3f;

public class LightBug extends Sprite {


    private static Bird bird;
    private Vector3f position = new Vector3f();
    private float speed;
    private float dir = 0;

    public LightBug(Shader shader, VertexArray vertexArray, Texture texture, Bird bird,int seed) {
        super(shader, vertexArray, texture);
        position.x = 0;
        position.y = 0;
        this.bird = bird;
    }

    @Override
    public void draw() {
        tex.bind();
        shader.enable();
        shader.setUniformMat4f("ml_matrix", Matrix4f.translate(position).translate(bird.getPos().negate())
                .multiply(Matrix4f.rotate(dir)));
        vertexArray.render();
        shader.disable();
        tex.unbind();
    }

}
