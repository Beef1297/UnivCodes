import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Radix extends JFrame implements ActionListener{
    private JFrame f;
    private JTextField hex,dec;
    
    public Radix(){
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
    
    public void actionPerformed(ActionEvent ev){
	if(ev.getSource() == hex) {
	    dec.setText(Integer.toString(Integer.parseInt(hex.getText(),16),10));//setText(セットする文字列-(数を文字列として10進数に変換--(getTextで取り出した文字列を16進数として数に変換)))
	}
	else if(ev.getSource() == dec) {
	    hex.setText(Integer.toString(Integer.parseInt(dec.getText(),10),16));//上と同じことを10,16逆にしてするだけ
	}
    }
    public static void main(String argv[]){
	new Radix();
    }
}
	
		 
