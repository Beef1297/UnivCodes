import java.util.*;

class TestBoxing{
    public static void main(String argv[]){
	ArrayList<Integer> a;
	int sum = 0;
	a = new ArrayList<Integer>();
	a.add(10);
	a.add(100);
	a.add(1000);
	/*
	for(int i=0;i < a.size();i++){
	    String s = a.get(i);
	*/
	for(int v: a){
	    sum += v;
	}
	System.out.println(sum);
    }
}
