package chessbadger.position;

import chessbadger.*;
//import java.util.*;
import chessbadger.move.Move;
import chessbadger.move.IllegalMoveException;

public abstract class AbstractMovablePosition extends AbstractMutablePosition
    implements MoveablePosition
{
    
    public void doMove(Move move) throws IllegalMoveException
    {
        doMove(move.getShortMoveDesc());
    }

    public short getMove(int from, int to, int promoPiece)
    {
        if (getColor(from) != getToPlay()) return Move.ILLEGAL_MOVE;  // =====>
        int piece = getPiece(from);
        if (piece == Chess.PAWN) {
            return Move.getPawnMove(from, to, Chess.sqiToCol(from) != Chess.sqiToCol(to), promoPiece);
        } else if (piece == Chess.KING && (to - from) ==  2) {
            return Move.getShortCastle(getToPlay());
        } else if (piece == Chess.KING && (to - from) == -2) {
            return Move.getLongCastle(getToPlay());
        } else {
            return Move.getRegularMove(from, to, !isSquareEmpty(to));
        }
    }

}