
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class client extends Thread {

    public int horas, minutos, segundos;
    public Calendar calendario;
    public Thread h1;
    boolean estado;
    public String hora, stop;
    DataOutputStream dos;
    DataInputStream dis;
    JLabel clockScreen;
    Socket cliente;

    public client(JLabel clockScreen) {
        this.clockScreen = clockScreen;
        stop = "true";
        h1 = new Thread(this);
        h1.start();
    }

    @Override
    public void run() {
        try {
            String host = "localhost";
            int puerto = 5000;
            cliente = new Socket(host, puerto);
            dis = new DataInputStream(cliente.getInputStream());
            dos = new DataOutputStream(cliente.getOutputStream());
            Thread ct = Thread.currentThread();
            while (ct == h1) {
                setHour(15, 9, 55, true);
            }
        } catch (IOException ex) {
            Logger.getLogger(clientFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

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
            clockScreen.setText(h + ":" + m + ":" + s);
            hora = h + ":" + m + ":" + s;
            dos.writeUTF(hora);
            dos.flush();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }

    }
}
