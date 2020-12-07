
import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class cliente extends Thread {

    public String hora, setHora;
    public int horas, minutos, segundos, puerto;
    public DatagramSocket cl;
    public InetAddress host;
    public Thread h1;
    JLabel clockScreen;
    DatagramPacket sendHour;

    public cliente(JLabel clockScreen) {
        this.clockScreen = clockScreen;
        h1 = new Thread(this);
        h1.start();

    }

    @Override
    public void run() {
        try {
            cl = new DatagramSocket();
            

            Thread ct = Thread.currentThread();

            while (ct == h1) {
                setHour(20, 29, 55, true);
            }

            // ----------------------------- Recibir Paquete ----------------------------
            /*byte[] buff2 = new byte[20];

            String cadenaServer = "";
            DatagramPacket mensajeServer;
                mensajeServer = new DatagramPacket(buff2, buff2.length);
                cl.receive(mensajeServer);
                cadenaServer = new String(mensajeServer.getData(), 0, mensajeServer.getLength());
            

            System.out.println("Mensaje del servidor: " + cadenaServer);
             */
            //cl.close();
        } catch (Exception e) {
            e.printStackTrace();
        }//catch
    }//metodo Run

    public void setHour(int horas, int minutos, int segundos, boolean estado) throws IOException {
        this.horas = horas;
        this.minutos = minutos;
        this.segundos = segundos;
        String h = "";
        String m = "";
        String s = "";

        while (estado) {
            segundos++;
            //Comprobacion de tiempo
            if (segundos == 60) {
                minutos++;
                segundos = 00;
                if (minutos == 60) {
                    minutos = 00;
                    horas++;
                    if (horas == 24) {
                        horas = 00;
                    }
                }
            }
            h = Integer.toString(horas);
            m = Integer.toString(minutos);
            s = Integer.toString(segundos);
            hora = "cliente 1: " + h + ":" + m + ":" + s;
            setHora = hora.substring(11,hora.length());
            clockScreen.setText(setHora);

            byte[] b = hora.getBytes();
            sendHour = new DatagramPacket(b, b.length, InetAddress.getByName("192.168.0.11"), 2000);
            cl.send(sendHour);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }

    }

}
