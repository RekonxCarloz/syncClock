
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
    public static InetAddress chost1, chost2, chost3;
    public static int puerto1, puerto2, puerto3;

    public server(JTextArea clockScreen1, JTextArea clockScreen2, JTextArea clockScreen3, JLabel host1, JLabel host2, JLabel host3) {
        this.clockScreen = clockScreen1;
        this.clockScreen2 = clockScreen2;
        this.clockScreen3 = clockScreen3;
        this.host1 = host1;
        this.host2 = host2;
        this.host3 = host3;

    }

    @Override
    public void run() {
        try {
            DatagramSocket s = new DatagramSocket(1234);
            System.out.println("Server Listo");
            for (;;) {
                byte[] buff = new byte[1024];
                DatagramPacket clientHour = new DatagramPacket(buff, buff.length);
                while (true) {
                    s.receive(clientHour);
                    String hora = new String(clientHour.getData(), 0, clientHour.getLength());
                    String horac = hora.substring(0, 9);
                    if (horac.equals("cliente 1")) {
                        chost1 = clientHour.getAddress();
                        puerto1 = clientHour.getPort();
                        host1.setText(chost1.toString() +" : "+puerto1);
                        hora1 = hora.substring(11, hora.length());
                        clockScreen.setText(hora1);
                    }
                    if (horac.equals("cliente 2")) {
                        chost2 = clientHour.getAddress();
                        puerto2 = clientHour.getPort();
                        host2.setText(chost2.toString() +" : "+puerto2);
                        hora2 = hora.substring(11, hora.length());
                        clockScreen2.setText(hora2);
                    }
                    if (horac.equals("cliente 3")) {
                        chost3 = clientHour.getAddress();
                        puerto3 = clientHour.getPort();
                        host3.setText(chost3.toString() +" : "+puerto3);
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
    
    
   // public DatagramSocket s(){
     //   return s;
    //}
    
    public InetAddress host1(){
        return chost1;
    }
    
    public int puerto1(){
        return puerto1;
    }
    
    public InetAddress host2(){
        return chost2;
    }
    
    public int puerto2(){
        return puerto2;
    }
    
    public InetAddress host3(){
        return chost3;
    }
    
    public int puerto3(){
        return puerto3;
    }
}
