import java.io.*;
class IODemo3{

public static void main(String p[])throws Exception{

	BufferedReader in;
	String str;

	in=new BufferedReader(new InputStreamReader(System.in));


	while((str=in.readLine())!=null){


	System.out.println(str);

	}



}


}