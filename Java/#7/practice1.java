import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

class StringObservable extends Observable {
    private int value = 0;
    public String getValue(int n){
	return Integer.toString(value + n);
	//基数をいれないと10進数となる
    }
    public void setValue(String s, int n){
	value = Integer.parseInt(s,10) - n;
	//同じく10進数とされる.
	setChanged();
	notifyObservers();
    }
}
//obserableクラスと
class TextFieldObserver extends JTextField implements Observer,ActionListener{
    private StringObservable stringObservable;
    private int num;
    //位置を覚えておくならインスタンス変数にすればいい
    //そのオブジェクト特有の値はインスタンス変数にいれる
    public TextFieldObserver(StringObservable so,int n){
	num = n;
	stringObservable = so;
	stringObservable.addObserver(this);
	this.setFont(new Font(Font.SANS_SERIF, Font.BOLD,26));
	this.addActionListener(this);
    }
    public void update(Observable o,Object arg){
	String s = stringObservable.getValue(num);
	setText(s);
	/*
	  updateするときつまりgetValueすると
	  obserable(subject)に入ってる値に自分の
	  位置番号を足して得られる.
	*/
    }
    public void actionPerformed(ActionEvent e){
	String s = this.getText();
	stringObservable.setValue(s,num);
	/*
	  setValueするとobserable(subject)に一番上に入るべき値
	  が保存される.
	*/
    }
}
//observerになるクラスを作る
class ObserverFrame extends JFrame{
    public ObserverFrame(){
	this.setTitle("Observer Frame");
	this.setLayout(new GridLayout(5, 1));
	StringObservable s = new StringObservable();
	for(int i = 0;i < 5;i++){
	    this.add(new TextFieldObserver(s,i));
	}
	s.setValue("100",4);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.pack();
	this.setVisible(true);
    }
    public static void main(String argv[]){
	new ObserverFrame();
    }
}
