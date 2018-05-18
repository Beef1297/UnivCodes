public class turtle3{
    public static void main(String argv[]){
	TurtleFrame f = new TurtleFrame();
	Turtle m = new Turtle();
	f.add(m);
	double r,R;
	int k,j = 0,n = 20,l;
	m.up();
	while(j != 10){
	    r = Math.random();
	    k = (int)(r*10);
	    if(k < 2)k+=2;
	    System.out.println(k);
	    l = (10-k)*(10-k);
	    m.lt(90);m.fd(l);m.rt(90);
	    m.down();
	    for(int i=1;i <= 20;i++){
		m.fd(80);
		m.rt((360*k)/n);
	    }
	    m.up();
	    m.rt(90);m.fd(l);m.lt(90);
	    j++;
	}
    }
}
