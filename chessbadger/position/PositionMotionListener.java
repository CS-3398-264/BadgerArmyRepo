package chessbadger.position;


import java.awt.event.MouseEvent;


public interface PositionMotionListener
{
    public boolean allowDrag(ImmutablePosition position, int from);
    public int getPartnerSqi(ImmutablePosition position, int from);
    
    public void dragged(ImmutablePosition position, int from, int to, MouseEvent e);
    public void squareClicked(ImmutablePosition position, int sqi, MouseEvent e);
}