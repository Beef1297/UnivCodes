import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ButtonPanel extends JPanel {
    private JLabel label;
    private JButton b1, b2, b3;

    public ButtonPanel(){
	b1 = new JButton("Button 1");
	b2 = new JButton("Button 2");
	b3 = new JButton("Button 3");
	label = new JLabel("0", JLabel.CENTER);

	Button1Listener bl1 = new Button1Listener();
	Button2Listener bl2 = new Button2Listener();
	Button3Listener bl3 = new Button3Listener();
	
	b1.addActionListener(bl1);
	b2.addActionListener(bl2);
	b3.addActionListener(bl3);

	setLayout(new GridLayout(2,2));
	add(b1); add(b2); add(b3); add(label);
    }
    
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
}
class TestButton extends JFrame{
    public TestButton(){
	ButtonPanel b = new ButtonPanel();
	this.setTitle("TestButton");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.add(b);
	this.pack();
	this.setVisible(true);
    }
    public static void main(String argv[]){
	new TestButton();
    }
}
