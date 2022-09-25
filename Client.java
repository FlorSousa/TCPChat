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
    private static Boolean starting = true;
    public static void main(String[] args) throws IOException {
        System.out.println("Iniciando conexão. . .");
        Scanner scanner = new Scanner(System.in);
        while(true){
            ExecutorService threadPool = Executors.newFixedThreadPool(2);
            Connection conexao =  new Connection("0.0.0.0", 6666);         
            Boolean Open = conexao.startConnection();
            if(Open){
                if(starting) {
                    System.out.println("Insira uma mensagem: ");
                    starting = false;
                }
                socket = conexao.getSocket();
                input = conexao.getInput();
                output = conexao.getOutObject();
                Listener listener = new Listener(input, conexao);
                String msg = scanner.nextLine();
                sendMessage(msg);
                Future<?> activeThread = threadPool.submit(listener);
                continue;
            }
            System.out.println("Erro ao iniciar, sem conexão com o servidor");
        }
    }

    public static void sendMessage(String msg){
        try{
            output.writeUTF(msg); 
            System.out.println("Enviada");
        }catch(Exception io){
            System.out.println("Erro ao enviar");
        }
    }   
}