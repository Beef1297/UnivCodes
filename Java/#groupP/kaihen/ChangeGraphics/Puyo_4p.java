//package main;

import java.util.Random;
import java.awt.*;

class Puyo extends Field {

    Score score = new Score(); // クラスScoreのインスタンス生成
    private int Puyo[][]; // 動くぷよ用の配列
    public int PuyoField[][] = new int[4][8]; // 当たり判定終了後のぷよの配列

    private int PuyoRows; // 幅
    private int PuyoColumns; // 高さ
    public int PuyoX; // ぷよのX位置
    public int PuyoY; // ぷよのY位置

    /*add*/
    private int yoko,tate;
    private int locx,locy;
    private int pfx,pfy;
    /****/

    private boolean Judge; // falseのとき移動可能、trueのときは不可
    
    static private boolean Judge2; // trueかつcounter=0のときnewPuyo()を実行
    private int ChainCounter; // 連鎖回数
    private int ChainNum; // 連鎖したかどうかを判定する変数
    
    static private boolean NextPuyo; // 次のぷよを出す為の判定
    
    private int PuyoType1; // 次のぷよの種類
    public int NextType1; // 表示用の次のぷよの種類
    private int PuyoState; // ぷよの向き用の変数
    
    static public int DeleteCounter; // 削除したぷよの個数
    
    private int DeleteArray[][] = new int[4][7];// 削除する為の配列
    private int JudgeCounter[][] = new int[4][7];// 判定を行った回数の配列。０のときのみ判定実行。
    Random rand = new Random(); // ぷよの種類を決めるためのランダム変
    private int number;//乱数を一時的に記憶する変数


	/**************************************************************
	 * コンストラクタ
	 **************************************************************/
	Puyo() {
		PuyoRows = 4;
		PuyoColumns = 7;//14;
		PuyoState = 2;
		tate = 6;//9;//13;
    yoko = 5;
		number = rand.nextInt(1000) + 1;
		System.out.println(number);
		if(number <= 200)PuyoType1 = 1;
		else if(number <= 400) PuyoType1 = 2;
		else if(number <= 600) PuyoType1 = 3;
		else if(number <= 800) PuyoType1 = 4;
		else if(number <= 860) PuyoType1 = 5;
		else if(number <= 910) PuyoType1 = 6;
		else if(number <= 950) PuyoType1 = 7;
		else if(number <= 980) PuyoType1 = 8;
		else PuyoType1 = 9;

		NextType1 = rand.nextInt(4) + 1;

		PuyoX = 2;
		PuyoY = 1;

		DeleteCounter = 0;
		str.setColor(Color.BLACK);
		bg.setColor(Color.WHITE);
		Font font = new Font("DejaVu Sans Mono", 0, 60);
		str.setFont(font);
		System.out.println("puyoconst");
	}

	/**************************************************************
	 * ぷよの右移動
	 **************************************************************/
	public void moveRight() {
		if (Judge == false) {
			if (PuyoField[PuyoX + 1][PuyoY] == 0) {
				++PuyoX;
			}
		}
	}

	/**************************************************************
	 * ぷよの左移動
	 **************************************************************/
	public void moveLeft() {
		if (Judge == false) {
		    if (PuyoField[PuyoX - 1][PuyoY] == 0) {
				--PuyoX;
			}

		}
	}

	/**************************************************************
	 * ぷよの右回転
	 **************************************************************/
    /*	public void turnRight() {
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
    */
	/**************************************************************
	 * ぷよの左回転
	 **************************************************************/
    /*	public void turnLeft() {
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
    */
	/**************************************************************
	 * ぷよの上昇（テスト用）
	 **************************************************************/
	public void moveUp() {
		--PuyoY;
	}

	/**************************************************************
	 * ぷよの落下
	 **************************************************************/
	public void fallDown() {
	    //System.out.println(PuyoState);
	    //System.out.println("PuyoX:"+PuyoX+" PuyoY"+PuyoY);
	    //System.out.println("Puyo:"+Puyo[PuyoX][PuyoY]);
	    //System.out.println("PuyoField:"+PuyoField[0][4]);

		if (PuyoField[PuyoX][PuyoY + 1] == 0 && PuyoY < 5) {

			++PuyoY;
		} else {
			hit1();
		}
	}

	/**************************************************************
	 * ぷよのセット
	 **************************************************************/
	public void setPuyo() {
		Puyo = new int[PuyoRows][PuyoColumns];

		Puyo[PuyoX][PuyoY] = PuyoType1;

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
		//System.out.println("hit test");
		if (PuyoY >= 5) {
			X = PuyoX;
			Y = 5;
			PuyoField[X][Y] = PuyoType1;
			NextPuyo = true;
		}
		if (NextPuyo == true ) {
			deleteJudge();
		}

	}
    /*
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
    */
	/**************************************************************
	 * 新しいぷよの作成
	 **************************************************************/
	public void newPuyo() {
		if (PuyoField[1][1] != 0 || PuyoField[2][1] != 0)
			Key.GameStage = 2;
		PuyoX = 2;
		PuyoY = 1;
		Judge = false;
		NextPuyo = false;
		PuyoType1 = NextType1;

		/** ramdamで数字をセットする **/
		number = rand.nextInt(1000) + 1;
		System.out.println(number);
		if(number <= 200)NextType1 = 1;
		else if(number <= 400) NextType1 = 2;
		else if(number <= 600) NextType1 = 3;
		else if(number <= 800) NextType1 = 4;
		else if(number <= 860) NextType1 = 5;
		else if(number <= 910) NextType1 = 6;
		else if(number <= 950) NextType1 = 7;
		else if(number <= 980) NextType1 = 8;
		else NextType1 = 9;
		/****************************/
		PuyoState = 2;
		ChainCounter = 0;
		ChainNum = 0;
	}

	/**************************************************************
	 * ぷよの消去
	 **************************************************************/
	 	public void deleteJudge() {

		ChainNum = 0;

		for (int i = 5; i > 0; --i) {
			for (int j = 0; j < 3; ++j) {
				int delete1 = PuyoField[j][i];
				int delete2 = PuyoField[j + 1][i];
				if (delete1+delete2<=10 && delete1 > 0) {
					deletePuyo(j, i);
				}
			}
		}
		for (int i = 5; i > 0; --i) {
			for (int j = 0; j < 4; ++j) {
				int delete1 = PuyoField[j][i];
				int delete2 = PuyoField[j][i + 1];
				if (delete1+delete2<=10 && delete1 != 0) {
					deletePuyo(j, i);
				}
			}
		}
		if (ChainNum == 0)
			Judge2 = true;
		else
			Judge2 = false;
	}


	public void deletePuyo(int row, int col) {

		DeleteCounter = 0;
		DeleteArray[row][col] = 0;
		judgeRight(row, col);
		if(DeleteArray[row][col]!=10) {
			judgeLeft(row, col);
		}
		if(DeleteArray[row][col]!=10) {
			judgeTop(row, col);
		}
		if(DeleteArray[row][col]!=10) {
			judgeBottom(row, col);
		}

		JudgeCounter[row][col] = 1;

		deleteCheck();
		for (int i = 0; i < 6; ++i) {
			for (int j = 0; j < 4; ++j) {
				JudgeCounter[j][i] = 0;
				if (DeleteArray[j][i] == 10) {
					++DeleteCounter;
				}
			}
		}

		if (DeleteArray[row][col] == 10) {
			++ChainNum;
			++ChainCounter;
			score.puyoScore(DeleteCounter, ChainCounter);
			for (int i = 0; i < 6; ++i) {
				for (int j = 0; j < 4; ++j) {
					if (DeleteArray[j][i] == 10) {
						PuyoField[j][i] = 0;
					}
				}
			}
		}

		for (int i = 0; i < 6; ++i) {
			for (int j = 0; j < 4; ++j) {
				DeleteArray[j][i] = 0;
			}
		}
		drawPuyo();
	}

	public void deleteCheck() {
		int CheckCounter = 0;

		for (int i = 0; i < 6; ++i) {
			for (int j = 0; j < 4; ++j) {
				if (DeleteArray[j][i] == 10 && JudgeCounter[j][i] == 0) {
					CheckCounter = 1;
					JudgeCounter[j][i] = 1;
				}
			}
		}
		if (CheckCounter == 1) {
			deleteCheck();
			CheckCounter = 0;
		}
	}


	public void judgeRight(int x, int y) {
	int right;
		for (right = x;right <= 3;right++ ) {
			if(PuyoField[right][y]==0) {
				break;
			}
			DeleteArray[x][y] += PuyoField[right][y];
			if(DeleteArray[x][y]>10) {
				break;
			} else if(DeleteArray[x][y]==10) {
				for( ; right>x;right--) {
					DeleteArray[right][y]=10;
				}
				break;
			}
		}
		if(DeleteArray[x][y]!=10) {
			DeleteArray[x][y]=0;
		}
	}

	public void judgeLeft(int x, int y) {
	int left;
		for (left = x;left >= 0;left-- ) {
			if(PuyoField[left][y]==0) {
				break;
			}
			DeleteArray[x][y] += PuyoField[left][y];
			if(DeleteArray[x][y]>10) {
				DeleteArray[x][y] = 0;
				break;
			}
			if(DeleteArray[x][y]==10) {
				for( ; left<x;left++) {
					DeleteArray[left][y]=10;
				}
				break;
			}
		}
		if(DeleteArray[x][y]<10) {
			DeleteArray[x][y]=0;
		}
	}


	public void judgeTop(int x, int y) {
	int top;
		for (top = y;top <= 5; top++ ) {
			if(PuyoField[x][top]==0) {
				break;
			}
			DeleteArray[x][y] += PuyoField[x][top];
			if(DeleteArray[x][y]>10) {
				DeleteArray[x][y] = 0;
				break;
			}
			if(DeleteArray[x][y]==10) {
				for( ; top>y;top--) {
					DeleteArray[x][top]=10;
				}
				break;
			}
		}
		if(DeleteArray[x][y]<10) {
			DeleteArray[x][y]=0;
		}
	}
	public void judgeBottom(int x, int y) {
	int bottom;
		for (bottom = y;bottom >= 0;bottom-- ) {
			if(PuyoField[x][bottom]==0) {
				break;
			}
			DeleteArray[x][y] += PuyoField[x][bottom];
			if(DeleteArray[x][y]>10) {
				DeleteArray[x][y] = 0;
				break;
			}
			if(DeleteArray[x][y]==10) {
				for( ; bottom<y;bottom++) {
					DeleteArray[x][bottom]=10;
				}
				break;
			}
		}
		if(DeleteArray[x][y]<10) {
			DeleteArray[x][y]=0;
		}
	}





		/**************************************************************
	 * ぷよの消去後の落下
	 **************************************************************/
	public void reDown() {
		int counter = 0;
		for (int i = 4; i >= 0; --i) {
			for (int j = 0; j < 4; ++j) {
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
		//System.out.println("Test");
		if (Key.GameStage == 2) {
			// System.out.println("GameOverTest");
			for (int col = 0; col < PuyoColumns - 1; col++) {
				for (int row = 0; row < PuyoRows; row++) {
					bg.setColor(Color.black);
					bg.fillOval(aSIZE * (row + 2) + 2, aSIZE * (col + 3) + 2, aSIZE - 1, aSIZE - 1);
				}
			}
		}

		locx = 13;
		locy = 52;
		pfx = 2;
		pfy = 3;

		for (int col = 0; col < PuyoColumns - 1; col++) {
			for (int row = 0; row < PuyoRows; row++) {
				if (Puyo[row][col] == 1) {
				    	bg.setColor(Color.white);
					bg.fillOval(aSIZE * (row + pfx/*1*/) + 2, aSIZE * (col + pfy/*2*/) + 2, aSIZE - 1, aSIZE - 1);
					str.drawString("1",aSIZE * (row + pfx) +locx, aSIZE * (col + pfy) + locy);

				}
			       /*     	if (Puyo2[row][col] == 1) {
					bg.setColor(Color.red);
					bg.fillOval(aSIZE * (row + 1) + 2, aSIZE * (col + 2) + 2, aSIZE - 1, aSIZE - 1);
				}
				*/
				if (Puyo[row][col] == 2) {
				    bg.setColor(Color.white);
					bg.fillOval(aSIZE * (row + pfx) + 2, aSIZE * (col + pfy) + 2, aSIZE - 1, aSIZE - 1);
					str.drawString("2",aSIZE * (row + pfx) + locx, aSIZE * (col + pfy) + locy);
				}
				/*
				if (Puyo2[row][col] == 2) {
					bg.setColor(Color.yellow);
					bg.fillOval(aSIZE * (row + 1) + 2, aSIZE * (col + 2) + 2, aSIZE - 1, aSIZE - 1);
					}
				*/
				if (Puyo[row][col] == 3) {
				    bg.setColor(Color.white);
				    bg.fillOval(aSIZE * (row + pfx) + 2, aSIZE * (col + pfy) + 2, aSIZE - 1, aSIZE - 1);
				    str.drawString("3",aSIZE * (row + pfx) + locx, aSIZE * (col + pfy) + locy);
				}
				/*
				if (Puyo2[row][col] == 3) {
					bg.setColor(Color.green);
					bg.fillOval(aSIZE * (row + 1) + 2, aSIZE * (col + 2) + 2, aSIZE - 1, aSIZE - 1);
				}
				*/
				if (Puyo[row][col] == 4) {
				    	bg.setColor(Color.white);
					bg.fillOval(aSIZE * (row + pfx) + 2, aSIZE * (col + pfy) + 2, aSIZE - 1, aSIZE - 1);
					str.drawString("4",aSIZE * (row + pfx) + locx, aSIZE * (col + pfy) + locy);
				}
				if (Puyo[row][col] == 5) {
				    bg.setColor(Color.white);
				    bg.fillOval(aSIZE * (row + pfx) + 2, aSIZE * (col + pfy) + 2, aSIZE - 1, aSIZE - 1);
				    str.drawString("5",aSIZE * (row + pfx) + locx, aSIZE * (col + pfy) + locy);
				}
				if (Puyo[row][col] == 6) {
				    bg.setColor(Color.white);
				    bg.fillOval(aSIZE * (row + pfx) + 2, aSIZE * (col + pfy) + 2, aSIZE - 1, aSIZE - 1);
				    str.drawString("6",aSIZE * (row + pfx) + locx, aSIZE * (col + pfy) + locy);
				}
				if (Puyo[row][col] == 7) {
				    bg.setColor(Color.white);
				    bg.fillOval(aSIZE * (row + pfx) + 2, aSIZE * (col + pfy) + 2, aSIZE - 1, aSIZE - 1);
				    str.drawString("7",aSIZE * (row + pfx) + locx, aSIZE * (col + pfy) + locy);
				}
				if (Puyo[row][col] == 8) {
				    bg.setColor(Color.white);
				    bg.fillOval(aSIZE * (row + pfx) + 2, aSIZE * (col + pfy) + 2, aSIZE - 1, aSIZE - 1);
				    str.drawString("8",aSIZE * (row + pfx) + locx, aSIZE * (col + pfy) + locy);
				}
				if (Puyo[row][col] == 9) {
				    bg.setColor(Color.white);
				    bg.fillOval(aSIZE * (row + pfx) + 2, aSIZE * (col + pfy) + 2, aSIZE - 1, aSIZE - 1);
				    str.drawString("9",aSIZE * (row + pfx) + locx, aSIZE * (col + pfy) + locy);
				}
				/*
				if (Puyo2[row][col] == 4) {
					bg.setColor(Color.blue);
					bg.fillOval(aSIZE * (row + 1) + 2, aSIZE * (col + 2) + 2, aSIZE - 1, aSIZE - 1);
				}
				*/
				if (PuyoField[row][col] == 1) {
				    	bg.setColor(Color.white);
					bg.fillOval(aSIZE * (row + pfx) + 2, aSIZE * (col + pfy) + 2, aSIZE - 1, aSIZE - 1);
					str.drawString("1",aSIZE * (row + pfx) + locx, aSIZE * (col + pfy) + locy);
				}
				if (PuyoField[row][col] == 2) {
				    	bg.setColor(Color.white);
					bg.fillOval(aSIZE * (row + pfx) + 2, aSIZE * (col + pfy) + 2, aSIZE - 1, aSIZE - 1);
					str.drawString("2",aSIZE * (row + pfx) + locx, aSIZE * (col + pfy) + locy);
				}
				if (PuyoField[row][col] == 3) {
				    	bg.setColor(Color.white);
					bg.fillOval(aSIZE * (row + pfx) + 2, aSIZE * (col + pfy) + 2, aSIZE - 1, aSIZE - 1);
					str.drawString("3",aSIZE * (row + pfx) + locx, aSIZE * (col + pfy) + locy);
				}
				if (PuyoField[row][col] == 4) {
				    	bg.setColor(Color.white);
					bg.fillOval(aSIZE * (row + pfx) + 2, aSIZE * (col + pfy) + 2, aSIZE - 1, aSIZE - 1);
					str.drawString("4",aSIZE * (row + pfx) + locx, aSIZE * (col + pfy) + locy);
				}
				if (PuyoField[row][col] == 5) {
				    bg.setColor(Color.white);
				    bg.fillOval(aSIZE * (row + pfx) + 2, aSIZE * (col + pfy) + 2, aSIZE - 1, aSIZE - 1);
				    str.drawString("5",aSIZE * (row + pfx) + locx, aSIZE * (col + pfy) + locy);
				}
				if (PuyoField[row][col] == 6) {
				    bg.setColor(Color.white);
				    bg.fillOval(aSIZE * (row + pfx) + 2, aSIZE * (col + pfy) + 2, aSIZE - 1, aSIZE - 1);
				    str.drawString("6",aSIZE * (row + pfx) + locx, aSIZE * (col + pfy) + locy);
				}
				if (PuyoField[row][col] == 7) {
				    bg.setColor(Color.white);
				    bg.fillOval(aSIZE * (row + pfx) + 2, aSIZE * (col + pfy) + 2, aSIZE - 1, aSIZE - 1);
				    str.drawString("7",aSIZE * (row + pfx) + locx, aSIZE * (col + pfy) + locy);
				}
				if (PuyoField[row][col] == 8) {
				    bg.setColor(Color.white);
				    bg.fillOval(aSIZE * (row + pfx) + 2, aSIZE * (col + pfy) + 2, aSIZE - 1, aSIZE - 1);
				    str.drawString("8",aSIZE * (row + pfx) + locx, aSIZE * (col + pfy) + locy);
				}
				if (PuyoField[row][col] == 9) {
				    bg.setColor(Color.white);
				    bg.fillOval(aSIZE * (row + pfx) + 2, aSIZE * (col + pfy) + 2, aSIZE - 1, aSIZE - 1);
				    str.drawString("9",aSIZE * (row + pfx) + locx, aSIZE * (col + pfy) + locy);
				}

			}
		}
		if (NextType1 == 1) {
		    	bg.setColor(Color.white);
			bg.fillOval(aSIZE * 2 + 2, aSIZE * 1 + 2, aSIZE - 1, aSIZE - 1);
			str.drawString("1",aSIZE * 2 + locx, aSIZE * 1 + locy);
		}
		if (NextType1 == 2) {
		    	bg.setColor(Color.white);
			bg.fillOval(aSIZE * 2 + 2, aSIZE * 1 + 2, aSIZE - 1, aSIZE - 1);
			str.drawString("2",aSIZE * 2 + locx, aSIZE * 1 + locy);
		}
		if (NextType1 == 3) {
		    	bg.setColor(Color.white);
			bg.fillOval(aSIZE * 2 + 2, aSIZE * 1 + 2, aSIZE - 1, aSIZE - 1);
			str.drawString("3",aSIZE * 2 + locx, aSIZE * 1 + locy);
		}
		if (NextType1 == 4) {
		    	bg.setColor(Color.white);
			bg.fillOval(aSIZE * 2 + 2, aSIZE * 1 + 2, aSIZE - 1, aSIZE - 1);
			str.drawString("4",aSIZE * 2 + locx, aSIZE * 1 + locy);
		}
		if (NextType1 == 5) {
		    bg.setColor(Color.white);
		    bg.fillOval(aSIZE * 2 + 2, aSIZE * 1 + 2, aSIZE - 1, aSIZE - 1);
		    str.drawString("5",aSIZE * 2 + locx, aSIZE * 1 + locy);
		}
		if (NextType1 == 6) {
		    bg.setColor(Color.white);
		    bg.fillOval(aSIZE * 2 + 2, aSIZE * 1 + 2, aSIZE - 1, aSIZE - 1);
		    str.drawString("6",aSIZE * 2 + locx, aSIZE * 1 + locy);
		}
		if (NextType1 == 7) {
		    bg.setColor(Color.white);
		    bg.fillOval(aSIZE * 2 + 2, aSIZE * 1 + 2, aSIZE - 1, aSIZE - 1);
		    str.drawString("7",aSIZE * 2 + locx, aSIZE * 1 + locy);
		}
		if (NextType1 == 8) {
		    bg.setColor(Color.white);
		    bg.fillOval(aSIZE * 2 + 2, aSIZE * 1 + 2, aSIZE - 1, aSIZE - 1);
		    str.drawString("8",aSIZE * 2 + locx, aSIZE * 1 + locy);
		}
		if (NextType1 == 9) {
		    bg.setColor(Color.white);
		    bg.fillOval(aSIZE * 2 + 2, aSIZE * 1 + 2, aSIZE - 1, aSIZE - 1);
		    str.drawString("9",aSIZE * 2 + locx, aSIZE * 1 + locy);
		}
		/*
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
		*/

	}

	public void drawFrontGraphics() {
	    	fg.drawImage(image, 0, 0, panel);
	}
}
