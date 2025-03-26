class MyMainThread{

public static void main(String p[])throws Exception{

	MyThread pipe1 = new MyThread();
	MyThread pipe2 = new MyThread();
	MyThread pipe3 = new MyThread();
	pipe1.start();
	pipe2.start();
	pipe3.start();	

}

}

class MyThread extends Thread{

	public void run(){
		try{
		for(int i=1;i<=50;i++){
			System.out.println(i);
			Thread.sleep(100);
		}
		}catch(Exception e){
	
		}
	   	


	}

}