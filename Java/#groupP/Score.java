//package main;

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
		Rectangle rectText = fm.getStringBounds("" + Score.ScoreValue, g).getBounds();
		Rectangle rectText2 = fm.getStringBounds("Score", g).getBounds();
		int scoreX = 380 - rectText.width / 2;
		int scoreY = 420;
		int TextX = 380 - rectText2.width / 2;

		g.setColor(Color.black);
		g.clearRect(0, 0, 480, 640);
		Font font = new Font("Arial", 0, 30);
		g.setFont(font);
		g.fillRect(0, 0, 480, 640);
		g.setColor(Color.white);
		g.drawString("Score", TextX, 320);
		g.drawString("" + Score.ScoreValue, scoreX, scoreY);
	}
}
