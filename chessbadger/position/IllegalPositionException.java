package chessbadger.position;

@SuppressWarnings("serial")
public class IllegalPositionException extends java.lang.Exception
{
    public IllegalPositionException(String msg)
    {
        super (msg);
    }
}