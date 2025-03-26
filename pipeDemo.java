import java.io.*;
class pipeDemo
{
	public static void main(String p[]) throws IOException{
		final PipedOutputStream pout = new PipedOutputStream();
		final PipedInputStream pin = new PipedInputStream();
		pout.connect(pin);



		Thread t1 = new Thread()
		{
			public void run(){
				try{
					for(int i = 50;i <= 60;i++)
					{
						pout.write(i);
						System.out.println("PipedOutStream Writting i = " + i);
						Thread.sleep(1000);
					}
					pout.close();
				}
				catch (Exception exe){
					exe.printStackTrace();
				}
			}
		};
		
		Thread t2 = new Thread()
		{
			public void run(){
				try{
					int i;
					while((i = pin.read()) != -1)
					{
						System.out.println("PipedInStream Reading i = " + i);
					}
					pin.close();
				}
				catch (Exception exe){
					exe.printStackTrace();
				}
			}
		};
		t1.start();
		t2.start();
	}
}