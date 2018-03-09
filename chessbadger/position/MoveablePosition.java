package chessbadger.position;

import chessbadger.move.*;

public interface MoveablePosition extends MutablePosition
{
    public void doMove(short move) throws IllegalMoveException;
    public void doMove(Move move) throws IllegalMoveException;
    
    public short getLastShortMove() throws IllegalMoveException;
    public Move getLastMove() throws IllegalMoveException;
    
    public boolean canUndoMove();
    public boolean undoMove();
    
    public boolean canRedoMove();
    public boolean redoMove();
    
    public short getMove(int from, int to, int promoPiece);
    
    public short[] getAllMoves();    
    public String getMovesAsString(short[] moves, boolean validateEachMove);
     
}