import java.util.ArrayList;

public class HandleConnection {
    static ArrayList<ServerConnection> connections = new ArrayList<>();

    public static void addSocket(ServerConnection novoSocket){
        connections.add(novoSocket);
    }

    public static void sendToAll(String message, ServerConnection from){
        for(ServerConnection connection: connections){
            if(connection.isOpen()){
                message = (connection == from) ? "self-connection" : message;
                connection.sendResponse(message);
            }
        }
    }
}
