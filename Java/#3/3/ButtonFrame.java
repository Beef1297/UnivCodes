import javax.swing.*;
import java.awt.*;

class ButtonFrame extends JFrame {
    public ButtonFrame(){
      this.setSize(300,200);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JButton b = new JButton("OK");
      this.add(b,BorderLayout.SOUTH);
      this.setVisible(true);
    }

    public static void main(String argv[]) {
      new ButtonFrame();
   }
}
