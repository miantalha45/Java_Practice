class asad{

	int width;
	int height;
	int depth;

	asad(int width,int height,int depth){

	this.width = width;
	this.height = height;
	this.depth = depth;

	}

	int volume(){

	return width*depth*height;

	}

}
class empty{
public static void main(String[] args){

	System.out.println("i am new constructor");

	asad obj = new asad(10, 20, 30);
	asad obj2 = new asad(20,30,40);
	int vol;

//obj.setDim(10,20,30);
//obj.width=10;
//obj.height=20;
//obj.depth=30;

vol = obj.volume();
System.out.println(vol);
vol = obj2.volume();
System.out.println(vol);

}
}