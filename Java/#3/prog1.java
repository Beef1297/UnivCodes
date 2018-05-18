import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class NewFrame extends JFrame implements ActionListener{
    private JLabel label;
	
    public NewFrame (){
	this.setTitle("NewFrame");
	this.setSize(400,200);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	JButton b = new JButton("Hello");
	this.add(b,BorderLayout.SOUTH);
	//JButton っていうオブジェクトを生成してそれをコンストラクタを使って名前をOKに初期化している。
	label = new JLabel(" ",JLabel.CENTER);
	this.add(label,BorderLayout.CENTER);
	b.addActionListener(this);
//bっていうオブジェクトとBorderLayoutっていうクラスのインスタンス変数のSOUTHを引数としてaddメソッドに渡している.
	//JLabel l = new JLabel("I'm JLabel");
	//JLabel l = new JLabel("<html><span style= 'font-size:36pt; color:blue;"+"background-color:yellow;'> JLabel </span></html>",JLabel.CENTER);
	//this.add(l,BorderLayout.CENTER);
	this.setVisible(true);
    }
    public void actionPerformed(ActionEvent ev){
	label.setText("SOOOOOOO COOOOOOOL!!!!");
    }
    public static void main(String argv[]){
    	new NewFrame();
    }
}
