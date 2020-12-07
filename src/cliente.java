
import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class cliente extends Thread {

    public String hora, setHora, serverEstado, nuevaHora, nserverEstado;
    public int horas, minutos, segundos, puerto, nhoras, nminutos, nsegundos;
    public DatagramSocket cl;
    public InetAddress host;
    public Thread h1;
    JLabel clockScreen;
    DatagramPacket sendHour;
    DatagramPacket mensajeServer;
    DatagramPacket mensajeServer2;

    public cliente(JLabel clockScreen) {
        this.clockScreen = clockScreen;
        h1 = new Thread(this);
        h1.start();
        serverEstado = "true";
    }

    @Override
    public void run() {
        try {
            cl = new DatagramSocket();
            Thread ct = Thread.currentThread();
            horas = 20;
            minutos = 29;
            segundos = 03;
                while (ct == h1) {
                    setHour(horas, minutos, segundos, true);
                }
                System.out.println("corriending");

                // ----------------------------- Recibir Accion Parar Reloj ----------------------------
                byte[] buff2 = new byte[20];
                mensajeServer = new DatagramPacket(buff2, buff2.length);
                cl.receive(mensajeServer);
                serverEstado = new String(mensajeServer.getData(), 0, mensajeServer.getLength());

                // ----------------------------- Recibir Paquete Con Nueva Hora ----------------------------
                byte[] buff3 = new byte[20];
                mensajeServer2 = new DatagramPacket(buff3, buff3.length);
                cl.receive(mensajeServer2);
                nuevaHora = new String(mensajeServer2.getData(), 0, mensajeServer2.getLength());
                serverEstado = nuevaHora.substring(0, 4);
                horas = (int) Integer.parseInt(nuevaHora.substring(4, 6));
                minutos = (int) Integer.parseInt(nuevaHora.substring(7, 9));
                segundos = (int) Integer.parseInt(nuevaHora.substring(10, nuevaHora.length()));
            
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
            setHora = hora.substring(11, hora.length());
            clockScreen.setText(setHora);

            if (serverEstado == "true") {
                byte[] b = hora.getBytes();
                sendHour = new DatagramPacket(b, b.length, InetAddress.getByName("localhost"), 1234);
                cl.send(sendHour);
                estado = true;
            } else {
                estado = false;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }

    }

}
