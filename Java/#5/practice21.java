import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ButtonPanel extends JPanel implements ActionListener{
    //implements をしないといけない ButtonPanel のactionPerformed を呼び出すから
    private JLabel label;
    private JButton b1, b2, b3;

    public ButtonPanel(){
	b1 = new JButton("Button 1");
	b2 = new JButton("Button 2");
	b3 = new JButton("Button 3");
	label = new JLabel("0", JLabel.CENTER);
	
	b1.addActionListener(this);
	b2.addActionListener(this);
	b3.addActionListener(this);

	setLayout(new GridLayout(2,2));
	add(b1); add(b2); add(b3); add(label);
    }
    public void  actionPerformed(ActionEvent e){
	if(e.getSource() == b1)label.setText("1");
	else if(e.getSource() == b2)label.setText("2");
	else if(e.getSource() == b3)label.setText("3");
    }
    /*
    class Button1Listener implements ActionListener {
	public void actionPerformed(ActionEvent e){
	    label.setText("1");
	}
    }
    class Button2Listener implements ActionListener {
	public void actionPerformed(ActionEvent e){
	    label.setText("2");
	}
    }
    class Button3Listener implements ActionListener {
    public void actionPerformed(ActionEvent e){
	label.setText("3");
	    }
	}
    */
}
class TestButtongetSource extends JFrame{
    public TestButtongetSource(){
	ButtonPanel b = new ButtonPanel();
	this.setTitle("TestButton_getSource");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.add(b);
	this.pack();
	this.setVisible(true);
    }
    public static void main(String argv[]){
	new TestButtongetSource();
    }
}
