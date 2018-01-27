package Engine.graphics;

import Engine.maths.Matrix4f;

public class Sprite {

    protected Shader shader;
    protected VertexArray vertexArray;
    protected Texture tex;

    public Sprite(Shader shader, VertexArray vertexArray, Texture tex) {
        this.shader = shader;
        this.vertexArray = vertexArray;
        this.tex = tex;
    }
     public void draw() {
        tex.bind();
        shader.enable();
        vertexArray.render();
        shader.disable();
        tex.unbind();
     }
}
