import Engine.Game;
import Engine.input.Input;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE1;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Main implements Runnable {

    private int width;
    private int height;

    private Thread thread;
    private boolean running = false;

    private long window;

    private Game game;
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

        glfwDestroyWindow(window);
        glfwTerminate();

        game.close();

        System.exit(0);
    }

    public void init() {
        if (!glfwInit()) {
            // TODO
        }

        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        width = vidmode.width();
        height = vidmode.height();

        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
        window = glfwCreateWindow(width, height, "Test", glfwGetPrimaryMonitor(), NULL);

        glfwSetWindowPos(window, 0, 0);

        glfwSetKeyCallback(window, new Input());

        glfwMakeContextCurrent(window);
        glfwShowWindow(window);

        GL.createCapabilities();

        glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        glEnable(GL_DEPTH_TEST);
        System.out.println("OpenGl: " + glGetString(GL_VERSION));

        glEnable(GL_DEPTH_TEST);
        glActiveTexture(GL_TEXTURE1);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        game = new Game();

    }

    public void update() {
        glfwPollEvents();
        if (Input.keys[GLFW_KEY_ESCAPE]) {
            running = false;
        }

        game.update();
    }

    public void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        game.render();

        glfwSwapBuffers(window);
    }

}
