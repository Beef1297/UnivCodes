import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class HelloFrame extends JFrame implements ActionListener { 
  public HelloFrame(){
    this.setSize(300,200);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JButton b = new JButton("Hello");
    this.add(b,BorderLayout.SOUTH);
    b.addActionListener(this);
    this.setVisible(true);
  }
  //  public void actionPerformed(ActionEvent ev) {
  //  System.out.println("Hello");
  //  }
  public static void main(String argv[]) {
    new HelloFrame();
  }
}
