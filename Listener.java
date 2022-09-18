import java.io.DataInputStream;
import java.io.IOException;

public class Listener extends Thread {
    private DataInputStream input;
    Listener(DataInputStream input){
        this.input = input;
    }

    @Override
    public void run(){
        String serverResponse;
        try {
            System.out.println("Ouvindo");
            serverResponse = input.readUTF();
            System.out.println("Conexão:"+serverResponse);
        } catch (IOException e) {  
            e.printStackTrace();
        }
    }
}