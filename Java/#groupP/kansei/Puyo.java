//package main;
/****kaetatokoro*****
ushiyama:
PuyoField を 12x6 から 5x4(tate x yoko)にしたためそれによる
(->6x5にした
forループの範囲の変化や配列の大きさをかえた.
さらに、drawPuyoでもともとのPuyo領域に合わせて座標設定がされていたため
新しい領域に合わせて設定し直した。その際,定数を表すのにlocx,locy,pfx,pfyを用いた.
79: フォントを変えてみた

************************/
import java.util.Random;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

class Puyo extends Field {

  Score score = new Score(); // クラスScoreのインスタンス生成
  private int Puyo[][]; // 動くぷよ用の配列
  public int PuyoField[][] = new int[5][9]; // 当たり判定終了後のぷよの配列

  private int PuyoRows; // 幅
  private int PuyoColumns; // 高さ
  public int PuyoX; // ぷよのX位置
  public int PuyoY; // ぷよのY位置

  /*add*/
  private int yoko,tate;
  private int locx,locy;
  private int pfx,pfy;
  private Graphics2D g2 = (Graphics2D)bg;
  BufferedImage readImage = null;
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

  private int DeleteArray[][] = new int[5][8];// 削除する為の配列
  //   private int JudgeCounter[][] = new int[5][8];// 判定を行った回数の配列。０のときのみ判定実行。
  Random rand = new Random(); // ぷよの種類を決めるためのランダム変
  private int number;//乱数を一時的に記憶する変数


  /**************************************************************
  * コンストラクタ
  **************************************************************/
  Puyo() {
    /*changed*/
    PuyoRows = 5;
    PuyoColumns = 8;//14;
    /*********/
    PuyoState = 2;
    tate = 9;//9;//13;
    yoko = 6;
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


    //change&add
    number = rand.nextInt(1000) + 1;
    if(number <= 100)NextType1 = 1;
    else if(number <= 300) NextType1 = 2;
    else if(number <= 550) NextType1 = 3;
    else if(number <= 800) NextType1 = 4;
    else if(number <= 860) NextType1 = 5;
    else if(number <= 910) NextType1 = 6;
    else if(number <= 950) NextType1 = 7;
    else if(number <= 980) NextType1 = 8;
    else NextType1 = 9;
    //ここまで

    PuyoX = 2;
    PuyoY = 1;

    DeleteCounter = 0;
    str.setColor(Color.BLACK);
    bg.setColor(Color.WHITE);
    Font font = new Font("DejaVu Sans Mono", 0, 60);
    str.setFont(font);
    System.out.println("puyoconst");
    //BufferedImage readImage = null;

    try {
      readImage = ImageIO.read(new File("chiwawa-white-01.png"));
    }catch(Exception e){
      e.printStackTrace();
      readImage = null;
    }
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

    if (PuyoField[PuyoX][PuyoY + 1] == 0 && PuyoY < 6) {

      ++PuyoY;
    } else {
      try{//着地したあとの少し動かせる猶予
        Thread.sleep(600);
      }catch (InterruptedException e){
      }
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
    if (PuyoY >= 6) {
      X = PuyoX;
      Y = 6;
      PuyoField[X][Y] = PuyoType1;
      NextPuyo = true;
    }
    if (NextPuyo == true ) {
      deleteJudge();
    }

  }

  /**************************************************************
  * 新しいぷよの作成
  **************************************************************/
  public void newPuyo() {
    if (PuyoField[2][1] != 0)
    Key.GameStage = 2;
    PuyoX = 2;
    PuyoY = 1;
    Judge = false;
    NextPuyo = false;
    PuyoType1 = NextType1;

    /** ramdamで数字をセットする **/
    if(level==0 || level==1){
      number = rand.nextInt(1050) + 1;
    }else if(level==2){
      number = rand.nextInt(1150)+1;
    }else if(level==3){
      number = rand.nextInt(1260)+1;
    }else if(level==4){
      number = rand.nextInt(1350)+1;
    }
    //	System.out.println(number);
    if(level==0 || level==1){
      if(number <= 100)NextType1 = 1;
      else if(number <= 300) NextType1 = 2;
      else if(number <= 550) NextType1 = 3;
      else if(number <= 800) NextType1 = 4;
      else if(number <= 860) NextType1 = 5;
      else if(number <= 910) NextType1 = 6;
      else if(number <= 950) NextType1 = 7;
      else if(number <= 980) NextType1 = 8;
      else if(number <= 1000)NextType1 = 9;
      else NextType1 = 100;
    }else if(level==2){
      if(number <= 50)NextType1 = 1;
      else if(number <= 200) NextType1 = 2;
      else if(number <= 400) NextType1 = 3;
      else if(number <= 600) NextType1 = 4;
      else if(number <= 710) NextType1 = 5;
      else if(number <= 810) NextType1 = 6;
      else if(number <= 900) NextType1 = 7;
      else if(number <= 980) NextType1 = 8;
      else if(number <= 1050) NextType1 = 9;
      else NextType1 = 100;
    }else if(level==3){
      if(number <= 50)NextType1 = 1;
      else if(number <= 170) NextType1 = 2;
      else if(number <= 340) NextType1 = 3;
      else if(number <= 510) NextType1 = 4;
      else if(number <= 650) NextType1 = 5;
      else if(number <= 780) NextType1 = 6;
      else if(number <= 900) NextType1 = 7;
      else if(number <= 1010) NextType1 = 8;
      else if(number <= 1110)NextType1 = 9;
      else NextType1 = 100;
    }else if(level==4){
      if(number <= 80)NextType1 = 1;
      else if(number <= 190) NextType1 = 2;
      else if(number <= 310) NextType1 = 3;
      else if(number <= 430) NextType1 = 4;
      else if(number <= 570) NextType1 = 5;
      else if(number <= 730) NextType1 = 6;
      else if(number <= 880) NextType1 = 7;
      else if(number <= 1020) NextType1 = 8;
      else if(number <= 1150) NextType1 = 9;
      else NextType1 = 100;
    }
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

    for (int i = 6; i > 0; --i) {
      for (int j = 0; j < 4; ++j) {
        int delete1 = PuyoField[j][i];
        int delete2 = PuyoField[j + 1][i];
        if (delete1+delete2<=dnum && delete1 > 0) {
          deletePuyo(j, i);
        }
      }
    }
    for (int i = 6; i > 0; --i) {
      for (int j = 0; j < 5; ++j) {
        int delete1 = PuyoField[j][i];
        int delete2 = PuyoField[j][i + 1];
        if (delete1+delete2<=dnum && delete1 != 0) {
          deletePuyo(j, i);
        }
      }
    }
    if (ChainNum == 0)
    Judge2 = true;
    else
    Judge2 = false;

    realDelete();

  }


  public void deletePuyo(int row, int col) {
    int already=0;
    DeleteCounter = 0;
    if(DeleteArray[row][col]==dnum){already=1;}
    DeleteArray[row][col] = 0;
    judgeRight(row, col);
    if(DeleteArray[row][col]!=dnum) {
      judgeLeft(row, col);
    }
    if(DeleteArray[row][col]!=dnum) {
      judgeTop(row, col);
    }
    if(DeleteArray[row][col]!=dnum) {
      judgeBottom(row, col);
    }
    if(already==1){DeleteArray[row][col]=dnum;}
  }

  public void realDelete(){
    DeleteCounter=0;
    ++ChainNum;
    ++ChainCounter;
    //System.out.println("ChainNum="+ChainNum);
    //System.out.println("ChainCounter="+ChainCounter);
    //System.out.println("");
    for (int i = 0; i < 7; ++i) {
      for (int j = 0; j < 5; ++j) {
        if (DeleteArray[j][i] == dnum) {
          ++DeleteCounter;
          drawPuyo();
          PuyoField[j][i] = 0;
          OjamaPuyo(j,i);

        }
      }
    }

    score.puyoScore(DeleteCounter, ChainCounter);
    //add
    /*
    for (int i = 0; i < 7; ++i) {
    for (int j = 0; j < 5; ++j) {
    System.out.print(DeleteArray[j][i]+" ");
  }
  System.out.println("");
}
System.out.println("");
*/
//ここまで

for (int i = 0; i < 7; ++i) {
  for (int j = 0; j < 5; ++j) {
    DeleteArray[j][i] = 0;
  }
}

drawPuyo();
}

public void judgeRight(int x, int y) {
  int right;
  for (right = x;right <= 4;right++ ) {
    if(PuyoField[right][y]==0) {
      break;
    }
    DeleteArray[x][y] += PuyoField[right][y];
    if(DeleteArray[x][y]>dnum) {
      break;
    } else if(DeleteArray[x][y]==dnum) {
      for( ; right>x;right--) {
        DeleteArray[right][y]=dnum;
      }
      break;
    }
  }
  if(DeleteArray[x][y]!=dnum) {
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
    if(DeleteArray[x][y]>dnum) {
      DeleteArray[x][y] = 0;
      break;
    }
    if(DeleteArray[x][y]==dnum) {
      for( ; left<x;left++) {
        DeleteArray[left][y]=dnum;
      }
      break;
    }
  }
  if(DeleteArray[x][y]<dnum) {
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
    if(DeleteArray[x][y]>dnum) {
      DeleteArray[x][y] = 0;
      break;
    }
    if(DeleteArray[x][y]==dnum) {
      for( ; top>y;top--) {
        DeleteArray[x][top]=dnum;
      }
      break;
    }
  }
  if(DeleteArray[x][y]<dnum) {
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
    if(DeleteArray[x][y]>dnum) {
      DeleteArray[x][y] = 0;
      break;
    }
    if(DeleteArray[x][y]==dnum) {
      for( ; bottom<y;bottom++) {
        DeleteArray[x][bottom]=dnum;
      }
      break;
    }
  }
  if(DeleteArray[x][y]<dnum) {
    DeleteArray[x][y]=0;
  }
}

public void OjamaPuyo(int x,int y){
  if(x>0){
    if(PuyoField[x-1][y]==100){
      PuyoField[x-1][y]=0;
      ++DeleteCounter;
    }
  }
  if(x<4){
    if(PuyoField[x+1][y]==100){
      PuyoField[x+1][y]=0;
      ++DeleteCounter;
    }
  }
  if(y<6){
    if(PuyoField[x][y+1]==100){
      PuyoField[x][y+1]=0;
      ++DeleteCounter;
    }
  }
  if(y>0){
    if(PuyoField[x][y-1]==100){
      PuyoField[x][y-1]=0;
      ++DeleteCounter;
    }
  }
}




/**************************************************************
* ぷよの消去後の落下
**************************************************************/
public void reDown() {
  int counter = 0;
  for (int i = 5; i >= 0; --i) {
    for (int j = 0; j < 5; ++j) {
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

      if (Puyo[row][col] == 2) {
        bg.setColor(Color.white);
        bg.fillOval(aSIZE * (row + pfx) + 2, aSIZE * (col + pfy) + 2, aSIZE - 1, aSIZE - 1);
        str.drawString("2",aSIZE * (row + pfx) + locx, aSIZE * (col + pfy) + locy);
      }

      if (Puyo[row][col] == 3) {
        bg.setColor(Color.white);
        bg.fillOval(aSIZE * (row + pfx) + 2, aSIZE * (col + pfy) + 2, aSIZE - 1, aSIZE - 1);
        str.drawString("3",aSIZE * (row + pfx) + locx, aSIZE * (col + pfy) + locy);
      }

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
      if (Puyo[row][col] == 100) {
        if(readImage == null){
          bg.setColor(Color.red);
          bg.fillOval(aSIZE * (row + pfx) + 2, aSIZE * (col + pfy) + 2, aSIZE - 1, aSIZE - 1);
          str.drawString("j",aSIZE * (row + pfx) + locx, aSIZE * (col + pfy) + locy);
        }else{
          g2.drawImage(readImage, aSIZE * (row + pfx) + 2, aSIZE * (col + pfy) + 2, 60, 60, this);
        }
      }

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
      if (PuyoField[row][col] == 100) {
        if(readImage == null){
          bg.setColor(Color.red);
          bg.fillOval(aSIZE * (row + pfx) + 2, aSIZE * (col + pfy) + 2, aSIZE - 1, aSIZE - 1);
          str.drawString("j",aSIZE * (row + pfx) + locx, aSIZE * (col + pfy) + locy);
        }else{
          g2.drawImage(readImage, aSIZE * (row + pfx) + 2, aSIZE * (col + pfy) + 2, 60, 60, this);
        }
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
  if (NextType1 == 100) {
    if(readImage == null){
      bg.setColor(Color.red);
      bg.fillOval(aSIZE * 2 + 2, aSIZE * 1 + 2, aSIZE - 1, aSIZE - 1);
      str.drawString("j",aSIZE * 2 + locx, aSIZE * 1 + locy);
    }else{
      g2.drawImage(readImage, aSIZE * 2 + 2, aSIZE * 1 + 2, 60, 60, this);
    }
  }

}

public void drawFrontGraphics() {
  fg.drawImage(image, 0, 0, panel);
}
}
