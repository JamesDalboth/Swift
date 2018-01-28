package Engine;

import Engine.graphics.Shader;
import Engine.graphics.Texture;
import Engine.graphics.VertexArray;
import Engine.maths.Matrix4f;
import Engine.maths.Vector3f;

public class Sun {

  private static Shader shader;
  private static VertexArray vertexArray;
  private static Texture tex;

  private Vector3f position = new Vector3f();
  private Bird bird;

  public Sun(Shader shader, VertexArray vertexArray, Texture texture, Bird bird) {
    this.shader = shader;
    this.vertexArray = vertexArray;
    this.tex = texture;
    this.bird = bird;

    position.x = 0;
    position.y = 0;
  }

  public float getX() {
    return position.x;
  }

  public float getY() {
    return position.y;
  }

  public Vector3f getPos() {
    return position;
  }

  public void draw() {
    bind();
    render();
    unbind();
  }

  public static void bind() {
    tex.bind();
    shader.enable();
  }

  public static void unbind() {
    shader.disable();
    tex.unbind();
  }

  public void render () {
    shader.setUniformMat4f("ml_matrix", Matrix4f.translate(bird.getPos()
        .negate().multiply(0.1f)));
    vertexArray.render();
  }

}
