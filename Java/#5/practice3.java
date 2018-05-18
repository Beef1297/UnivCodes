import javax.swing.*;
import java.awt.*;
 
class RectPanel extends JPanel {
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Color c = new Color(200,50,150);
    //colorがクラスならオブジェクトをつくってメソッドで色をきめられるのでは?
    //と考えた.
    g.setColor(c);
    g.fillRoundRect(50,50,200,200,100,100);
    g.draw3DRect(300,300,100,100,false);
  }
}
class RectFrame extends JFrame {
    public RectFrame(){
      this.setTitle("RectFrame");
      this.setSize(500,500);
      this.add(new RectPanel());
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);
    }
    public static void main(String argv[]) {
      new RectFrame();
   }
}
