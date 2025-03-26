import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ClientGUI {
    private Frame frame;
    private TextField serverIPField;
    private TextField portField;
    private TextField messageField;
    private TextArea chatArea;
    private Button connectButton;
    private Button sendButton;
    private PrintWriter out;
    private BufferedReader in;

    public ClientGUI() {
        frame = new Frame("Client");
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        Panel topPanel = new Panel();
        serverIPField = new TextField(10);
        portField = new TextField(5);
        connectButton = new Button("Connect");
        connectButton.addActionListener(new ConnectButtonListener());
        topPanel.add(new Label("Server IP: "));
        topPanel.add(serverIPField);
        topPanel.add(new Label("Port: "));
        topPanel.add(portField);
        topPanel.add(connectButton);
        frame.add(topPanel, BorderLayout.NORTH);

        chatArea = new TextArea();
        chatArea.setEditable(false);
        frame.add(chatArea, BorderLayout.CENTER);

        Panel bottomPanel = new Panel();
        messageField = new TextField(20);
        sendButton = new Button("Send");
        sendButton.addActionListener(new SendButtonListener());
        bottomPanel.add(messageField);
        bottomPanel.add(sendButton);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private class ConnectButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                String serverIP = serverIPField.getText();
                int port = Integer.parseInt(portField.getText());
                Socket socket = new Socket(serverIP, port);
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                new Thread(new ReceiveMessage()).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class SendButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String message = messageField.getText();
            out.println(message);
            messageField.setText("");
        }
    }

    private class ReceiveMessage implements Runnable {
        public void run() {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    chatArea.append(message + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new ClientGUI();
    }
}
