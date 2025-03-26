import java.awt.*;
import javax.swing.*;

class Notepad{
public static void main(String[] args){
JFrame frame=new JFrame();
frame.setVisible(true);
frame.setBounds(400,150,350,430);
frame.setTitle("Notepad");
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


JMenuBar menubar = new JMenuBar();

JMenu File =new JMenu("File");

JMenuItem i1=new JMenuItem("     New                       Ctrl+N");
JMenuItem i2=new JMenuItem("     New Window                          Ctrl+Shift+N");
JMenuItem i3=new JMenuItem("     Open                          Ctrl+O");
JMenuItem i4=new JMenuItem("     Save                          Ctrl+S");
JMenuItem i5=new JMenuItem("     Save as                              Ctrl+Shift+S");
JMenuItem i6=new JMenuItem("     Page Setup");
JMenuItem i7=new JMenuItem("     Print                          Ctrl+P");
JMenuItem i8=new JMenuItem("     Exit");

File.add(i1);
File.add(i2);
File.add(i3);
File.add(i4);
File.add(i5);
File.add(i6);
File.add(i7);
File.add(i8);

menubar.add(File);


JMenu Fil =new JMenu("Edit");

JMenuItem i9=new  JMenuItem("     Undo                     Ctrl+Z");
JMenuItem i10=new JMenuItem("     Cut                      Ctrl+X");
JMenuItem i11=new JMenuItem("     Copy                      Ctrl+C");
JMenuItem i12=new JMenuItem("     Paste                     Ctrl+V");
JMenuItem i13=new JMenuItem("     Delete                     Del");
JMenuItem i14=new JMenuItem("     Find                     Ctrl+F");
JMenuItem i15=new JMenuItem("     Find Next                     F3 ");
JMenuItem i16=new JMenuItem("     Replace                     Ctrl+H");
JMenuItem i17=new JMenuItem("     Go To                       Ctrl+G");
JMenuItem i18=new JMenuItem("     Select All                     Ctrl+A");
JMenuItem i19=new JMenuItem("     Date/Time                     F5");

Fil.add(i9);
Fil.add(i10);
Fil.add(i11);
Fil.add(i12);
Fil.add(i13);
Fil.add(i14);
Fil.add(i15);
Fil.add(i16);
File.add(i17);
Fil.add(i18);
Fil.add(i19);
menubar.add(Fil);



JMenu fi=new JMenu("Formate");
JMenuItem i20=new JMenuItem("Word Wrap");
JMenuItem i21=new JMenuItem("Font...");
fi.add(i20);
fi.add(i21);
menubar.add(fi);

JMenu f = new JMenu("View");
JMenuItem i22=new JMenuItem("Zoom     >");
JMenuItem i23=new JMenuItem("Status Bar");
f.add(i22);
f.add(i23);
menubar.add(f);

JMenu ff=new JMenu("Help");
JMenuItem i24=new JMenuItem("View Help");
JMenuItem i25=new JMenuItem("Send Feedback");
JMenuItem i26=new JMenuItem("About Notepad");
ff.add(i24);
ff.add(i25);
ff.add(i26);
menubar.add(ff);


frame.setJMenuBar(menubar);
Font font=new Font("Italic",Font.PLAIN,15);
menubar.setFont(font);




Container c=frame.getContentPane();
JTextArea ta = new JTextArea();
c.add(ta); 

}
}