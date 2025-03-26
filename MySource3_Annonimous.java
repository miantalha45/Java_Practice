import java.awt.*;
import java.awt.event.*;


class MySource3_Annonimous extends Frame {
	Button b;
	MySource3_Annonimous(){
	setSize(300,300);
	b=new Button("click here");
	b.addActionListener(new ActionListener(){


	public void actionPerformed(ActionEvent e){
	
	System.out.println("button is pressed");

	}

	});
	addWindowListener(new WindowAdapter(){
	public void windowClosing(WindowEvent e){
	System.exit(0);
	}
	});
	add(b);setLayout(new FlowLayout()); 
	setVisible(true);
	}

public static void main(String p[]){
	new MySource3_Annonimous();
}
}
