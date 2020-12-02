import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class client {

    public static void main(String[] args) {
        try {
            String host = "localhost";
            int puerto = 9000;
            Socket cliente = new Socket(host, puerto);
            cliente.close();
        } catch (IOException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}