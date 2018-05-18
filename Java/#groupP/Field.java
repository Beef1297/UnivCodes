//package main;

import java.awt.*;
import javax.swing.*;

/*
 *フィールド作成0はぷよの移動できるマス、1は壁
 */
public class Field {

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

	Field() {
		FieldRows = 12;
		FieldColumns = 16;
		color[0] = new Color(0, 0, 0); // 黒
		color[1] = new Color(0, 150, 51); // 壁色
		color[2] = new Color(90, 90, 90); // 灰色

		ScreenW = 480;
		ScreenH = 640;

		aSIZE = 40;

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 終了処理
		frame.setBounds(100, 70, 480, 640);//
		frame.setTitle("ぷよぷよ");
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

		bg.setColor(Color.black);
		bg.fillRect(0, 0, ScreenW, ScreenH);

		String Text1 = "ぷよぷよ";
		String Text2 = "Push Enter";
		FontMetrics fm = bg.getFontMetrics();
		Rectangle rectText1 = fm.getStringBounds(Text1, fg).getBounds();
		Rectangle rectText2 = fm.getStringBounds(Text2, fg).getBounds();

		int pTextX1 = 200 - (rectText1.width / 2);
		int pTextX2 = 200 - (rectText2.width / 2);

		Font font = new Font("Arial", 0, 30);
		fg.setFont(font);
		fg.setColor(Color.black);
		fg.drawString(Text1, pTextX1, 280);
		fg.drawString(Text2, pTextX2, 360);

		frame.addKeyListener(new Key());
	}

	/********************************************************
	 * セッター、ゲッター
	 *****************************************************/

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

		Field = new int[FieldRows][FieldColumns];
		for (int col = 0; col < FieldColumns; col++) {
			Field[0][col] = 1;// 左の壁
			for (int row = 1; row < FieldRows - 1; row++) {
				Field[row][0] = 1;
				if (row < 7) {
					Field[row][1] = 1;
					Field[row][2] = 1;
				}
				if (row > 7)
					Field[row][5] = 1;
				if (row > 7 && row < 12 && col > 5 && col < FieldColumns - 1)
					Field[row][col] = 2;
				else
					Field[row][col] = 0;// 空白
			}
			Field[7][col] = 1;// 右の壁
			Field[11][col] = 1;// 右の壁
		}
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
					bg.setColor(color[2]);
					bg.fillRect(aSIZE * row + 2, aSIZE * col + 2, aSIZE - 1, aSIZE - 1);
				} else if (Field[row][col] == 1) {
					bg.setColor(color[1]);
					bg.fillRect(aSIZE * row + 2, aSIZE * col + 2, aSIZE - 1, aSIZE - 1);
				}

			}
		}

	}
}
