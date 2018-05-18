import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
class CirclePanel extends JPanel implements MouseListener {
    private int radius =5;        // 円の半径
    private Color color=Color.red; // 円の色
    private int   x[],y[],r[];     // すべての丸の位置と半径を記録する位置を配列
    private Color c[]; // 丸の色を記録するColorクラス型の配列
    private int   num=0;           // 今まで記録した丸の数
    final static int MAX=5000;     // 記録できる丸の最大数 (定数)
    
    public CirclePanel(){
	x=new int[MAX];
	y=new int[MAX];
	r=new int[MAX];
	c=new Color[MAX];
	this.addMouseListener(this);
    }
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	for(int i=0;i<num;i++){
	    g.setColor(c[i]);
	    g.fillOval(x[i]-r[i],y[i]-r[i],r[i]*2,r[i]*2);
	}
    }
    public void setRadius(int r){
	radius = r;
    }
    public void setPanelColor(Color c){
	color = c;
    }
    
    private void addCircle(int x0,int y0){
	if (num>=MAX) return; 
	x[num]=x0; y[num]=y0;
	r[num]=radius; c[num]=color;
	num++;
	this.repaint();
    }
    
    public void mousePressed(MouseEvent e) {
	addCircle(e.getX(),e.getY());
    }
    public void mouseClicked(MouseEvent e) { }
    public void mouseReleased(MouseEvent e){ }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e)  { }
}

class CircleFrame extends JFrame implements ActionListener {
    private CirclePanel panel;
    private JButton b1,b2,b3,b4,b5;
    private JPanel p1 = new JPanel(),p2 = new JPanel();
    public CircleFrame(){
	this.setTitle("CircleFrame");
	this.setSize(500,500);

	p1.setLayout(new GridLayout(3,1));
	p2.setLayout(new GridLayout(2,1));
	b1 = new JButton("YELLOW");
	b2 = new JButton("BLUE");
	b3 = new JButton("GREEN");
	p1.add(b1);p1.add(b2);p1.add(b3);
	b4 = new JButton("5");
	b5 = new JButton("10");
	p2.add(b4);p2.add(b5);
	b1.addActionListener(this);
	b2.addActionListener(this);
	b3.addActionListener(this);
	b4.addActionListener(this);
	b5.addActionListener(this);
	this.add(p1,BorderLayout.WEST);
	this.add(p2,BorderLayout.EAST);
	
	panel=new CirclePanel();
	this.add(panel);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
    }
    public void actionPerformed(ActionEvent ev){
	if(ev.getSource == b1)panel.setPanelColor(Color.YELLOW);
	else if(ev.getSource == b2)panel.setPanelColor(Color.BLUE);
	else if(ev.getSource == b3)panel.setPanelColor(Color.GREEN);
	else if(ev.getSource == b4)panel.Radius(5);
	else if(ev.getSource == b5)panel.Radius(10);
    }
    
    public static void main(String argv[]) {
	new CircleFrame();  
    }
}
