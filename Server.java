import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class Server {
	public static void main(String p[]) throws Exception {

		JFrame f = new JFrame();
		JTextField tf = new JTextField();
		JButton b = new JButton("send");
		JTextArea ta = new JTextArea();
		JLabel l = new JLabel("Message");
		ta.setEditable(false);

		f.setLayout(null);
		f.setBounds(10, 10, 450, 410);
		f.setVisible(true);
		f.setTitle("Server");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		tf.setBounds(10, 330, 340, 30);
		f.add(tf);

		l.setBounds(20, 311, 80, 20);
		f.add(l);

		b.setBounds(355, 330, 70, 30);
		f.add(b);

		ta.setBounds(10, 10, 415, 295);
		f.add(ta);

		ServerSocket ss = new ServerSocket(9999);
		Socket s;

		s = ss.accept();

		// for writing to client
		Thread writeThread = new Thread(() -> {
			try {

				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						String text = tf.getText();

						if (!text.isEmpty()) {
							try {
								PrintWriter out = new PrintWriter(s.getOutputStream());
								out.println(text);
								ta.append("You: " + "  " + text + "\n");
								out.flush();
							} catch (Exception a) {
								System.out.println(a);
							}
						}

						tf.setText("");
					}
				});
			} catch (Exception aa) {
				System.out.println(aa);
			}
		});
		writeThread.start();

		// for reading from client
		Thread readThread = new Thread(() -> {

			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				String str2;
				while ((str2 = in.readLine()) != null) {

					ta.append("Client: " + "  " + str2 + "\n");
				}

			} catch (Exception e) {
				System.out.println(e);
			}

		});

		readThread.start();

	}

}
