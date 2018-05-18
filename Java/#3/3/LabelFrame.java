import javax.swing.*;
import java.awt.*;

class LabelFrame extends JFrame {
    public LabelFrame(){
      this.setSize(300,200);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JButton b = new JButton("OK");
      this.add(b,BorderLayout.SOUTH);
      JLabel l = new JLabel("JLabel");
      //JLabel l = new JLabel("<html><span style='font-size:36pt; color:blue; " 
      //    +"background-color:yellow;'>JLabel</span></html>",JLabel.CENTER);
      // JLabel l = new JLabel("<html><img src='file:lion.jpg' width=100 height=100></html>",JLabel.CENTER);
      this.add(l,BorderLayout.CENTER);
      this.setVisible(true);
    }

    public static void main(String argv[]) {
      new LabelFrame();
   }
}
