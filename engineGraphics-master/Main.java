import Engine.input.Input;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Main implements Runnable {

    private int width = 800;
    private int height = 800;

    private Thread thread;
    private boolean running = false;

    private long window;

    public static void main(String[] args) {
        new Main().start();
    }

    public void start() {
        running = true;
        thread = new Thread(this, "Game");
        thread.start();
    }

    public void run() {
        init();
        while (running) {
            update();
            render();

            if (glfwWindowShouldClose(window)) {
                running = false;
            }
        }
    }

    public void init() {
        if (!glfwInit()) {
            // TODO
        }

        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
        window = glfwCreateWindow(width, height, "Test", NULL, NULL);

        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, (vidmode.width() - width) / 2, (vidmode.height() - height) / 2);

        glfwSetKeyCallback(window, new Input());

        glfwMakeContextCurrent(window);
        glfwShowWindow(window);

        GL.createCapabilities();

        glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        glEnable(GL_DEPTH_TEST);
        System.out.println("OpenGl: " + glGetString(GL_VERSION));
    }

    public void update() {
        glfwPollEvents();
        if (Input.keys[GLFW_KEY_SPACE]) {
            System.out.println("FLAP");
        }
    }

    public void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glfwSwapBuffers(window);
    }

}
