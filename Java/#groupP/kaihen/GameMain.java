//package main;

import java.util.Timer;

public class GameMain{
    int judge=0; //startボタンが押されたか判断
    Field j = new Field();
    GameMain() {
	new Processing();
	Timer timer = new Timer();
	while (true){
	    judge=j.getjudge();
	    System.out.print(""); //why
	    if(judge==1){
		System.out.println("start!");
		break;
	    }
	}
	timer.schedule(new Processing(), 0,90-Field.level*10);
    }
    public static void main(String[] args) {
	new GameMain();
    }
}
