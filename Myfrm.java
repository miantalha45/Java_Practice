import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class process{

public void frm(){



    JFrame f=new JFrame();
    JTextField tf=new JTextField();
    JTextField tf1=new JTextField();
    JTextField tf2=new JTextField(); 
    JButton b=new JButton("send");
    JTextArea ta=new JTextArea();
    JLabel l = new JLabel("Message");
    JLabel l1 = new JLabel("IP:");
    JLabel l2 = new JLabel("Port:");  
      
        tf1.setBounds(60,10,100,20);
	f.add(tf1);

	l1.setBounds(40,10,20,20);
	f.add(l1);

	tf2.setBounds(255,10,50,20);
	f.add(tf2);

	l2.setBounds(220,10,40,20);
	f.add(l2);
	
	f.setLayout(null);
	f.setBounds(10,10,450,410);
	f.setVisible(true);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	tf.setBounds(10,330,340,30);
	f.add(tf);

	l.setBounds(20,311,80,20);
        f.add(l);

	b.setBounds(355,330,70,30);
	f.add(b);

	ta.setBounds(10,35,415,275);
	f.add(ta);

        

	 b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
	       String text = tf.getText();  
		            
               if(text.isEmpty()){
		//ta.append("Type anything" + "\n");
		}
 		else{
		ta.append("Client:  "+text + "\n");
		}
                tf.setText("");
            }
        });

}

}

class Myfrm{
public static void main(String p[]){

	process obj=new process();
	obj.frm();
   
    }

}
