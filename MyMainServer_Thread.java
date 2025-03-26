import java.net.*;
import java.io.*;

class MyMainServer_Thread{

	public static void main(String p[])throws Exception{
	ServerSocket ss;
	Socket s;
	BufferedReader in;
	String str;

	ss=new ServerSocket(5000);
	while(true){
	s=ss.accept();
	in=new BufferedReader(new InputStreamReader(s.getInputStream()));
	new MyThread(this).start();

	}
	
	
	

}
}
class MyThread extends Thread{
        String str;
	MyMainServer_Thread obj;
	MyThread(MyMainServer_Thread i){
	obj=i;

	}
	public void run(){
	try{
	while((str=obj.in.readLine()!=null)){

	System.out.println(str);

	}
}catch(Exception e){}	
	

	}
	



}