package pei.java.design.pattern.lab.command;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author pei
 *
 */
public class CommandPatternDemo1{

    public static void main(String[] args) {
        
        Invoker invoker = new Invoker();
        
        AsiaServer asiaServer = new AsiaServer();
        EuroServer euroServer = new EuroServer();

        invoker.setCommand(new DiagnoseCommand(asiaServer));
        invoker.run();
        
        invoker.setCommand(new ShutDownCommand(asiaServer));
        invoker.run();
        
        invoker.setCommand(new RebootCommand(euroServer));
        invoker.run();
        
    }
}

/*
 * Command contains the command target (object on which the command will be executed)
 */
@AllArgsConstructor
class ShutDownCommand implements Command {
    Server server; 
    public void execute() {
        server.connect();
        server.shutdown();
        server.disconnect();
        System.out.println();
    }
}
@AllArgsConstructor
class DiagnoseCommand implements Command {
    Server server;
    public void execute() {
        server.connect();
        server.diagnostics();
        server.disconnect();
        System.out.println();
    }
}
@AllArgsConstructor
class RebootCommand implements Command {
    Server server;
    public void execute() {
        server.connect();
        server.reboot();
        server.disconnect();
        System.out.println();
    }
}

/*
 * Invoker is like a manager of commands,
 * It can launch/save/roll back/schedule/route(dispatch)/... commands  
 */
@NoArgsConstructor @Setter
class Invoker {
    Command command;

    public void run() {
        command.execute();
    }
}

interface Server {
    public void connect();
    public void diagnostics();
    public void reboot();
    public void shutdown();
    public void disconnect();
}

@NoArgsConstructor
class AsiaServer implements Server {
    public void connect() {
        System.out.println("Connected to the Asia server.");
    }
    public void diagnostics() {
        System.out.println("The asia server diagnostics check out OK.");
    }
    public void shutdown() {
        System.out.println("Shutting down the Asia server.");
    }
    public void reboot() {
        System.out.println("Rebooting the Asia server.");
    }
    public void disconnect() {
        System.out.println("Disconnected from the Asia server.");
    }
}
@NoArgsConstructor
class EuroServer implements Server {
    public void connect() {
        System.out.println("Connected to the Euro server.");
    }
    public void diagnostics() {
        System.out.println("The Euro server diagnostics check out OK.");
    }
    public void shutdown() {
        System.out.println("Shutting down the Euro server.");
    }
    public void reboot() {
        System.out.println("Rebooting the Euro server.");
    }
    public void disconnect() {
        System.out.println("Disconnected from the Euro server.");
    }
}
