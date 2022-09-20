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
            serverResponse = input.readUTF();
            
            System.out.println("Mensagem do outro cliente:"+serverResponse);
            System.out.println("Insira uma mensagem: ");
        } catch (IOException e) {  
            e.printStackTrace();
        }
    }
}