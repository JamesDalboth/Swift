package Engine.graphics;

public class Sprite {

    private Shader shader;
    private VertexArray vertexArray;
    private Texture tex;

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
