import java.io.*;
class IODemo4{

public static void main(String p[])throws Exception{
	FileInputStream in;
	FileOutputStream out;
	in=new FileInputStream("ik.webp");
	out=new FileOutputStream("f:\\Munawar.gif");
	int i;

	while((i=in.read())!=-1){


	out.write(i);

	}
out.close();


}


}