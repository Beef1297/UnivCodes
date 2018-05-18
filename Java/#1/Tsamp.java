public class Tsamp{
    public static void main(String[] args){
	TurtleFrame f;
	f = new TurtleFrame();
	Turtle m = new Turtle();
	f.add(m);
	m.rt(180);
	m.fd(50);
	m.lt(135);
	m.fd(25);
	m.rt(180);
	m.up(); m.fd(25); m.down();
	m.rt(90);
	m.fd(25);
	m.up();m.fd(25);m.down();
    }
}
