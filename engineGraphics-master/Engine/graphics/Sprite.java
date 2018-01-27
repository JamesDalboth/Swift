package Engine.graphics;

import Engine.maths.Matrix4f;

import static org.lwjgl.opengl.GL13.GL_TEXTURE1;
import static org.lwjgl.opengl.GL13.glActiveTexture;

public class Sprite {

    protected Shader shader;
    protected VertexArray vertexArray;
    protected Texture tex;

    public Sprite(Shader shader, VertexArray vertexArray, Texture tex) {
        this.shader = shader;
        this.vertexArray = vertexArray;
        this.tex = tex;

        this.shader.setUniform1i("tex", 1);
    }
     public void draw() {
        tex.bind();
        shader.enable();
        vertexArray.render();
        shader.disable();
        tex.unbind();
     }
}
