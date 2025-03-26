import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

public class Client2 {
    public static void main(String p[]) throws Exception {
        JFrame f = new JFrame();
        JTextField tf = new JTextField();
        JButton b = new JButton("send");
        JTextArea ta = new JTextArea();
        JLabel l = new JLabel("Message");
        JLabel l2 = new JLabel("Asad");
        String N = "Asad";

        tf.setBounds(10, 330, 340, 30);
        f.add(tf);

        l.setBounds(20, 311, 80, 20);
        l2.setBounds(20, 10, 50, 20);
        f.add(l2);
        f.add(l);

        b.setBounds(355, 330, 70, 30);
        f.add(b);

        ta.setBounds(10, 35, 415, 275);
        f.add(ta);

        f.setLayout(null);
        f.setBounds(10, 10, 450, 410);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
	f.setTitle("Asad");

        Socket s = new Socket("10.96.40.226", 9999); // Connect to the server

        try {
            PrintWriter out = new PrintWriter(s.getOutputStream());
            out.println(N); // Send client name to server
            out.flush();
        } catch (Exception ne) {
            System.out.println(ne);
        }

        // for writing to server
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = tf.getText();
                if (!text.isEmpty()) {
                    try {
                        PrintWriter out = new PrintWriter(s.getOutputStream());
                        out.println(text);
                        ta.append("You: " + text + "\n");
                        out.flush();
                    } catch (Exception a) {
                        System.out.println(a);
                    }
                }
                tf.setText("");
            }
        });

        // for reading from server
        Thread readThread = new Thread(() -> {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String str;
                while ((str = in.readLine()) != null) {
                    ta.append("Server: " + str + "\n");
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        });

        readThread.start();
    }
}
