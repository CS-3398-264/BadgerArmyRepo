package chessbadger.position;

import chessbadger.*;
import chessbadger.move.*;

public class LightWeightPosition extends AbstractMutablePosition
{
    private int[] m_stone;
    private int m_sqiEP;
    private int m_castles;
    private int m_toPlay;
    private int m_plyNumber;
    private int m_halfMoveClock;
    
    /*================================================================================*/
    
    public LightWeightPosition()
    {
        m_stone = new int[Chess.NUM_OF_SQUARES];
        clear();
    }

    public LightWeightPosition(ImmutablePosition position)
    {
        this();
        set(position);
    }
    
    /*================================================================================*/
    
    public int getStone(int sqi)  {return m_stone[sqi];}    
    public int getToPlay()        {return m_toPlay;}
    public int getSqiEP()         {return m_sqiEP;}
    public int getCastles()       {return m_castles;}    
    public int getPlyNumber()     {return m_plyNumber;}    
    public int getHalfMoveClock() {return m_halfMoveClock;}

    /*================================================================================*/
    
    public void setStone(int sqi, int stone)
    {
        if (m_stone[sqi] != stone) {
            m_stone[sqi] = stone;
            fireSquareChanged(sqi);
        }
    }

    public void setCastles(int castles)
    {
        if (m_castles != castles) {
            m_castles = castles;
            fireCastlesChanged();
        }
    }
    
    public void setSqiEP(int sqiEP)
    {
        if (m_sqiEP != sqiEP) {
            m_sqiEP = sqiEP;
            fireSqiEPChanged();
        }
    }
    
    public void setToPlay(int toPlay)
    {
        if (m_toPlay != toPlay) {
            m_toPlay = toPlay;
            fireToPlayChanged();
        }
    }
    
    public void setPlyNumber(int plyNumber)
    {
        if (m_plyNumber != plyNumber) {
            m_plyNumber = plyNumber;
            firePlyNumberChanged();
        }
    }
    
    public void setHalfMoveClock(int halfMoveClock)
    {
        if (m_halfMoveClock != halfMoveClock) {
            m_halfMoveClock = halfMoveClock;
            fireHalfMoveClockChanged();
        }
    }
    
    /*================================================================================*/
    
    public void doMove(short move) throws IllegalMoveException
    {
        throw new IllegalMoveException ("Moves not supported");
    }
    
    public boolean canUndoMove() {return false;}
    
    public boolean undoMove() {return false;}
    
    public short getLastShortMove() throws IllegalMoveException
    {
        throw new IllegalMoveException ("Moves not supported");
    }
    
    public Move getLastMove() throws IllegalMoveException
    {
        throw new IllegalMoveException ("Moves not supported");
    }
    
    public boolean canRedoMove() {return false;}
    public boolean redoMove() {return false;}
    
}