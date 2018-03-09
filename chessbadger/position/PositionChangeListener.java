package chessbadger.position;


public interface PositionChangeListener
{
    // always called when position changes
    public void notifyPositionChanged(ImmutablePosition position);
    
    public void notifyMoveDone(ImmutablePosition position, short move);
    public void notifyMoveUndone(ImmutablePosition position);
//    // called if position changes not caused by a move
//    public void notifyOtherChange(IChPosition position);
}