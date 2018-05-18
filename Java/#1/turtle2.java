public class turtle2 {
    public static void main(String[] args){
	TurtleFrame f;
	f = new TurtleFrame();
	Turtle m = new Turtle();
	f.add(m);
        int n = 360/20;
	m.lt(90); //大きい図形が書きたかったのと慣れのため
	m.up(); m.fd(180);m.rt(90);//左の方に移動した
	m.down();
	for(int i=1;i <= 20;i++){    
	    m.fd(55);//表示フレームにはいるギリギリの大きさになるのが55pxだった.
	    m.rt(n);
	}
    }
}
