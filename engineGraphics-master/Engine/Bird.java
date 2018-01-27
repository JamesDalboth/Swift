package Engine;

import Engine.graphics.Shader;
import Engine.graphics.Sprite;
import Engine.graphics.Texture;
import Engine.graphics.VertexArray;
import Engine.maths.Vector3f;

public class Bird extends Sprite {

  private Vector3f position = new Vector3f();
  private float speed;
  private Vector3f dir = new Vector3f(1, 0, 0);

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

  public void setPosition(float newX, float newY) {
    position = new Vector3f(newX, newY, 0);
  }

  public void setSpeed(float speed) {
    this.speed = speed;
  }

  public void render() {

  }

  public void rotate(float degrees) {
    float radians = (float) Math.toRadians(degrees);
    float cos = (float) Math.cos(radians);
    float sin = (float) Math.sin(radians);

    float oldX = dir.x;
    float oldY = dir.y;

    dir.x = oldX * cos - oldY * sin;
    dir.y = oldX * sin + oldY * cos;
  }

  public void move() {
    position.x = position.x + dir.x * speed;
    position.y = position.y + dir.y * speed;
  }
}
