package chessbadger.position;


public interface PositionListener
{
    public void squareChanged(int sqi, int stone);
    public void toPlayChanged(int toPlay);
    public void castlesChanged(int castles);
    public void sqiEPChanged(int sqiEP);
    public void plyNumberChanged(int plyNumber);
    public void halfMoveClockChanged(int halfMoveClock);
}