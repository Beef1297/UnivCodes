import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Radix extends JFrame implements ActionListener {
    private JFrame f;
    private JTextField hex,dec;
    private JLabel hexLabel,decLabel;
      
    public Radix() {
      hex = new JTextField(3);
      dec = new JTextField(3);
      this.setLayout(new GridLayout(2,2));
      this.add(new JLabel("16"));
      this.add(hex);
      this.add(new JLabel("10"));
      this.add(dec);
      this.pack();
      hex.addActionListener(this);
      dec.addActionListener(this);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
      if (e.getSource()==hex)      {} // JTextFied hex に対する処理
      else if (e.getSource()==dec) {} // JTextFied dec に対する処理
    }
    public static void main(String argv[]) {
      new Radix();
    }
}
