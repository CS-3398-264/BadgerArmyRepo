package chessbadger.move;

@SuppressWarnings("serial")
public class IllegalMoveException extends Exception
{
    public IllegalMoveException(short move)
    {
        super ("Illegal move: " + Move.getString(move));
    }    

    public IllegalMoveException(short move, String msg)
    {
        super ("Illegal move: " + Move.getString(move) + ": " + msg);
    }    

    public IllegalMoveException(Move move)
    {
        super ("Illegal move: " + move);
    }    

    public IllegalMoveException(Move move, String msg)
    {
        super ("Illegal move: " + move + ": " + msg);
    }    

    public IllegalMoveException(String msg)
    {
        super ("Illegal move: " + msg);
    }    
}