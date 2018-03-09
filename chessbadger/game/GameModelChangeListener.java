package chessbadger.game;

public interface GameModelChangeListener
{
    public void headerModelChanged(Game game);
    public void moveModelChanged(Game game);
}