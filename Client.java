import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Client {
    private static Socket socket;
    private static DataInputStream input;
    private static DataOutputStream output;
    
    public static void main(String[] args) throws IOException {
        System.out.println("Iniciando conexão");
        Scanner scanner = new Scanner(System.in);
        while(true){
            ExecutorService threadPool = Executors.newFixedThreadPool(2);
            Connection conexao =  new Connection("0.0.0.0", 6666);
            conexao.startConnection();
            socket = conexao.getSocket();
            input = conexao.getInput();
            output = conexao.getOutObject();
            Listener listener = new Listener(input);
            Future<?> activeThread = threadPool.submit(listener);
            System.out.println("Insira uma mensagem");
            String msg = scanner.nextLine();
            sendMessage(msg);
            if(activeThread.isDone()){
                conexao.closeConnection();
            }
        }
    }

    public static void sendMessage(String msg) throws IOException{
        output.writeUTF(msg);    
    }   
}