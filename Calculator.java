import java.awt.*;
import java.awt.event.*;
class Calculator extends Frame implements ActionListener{

	TextField tf;
	Button[] numbuttons;
	Button[] functionbuttons;
	Button addButton,subButton,mulButton,divButton;
	Button decButton,equButton,delButton,clrButton,negButton;
	Panel panel;
	Font myfont = new Font("Ink Free",Font.BOLD,30);
	double num1=0,num2=0,result=0;
	char operator;

	Calculator(){
	setTitle("Calculator");
	setSize(420,550);
	setLayout(null);
	
	numbuttons = new Button[10];
	functionbuttons = new Button[9];

	tf = new TextField();
	tf.setBounds(50,50,300,50);
	tf.setFont(myfont);
	add(tf);
	tf.setEditable(false);

	addButton = new Button("+");
	subButton = new Button("-");
	mulButton = new Button("*");
	divButton = new Button("/");
	decButton = new Button(".");
	equButton = new Button("=");
	delButton = new Button("Delete");
	clrButton = new Button("Clear");
	negButton = new Button("(-)");

	functionbuttons[0] = addButton;
	functionbuttons[1] = subButton;
	functionbuttons[2] = mulButton;
	functionbuttons[3] = divButton;
	functionbuttons[4] = decButton;
	functionbuttons[5] = equButton;
	functionbuttons[6] = delButton;
	functionbuttons[7] = clrButton;
	functionbuttons[8] = negButton;

	for(int i = 0;i < 9; i++){
	functionbuttons[i].addActionListener(this);
	functionbuttons[i].setFont(myfont);
	functionbuttons[i].setFocusable(false);
	}

	for(int j = 0;j < 10; j++){
	numbuttons[j] = new Button(String.valueOf(j));
	numbuttons[j].addActionListener(this);
	numbuttons[j].setFont(myfont);
	numbuttons[j].setFocusable(false);
	}

	negButton.setBounds(50,430,100,50);
	delButton.setBounds(150,430,100,50);
	clrButton.setBounds(250,430,100,50);

	panel = new Panel();
	panel.setBounds(50,100,300,300);
	panel.setLayout(new GridLayout(4,4,10,10));

	panel.add(numbuttons[1]);
	panel.add(numbuttons[2]);
	panel.add(numbuttons[3]);
	panel.add(addButton);
	panel.add(numbuttons[4]);
	panel.add(numbuttons[5]);
	panel.add(numbuttons[6]);
	panel.add(subButton);
	panel.add(numbuttons[7]);
	panel.add(numbuttons[8]);
	panel.add(numbuttons[9]);
	panel.add(mulButton);
	panel.add(decButton);
	panel.add(numbuttons[0]);
	panel.add(equButton);
	panel.add(divButton);

	add(panel);
	add(negButton);
	add(delButton);
	add(clrButton);
	setVisible(true);
	addWindowListener(new WindowAdapter(){
	public void windowClosing(WindowEvent e){
	System.exit(0);
	}
	});
	}

	public void actionPerformed(ActionEvent e){
		for(int i = 0;i < 10;i++){
			if(e.getSource() == numbuttons[i]){
				tf.setText(tf.getText().concat(String.valueOf(i)));
			}
		}
		if(e.getSource() == decButton){
			tf.setText(tf.getText().concat("."));
		}
		if(e.getSource() == addButton){
			num1 = Double.parseDouble(tf.getText());
			operator = '+';
			tf.setText("");
		}
		if(e.getSource() == subButton){
			num1 = Double.parseDouble(tf.getText());
			operator = '-';
			tf.setText("");
		}
		if(e.getSource() == mulButton){
			num1 = Double.parseDouble(tf.getText());
			operator = '*';
			tf.setText("");
		}
		if(e.getSource() == divButton){
			num1 = Double.parseDouble(tf.getText());
			operator = '/';
			tf.setText("");
		}
		if(e.getSource() == equButton){
			num2 = Double.parseDouble(tf.getText());

			switch(operator){
			case '+':
				result=num1+num2;
				break;
			case '-':
				result=num1-num2;
				break;
			case '*':
				result=num1*num2;
				break;
			case '/':
				result=num1/num2;
				break;
			}
			tf.setText(String.valueOf(result));
			num1 = result;
		}
		if(e.getSource() == clrButton){
			tf.setText("");
		}
		if(e.getSource() == delButton){
			String str = tf.getText();
			tf.setText("");
			for(int i=0;i<str.length()-1;i++){
				tf.setText(tf.getText()+str.charAt(i));
			}
		}
		if(e.getSource() == negButton){
			double temp = Double.parseDouble(tf.getText());
			temp*=-1;
			tf.setText(String.valueOf(temp));
		}
	}

	public static void main(String p[]){
	new Calculator();
	}

}