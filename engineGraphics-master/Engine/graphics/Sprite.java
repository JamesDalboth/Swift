package Engine.graphics;

import Engine.maths.Matrix4f;
import Engine.maths.Vector3f;

import static org.lwjgl.opengl.GL13.GL_TEXTURE1;
import static org.lwjgl.opengl.GL13.glActiveTexture;

public class Sprite {

    public Shader shader;
    protected VertexArray vertexArray;
    protected Texture tex;

    public Sprite(Shader shader, VertexArray vertexArray, Texture tex) {
        this.shader = shader;
        this.vertexArray = vertexArray;
        this.tex = tex;

        this.shader.setUniform1i("tex", 1);
    }
     public void draw(Vector3f pos, int time) {
        tex.bind();
        shader.enable();
        vertexArray.bind();
        shader.setUniform1f("iTime", time);
        shader.setUniformMat4f("ml_matrix", Matrix4f.translate(pos));
        vertexArray.render();
        vertexArray.unbind();
        shader.disable();
        tex.unbind();
     }
}
