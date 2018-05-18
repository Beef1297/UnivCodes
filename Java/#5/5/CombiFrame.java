import java.awt.*;
import javax.swing.*;

class CombiFrame extends JFrame {
  public CombiFrame(){
    JPanel  p1=new JPanel(),p2=new JPanel();
    JButton b1=new JButton("button 1"),b2=new JButton("button 2");
    JButton b3=new JButton("button 3"),b4=new JButton("button 4");
    JButton b5=new JButton("button 5");
    JTextArea t=new JTextArea(10,20);
    this.setTitle("Panel Combination");
    p1.setLayout(new GridLayout(3,1));
    p2.setLayout(new GridLayout(2,1));
    p1.add(b1); p1.add(b2); p1.add(b3);
    p2.add(b4); p2.add(b5);
    this.add(p1,BorderLayout.WEST);
    this.add(p2,BorderLayout.EAST);
    this.add(t,BorderLayout.CENTER);
    this.pack(); 
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }
  public static void main(String argv[]) {
    new CombiFrame();
  }
}
