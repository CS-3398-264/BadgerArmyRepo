package chessbadger.pgn;

@SuppressWarnings("serial")
public class PGNSyntaxError extends java.lang.Exception
{
    public final static int
        ERROR   = 0,
        WARNING = 1,
        MESSAGE = 2;
    
    //======================================================================
    
    private int m_severity;
    private String m_filename;
    private int m_lineNumber;
    private String m_lastToken;

    //======================================================================
    
    public PGNSyntaxError(int severity, String msg, String filename, int lineNumber, String lastToken)
    {
        super(msg);
        m_severity = severity;
        m_filename = filename;
        m_lineNumber = lineNumber;
        m_lastToken = lastToken;
    }
    
    //======================================================================
    
    public int getSeverity()     {return m_severity;}
    public String getFilename()  {return m_filename;}
    public int getLineNumber()   {return m_lineNumber;}
    public String getLastToken() {return m_lastToken;}

    //======================================================================
    
    public String toString()
    {
        if (m_severity == ERROR) {
            return "ERROR: " + m_filename + ":" + m_lineNumber + ": near " + m_lastToken + ": " + getMessage();
        } else if (m_severity == WARNING) {
            return "WARNING: " + m_filename + ":" + m_lineNumber + ": near " + m_lastToken + ": " + getMessage();
        } else if (m_severity == MESSAGE) {
            return "MESSAGE: " + m_filename + ":" + m_lineNumber + ": near " + m_lastToken + ": " + getMessage();
        } else {
            throw new RuntimeException("Illegal severity");
        }
    }

}


