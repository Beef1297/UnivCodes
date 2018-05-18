import java.awt.*;
import javax.swing.*;
 
class CombiFrame extends JFrame {
    public CombiFrame(){
	// JPanelを２つ生成
	JPanel  p1=new JPanel(),p2=new JPanel();
	// JPanelに貼付けるためのJButton を 5つ生成
	JButton b1 = new JButton("button 1"), b2 = new JButton("button 2");
	JButton b3 = new JButton("button 3"), b4 = new JButton("button 4");
	JButton b5 = new JButton("button 5");
	// JFrame のCENTER に貼付けるための複数行の文字入力の部品
	// の JTextArea を生成
	JTextArea t = new JTextArea(10, 20);
	this.setTitle("Panel Combination");
	//2つのJPanelをそれぞれ, 3x1, 2x1 のGridLayoutに設定.
	p1.setLayout(new GridLayout(3,1));
	p2.setLayout(new GridLayout(2,1));
	// ヴォタンををそれぞれのJPanel に貼付け
	p1.add(b1); p1.add(b2); p1.add(b3);
	p2.add(b4); p2.add(b5);
	
	// JButtonが３つ貼付いたJPanelを左,一方を右に
	this.add(p1,BorderLayout.WEST);
	this.add(p2,BorderLayout.EAST);
	// 真ん中は, JTextArea
	this.add(t,BorderLayout.CENTER);
	
	// pack は JFrameのサイズを自動設定するメソッド
	// this.setSize(300,200); などの代わり
	this.pack();
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
    }
    public static void main(String argvb[]){
	new CombiFrame();
    }
}
