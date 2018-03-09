package chessbadger.pgn;

import java.io.*;

public class PGNSimpleErrorHandler implements PGNErrorHandler
{

    private PrintStream m_out;
    
    /*================================================================================*/
    
    public PGNSimpleErrorHandler(PrintStream out)
    {
        m_out = out;
    }

    /*================================================================================*/
    
    public void handleError(PGNSyntaxError error)
    {
        m_out.println(error);
    }
    
    public void handleWarning(PGNSyntaxError warning)
    {
        m_out.println(warning);
    }    
}