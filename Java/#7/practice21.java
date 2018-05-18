import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

class TimeObservable extends Observable implements ActionListener {
  private javax.swing.Timer timer;
  private int hour,min,sec; // 現在時刻を保持するインスタンス変数
  TimeObservable(){
    timer = new javax.swing.Timer(1000, this); // 1秒毎にactionPerformedを呼び出し
    timer.start(); 
  }
  public void actionPerformed(ActionEvent e) {
    this.setValue(); // setValueでは，現在時刻をインスタンス変数にセットします．
  }
     public String getValue(int n){
	 String current_time = String.format("%02d:%02d:%02d",(hour-n+24)%24,min,sec);
	 return current_time;

	//基数をいれないと10進数となる
     }
    public void setValue(){
	Calendar cal = Calendar.getInstance(); 
	 hour= cal.get(Calendar.HOUR_OF_DAY); 
	 min = cal.get(Calendar.MINUTE); 
	 sec = cal.get(Calendar.SECOND); 
	setChanged();
	notifyObservers();
    }
}

//obserableクラスと

class ClockPanel extends JPanel implements Observer{
  private TimeObservable timeObservable;
  private String place_name;
  private int diff;
  private JLabel label_place,label_time;
  public ClockPanel(TimeObservable to,String s,int d) {
    timeObservable = to; 
    place_name=s; diff=d;
    timeObservable.addObserver(this);

    this.setLayout(new GridLayout(1,2));
    Font font = new Font(Font.SANS_SERIF,Font.BOLD,32);
    label_place=new JLabel(place_name,JLabel.CENTER);
    label_time=new JLabel(to.getValue(diff),JLabel.CENTER);
    
    label_place.setFont(font);
    label_time.setFont(font);
    this.add(label_place);
    this.add(label_time);
  }
  public void update(Observable o,Object arg) {
    label_time.setText(timeObservable.getValue(diff));
  }
}
//observerになるクラスを作る
class TimexObserverFrame extends JFrame{
    public TimexObserverFrame(){
	this.setTitle("Observer Frame");
	this.setLayout(new GridLayout(6, 1));
	TimeObservable t = new TimeObservable();
	this.add(new ClockPanel(t,"Tokyo",0));
	this.add(new ClockPanel(t,"London",-9));
	this.add(new ClockPanel(t,"New York",-14));
	this.add(new ClockPanel(t,"Beijing", -1));
	this.add(new ClockPanel(t,"Paris", -7));
	this.add(new ClockPanel(t,"Los Angels", -17));
	
	t.setValue();
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.pack();
	this.setVisible(true);
    }
    public static void main(String argv[]){
	new TimexObserverFrame();
    }
}
