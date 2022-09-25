import java.io.DataInputStream;
import java.io.IOException;

public class Listener extends Thread {
    private DataInputStream input;
    private Connection connection;
    Listener(DataInputStream input, Connection connection){
        this.input = input;
        this.connection = connection;
    }

    @Override
    public void run(){
        String serverResponse;
        try {
            serverResponse = input.readUTF();
            
            System.out.println("Mensagem do outro cliente:"+serverResponse);
            System.out.println("Insira uma mensagem: ");
            connection.closeConnection();
        } catch (IOException e) {  
            e.printStackTrace();
        }
    }
}