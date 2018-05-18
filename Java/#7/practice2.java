import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

class TimeObservable extends Observable implements ActionListener{
    private javax.swing.Timer timer;
    private int hour,min,sec;
    TimeObservable(){
	timer = new javax.swing.Timer(1000, this);
	timer.start();
    }
    public void actionPerformed(ActionEvent e){
	this.setValue();
    }
    public int getHour(int n){
	return (hour-n+24)%24;
	//基数をいれないと10進数となる
    }
    public int getMin(){
	return min;
    }
    public int getSec(){
	return sec;
    }
    public void setValue(){
	Calendar cal = Calendar.getInstance();
	hour = cal.get(Calendar.HOUR_OF_DAY);
	min = cal.get(Calendar.MINUTE);
	sec = cal.get(Calendar.SECOND);
	setChanged();
	notifyObservers();
    }
}
//obserableクラスと
class TimeFieldObserver extends JPanel implements Observer{
    private TimeObservable timeObservable;
    private int diff,hour,min,sec;
    private String capital;
    private JPanel p;
    //位置を覚えておくならインスタンス変数にすればいい
    //そのオブジェクト特有の値はインスタンス変数にいれる
    public TimeFieldObserver(TimeObservable so,String cap, int n){
	diff = n;
	capital = cap;
	timeObservable = so;
	timeObservable.addObserver(this);
	p = new JPanel();
	p.setLayout(new GridLayout(1,2));
	p.add(new JLabel(capital));
	String current_time = String.format("%02d:%02d:%02d",timeObservable.getHour(diff),timeObservable.getMin(), timeObservable.getSec());
	p.add(new JLabel(current_time));

	this.setFont(new Font(Font.SANS_SERIF, Font.BOLD,26));
    }
    public void update(Observable o,Object arg){
	String s = String.format("%02d:%02d:%02d",timeObservable.getHour(diff),timeObservable.getMin(),timeObservable.getSec());
	p.add(new JLabel(s),2);
	/*
	  updateするときつまりgetValueすると
	  obserable(subject)に入ってる値に自分の
	  位置番号を足して得られる.
	*/
    }
}
//observerになるクラスを作る
class TimeObserverFrame extends JFrame{
    private final static String capital[] = {"Tokyo", "Beijing", "Paris","London","New York","Los Angels"};
    private final static int td[] = {0, -1, -8, -9, 10, 7};

    public TimeObserverFrame(){
	this.setTitle("World Time");
	this.setLayout(new GridLayout(6, 1));
	TimeObservable s = new TimeObservable();
	for(int i = 0;i < 6;i++){
	    this.add(new TimeFieldObserver(s,capital[i],td[i]));
	}
	s.setValue();
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.pack();
	this.setVisible(true);
    }
    public static void main(String argv[]){
	new TimeObserverFrame();
    }
}
