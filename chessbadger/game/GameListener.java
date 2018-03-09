package chessbadger.game;

import chessbadger.move.Move;

public interface GameListener
{
    public void notifyMove(Move move, short[] nags, String comment, int plyNumber, int level);
    public void notifyLineStart(int level);
    public void notifyLineEnd(int level);
}