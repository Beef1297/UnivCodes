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
	Rectangle rectText = fm.getStringBounds("" + Score.ScoreValue, g).getBounds();
	Rectangle rectText2 = fm.getStringBounds("Score", g).getBounds();
	//System.out.println("rectText:"+rectText+"rectText2:"+rectText2);
	/*上記のRectangleでその文字列を配置した場合のx,y,width,heightを得ている*/
	int scoreX = 390 - rectText.width / 2;
	int scoreY = 140;
	int TextX = 390 - rectText2.width / 2;

	//g.setColor(Color.black);
	//g.clearRect(0, 0,460 ,640);
	Font font = new Font("Arial", 0, 40);
	g.setFont(font);
  /*changed*/
	g.fillRect(0, 0, 540, 660);
  /********/
	g.setColor(Color.black);
	g.drawString("Score", TextX, 60);
	g.drawString("" + Score.ScoreValue, scoreX, scoreY);
    }
}
