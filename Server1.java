import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Server1 {
    static JTextArea ta;
    static JComboBox<String> comboBox;
    static HashMap<String, Socket> clientSockets;
    static PrintWriter serverOut;

    public static void main(String p[]) throws Exception {

        JFrame f = new JFrame();
        JTextField tf = new JTextField();
        JButton b = new JButton("send");
        ta = new JTextArea();
        JTextArea ta2 = new JTextArea();
        JLabel l = new JLabel("Message");
        comboBox = new JComboBox<>();
        clientSockets = new HashMap<>();

        comboBox.setBounds(335, 165, 130, 20);
        f.add(comboBox);

        f.setLayout(null);
        f.setBounds(10, 10, 495, 410);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tf.setBounds(10, 330, 340, 30);
        f.add(tf);

        l.setBounds(20, 311, 80, 20);
        f.add(l);

        b.setBounds(355, 330, 70, 30);
        f.add(b);

        ta.setBounds(10, 10, 320, 295);
        ta2.setBounds(335, 10, 135, 140);
        f.add(ta);
        f.add(ta2);

        ServerSocket ss = new ServerSocket(9999);

        ta2.append("waiting...\n");

        // Listen for connections from clients
        Thread acceptThread = new Thread(() -> {
            try {
                while (true) {
                    Socket clientSocket = ss.accept();
                    handleClientConnection(clientSocket, ta2);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        acceptThread.start();

        // Action listener for JComboBox
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedClient = (String) comboBox.getSelectedItem();
                if (selectedClient != null) {
                    startChatSession(selectedClient);
                }
            }
        });

        // Action listener for send button
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = tf.getText();
                String selectedClient = (String) comboBox.getSelectedItem();
                if (selectedClient != null && !text.isEmpty()) {
                    sendToClient(selectedClient, text);
                    ta.append("You to " + selectedClient + ": " + text + "\n");
                    tf.setText("");
                }
            }
        });
    }

    static void handleClientConnection(Socket clientSocket, JTextArea ta2) {
        try {
            BufferedReader nr = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientName = nr.readLine();
            if (clientName != null) {
                SwingUtilities.invokeLater(() -> {
                    ta2.append(clientName + " is connected\n");
                    comboBox.addItem(clientName);
                    try {
                        PrintWriter clientOut = new PrintWriter(clientSocket.getOutputStream(), true);
                        clientSockets.put(clientName, clientSocket);
                        if (serverOut == null) {
                            serverOut = clientOut; // Store server output stream for sending messages to clients
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static void startChatSession(String selectedClient) {
        // Start chat session with the selected client
        Socket selectedSocket = clientSockets.get(selectedClient);
        if (selectedSocket != null) {
            Thread readThread = new Thread(() -> {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(selectedSocket.getInputStream()));
                    String str;
                    while ((str = in.readLine()) != null) {
                        ta.append(selectedClient + ": " + str + "\n");
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            });
            readThread.start();
        }
    }

    static void sendToClient(String clientName, String message) {
        Socket clientSocket = clientSockets.get(clientName);
        if (clientSocket != null) {
            try {
                PrintWriter clientOut = new PrintWriter(clientSocket.getOutputStream(), true);
                clientOut.println(message);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
