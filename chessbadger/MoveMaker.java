package chessbadger;

import javafx.concurrent.Worker;
import chessbadger.position.Position;

interface MoveMaker {
    public abstract void start(Position position);
    public void reset();   // set state to READY

    public Worker.State getState();
    public short getMove();
}
