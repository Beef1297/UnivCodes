//package main;

import java.util.Timer;

public class GameMain {
	GameMain() {
		Timer timer = new Timer();
		timer.schedule(new Processing(), 0, 100);// 100ミリ秒に1回実行
	}

	public static void main(String[] args) {
		new GameMain();
	}
}
