//package main;
/*add
変更した部分(牛)
FieldRows を 8 FieldColumns を 10にした.
aSIZEを60にした.
壁やPuyoの領域のフィールドを変更した
  Puyo領域を5x4にした
  ->Puyo領域を6x5にした(1/14)
壁の色がグラデーションになるようにした
***/
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/*
 *フィールド作成0はぷよの移動できるマス、1は壁
 */
class Field extends JFrame implements ActionListener {

    public int Field[][];
    private int FieldRows;// 横
    private int FieldColumns;// 縦


    protected Color[] color = new Color[3];
    protected JFrame frame = new JFrame();
    protected JPanel panel = new JPanel();
    protected Image image;
    protected int ScreenW; // 画面幅
    protected int ScreenH; // 画面高さ
    final protected int aSIZE;
    public Container cont;

    public Graphics fg;
    protected Graphics bg;
    /*add*/
    protected Graphics str;
    private int r,g,b;
    private boolean udr = true,udg = true,udb = true;
    private int count = 0;
    static int dnum = 10;
    private JTextField num;
    private JButton start;
    private JButton Level1;
    private JButton Level2;
    private JButton Level3;
    private JButton Level4;
    static int level = 0;
    static int judge = 0;
   /*********/
    Field() {
	FieldRows = 9;// よこ
	FieldColumns = 11;//16;// たて
	/* 作業するfieldを4x5にする */
	color[0] = new Color(0, 0, 0); // 黒
	color[1] = new Color(0, 150, 51); // 壁色
	color[2] = new Color(90, 90, 90); // 灰色
  /*add*/
  r = 30; g = 20; b = 15;
  JPanel Level = new JPanel();
	JPanel Num = new JPanel();
	JPanel Start = new JPanel();

  num = new JTextField(3);
	start = new JButton("Start");
	Level1 = new JButton("やさしい");
	Level2 = new JButton("ふつう");
	Level3 = new JButton("難しい");
	Level4 = new JButton("ゲキムズ");
	num.addActionListener(this);
	start.addActionListener(this);
	Level1.addActionListener(this);
	Level2.addActionListener(this);
	Level3.addActionListener(this);
	Level4.addActionListener(this);
  /******/


	ScreenW = 540;
	ScreenH = 660;
  /*Frame: 9x11*/
	aSIZE = 60;

	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 終了処理
	frame.setSize(ScreenW,ScreenH);
	frame.setLocationRelativeTo(null);
	frame.setTitle("puyopuyo");
	frame.setResizable(false);// サイズ変更不可
	frame.setVisible(true);

	// コンテンツペインの設定
	cont = frame.getContentPane();
	cont.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
	panel.setPreferredSize(new Dimension(ScreenW, ScreenH));
	cont.add(panel);
	frame.pack();// フレームサイズの最適化

	image = panel.createImage(ScreenW, ScreenH);
	fg = panel.getGraphics();
	bg = image.getGraphics();
	str = image.getGraphics();//add


	bg.setColor(Color.black);
	bg.fillRect(0, 0, ScreenW, ScreenH);

  /**add**/
	Level.setLayout(new GridLayout(1,5));
	Level.add(new JLabel("Select Level"));
	Level.add(Level1);
	Level.add(Level2);
	Level.add(Level3);
	Level.add(Level4);

	Num.setLayout(new GridLayout(1,2));
	Num.add(new JLabel("Delete number"));
	Num.add(num);

	Start.setLayout(new GridLayout(1,2));
	Start.add(new JLabel("Game Start"));
	Start.add(start);

	panel.add(Level);
	panel.add(Num);
	panel.add(Start);
  /*****************************/
  /*
	String Text1 = "ぷよぷよ";
	String Text2 = "Push Enter";
	FontMetrics fm = bg.getFontMetrics();
	Rectangle rectText1 = fm.getStringBounds(Text1, fg).getBounds();
	Rectangle rectText2 = fm.getStringBounds(Text2, fg).getBounds();

	int pTextX1 = 150- (rectText1.width / 2);
	int pTextX2 = 150 - (rectText2.width / 2);

	Font font = new Font("Arial", 0, 60);
	fg.setFont(font);
	fg.setColor(Color.black);
	fg.drawString(Text1, pTextX1, 280);
	fg.drawString(Text2, pTextX2, 360);

	frame.addKeyListener(new Key());
  */
    }

    /*add*************************/
    public void actionPerformed(ActionEvent e) {
  if(e.getSource()==num) {
      String str = num.getText();
      dnum = Integer.parseInt(str,10);
      //System.out.println("numOK");
      num.setEnabled(false);
      num.removeActionListener(this);
  }
  if (e.getSource()==start) {
      if (Key.GameStage == 0 || Key.GameStage == 3){
    Key.GameStage = 1;
    judge=1;
      }
      start.setEnabled(false);
      start.removeActionListener(this);
  }
  if (e.getSource()==Level1) {
      level=1;
  }
  if (e.getSource()==Level2) {
      level=2;
  }
  if (e.getSource()==Level3) {
    level=3;
  }
  if (e.getSource()==Level4) {
    level=4;
  }
}
/*********************************************/

	/********************************************************
	 * セッター、ゲッター
	 *****************************************************/
   /*add*/
   public int getjudge(){
     return judge;
   }
   /*******/

    public int getFieldRows() {
	return FieldRows;
    }

    public void setFieldRows(int fieldRows) {
	FieldRows = fieldRows;
    }

    public int getFieldColumns() {
	return FieldColumns;
    }

    public void setFieldColumns(int fieldColumns) {
	FieldColumns = fieldColumns;
    }

    public Color[] getColor() {
	return color;
    }

    public void setColor(Color[] color) {
	this.color = color;
    }

    /****************************
    * フィールドのセット
    ***************************/
    public void setField() {
      /**add**/
	Level1.setEnabled(false);
    	Level2.setEnabled(false);
    	Level3.setEnabled(false);
    	Level4.setEnabled(false);
    	Level1.removeActionListener(this);
    	Level2.removeActionListener(this);
    	Level3.removeActionListener(this);
    	Level4.removeActionListener(this);
    	//	num.setEnabled(false);
    	frame.addKeyListener(new Key());
    	frame.requestFocus();
      /***********************/

      Field = new int[FieldRows][FieldColumns];
      /**+*++*++*++*+*+*++変えた**********************/
      for (int col = 0; col < FieldColumns; col++) {
        Field[0][col] = 1;// 左の壁

        for (int row = 1; row < FieldRows - 1; row++) {
          //Field[row][0] = 1;//天井
          Field[row][3] = 1;
          if (row > 4 && row < 8 && col >= 0 && col < 4)
          Field[row][col] = 2;
          else
          Field[row][col] = 0;// 空白
        }
        if(col < 4){
          Field[4][col] = 1;
        }
        if(col > 3){
          Field[1][col] = 1;
          Field[7][col] = 1;
        }
        Field[8][col] = 1;//右の壁
      }
      /******************************************/
      for (int row = 0; row < FieldRows; row++) {
        Field[row][FieldColumns - 1] = 1; // 底
      }

    }

    /**************************************************************
     * フィールド描画
     **************************************************************/
    public void drawField() {
	for (int col = 0; col <= FieldColumns - 1; col++) {
	    for (int row = 0; row <= FieldRows - 1; row++) {
		if (Field[row][col] == 0) {
      /*変えた*/
		    bg.setColor(color[2]);
		    bg.fillRect(aSIZE * row + 2, aSIZE * col + 2, aSIZE - 1, aSIZE - 1);
		    bg.drawRect(aSIZE * row + 2, aSIZE * col + 2, aSIZE - 1, aSIZE - 1);
        /***/
    } else if (Field[row][col] == 1) {
		    //bg.setColor(color[1]);
        /*kaeta*/
		    bg.setColor(new Color((col+r) * 5, (row+g) * 7, (col+b) * 5).brighter());
		    bg.fillRect(aSIZE * row + 2, aSIZE * col + 2, aSIZE - 1, aSIZE - 1);
		    bg.drawRect(aSIZE * row + 2, aSIZE * col + 2, aSIZE - 1, aSIZE - 1);
		    /***/
		}

	    }
	}
	/********add*********/

	if(count >= 2){
	    if(udr)r++;
	    else r--;
	    if(udg)g++;
	    else g--;
	    if(udb)b++;
	    else b--;
	    if(r <= 0)udr = true;
	    else if(r >= 30)udr = false;
	    if(g <= 0)udg = true;
	    else if(g >= 20)udg = false;
	    if(b <= 0)udb = true;
	    else if(b >= 15)udb = false;
	    count = 0;
	}
	count ++;
	/********************/
    }
}
