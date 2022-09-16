import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class Client {
    private static Socket socket;
    private static DataInputStream input;
    private static DataOutputStream output;
    
    public static void main(String[] args) throws IOException {
        Connection conexao =  new Connection("0.0.0.0", 6666);
        socket = conexao.getSocket();
        input = conexao.getInput();
        output = conexao.getOutObject();

        Client client = new Client();
        while(true){
            Scanner scanner = new Scanner(System.in);
            String msg = scanner.nextLine();
            scanner.close();
            client.sendMessage(msg);

        }
    }

    public void sendMessage(String msg) throws IOException{
        output.writeUTF(msg);
        String response = input.readUTF();
        System.out.println("[C5] Resposta recebida: " + response);
    }
    

   
}
