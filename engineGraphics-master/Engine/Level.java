package Engine;

import Engine.graphics.Sprite;

public class Level {
    private Sprite background;
    private MidiHandler mh;

    public Level(Sprite background) {
        this.background = background;
        mh = new MidiHandler();
    }

    public void draw() {
        background.draw();
}

    public void update() {

    }

    public void close() {
        mh.close();
    }
}