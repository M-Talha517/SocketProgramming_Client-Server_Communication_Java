import Backend.Server.MultiServer;
import GUI.InitialScreen;


import java.io.IOException;

class Main{
        public static void main(String[] args) throws IOException {
            new InitialScreen();

            // comment this line and run the MultiServer.java file
            // at  src/Backend/Server/MultiServer.java
            // to establish connection with Multiple clients
            MultiServer.main(args);

        }
}
