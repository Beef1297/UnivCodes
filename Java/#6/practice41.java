import javax.swing.*;
import java.awt.*;
import java.util.*;

class Figure {
    private final static Color c[] = {new Color(255,99,71), new Color(255,20,147), new Color(255,140, 0), new Color(124,252,0), new Color(0,255,255)};
    //コンストラクタをりよう
    protected Color color;
    protected int x,y,size;
  Figure() {
    x = (int)(Math.random()*450);
    y = (int)(Math.random()*450);
    size=(int)(Math.random()*30+20);
    color = c[(int)(Math.random() * 10) % 5];
  }
  void draw(Graphics g) {}
}

class Circle extends Figure {
  void draw(Graphics g) {
    g.setColor(color);
    g.drawOval(x,y,size,size);
  }
}

class Box extends Figure {
  void draw(Graphics g) {
    g.setColor(color);
    g.drawRect(x,y,size,size);
  }
}
class Cross extends Figure{
    void draw(Graphics g){
	g.setColor(color);
	g.drawLine(x,y,x+size,y-size);
	g.drawLine(x+size,y,x,y-size);
    }
}

class RandomPanel extends JPanel {
  private final static int NUM=50;
  private ArrayList<Figure> fig;
  RandomPanel(){
    fig=new ArrayList<Figure>();
    for(int i=0;i<NUM;i++){
      fig.add(new Box());
      fig.add(new Circle());
      fig.add(new Cross());
    }
  }
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for(Figure f: fig){
      f.draw(g); 
    }
  }
}

class RandomFramex extends JFrame {
    public RandomFramex(){
      this.setTitle("Random Frame");
      this.setSize(500,530);
      this.add(new RandomPanel());
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);
    }
    public static void main(String argv[]) {
      new RandomFramex();
   }
}
