import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Connection {
    String serverIp;
    int serverPort;
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;

    Connection(String serverIp, int serverPort){
        this.serverIp = serverIp;
        this.serverPort = serverPort;
    }

    public void startConnection() throws IOException{
        System.out.println("Fazendo conexão");
        socket = new Socket(this.serverIp, this.serverPort);
        input = new DataInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());
        
    }

    public Socket getSocket(){
        return socket;
    }

    public DataInputStream getInput(){
        return input;
    }

    public DataOutputStream getOutObject(){
        return output;
    }

    public void closeConnection(){
        try {
            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}