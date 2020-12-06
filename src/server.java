
import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class server extends Thread {

    public static JLabel clockScreen;

    public static void main(String[] args) {
        try {
            DatagramSocket s = new DatagramSocket(2000);
            System.out.println("Server Listo");
            for (;;) {
                byte[] buff = new byte[1024];
                DatagramPacket clientHour = new DatagramPacket(buff, buff.length);
                while (true) {
                    s.receive(clientHour);
                    String hora = new String(clientHour.getData(), 0, clientHour.getLength());
                    JOptionPane.showMessageDialog(null, hora);
                }
            }//for
            //s.close()
        } catch (IOException e) {
            System.out.println("Error en levantar servidor");
        }//catch
    }//main
}
