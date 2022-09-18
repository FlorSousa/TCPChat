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
        System.out.println("Iniciando conexão");
        Scanner scanner = new Scanner(System.in);
        while(true){
            Connection conexao =  new Connection("0.0.0.0", 6666);
            conexao.startConnection();
            socket = conexao.getSocket();
            input = conexao.getInput();
            output = conexao.getOutObject();
            System.out.println("Insira uma mensagem");
            String msg = scanner.nextLine();
            sendMessage(msg);
            conexao.closeConnection();
        }
    }

    public static void sendMessage(String msg) throws IOException{
        output.writeUTF(msg);
        String response = input.readUTF();
        System.out.println("[C5] Resposta recebida: " + response);       
    }   
}