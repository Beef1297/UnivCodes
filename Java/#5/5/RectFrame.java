import javax.swing.*;
import java.awt.*;

class RectPanel extends JPanel {
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.ORANGE);
    g.fillRect(150,150,200,200);
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
