package Backend.Server;

import java.net.*;
import java.io.*;

public class MultiServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        boolean listening = true;
        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            System.err.println("Could not listen on port 4444");
            System.exit(-1);
        }
        while (listening)
            try{
                new NewMultiServerThread(serverSocket.accept()).start();
            }catch (SocketException SE){
                System.out.println("CLIENT DISCONNECTED");
            }
        serverSocket.close();

    }

}
