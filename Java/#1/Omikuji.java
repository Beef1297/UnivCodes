class Omikuji {
    public static void main(String argv[]){
	int val;
	try{
	    while(true){
		val = System.in.read();
		double r = Math.random();
		if(r > 0.7){
		    System.out.println("大吉");
		}else if(r <= 0.2){
		    System.out.println("凶");
		}else{
		    System.out.println("吉");//0.2より大きく0.7以下という条件式を書くのは手間だから最後に持ってきた.
		}
		
		
	    }
	}catch(java.io.IOException e){return ;}
    }
}
