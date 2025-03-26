import java.io.*;
import java.net.*;

public class ClientExample {
    public static void main(String[] args) {
        try {
            // Establish connection with the server
            Socket socket = new Socket("192.168.100.5", 5000); // Assuming the server is running on the same machine
            
            // Setup input and output streams for communication with the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            
            // Send data to the server
            writer.println("Hello, server!");
            
            // Receive response from the server
            String response = reader.readLine();
            System.out.println("Server response: " + response);
            
            // Close the socket and streams
            reader.close();
            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
