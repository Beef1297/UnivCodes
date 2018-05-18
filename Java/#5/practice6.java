import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

class ColorPanel extends JPanel{
  
    public ColorPanel(){
	this(Color.white);	
    }
    public ColorPanel(Color c){
	setColor(c);
	setPreferredSize(new Dimension(200,150));
    }
    public void setColor(Color c){
	setBackground(c);
    }
    public Color getColor(){
	return getBackground();
    }
}

    
class ColorFrame extends JFrame implements ChangeListener,ActionListener{
    //field
    JSlider red,green,blue;
    JLabel l1,l2,l3;
    ColorPanel c;
    JTextField rhex,ghex,bhex;
    JTextField colorcode;
    int bits = 255;
    
    public ColorFrame(){
	Color initColor = new Color(0,0,0);
	c = new ColorPanel(initColor);
	this.add(c,BorderLayout.CENTER);
	/*以下それぞれの色のスライダーを作る*/
	/*-----------red-------------*/
	red = new JSlider(JSlider.HORIZONTAL,0,255,0);
	l1 = new JLabel();
	l1.setText("RED ");
	red.setPaintTicks(true);
	red.setMajorTickSpacing(50);
	red.setMinorTickSpacing(10);
	red.setLabelTable(red.createStandardLabels(50));
	red.setPaintLabels(true);
	red.addChangeListener(this);
	/*--------------------------*/
	
	/*-^-^-^-^-^green^-^-^-^-*/
	green = new JSlider(JSlider.HORIZONTAL,0,255,0);
	l2 = new JLabel();
	l2.setText("GREEN ");
	green.setPaintTicks(true);
	green.setMajorTickSpacing(50);
	green.setMinorTickSpacing(10);
	green.setLabelTable(green.createStandardLabels(50));
	green.setPaintLabels(true);
	green.addChangeListener(this);
	/*^-^-^-^-^-^-^-^-^-^-^-^-^*/
	
	/*-^-^-^-^-^blue^-^-^-^-^-^*/
	blue = new JSlider(JSlider.HORIZONTAL,0,255,0);
	l3 = new JLabel();
	l3.setText("BLUE ");
	blue.setPaintTicks(true);
	blue.setMajorTickSpacing(50);
	blue.setMinorTickSpacing(10);
	blue.setLabelTable(blue.createStandardLabels(50));
	blue.setPaintLabels(true);
	blue.addChangeListener(this);
	/*-^^-^-^-^-^-^-^-^-^-^-^-^*/
	/*ここで16進数を表示するTextFieldを作る*/
	rhex = new JTextField("0",3); rhex.addActionListener(this);
	ghex = new JTextField("0",3); ghex.addActionListener(this);
	bhex = new JTextField("0",3); bhex.addActionListener(this);
	rhex.setActionCommand("red");
	ghex.setActionCommand("green");
	bhex.setActionCommand("blue");
	/*16進数コードを入力して色に反映できるようにするための
	  TextFieldの部分*/
	colorcode = new JTextField(5);
	colorcode.setActionCommand("code");
	JPanel q = new JPanel();
	q.setLayout(new FlowLayout(10));
	q.add(colorcode);
	q.setPreferredSize(new Dimension(70,20));
	colorcode.addActionListener(this);
	this.add(q,BorderLayout.WEST);
	/*panel を GrideLayout として 上の3つはREDに関する..
	  といったようにに配置した.*/
	JPanel p = new JPanel();
	p.setLayout(new GridLayout(3,3));
	p.add(l1); p.add(red);  p.add(rhex);
	p.add(l2); p.add(green);p.add(ghex);
	p.add(l3); p.add(blue); p.add(bhex);
	this.add(p,BorderLayout.SOUTH);
	
	
	this.setTitle("TestColor");
	this.pack();
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);


    }
    public void stateChanged(ChangeEvent e){
	if(e.getSource() == red){
	    l1.setText("RED ");
	    rhex.setText(Integer.toString(red.getValue(),16));
	}else if(e.getSource() == green){
	    l2.setText("GREEN ");
	    ghex.setText(Integer.toString(green.getValue(),16));
	}else if(e.getSource() == blue){
	    l3.setText("BLUE ");
	    bhex.setText(Integer.toString(blue.getValue(),16));
	}
	c.setColor(new Color(red.getValue(),green.getValue(),blue.getValue()));
	//それぞれのsliderから値をとってきて,rbgを指定するコンストラクタ
	//にぶち込んだ.
    }
    public void actionPerformed(ActionEvent e){
	String es = e.getActionCommand();
	
	if(es.equals("red")){
	    System.out.println("entered");
	    red.setValue(Integer.parseInt(rhex.getText(),16));
	}else if(es.equals("green")){
	    green.setValue(Integer.parseInt(ghex.getText(),16));
	}else if(es.equals("blue")){
	    blue.setValue(Integer.parseInt(bhex.getText(),16));
	}else if(es.equals("code")){
	    /*16進数6桁の数を10進数に変換し255とAND処理をする
	      そのあと右に8bitシフトして..を3回やればb,g,r順で
	      指定された数がわかる
	    */
	    int n[],dec;
	    n = new int[3];
	    dec = Integer.parseInt(colorcode.getText(),16);
	    for(int i = 0;i < 3;i++){
		n[i] = bits & dec;
		dec = dec >> 8;
	    }
	    red.setValue(n[2]);
	    green.setValue(n[1]);
	    blue.setValue(n[0]);
	    //ただ6桁以上入力された場合のerror処理が実装されてない
	    //TextFieldの入力で6桁以上入力できないようにしたかったがいい方法
	    //が見つからなかった.今後見つけたい.
	}
    }
	
    
    public static void main(String argv[]){
	new ColorFrame();
	
    }
}
