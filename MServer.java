import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;



class MServer{
public static void main(String p[])throws Exception{

	
	JFrame f=new JFrame();
    JTextField tf=new JTextField(); 
    JButton b=new JButton("send");
	JButton b2=new JButton("chat");
    JTextArea ta=new JTextArea();
	JTextArea ta2=new JTextArea();
    JLabel l = new JLabel("Message");  
	

	f.setLayout(null);
	f.setBounds(10,10,495,410);
	f.setVisible(true);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	tf.setBounds(10,330,340,30);
	f.add(tf);

	l.setBounds(20,311,80,20);
        f.add(l);

	b.setBounds(355,330,70,30);
	b.setSize(35, 20);
	f.add(b);

	ta.setBounds(10,10,320,295);
	ta2.setBounds(335, 10, 135, 295);
	f.add(ta);
	f.add(ta2);




        ServerSocket ss=new ServerSocket(9999);

	ta2.append("waiting...\n");
	int i=1;
	String N="";
	

    while(i>0){
		Socket s;
		s=ss.accept();
		try{
			BufferedReader nr =new BufferedReader(new InputStreamReader(s.getInputStream()));
		 
		 N=nr.readLine();}catch(Exception ne)
		 {
			System.out.println(ne);
		}
		ta2.append(N+" is connected\n");
		i++;
		// for writing to client
		Thread writeThread=new Thread(()->{
            try{
                //BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

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

		 	//for reading from client
			 Thread readThread=new Thread(()->{

				try{	
					BufferedReader in =new BufferedReader(new InputStreamReader(s.getInputStream()));
					String str2;
					while ((str2=in.readLine())!=null) 
					{
					 //System.out.println(str2);
					 ta.append("Client: "+"  "+str2 + "\n"); 
					}
			
				}catch(Exception e){
			      System.out.println(e);
				}
	
			 });
	
			readThread.start();    
			}
    }

}
