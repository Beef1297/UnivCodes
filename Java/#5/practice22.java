import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ButtonPanel extends JPanel implements ActionListener{
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
	b1.setActionCommand("1");
	b2.setActionCommand("2");
	b3.setActionCommand("3");
	//これはこれでボタンの数だけ書かないといけないのは手間だと感じる
	

	setLayout(new GridLayout(2,2));
	add(b1); add(b2); add(b3); add(label);
    }
    public void actionPerformed(ActionEvent e){
	String es = e.getActionCommand();
	label.setText(es);
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
class TestButtongetCommand extends JFrame{
    public TestButtongetCommand(){
	ButtonPanel b = new ButtonPanel();
	this.setTitle("TestButton_getCommand");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.add(b);
	this.pack();
	this.setVisible(true);
    }
    public static void main(String argv[]){
	new TestButtongetCommand();
    }
}
