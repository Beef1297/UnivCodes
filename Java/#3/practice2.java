import javax.swing.*;
import java.awt.*;

class LabelFrame extends JFrame{
    public LabelFrame(){
	this.setSize(300,200);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	JButton b = new JButton("!!CLICK!!");
	this.add(b,BorderLayout.SOUTH);
	JLabel l = new JLabel("<html><span style = 'font-size:24pt; color:orange;"+"font-family:Courier; background-color:#cd853f;'> RION-> </span></html>",JLabel.LEFT);
	this.add(l, BorderLayout.WEST);
	JLabel c = new JLabel("<html><span style = 'font-size:12pt; color: #d2691e;"+"font-family:fantasy; background-color:#ffc0cb;'>  [[!!Gawow!!]] </span></html>",JLabel.CENTER);//ライオンの鳴き声を表現
	this.add(c,BorderLayout.CENTER);
	JLabel i = new JLabel("<html><img src='file:lion.jpg' width = 100 height = 100></html>",JLabel.CENTER);
	this.add(i,BorderLayout.EAST);
	this.setVisible(true);
    }
    public static void main(String argv[]){
	new LabelFrame();
    }
}
