
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class server extends Thread {

    public JTextArea status;
    public String statusConex;

    public server(JTextArea status) {
        this.status = status;
        statusConex = "";
    }

    @Override
    public void run() {
        int puerto = 9000;
        ServerSocket server;
        Socket cliente;
        try {
            server = new ServerSocket(puerto);
            status.setText("Servidor esperando cliente");
            for (;;) {
                cliente = server.accept();
                statusConex += "Cliente conectado: " + cliente.getInetAddress() + ": " + cliente.getPort() + "\n";
                status.setText(statusConex);
                cliente.close();
            }

        } catch (IOException ex) {
            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
