package chessbadger.pgn;

public interface PGNErrorHandler
{
    /**
     * Called in case of an error.
     *
     *@return the error
     */
    public void handleError(PGNSyntaxError error);
    
    /**
     * Called in case of a warning.
     *
     *@return the warning
     */
    public void handleWarning(PGNSyntaxError warning);
}