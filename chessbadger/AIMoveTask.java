package chessbadger;

import javafx.concurrent.Task;
import chessbadger.position.Position;

public class AIMoveTask extends Task<Short> {

    private Position position = null;
    private AI ai;

    public AIMoveTask(AI ai, Position p) {
        super();
        position = p;
        this.ai = ai;
    }

    @Override
    protected Short call() throws Exception {
        return ai.getMove(position);
    }

}