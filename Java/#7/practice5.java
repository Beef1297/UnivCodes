import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class RadixObservable extends Observable {
    private int ten;
    public void setValue(String text,int radix){
	ten = Integer.parseInt(text, radix);
	setChanged();
	notifyObservers();
    }
    public int getValue(){
	return ten;
    }
    
}
class RadixPanel extends JPanel implements Observer,ActionListener{
    private int radix;
    private RadixObservable radixObservable;
    private JTextField radix_tfield;
    private JLabel radix_label;
    public RadixPanel(int rad, RadixObservable ro){
	radixObservable = ro; this.radix = rad;
	radixObservable.addObserver(this);
	radix_tfield = new JTextField(5);
	radix_label = new JLabel(Integer.toString(rad));
	this.setLayout(new GridLayout(1,2));
	this.add(radix_label);
	this.add(radix_tfield);
	radix_tfield.addActionListener(this);
    }
    public void update(Observable o, Object arg){
	radix_tfield.setText(Integer.toString(radixObservable.getValue(),radix));
    }
    public void actionPerformed(ActionEvent ev){
	radixObservable.setValue(radix_tfield.getText(), radix);
    }
}

class RadixFrame extends JFrame{
    private JFrame f;
    private JTextField hex,dec;
    
    public RadixFrame(){
	this.setTitle("Radix Frame");
	RadixObservable r = new RadixObservable();
	this.setLayout(new GridLayout(4,2));
	this.add(new RadixPanel(10, r));
	this.add(new RadixPanel(16, r));
	this.add(new RadixPanel(8, r));
	this.add(new RadixPanel(2, r));
	this.pack();
	
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
    }
    
 
    public static void main(String argv[]){
	new RadixFrame();
    }
}
	
		 
