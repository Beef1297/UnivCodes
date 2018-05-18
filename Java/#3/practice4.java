import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class HelloFrame extends JFrame implements ActionListener {
    private JLabel label;
    
    public HelloFrame(){
	this.setSize(300,200);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	JButton b = new JButton("GYARUMIKUJI");
	this.add(b, BorderLayout.SOUTH);
	this.add(new JLabel("鬼エモい=大吉,あげぽよ=吉,ガン萎え=大凶"), BorderLayout.NORTH);
	label = new JLabel(" ",JLabel.CENTER);
	this.add(label,BorderLayout.CENTER);
	b.addActionListener(this);
	this.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ev) {
	double r = Math.random();
	if(r > 0.7)label.setText("<html><span style = 'font-size:70pt; color:#ff00ff; font-family:Impact;'>鬼エモい</span></html>");
	else if(r <= 0.2)label.setText("<html><span style = 'font-size:60pt; color:#00008b; font-family: serif;'>ガン萎え</span></html>");
	else label.setText("<html><span style = 'font-size: 40pt; color:orange; font-family:Croobie;'>あげぽよ</span></html>");//htmlの文を毎回セットされるようにすることでフォントの変換などを行うようにした。ただ雛形は3つ同じため,もう少しきれいに書く方法はないのだろうか...
	/* 注釈:鬼エモい = 大吉, あげぽよ = 吉, ガン萎え = 大凶 */
    }
    public static void main(String argv[]){
	new HelloFrame();
    }
}
