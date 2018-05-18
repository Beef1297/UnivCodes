import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class OmikujiFrame extends JFrame implements ActionListener{
    private JLabel label;
    public OmikujiFrame(){
	this.setTitle("∞OMIKUJI∞");
	this.setSize(300, 200);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	JButton b = new JButton("OMIKUJI");
	this.add(b, BorderLayout.SOUTH);
	label = new JLabel(" ",JLabel.CENTER);
	this.add(label, BorderLayout.CENTER);
	b.addActionListener(this);
	Timer t = new Timer(1000,this);
	t.start();
	this.setVisible(true);
    }
    public void actionPerformed(ActionEvent ev){
	double r = Math.random();
	if(r > 0.7) 
	    label.setText("<html><span style = 'font-size: 50pt; color: red;'>大吉</span></html>");
	else if(r <= 0.2)
	    label.setText("<html><span style = 'font-size: 50pt; color: blue;'>大凶</span></html>");
	else 
	    label.setText("<html><span style = 'font-size: 50pt; color: green;'>吉</span></html>");
    }
    public static void main(String argv[]){
	new OmikujiFrame();
    }
}
