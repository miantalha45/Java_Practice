import java.io.*;
class ExpDemo{

public static void main(String p[]){

	int x=10;
	int y=1;
	int z=0;

	try{
	 z=x/y;
	System.in.read();

	}catch(ArithmeticException e){
	System.out.println("can not devide by zero .....");
	}
	catch(IOException e){
	System.out.println("read write issue");
	}finally{
	System.out.println("i m finally...");
	}

	System.out.println(z);
	System.out.println("The End .....");

}

}

