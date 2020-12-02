
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class server extends Thread{
    
    JLabel status;
    
    public server(JLabel status){
        this.status = status;
    }
    
    @Override
    public void run(){
        int puerto = 9000;
        ServerSocket server;
        Socket cliente;
        try {
            server = new ServerSocket(puerto);
            status.setText("Servidor esperando cliente");
            for (;;) {
                cliente = server.accept();
                 status.setText("Cliente conectado: " + cliente.getInetAddress() + ": " + cliente.getPort());
                cliente.close();
            }

        } catch (IOException ex) {
            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
