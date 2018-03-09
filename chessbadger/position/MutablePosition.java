package chessbadger.position;

public interface MutablePosition extends ImmutablePosition
{
    public void clear();
    public void set(ImmutablePosition position);
    public void setStart();
    
    public void setStone(int sqi, int stone);
    public void setCastles(int castles);
    public void setSqiEP(int sqiEP);
    public void setToPlay(int toPlay);
    public void setPlyNumber(int plyNumber);
    public void setHalfMoveClock(int halfMoveClock);     
}