//package main;
/****kaetatokoro*****
ushiyama:
  112 puyo.PuyoX < 3 に変更した
  118 uyo.PuyoY < 5にへんこうした
  ********************/

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.TimerTask;

 class Processing extends TimerTask {
	Puyo puyo = new Puyo();
	Score score = new Score();

	public int counter = 0;
	public int counter2 = 0;

	private Graphics g = puyo.bg;

	Processing() {
		if (Key.GameStage == 0) {
			//
		}
		if (Key.GameStage == 1) {
			run();
		}

	}

	/***********************************************
	 *
	 ***********************************************/
     public void autoDown() {
	 if (Key.GameStage == 1) {
	     if (counter == 3) { //
		 if (puyo.PuyoY < 7 /*&& puyo.PuyoY2 < 13*/)
		     puyo.fallDown();
		 counter = 0;
	     } else
		 ++counter;

	 }
     }

	/***********************************************
	 *
	 ***********************************************/
	public void run() {
		keyboard();
		draw();
		autoDown();
	}

	/***********************************************

	 ***********************************************/
	public void draw() {
		if (Key.GameStage == 3) {
			String Text1 = "Pause";

			FontMetrics fm = g.getFontMetrics();
			Rectangle rectText1 = fm.getStringBounds(Text1, g).getBounds();

			int pTextX1 = 270 - (rectText1.width / 2);
			Font font = new Font("Arial", 0, 30);
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString(Text1, pTextX1, 320);

			puyo.drawFrontGraphics();
		}
		if (Key.GameStage == 1) {
			score.drawScore(g);
			puyo.setField();
			puyo.drawField();
			puyo.setPuyo();
			puyo.drawPuyo();
			puyo.reDown();
			puyo.drawFrontGraphics();
		}
		if (Key.GameStage == 2) {
			g.setColor(Color.black);
			g.fillRect(0, 0, 540, 660);

			String Text1 = "GameOver";
			String Text2 = "Push Enter Exit";
			FontMetrics fm = g.getFontMetrics();
			Rectangle rectText1 = fm.getStringBounds(Text1, g).getBounds();
			Rectangle rectText2 = fm.getStringBounds(Text2, g).getBounds();
			Rectangle rectText3 = fm.getStringBounds("" + Score.ScoreValue, g).getBounds();

			int pTextX1 = 270 - (rectText1.width / 2);
			int pTextX2 = 270 - (rectText2.width / 2);
			int pTextX3 = 270 - (rectText3.width / 2);

			Font font = new Font("Arial", 0, 30);
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString(Text1, pTextX1, 280);
			g.drawString(Text2, pTextX2, 360);
			g.drawString("" + Score.ScoreValue, pTextX3, 480);
			puyo.drawFrontGraphics();
		}

	}

	/***********************************************

	 ***********************************************/
	public void keyboard() {
		if (Key.GameStage == 1) {
		    if (Key.Right && puyo.PuyoX < 4 /*&& puyo.PuyoX2 < 5*/) {
				puyo.moveRight();
			}
		    if (Key.Left && puyo.PuyoX > 0 /*&& puyo.PuyoX2 > 0*/) {
				puyo.moveLeft();
			}
		    if (Key.Down && puyo.PuyoY < 6 /*&& puyo.PuyoY2 < 12*/) {
				puyo.fallDown();
		    }
		    if (Key.TurnRight) {
			//puyo.turnRight();
		    }
		    if (Key.TurnLeft) {
			//	puyo.turnLeft();
		    }
		    if (Key.Up && puyo.PuyoY > 1 /*&& puyo.PuyoY2 > 1*/) {
			puyo.moveUp();
		    }
		    if (Key.GameStage == 0) {

		    }
		}
	}
 }
