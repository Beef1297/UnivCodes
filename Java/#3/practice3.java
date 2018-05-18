import javax.swing.*;
import java.awt.*;

class GridFrame extends JFrame {
    private int size = 50,color1 = 50,color2 = 255;
    
    public GridFrame(){
	this.setSize(300,200);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLayout(new GridLayout(4,5));
	for(int i = 1;i <= 20;i++){
	    JButton l = new JButton("<html><span style = 'font-size:"+size+"; color:rgb("+color2+",255,"+color1+"); background-color:rgb(255,"+color1+" ,"+color2+");'>HIT</span></html>");//html文のなかで変数を入れることでforループごとに値を変えグラデーションぽくするのに成功した.ただこの方法は変数の数が増えてくると修正などが手間になってくるので他の方法のが望ましいと思う.
	    this.add(l);
	    color1 += 10;
	    color2 -= 20;
	    size += 3;
	}

	this.setVisible(true);
    }
    
    public static void main(String argv[]){
	new GridFrame();
    }
}
