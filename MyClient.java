import java.net.*;
import java.io.*;
class MyClient{
    Socket s;
    BufferedReader br ;
    PrintWriter out;
    MyClient(){
        try{
            System.out.println("sending request to server");
            s = new Socket("127.0.0.1",7777);
            System.out.println("Connection done");

            br = new BufferedReader(new InputStreamReader(s.getInputStream()));

            out = new PrintWriter(s.getOutputStream());
            startReading();
            startWriting();

        }catch(Exception e){

        }
    }

    public void startReading(){
        //thread - read karke deta rahega
        //for making a thread
        Runnable r1 = ()->{
            System.out.println("Reader Started");
            while(true){
                try{
                String str = br.readLine();
                if(str.equals("exit"))
                {
                    System.out.println("Server has terminated");
                    break;
                }
                System.out.println("Server.."+str);
                }catch(Exception e){

                }
            }
        
        };
        new Thread(r1).start();
    }

    public void startWriting(){
        //thread - data user se lega and then usko send kre ga client tak
        Runnable r2 = ()->{
            System.out.println("Writer Started..");
            while(true){
                try {
                        BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                        String str2 = br1.readLine();
                        out.println(str2);
                        out.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(r2).start();
    }
    public static void main(String p[]){

        System.out.println("This is Client...");
	new MyClient();
    }
}