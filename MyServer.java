import java.net.*;
import java.io.*;
class MyServer{
    ServerSocket ss;
    Socket s;
    BufferedReader br;
    PrintWriter out;
    MyServer(){
     try{
        ss = new ServerSocket(7777);
        System.out.println("Server is ready..");
        s = ss.accept();

        br = new BufferedReader(new InputStreamReader(s.getInputStream()));

        out = new PrintWriter(s.getOutputStream());

        startReading();
        startWriting();
     }catch(Exception e){
        e.printStackTrace();
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
                    System.out.println("Client has terminated");
                    break;
                }
                System.out.println("Client.."+str);
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
        System.out.println("This is Server...Going to start");
        new MyServer();
    }
}