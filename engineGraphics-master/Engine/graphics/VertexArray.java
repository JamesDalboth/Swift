package Engine.graphics;

import Engine.maths.Vector3f;
import Engine.utils.BufferUtils;

import static Engine.graphics.Shader.TCOORD_ATTRIB;
import static Engine.graphics.Shader.VERTEX_ATTRIB;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class VertexArray {

    private int vao, vbo, ibo, tbo;
    private int count;


    public VertexArray(int count) {
        this.count = count;
        vao = glGenVertexArrays();
    }

    public VertexArray(float[] vertices, byte[] indices, float[] textureCoordinates) {
        count = indices.length;

        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(vertices), GL_STATIC_DRAW);
        glVertexAttribPointer(VERTEX_ATTRIB, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(VERTEX_ATTRIB);

        tbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, tbo);
        glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(textureCoordinates), GL_STATIC_DRAW);
        glVertexAttribPointer(TCOORD_ATTRIB, 2, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(TCOORD_ATTRIB);

        ibo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, BufferUtils.createByteBuffer(indices), GL_STATIC_DRAW);


        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindVertexArray(0);

    }

    public void bind() {
        glBindVertexArray(vao);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
    }

    public void unbind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    public void draw() {
        glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_BYTE, 0);
    }

    public void render() {
        bind();
        draw();
    }

}
