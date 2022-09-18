import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerConnection extends Thread{
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;

    public ServerConnection(Socket ClientSocket){
        try{
            clientSocket = ClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void sendResponse(String msg){
        try{
            out.writeUTF(msg.toUpperCase());
        }catch(Exception e){
            System.out.println("Não foi possivel enviar a mensagem");
        }
    }
    public void run(){
        try {
            String mensagem = in.readUTF();
            sendResponse(mensagem);
        } catch(Exception e){
            System.out.println("Ocorreu um erro");
        }finally{
            try{
                clientSocket.close();
            }catch(Exception e){
                System.out.println("Não foi possivel fechar a conexão");
            }
        }
    }
}