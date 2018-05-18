import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

class Figurefall {
    private final static Color c[] = {new Color(255,99,71), new Color(255,20,147), new Color(255,140, 0), new Color(124,252,0), new Color(0,255,255)};
    //コンストラクタをりよう
    protected Color color;
    protected int x,y,size;
    Figurefall() {
	x = (int)(Math.random()*450);
	y = (int)(Math.random()*450);
	size=(int)(Math.random()*30+20);
    color = c[(int)(Math.random() * 10) % 5];
    }
    public void fall(){
	y++;
    }
    public void up(){
	y--;
    }
    public int getY(){
	return y;
    }
    public void draw(Graphics g) {}
    
	
}

class Circle extends Figurefall {
    public void draw(Graphics g) {
	g.setColor(color);
	g.drawOval(x,y,size,size);
    }
}

class Box extends Figurefall {
    public void draw(Graphics g) {
	g.setColor(color);
	g.drawRect(x,y,size,size);
    }
}
class Cross extends Figurefall {
    public void draw(Graphics g){
	g.setColor(color);
	g.drawLine(x,y,x+size,y-size);
	g.drawLine(x+size,y,x,y-size);
    }
}

class FallPanel extends JPanel implements ActionListener{
    private final static int NUM=50;
    private ArrayList<Figurefall> fig;
    private javax.swing.Timer timer;
    private int count = 0;
    public FallPanel(){
	fig=new ArrayList<Figurefall>();
	for(int i=0;i<NUM;i++){
	    fig.add(new Box());
	    fig.add(new Circle());
	    fig.add(new Cross());
	}
	timer = new javax.swing.Timer(1, this);
	timer.start();
    }
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	for(Figurefall f: fig){
	    f.draw(g); 
	}
    }
    public void actionPerformed(ActionEvent e){
	if(count < 300){
	    ++count;
	    for(Figurefall af: fig){
		af.fall();
	    }
	}else{
	    ++count;
	    if(count >= 600)count = 0;
	    for(Figurefall af: fig){
		af.up();
	    }
	}
	/*ループ回数をカウントして上下運動するようにした*/    
	this.repaint();
    }
}

class FallFrame extends JFrame {
    public FallFrame(){
	this.setTitle("Fall Frame");
	this.setSize(500,530);
	this.add(new FallPanel());
	
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
    }
    public static void main(String argv[]) {
	new FallFrame();
    }
}
