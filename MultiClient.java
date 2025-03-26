import java.io.*;
import java.net.*;

public class MultiClient implements Runnable {

    Socket csocket;
    String str, str2;

    MultiClient(Socket csocket) {
        this.csocket = csocket;
    }

    public static void main(String args[]) throws Exception {
        ServerSocket ss = new ServerSocket(5000);
        System.out.println("Listening");
        while (true) {
            Socket s = ss.accept();
            System.out.println("Connected");
            new Thread(new MultiThreadServer(s)).start();
        }
    }

    public void run() {
        try {
            PrintStream out = new PrintStream(csocket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(csocket.getInputStream()));
            while ((str = in.readLine()) != null) {
                str2 = str.toUpperCase();
                str2 = str2 + "Munawar Hussain";
                out.println(str2);
            }
            out.close();
        } catch (

        IOException e) {
            System.out.println(e);
        }
    }
}