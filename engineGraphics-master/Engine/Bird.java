package Engine;

import Engine.graphics.Texture;
import Engine.graphics.VertexArray;
import Engine.maths.Vector3f;

public class Bird {

  private Vector3f position = new Vector3f();
  private VertexArray mesh;
  private Texture texture;
  private float speed;
  private Vector3f dir = new Vector3f(1, 0, 0);

  public Bird(float x, float y) {
    position.x = x;
    position.y = y;
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

  }

  public void move() {
    position.x = position.x + dir.x * speed;
    position.y = position.y + dir.y * speed;
  }
}
