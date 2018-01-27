package Engine;

import Engine.graphics.Shader;
import Engine.graphics.Sprite;
import Engine.graphics.Texture;
import Engine.graphics.VertexArray;
import Engine.maths.Matrix4f;
import Engine.maths.Vector3f;

public class Bird extends Sprite {

  private Vector3f position = new Vector3f();
  private float speed;
  private float dir = 0;

  public Bird(Shader shader, VertexArray vertexArray, Texture texture) {
    super(shader, vertexArray, texture);
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


  public void setSpeed(float speed) {
    this.speed = speed;
  }

  @Override
  public void draw() {
    tex.bind();
    shader.enable();
    shader.setUniformMat4f("ml_matrix", Matrix4f.translate(new Vector3f())
        .multiply(Matrix4f.rotate(dir - 90)));
    vertexArray.render();
    shader.disable();
    tex.unbind();
  }

  public void rotate(float degrees) {
    dir += degrees;
  }

  public void move() {
    position.x = position.x + (float) Math.cos(Math.toRadians(dir)) * speed / 2000;
    position.y = position.y + (float) Math.sin(Math.toRadians(dir)) * speed / 2000;
  }
}
