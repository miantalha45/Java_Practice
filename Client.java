import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.Scanner;

class Client{
public static void main(String p[])throws Exception{
    

	JFrame f=new JFrame();
    JTextField tf=new JTextField();
    JButton b=new JButton("send");
    JTextArea ta=new JTextArea();
    JLabel l = new JLabel("Message");

	tf.setBounds(10,330,340,30);
	f.add(tf);

	l.setBounds(20,311,80,20);
        f.add(l);

	b.setBounds(355,330,70,30);
	f.add(b);

	ta.setBounds(10,35,415,275);
	f.add(ta);
    

	f.setLayout(null);
	f.setBounds(10,10,450,410);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.setVisible(true);
	f.setTitle("Client");
 

       Socket s=new Socket("192.168.100.5",9999);	

		// for writing to server
		Thread writeThread=new Thread(()->{
            try{

				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
				   
		
				String text = tf.getText();  
                //String str; 
                
                if(!text.isEmpty()){
					try{
					PrintWriter out=new PrintWriter(s.getOutputStream());
					out.println(text);
					ta.append("You: "+"  "+text + "\n");
					out.flush();
				}catch(Exception a){
					System.out.println(a);
				}
				   }
	
				tf.setText("");
			    }
		       });
            }
            catch(Exception aa){
				System.out.println(aa);
            }
         });
         writeThread.start();

		 	//for reading from server
			 Thread readThread=new Thread(()->{

				try{	
					BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
					String str2;
					while ((str2=in.readLine())!=null) 
					{
					 //System.out.println(str2);
					 ta.append("Server: "+"  "+str2 + "\n"); 
					}
			
				}catch(Exception aaa){
			      System.out.println(aaa);
				}
	
			 });
	
			readThread.start();

    }
}
