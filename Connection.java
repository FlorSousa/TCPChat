import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

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

    public Boolean startConnection() throws IOException{
        try{
            socket = new Socket(this.serverIp, this.serverPort);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            return true;
        }catch(Exception io){
            return false;
        }
        
        
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