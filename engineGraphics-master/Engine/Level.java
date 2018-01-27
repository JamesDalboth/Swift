package Engine;

import Engine.graphics.Sprite;

public class Level {
    private Sprite background;

    public Level(Sprite background) {
        this.background = background;
    }

    public void draw() {
        background.draw();
    }

    public void update() {

    }
}
