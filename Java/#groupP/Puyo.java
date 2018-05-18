//package main;

import java.util.Random;
import java.awt.*;

public class Puyo extends Field {
    
    Score score = new Score(); // クラスScoreのインスタンス生成
    private int Puyo[][]; // 動くぷよ用の配列
    private int Puyo2[][]; // 動くぷよ用の配列
    public int PuyoField[][] = new int[6][15]; // 当たり判定終了後のぷよの配列
    
    private int PuyoRows; // 幅
    private int PuyoColumns; // 高さ
    public int PuyoX; // ぷよのX位置
    public int PuyoY; // ぷよのY位置
    public int PuyoX2; // ぷよのX位置
    public int PuyoY2; // ぷよのY位置

	private boolean Judge; // falseのとき移動可能、trueのときは不可

	static private boolean Judge2; // trueかつcounter=0のときnewPuyo()を実行
	private int ChainCounter; // 連鎖回数
	private int ChainNum; // 連鎖したかどうかを判定する変数

	static private boolean NextPuyo; // 次のぷよを出す為の判定
	static private boolean NextPuyo2; //

	private int PuyoType1; // 次のぷよの種類（色）
   	private int PuyoType2; // 次のぷよの種類（色）
	public int NextType1; // 表示用の次のぷよの種類（色）
    	public int NextType2; // 表示用の次のぷよの種類（色）
	private int PuyoState; // ぷよの向き用の変数

	static public int DeleteCounter; // 削除したぷよの個数

	private int DeleteArray[][] = new int[6][14];// 削除する為の配列
	private int JudgeCounter[][] = new int[6][14];// 判定を行った回数の配列。０のときのみ判定実行。

	Random rand = new Random(); // ぷよの種類を決めるためのランダム変数

	/**************************************************************
	 * コンストラクタ
	 **************************************************************/
	Puyo() {
		PuyoRows = 6;
		PuyoColumns = 14;
		PuyoState = 2;

		PuyoType1 = rand.nextInt(4) + 1;
		PuyoType2 = rand.nextInt(4) + 1;
		NextType1 = rand.nextInt(4) + 1;
		NextType2 = rand.nextInt(4) + 1;

		PuyoX = 2;
		PuyoY = 1;
		PuyoX2 = 2;
		PuyoY2 = 2;

		DeleteCounter = 0;

		System.out.println("puyoconst");
	}

	/**************************************************************
	 * ぷよの右移動
	 **************************************************************/
	public void moveRight() {
		if (Judge == false) {
			if (PuyoField[PuyoX + 1][PuyoY] == 0 && PuyoField[PuyoX2 + 1][PuyoY2] == 0) {
				++PuyoX;
				++PuyoX2;
			}
		}
	}

	/**************************************************************
	 * ぷよの左移動
	 **************************************************************/
	public void moveLeft() {
		if (Judge == false) {
			if (PuyoField[PuyoX - 1][PuyoY] == 0 && PuyoField[PuyoX2 - 1][PuyoY2] == 0) {
				--PuyoX;
				--PuyoX2;
			}

		}
	}

	/**************************************************************
	 * ぷよの右回転
	 **************************************************************/
	public void turnRight() {
		if (Judge == false) {
			if (PuyoX < PuyoX2 && PuyoY <= 11 && PuyoX < 5 && PuyoField[PuyoX][PuyoY + 1] == 0) {// ぷよ２が１の右側にある時
				--PuyoX2; // 右から下に回転
				++PuyoY2;
				PuyoState = 2;
			} else if (PuyoY < PuyoY2 && PuyoY <= 11 && PuyoX > 0 && PuyoField[PuyoX - 1][PuyoY] == 0) {// 下から左に回転
				--PuyoX2;
				--PuyoY2;
				PuyoState = 3;
			} else if (PuyoX > PuyoX2 && PuyoX2 >= 0 && PuyoField[PuyoX][PuyoY - 1] == 0 && PuyoY > 1) {// 左から上に回転
				++PuyoX2;
				--PuyoY2;
				PuyoState = 4;
			} else if (PuyoY > PuyoY2 && PuyoX < 5 && PuyoField[PuyoX + 1][PuyoY] == 0) {// 上から右に回転
				++PuyoX2;
				++PuyoY2;
				PuyoState = 1;
			}
		}
	}

	/**************************************************************
	 * ぷよの左回転
	 **************************************************************/
	public void turnLeft() {
		if (Judge == false) {
			if (PuyoX < PuyoX2 && PuyoField[PuyoX][PuyoY - 1] == 0 && PuyoY > 1) {// ぷよ２が１の右側にある時
				--PuyoX2; // 右から上に回転
				--PuyoY2;
				PuyoState = 4;
			} else if (PuyoY > PuyoY2 && PuyoX > 0 && PuyoField[PuyoX - 1][PuyoY] == 0) {// 上から左に回転
				--PuyoX2;
				++PuyoY2;
				PuyoState = 3;
			} else if (PuyoX > PuyoX2 && PuyoY <= 11 && PuyoField[PuyoX][PuyoY + 1] == 0) {// 左から下に回転
				++PuyoX2;
				++PuyoY2;
				PuyoState = 2;
			} else if (PuyoY < PuyoY2 && PuyoX < 5 && PuyoField[PuyoX + 1][PuyoY] == 0) {// 下から右に回転
				++PuyoX2;
				--PuyoY2;
				PuyoState = 1;
			}
		}
	}

	/**************************************************************
	 * ぷよの上昇（テスト用）
	 **************************************************************/
	public void moveUp() {
		--PuyoY;
		--PuyoY2;
	}

	/**************************************************************
	 * ぷよの落下
	 **************************************************************/
	public void fallDown() {
		// System.out.println(PuyoState);
		if (PuyoField[PuyoX][PuyoY + 1] == 0 && PuyoY < 12) {

			++PuyoY;
		} else {
			hit1();
		}
		if (PuyoField[PuyoX2][PuyoY2 + 1] == 0 && PuyoY2 < 12) {
			++PuyoY2;
		} else {
			hit2();
		}
	}

	/**************************************************************
	 * ぷよのセット
	 **************************************************************/
	public void setPuyo() {
		Puyo = new int[PuyoRows][PuyoColumns];
		Puyo2 = new int[PuyoRows][PuyoColumns];

		Puyo[PuyoX][PuyoY] = PuyoType1;
		Puyo2[PuyoX2][PuyoY2] = PuyoType2;

	}

	/**************************************************************
	 * ぷよの当たり判定
	 **************************************************************/
	public void hit1() {
		int X, Y;
		if (NextPuyo == false) {
			X = PuyoX;
			Y = PuyoY;
			PuyoField[X][Y] = PuyoType1;
			NextPuyo = true;
			if (Judge == false)
				Judge = true;

		}
		if (PuyoY >= 12) {
			X = PuyoX;
			Y = 12;
			PuyoField[X][Y] = PuyoType1;
			NextPuyo = true;
		}
		if (NextPuyo == true && NextPuyo2 == true) {
			// newPuyo();
			deleteJudge();
		}

	}

	public void hit2() {
		int X2, Y2;
		if (NextPuyo2 == false) {
			X2 = PuyoX2;
			Y2 = PuyoY2;
			PuyoField[X2][Y2] = PuyoType2;
			NextPuyo2 = true;
			if (Judge == false)
				Judge = true;
			if (PuyoState == 2) {
				PuyoField[X2][Y2 - 1] = PuyoType1;
				// System.out.println("test");
				NextPuyo = true;
			}
		}
		if (PuyoY2 >= 12) {
			X2 = PuyoX2;
			Y2 = 12;
			PuyoField[X2][Y2] = PuyoType2;
			NextPuyo2 = true;
			if (PuyoState == 2) {
				PuyoField[X2][11] = PuyoType1;
				// System.out.println("test2");
				NextPuyo = true;
			}
		}
		if (NextPuyo == true && NextPuyo2 == true) {
			deleteJudge();
			++PuyoY2;
		}

	}

	/**************************************************************
	 * 新しいぷよの作成
	 **************************************************************/
	public void newPuyo() {
		if (PuyoField[2][1] == 1)
			Key.GameStage = 2;
		PuyoX = 2;
		PuyoY = 1;
		PuyoX2 = 2;
		PuyoY2 = 2;
		Judge = false;
		NextPuyo = false;
		NextPuyo2 = false;
		PuyoType1 = NextType1;
		PuyoType2 = NextType2;
		NextType1 = rand.nextInt(4) + 1;
		NextType2 = rand.nextInt(4) + 1;
		PuyoState = 2;
		ChainCounter = 0;
		ChainNum = 0;
	}

	/**************************************************************
	 * ぷよの消去
	 **************************************************************/
	public void deleteJudge() {

		ChainNum = 0;

		for (int i = 12; i > 0; --i) {
			for (int j = 0; j < 5; ++j) {
				int delete1 = PuyoField[j][i];
				int delete2 = PuyoField[j + 1][i];
				if (delete1 == delete2 && delete1 > 0) {
					// System.out.println("test1");
					deletePuyo(j, i);
				}
			}
		}
		for (int i = 12; i > 0; --i) {
			for (int j = 0; j < 6; ++j) {
				int delete1 = PuyoField[j][i];
				int delete2 = PuyoField[j][i + 1];
				if (delete1 == delete2 && delete1 != 0) {
					// System.out.println("test2");
					deletePuyo(j, i);
				}
			}
		}
		if (ChainNum == 0)
			Judge2 = true;
		else
			Judge2 = false;
	}

	public void judgeRight(int x, int y) { // 右に同じ種類のぷよがあるかの判定
		if (x < 5 && PuyoField[x + 1][y] == PuyoField[x][y]) {
			DeleteArray[x + 1][y] = 1;
		}
	}

	public void judgeLeft(int x, int y) { // 左に同じ種類のぷよがあるかの判定
		if (x > 0 && PuyoField[x - 1][y] == PuyoField[x][y]) {
			DeleteArray[x - 1][y] = 1;
		}
	}

	public void judgeTop(int x, int y) { // 上に同じ種類のぷよがあるかの判定
		if (y > 0 && PuyoField[x][y - 1] == PuyoField[x][y]) {
			DeleteArray[x][y - 1] = 1;
		}
	}

	public void judgeBottom(int x, int y) { // 下に同じ種類のぷよがあるかの判定
		if (y < 13 && PuyoField[x][y + 1] == PuyoField[x][y]) {
			DeleteArray[x][y + 1] = 1;
		}
	}

	public void deletePuyo(int row, int col) {

		DeleteCounter = 0;
		DeleteArray[row][col] = 1;
		judgeRight(row, col);
		judgeLeft(row, col);
		judgeTop(row, col);
		judgeBottom(row, col);
		JudgeCounter[row][col] = 1;

		deleteCheck();
		for (int i = 0; i < 13; ++i) {
			for (int j = 0; j < 6; ++j) {
				JudgeCounter[j][i] = 0;
				if (DeleteArray[j][i] == 1) {
					++DeleteCounter;
				}
			}
		}

		if (DeleteCounter >= 4) {
			++ChainNum;
			++ChainCounter;
			score.puyoScore(DeleteCounter, ChainCounter);
			for (int i = 0; i < 13; ++i) {
				for (int j = 0; j < 6; ++j) {
					if (DeleteArray[j][i] == 1) {
						PuyoField[j][i] = 0;
					}
				}
			}
		}

		for (int i = 0; i < 13; ++i) {
			for (int j = 0; j < 6; ++j) {
				DeleteArray[j][i] = 0;
			}
		}
		drawPuyo();
	}

	public void deleteCheck() {
		int CheckCounter = 0;

		for (int i = 0; i < 13; ++i) {
			for (int j = 0; j < 6; ++j) {
				if (DeleteArray[j][i] == 1 && JudgeCounter[j][i] == 0) {
					CheckCounter = 1;
					judgeRight(j, i);
					judgeLeft(j, i);
					judgeTop(j, i);
					judgeBottom(j, i);
					JudgeCounter[j][i] = 1;
				}
			}
		}
		if (CheckCounter == 1) {
			deleteCheck();
			CheckCounter = 0;
		}
	}

	/**************************************************************
	 * ぷよの消去後の落下
	 **************************************************************/
	public void reDown() {
		int counter = 0;
		for (int i = 11; i >= 0; --i) {
			for (int j = 0; j < 6; ++j) {
				if (PuyoField[j][i + 1] == 0 && PuyoField[j][i] != 0) {
					PuyoField[j][i + 1] = PuyoField[j][i];
					PuyoField[j][i] = 0;
					++counter;
					// System.out.println("test" + counter);
				}
			}
		}
		if (counter == 0 && ChainCounter != 0) {
			deleteJudge();
		}

		if (counter == 0 && Judge2 == true) {

			newPuyo();
			Judge2 = false;
		}

	}

	/**************************************************************
	 * ぷよの描画
	 **************************************************************/
	public void drawPuyo() {
		// System.out.println("Test");
		if (Key.GameStage == 2) {
			// System.out.println("GameOverTest");
			for (int col = 0; col < PuyoColumns - 1; col++) {
				for (int row = 0; row < PuyoRows; row++) {
					bg.setColor(Color.black);
					bg.fillOval(aSIZE * (row + 1) + 2, aSIZE * (col + 2) + 2, aSIZE - 1, aSIZE - 1);
				}
			}
		}

		for (int col = 0; col < PuyoColumns - 1; col++) {
			for (int row = 0; row < PuyoRows; row++) {
				if (Puyo[row][col] == 1) {
					bg.setColor(Color.red);
					bg.fillOval(aSIZE * (row + 1) + 2, aSIZE * (col + 2) + 2, aSIZE - 1, aSIZE - 1);
				}
				if (Puyo2[row][col] == 1) {
					bg.setColor(Color.red);
					bg.fillOval(aSIZE * (row + 1) + 2, aSIZE * (col + 2) + 2, aSIZE - 1, aSIZE - 1);
				}
				if (Puyo[row][col] == 2) {
					bg.setColor(Color.yellow);
					bg.fillOval(aSIZE * (row + 1) + 2, aSIZE * (col + 2) + 2, aSIZE - 1, aSIZE - 1);
				}
				if (Puyo2[row][col] == 2) {
					bg.setColor(Color.yellow);
					bg.fillOval(aSIZE * (row + 1) + 2, aSIZE * (col + 2) + 2, aSIZE - 1, aSIZE - 1);
				}
				if (Puyo[row][col] == 3) {
					bg.setColor(Color.green);
					bg.fillOval(aSIZE * (row + 1) + 2, aSIZE * (col + 2) + 2, aSIZE - 1, aSIZE - 1);
				}
				if (Puyo2[row][col] == 3) {
					bg.setColor(Color.green);
					bg.fillOval(aSIZE * (row + 1) + 2, aSIZE * (col + 2) + 2, aSIZE - 1, aSIZE - 1);
				}
				if (Puyo[row][col] == 4) {
					bg.setColor(Color.blue);
					bg.fillOval(aSIZE * (row + 1) + 2, aSIZE * (col + 2) + 2, aSIZE - 1, aSIZE - 1);
				}
				if (Puyo2[row][col] == 4) {
					bg.setColor(Color.blue);
					bg.fillOval(aSIZE * (row + 1) + 2, aSIZE * (col + 2) + 2, aSIZE - 1, aSIZE - 1);
				}
				if (PuyoField[row][col] == 1) {
					bg.setColor(Color.red);
					bg.fillOval(aSIZE * (row + 1) + 2, aSIZE * (col + 2) + 2, aSIZE - 1, aSIZE - 1);
				}
				if (PuyoField[row][col] == 2) {
					bg.setColor(Color.yellow);
					bg.fillOval(aSIZE * (row + 1) + 2, aSIZE * (col + 2) + 2, aSIZE - 1, aSIZE - 1);
				}
				if (PuyoField[row][col] == 3) {
					bg.setColor(Color.green);
					bg.fillOval(aSIZE * (row + 1) + 2, aSIZE * (col + 2) + 2, aSIZE - 1, aSIZE - 1);
				}
				if (PuyoField[row][col] == 4) {
					bg.setColor(Color.blue);
					bg.fillOval(aSIZE * (row + 1) + 2, aSIZE * (col + 2) + 2, aSIZE - 1, aSIZE - 1);
				}
				if (PuyoField[row][col] == 5) {
					bg.setColor(Color.black);
					bg.fillOval(aSIZE * (row + 1) + 2, aSIZE * (col + 2) + 2, aSIZE - 1, aSIZE - 1);
				}
			}
		}
		if (NextType1 == 1) {
			bg.setColor(Color.red);
			bg.fillOval(aSIZE * 9 + 2, aSIZE * 2 + 2, aSIZE - 1, aSIZE - 1);
		}
		if (NextType1 == 2) {
			bg.setColor(Color.yellow);
			bg.fillOval(aSIZE * 9 + 2, aSIZE * 2 + 2, aSIZE - 1, aSIZE - 1);
		}
		if (NextType1 == 3) {
			bg.setColor(Color.green);
			bg.fillOval(aSIZE * 9 + 2, aSIZE * 2 + 2, aSIZE - 1, aSIZE - 1);
		}
		if (NextType1 == 4) {
			bg.setColor(Color.blue);
			bg.fillOval(aSIZE * 9 + 2, aSIZE * 2 + 2, aSIZE - 1, aSIZE - 1);
		}
		if (NextType2 == 1) {
			bg.setColor(Color.red);
			bg.fillOval(aSIZE * 9 + 2, aSIZE * 3 + 2, aSIZE - 1, aSIZE - 1);
		}
		if (NextType2 == 2) {
			bg.setColor(Color.yellow);
			bg.fillOval(aSIZE * 9 + 2, aSIZE * 3 + 2, aSIZE - 1, aSIZE - 1);
		}
		if (NextType2 == 3) {
			bg.setColor(Color.green);
			bg.fillOval(aSIZE * 9 + 2, aSIZE * 3 + 2, aSIZE - 1, aSIZE - 1);
		}
		if (NextType2 == 4) {
			bg.setColor(Color.blue);
			bg.fillOval(aSIZE * 9 + 2, aSIZE * 3 + 2, aSIZE - 1, aSIZE - 1);
		}

	}

	public void drawFrontGraphics() {
		fg.drawImage(image, 0, 0, panel);
	}
}
