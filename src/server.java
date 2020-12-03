
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class server extends Thread {

    public JTextArea status;
    public JLabel clock1;
    public JLabel clock2;
    public JLabel clock3;
    public String statusConex;
    public int cont;
    public boolean closeSocket;
    public boolean isConnected;
    public clientFrame cliente;

    public server(JTextArea status, boolean closeSocket, JLabel clock1, JLabel clock2, JLabel clock3, boolean isConnected) {
        this.status = status;
        this.clock1 = clock1;
        statusConex = "";
        this.closeSocket = closeSocket;
        cont = 1;
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
                
                statusConex = "Nuevo cliente conectado";
                
                status.setText(statusConex);
                
                clock1.setText(cliente.getInetAddress() + ": " + cliente.getPort());
                //if (closeSocket) {
                //  cliente.close();
                //}
            }

        } catch (IOException ex) {
            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
