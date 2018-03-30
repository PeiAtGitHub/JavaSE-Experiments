package pei.java.design.pattern.lab.chainOfResponsibility;

/**
 * the request does NOT END until end of chain
 * 
 * @author pei
 *
 */

public class ChainOfResponsibilityDemo2 {

    public static void main(String[] args) throws InterruptedException {
        Logger stdoutLogger = 
                new StdoutLogger(Logger.DEBUG
                        , new EmailLogger(Logger.NOTICE
                                , new StderrLogger(Logger.ERR, null)));

        stdoutLogger.message("This is a DEBUG level msg.", Logger.DEBUG); 
        stdoutLogger.message("This is a NOTICE level msg.", Logger.NOTICE); 
        stdoutLogger.message("This is a ERR level msg.", Logger.ERR);
        
        System.out.println();

        // reverse order
        Logger stderrLogger = 
                new StderrLogger(Logger.ERR
                        , new EmailLogger(Logger.NOTICE
                                , new StdoutLogger(Logger.DEBUG, null)));

        stderrLogger.message("This is a DEBUG level msg.", Logger.DEBUG); 
        stderrLogger.message("This is a NOTICE level msg.", Logger.NOTICE); 
        stderrLogger.message("This is an ERR level msg.", Logger.ERR);
        
    }

}

abstract class Logger {
    // severity, ERR > NOTICE > DEBUG
    public static int ERR = 7;
    public static int NOTICE = 5;
    public static int DEBUG = 3;
    
    protected int mask;// messages whose severity bigger than the mask will be processed

    protected Logger next;

    public void message(String msg, int priority) {
        if (priority >= mask) {
            writeMessage(msg);
        }
        if (next != null) { 
            next.message(msg, priority);
        }
    }

    abstract protected void writeMessage(String msg);
}

class StdoutLogger extends Logger {
    public StdoutLogger(int mask, Logger next) {
        this.mask = mask;
        this.next = next;
    }
    protected void writeMessage(String msg) {
        System.out.println("Writing to stdout: " + msg);
    }
}
class EmailLogger extends Logger {
    public EmailLogger(int mask, Logger next) {
        this.mask = mask;
        this.next = next;
    }
    protected void writeMessage(String msg) {
        System.out.println("Sending via email: " + msg);
    }
}
class StderrLogger extends Logger {
    public StderrLogger(int mask, Logger next) {
        this.mask = mask;
        this.next = next;
    }
    protected void writeMessage(String msg) {
        System.err.println("Sending to stderr: " + msg);
        try {
            Thread.sleep(200);// wait for err output
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

