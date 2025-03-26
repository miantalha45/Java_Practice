import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
class GuiClient extends Thread implements ActionListener{
Frame f; Button b; TextArea ta; TextField tf;Panel p;Socket s;String str;
       GuiClient(Socket s){
       this.s=s;
       }
       GuiClient(){
         try{
         s=new Socket("127.0.0.1",5000);
         GuiClient us=new GuiClient(s);
         us.start();
         }catch(Exception e){}
        Gui();
        }
        void Gui(){
        f=new Frame();
        f.setSize(300,300);
	b=new Button("send");
        b.addActionListener(this);
	ta=new TextArea();
        ta.setEditable(false);
	tf=new TextField(25);
	f.add(ta);p=new Panel();
	p.add(tf);p.add(b);
	f.add(p,BorderLayout.SOUTH);
        f.setVisible(true);
        }
	public void actionPerformed(ActionEvent e){
        run();
        }
        public void run(){
          try{
          ta.append("You  ::  ");
          str=tf.getText();
	  ta.append(str+"\n");
          PrintWriter out=new PrintWriter(s.getOutputStream(),true);
          out.println(str);
          out.flush();
          tf.setText("");
          }catch(Exception x){}
        }
        public static void main(String p[])throws Exception{
	GuiClient obj=new GuiClient();
        obj.connection();
        }
        void connection(){
          try{
          String G;
          BufferedReader in;
          in=new BufferedReader(new InputStreamReader(s.getInputStream()));
            while((G=in.readLine())!=null){
            ta.append("Server :: "+G+"\n");
            }
           }catch(Exception e){}
        }
      
}