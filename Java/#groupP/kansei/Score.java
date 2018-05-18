//package main;
/*kaetatokoro************
39,40,41: フィールドの変更に伴い座標を変えた
46: フォントの大きさ変えた
49: 色を白から黒にした
53: 位置を60にした
*********************/
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Score {

  private Rectangle rText1,rText2,rText3,rText4;
  private int scoreX1,scoreX2,scoreY1,scoreY2,TextX1,TextX2,TextY1,TextY2;
  static public int ScoreValue;// スコア値

  Score() {
    System.out.println("Scoreconst");
  }

  /***********************************************
  * スコア計算
  ***********************************************/

  public void puyoScore(int deletepuyo, int chain) { // deletepuyoは削除したぷよの個数
    // chainは連鎖の回数
    ScoreValue += deletepuyo * 100 * chain; // 削除したぷよの個数 × 100 × (連鎖回数+1)

  }

  /***********************************************
  * スコア表示
  ***********************************************/
  public void drawScore(Graphics g) {

    FontMetrics fm = g.getFontMetrics();
    //System.out.println(fm);

    rText1 = fm.getStringBounds("" + Score.ScoreValue, g).getBounds();
    rText2 = fm.getStringBounds("Score: ", g).getBounds();
    rText3 = fm.getStringBounds("" + Field.DNUM, g).getBounds();
    rText4 = fm.getStringBounds("Delete: ", g).getBounds();
    //System.out.println("rectText:"+rectText+"rectText2:"+rectText2);
    /*上記のRectangleでその文字列を配置した場合のx,y,width,heightを得ている*/
    scoreX1 = 440 - rText1.width / 2;
    scoreX2 = 440 -rText3.width / 2;
    scoreY1 = TextY1 = 60;
    scoreY2 = TextY2 = 100;
    TextX1 = 360 - rText2.width / 2;
    TextX2 = 360 - rText4.width / 2;

    //g.setColor(Color.black);
    //g.clearRect(0, 0,460 ,640);
    Font font = new Font("Arial", 0, 30);
    g.setFont(font);
    /*changed*/
    g.fillRect(0, 0, 540, 660);
    /********/
    g.setColor(Color.black);
    g.drawString("Score: ", TextX1, TextY1);
    g.drawString("" + Score.ScoreValue, scoreX1, scoreY1);
    g.drawString("Delete: ", TextX2, TextY2);
    g.drawString("" + Field.DNUM, scoreX2, scoreY2);
  }
}
