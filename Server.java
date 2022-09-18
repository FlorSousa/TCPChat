import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;

    public void start(int port) throws IOException {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        System.out.println("[S1] Criando server socket para aguardar conexões de clientes em loop");
        serverSocket = new ServerSocket(port);
        while (serverSocket.isBound()) {   
            System.out.println("[S2] Aguardando conexão em: " + serverSocket.getLocalSocketAddress());
            socket = serverSocket.accept();
            ServerConnection serverConnection = new ServerConnection(socket);
            threadPool.submit(serverConnection);
            
        }
        serverSocket.close();
    }

    public void stop() throws IOException {
        input.close();
        output.close();
        socket.close();
        serverSocket.close();
    }
    public static void main(String[] args) {
        int serverPort = 6666;
        try {
            // Inicia e roda servidor
            Server server = new Server();
            server.start(serverPort);

            // Finaliza servidor
            server.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}