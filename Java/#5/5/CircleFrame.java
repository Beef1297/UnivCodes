import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class CirclePanel extends JPanel implements MouseListener {
  private int   radius = 5;
  private Color color  = Color.red;
  private int   x[],y[],r[];
  private Color c[];
  private int   num=0;
  final static int MAX=5000;
  
  public CirclePanel(){
    x=new int[MAX];
    y=new int[MAX];
    r=new int[MAX];
    c=new Color[MAX];
    this.addMouseListener(this);
  }
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for(int i=0;i<num;i++){
      g.setColor(c[i]);
      g.fillOval(x[i]-r[i],y[i]-r[i],r[i]*2,r[i]*2);
    }
  }
  private void addCircle(int x0,int y0){
    if (num>=MAX) return; 
    x[num]=x0; y[num]=y0;
    r[num]=radius; c[num]=color;
    num++;
    this.repaint();
  }
     
  public void mousePressed(MouseEvent e) {
     addCircle(e.getX(),e.getY());
  }
  public void mouseClicked(MouseEvent e) { }
  public void mouseReleased(MouseEvent e){ }
  public void mouseEntered(MouseEvent e) { }
  public void mouseExited(MouseEvent e)  { }
}

class CircleFrame extends JFrame {
   private CirclePanel panel;
   public CircleFrame(){
      this.setTitle("CircleFrame");
      this.setSize(500,500);
      panel=new CirclePanel();
      this.add(panel);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);
    }

    public static void main(String argv[]) {
      new CircleFrame();  
   }
}
