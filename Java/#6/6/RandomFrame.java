import javax.swing.*;
import java.awt.*;
import java.util.*;

class Figure {
  protected int x,y,size;
  Figure() {
    x = (int)(Math.random()*450);
    y = (int)(Math.random()*450);
    size=(int)(Math.random()*30+20);
  }
  void draw(Graphics g) {}
}

class Circle extends Figure {
  void draw(Graphics g) {
    g.setColor(Color.RED);
    g.drawOval(x,y,size,size);
  }
}

class Box extends Figure {
  void draw(Graphics g) {
    g.setColor(Color.BLUE);
    g.drawRect(x,y,size,size);
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
    }
  }
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for(Figure f: fig){
      f.draw(g); 
    }
  }
}

class RandomFrame extends JFrame {
    public RandomFrame(){
      this.setTitle("Random Frame");
      this.setSize(500,530);
      this.add(new RandomPanel());
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);
    }
    public static void main(String argv[]) {
      new RandomFrame();
   }
}
