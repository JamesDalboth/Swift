package Engine.graphics;

import Engine.maths.Matrix4f;
import Engine.maths.Vector3f;
import Engine.utils.ShaderUtils;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;

public class Shader {

    public static final int VERTEX_ATTRIB = 0;
    public static final int TCOORD_ATTRIB = 1;

    private int ID;

    private Map<String, Integer> locationCache = new HashMap<String, Integer>();

    public Shader(String vertex, String fragment) {
        ID = ShaderUtils.load(vertex, fragment);
    }

    public void enable() {
        glUseProgram(ID);
    }

    public void disable() {
        glUseProgram(0);
    }

    public int getUniform(String name) {
        if (locationCache.containsKey(name)) {
            return locationCache.get(name);
        }
        int result = glGetUniformLocation(ID, name);
        if (result == -1) {
            System.err.println("Could not find uniform variable " + name);
        } else {
            locationCache.put(name, result);
        }
        return result;
    }

    public void setUniform1i(String name, int value) {
        glUniform1i(getUniform(name), value);
    }

    public void setUniform1f(String name, int value) {
        glUniform1f(getUniform(name), value);
    }

    public void setUniform2f(String name, int x, int y) {
        glUniform2f(getUniform(name), x, y);
    }

    public void setUniform3f(String name, Vector3f value) {
        glUniform3f(getUniform(name), value.x, value.y, value.z);
    }

    public void setUniformMat4f(String name, Matrix4f matrix) {
        glUniformMatrix4fv(getUniform(name), false, matrix.toFloatBuffer());
    }
}
