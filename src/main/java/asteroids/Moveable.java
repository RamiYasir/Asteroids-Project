package asteroids;

import javafx.event.Event;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public interface Moveable {
    public void move(KeyCode keyPressed, long timeDifference);
    public void slow(long timeDifference);
}
