import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ServerGUI {
    private Frame frame;
    private TextArea chatArea;
    private TextField messageField;
    private Button sendButton;
    private ServerSocket serverSocket;
    private ArrayList<PrintWriter> clientWriters = new ArrayList<>();

    public ServerGUI() {
        frame = new Frame("Server");
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });

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

        startServer();
    }

    private void startServer() {
        final int PORT_NUMBER = 12345;

        try {
            serverSocket = new ServerSocket(PORT_NUMBER);
            chatArea.append("Server started. Listening on port " + PORT_NUMBER + "\n");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                chatArea.append("Client connected: " + clientSocket + "\n");

                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                clientWriters.add(writer);

                new Thread(new ClientHandler(clientSocket, writer)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class ClientHandler implements Runnable {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket, PrintWriter writer) {
            this.clientSocket = socket;
            this.out = writer;
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            try {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    chatArea.append("Client: " + inputLine + "\n");
                    sendToAllClients( inputLine);

                    if (inputLine.equalsIgnoreCase("bye")) {
                        break;
                    }
                }

                chatArea.append("Client disconnected: " + clientSocket + "\n");
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class SendButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String message = messageField.getText();
            chatArea.append(message + "\n");
            sendToAllClients("Server : " +message);
            messageField.setText("");
        }
    }

    private void sendToAllClients(String message) {
        for (PrintWriter writer : clientWriters) {
            writer.println(message);
        }
    }

    public static void main(String[] args) {
        new ServerGUI();
    }
}
