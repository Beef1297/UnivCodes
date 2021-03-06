import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.*;

abstract class Figure {
    protected int x,y,width,height;
    protected Color color;
    protected final static Color c[] = {new Color(255,99,71),new Color(255,20,147), new Color(255,140, 0), new Color(124,252,0), new Color(0,255,255)};
    public Figure() {
	x = (int)(Math.random()*450);
	y = (int)(Math.random()*450);
	color = c[(int)(Math.random() * 10) % 5];
    }
    public Figure(int x, int y, int w, int h, Color c){
	this.x = x; this.y = y;
	width = w; height = h;
	color = c;
    }
    public void setSize(int w, int h){
	width = w; height = h;
    }
    public void setLocation(int x, int y){
	this.x = x; this.y = y;
    }
    public void reshape(int x1, int y1, int x2, int y2){
	int newx = Math.min(x1,x2);
	int newy = Math.min(y1,y2);
	int neww = Math.abs(x1-x2);
	int newh = Math.abs(y1-y2);
	setLocation(newx, newy);
	setSize(neww, newh);
    }
    abstract public void draw(Graphics g);
}

class RectangleFigure extends Figure {
    public RectangleFigure(int x, int y, int w, int h, Color c){
	super(x,y,w,h,c);
	//引数つきのコンストラクタは継承されない
    }
    public void draw(Graphics g){
	g.setColor(color);
	g.drawRect(x,y,width,height);
    }
}

class DrawModel extends Observable{
    protected ArrayList<Figure> fig;
    protected Figure drawingFigure;
    protected Color currentColor;
    public DrawModel(){
	fig = new ArrayList<Figure>();
	drawingFigure = null;
	currentColor = Color.red;
    }
    public ArrayList<Figure> getFigures(){
	return fig;
    }
    public Figure getFigure(int idx){
	return fig.get(idx);
    }
    public void createFigure(int x, int y){
	Figure f = new RectangleFigure(x,y,0,0,currentColor);
	fig.add(f);
	drawingFigure = f;
	setChanged();
	notifyObservers();
    }
    public void reshapeFigure(int x1, int y1, int x2, int y2){
	if(drawingFigure != null){
	    drawingFigure.reshape(x1,y1,x2,y2);
	    setChanged();
	    notifyObservers();
	}
    }
}
/////////////////////////////////////////////////////////////

class ViewPanel extends JPanel implements Observer{
    protected DrawModel model;
    public ViewPanel(DrawModel m,DrawController c){
	this.setBackground(Color.white);
	this.addMouseListener(c);
	this.addMouseMotionListener(c);
	model = m;
	model.addObserver(this);
    }
    public void paintComponent(Graphics g){
	super.paintComponent(g);
	for(Figure f:model.getFigures()){
	    f.draw(g);
	}
    }
    public void update(Observable o, Object arg){
	repaint();
    }
}

////////////////////////////////////////////////////////

class DrawController implements MouseListener,MouseMotionListener{
    protected DrawModel model;
    protected int dragStartX, dragStartY;
    public DrawController(DrawModel a){
	model = a;
    }
    public void mouseClicked(MouseEvent e){}
    public void mousePressed(MouseEvent e){
	dragStartX = e.getX(); dragStartY = e.getY();
	model.createFigure(dragStartX, dragStartY);
    }
    public void mouseDragged(MouseEvent e){
	model.reshapeFigure(dragStartX, dragStartY, e.getX(), e.getY());
    }
    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseMoved(MouseEvent e){}
}

/////////////////////////////////////////////////////////
class DrawFrame extends JFrame{
    DrawModel model;
    ViewPanel view1, view2;
    DrawController cont;
    public DrawFrame(){
	model = new DrawModel();
	cont = new DrawController(model);
	this.setLayout(new GridLayout(2,2));
	for(int i = 0;i < 4;i++){
	    view1 = new ViewPanel(model, cont);
	    view1.setBorder(new LineBorder(Color.blue, 3));
	    this.add(view1);
	}
	this.setBackground(Color.black);
	this.setTitle("Draw Editor");
	this.setSize(500,500);
	this.add(view1);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
    }
    public static void main(String argv[]){
	new DrawFrame();
    }
}
