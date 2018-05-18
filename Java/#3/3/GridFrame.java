import javax.swing.*;
import java.awt.*;

class GridFrame extends JFrame {
    public GridFrame(){
      this.setSize(300,200);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setLayout(new GridLayout(3,2));
      for(int i=1;i<=6;i++)
        add(new JButton(i+""));
      this.setVisible(true);
    }

    public static void main(String argv[]) {
      new GridFrame();
   }
}
