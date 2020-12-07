
import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class server extends Thread {

    public static JTextArea clockScreen;
    public static JTextArea clockScreen2;
    public static JTextArea clockScreen3;
    public static JLabel host1, host2, host3;
    public static String hora1, hora2, hora3;
    public static InetAddress host;
    public static int puerto;

    public server(JTextArea clockScreen1, JTextArea clockScreen2, JTextArea clockScreen3, JLabel host1, JLabel host2, JLabel host3) {
        this.clockScreen = clockScreen1;
        this.clockScreen2 = clockScreen2;
        this.clockScreen3 = clockScreen3;
        this.host1 = host1;
        this.host2 = host2;
        this.host3 = host3;
        this.host = host;
        this.puerto = puerto;
    }

    @Override
    public void run() {
        try {
            DatagramSocket s = new DatagramSocket(2000);
            System.out.println("Server Listo");
            for (;;) {
                byte[] buff = new byte[1024];
                DatagramPacket clientHour = new DatagramPacket(buff, buff.length);
                while (true) {
                    s.receive(clientHour);
                    String hora = new String(clientHour.getData(), 0, clientHour.getLength());
                    String horac = hora.substring(0, 9);
                    if (horac.equals("cliente 1")) {
                        host = clientHour.getAddress();
                        puerto = clientHour.getPort();
                        host1.setText(host.toString() +" : "+puerto);
                        hora1 = hora.substring(11, hora.length());
                        clockScreen.setText(hora1);
                    }
                    if (horac.equals("cliente 2")) {
                        host2.setText(clientHour.getAddress().toString() + " : " + clientHour.getPort());
                        hora2 = hora.substring(11, hora.length());
                        clockScreen2.setText(hora2);
                    }
                    if (horac.equals("cliente 3")) {
                        host3.setText(clientHour.getAddress().toString() + " : " + clientHour.getPort());
                        hora3 = hora.substring(11, hora.length());
                        clockScreen3.setText(hora3);
                    }
                }
            }//for
            //s.close()
        } catch (IOException e) {
            System.out.println("Error en iniciar servidor");
        }//catch
    }//main
    
    
    public InetAddress host(){
        return host;
    }
    
    public int puerto(){
        return puerto;
    }
}
