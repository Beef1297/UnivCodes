import java.awt.*;
import javax.swing.*;
 
class KAIHENFrame extends JFrame {
    final static int MAX = 20;
    private String str;
    public KAIHENFrame(){
	
	// JPanelを２つ生成
	JPanel  p1=new JPanel(),p2=new JPanel();
	//JButton b[] = new JButton[MAX];
	// JPanelに貼付けるためのJButton を 5つ生成
	p1.setLayout(new GridLayout(5,1));
	p2.setLayout(new GridLayout(1,10));
	
	for(int i=1;i<=15;i++){
	    str = ""+i;
	    if(i <= 10){
		p2.add(new JButton(str));
	    }else {
		p1.add( new JButton("<html><div style = background-color:"+
				    "yellow;>"+str+"</div></html>"));
	    }
	}
	
	// JFrame のCENTER に貼付けるための複数行の文字入力の部品
	// の JTextArea を生成
	JTextArea t = new JTextArea(15, 27);
	this.setTitle("Panel Combination");;
	
	// JButtonが３つ貼付いたJPanelを左,一方を右に
	this.add(p1,BorderLayout.WEST);
	this.add(p2,BorderLayout.NORTH);
	// 真ん中は, JTextArea
	this.add(t,BorderLayout.CENTER);
	
	// pack は JFrameのサイズを自動設定するメソッド
	// this.setSize(300,200); などの代わり
	this.pack();
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
    }
    public static void main(String argv[]){
	new KAIHENFrame();
    }
}
