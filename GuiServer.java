import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
class GuiServer extends Thread implements ActionListener{
Frame f; Button b; TextArea ta; TextField tf;Panel p;Socket s;String str;
       GuiServer(Socket s){
       this.s=s;
       }
       GuiServer(){
         try{
         ServerSocket ss;
         ss=new ServerSocket(5000);
         s=ss.accept();
         GuiServer us=new GuiServer(s);
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
          ta.append("You :: ");
          str=tf.getText();
	  ta.append(str+"\n");
          PrintWriter out=new PrintWriter(s.getOutputStream(),true);
          out.println(str);
          out.flush();
          tf.setText("");
          }catch(Exception x){}
        }
        public static void main(String p[])throws Exception{
	GuiServer obj=new GuiServer();
        obj.connection();
        }
        void connection(){
          try{
          String G;
          BufferedReader in;
          in=new BufferedReader(new InputStreamReader(s.getInputStream()));
            while((G=in.readLine())!=null){
            ta.append("Client :: "+G+"\n");
            }
           }catch(Exception e){}
        }
      
}