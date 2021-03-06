package Engine.maths;

public class Vector3f {

    public float x, y, z;

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3f() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }


    public Vector3f negate() {
        return new Vector3f(-x * 9/16,-y,-z);
    }

    public Vector3f add(Vector3f other) {
        return new Vector3f(other.x + x, other.y + y, other.z + z);
    }

    public Vector3f multiply(float factor) {
        return new Vector3f(x * factor, y * factor, z * factor);
    }

    public float mag() {
        return (float) Math.sqrt(Math.pow(x,2) + Math.pow(y, 2));
    }
}
