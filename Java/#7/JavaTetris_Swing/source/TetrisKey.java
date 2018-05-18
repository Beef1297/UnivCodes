/***********************************************************
TetrisKey
	���̃N���X�́A�L�[���͂����m���邽�߂̃N���X�ł��B
	TetrisGUI�Ɍ��т����܂��B
	���m�����L�[���͂̏��́ATetrisProc ���Q�Ƃ��܂��B
***********************************************************/

import java.awt.event.*;

public class TetrisKey implements KeyListener {

	public boolean	UP;
	public boolean	DOWN;
	public boolean	LEFT;
	public boolean	RIGHT;
	public boolean	TURN_L;
	public boolean	TURN_R;
	public boolean	START;

	//keyTyped
	public void keyTyped(KeyEvent e){
	}
	
	//�L�[�{�[�h�������ꂽ�Ƃ��̏���
	public void keyPressed(KeyEvent e){
		
		switch(e.getKeyCode()){
			case 38 :
				UP = true;
				break;
			case 40 :
				DOWN = true;
				break;
			case 37 :
				LEFT = true;
				break;
			case 39 :
				RIGHT = true;
				break;
			case 10 :	//Enter
			case 32 :	//Space
				START = true;
				break;

		}
		switch(e.getKeyChar()){
			case 'z' :
			case 'Z' :
				TURN_L = true;
				break;
			case 'x' :
			case 'X' :
				TURN_R = true;
				break;
		}
		
		//System.out.println((int)e.character+" : "+e.keyCode);
	}//end keyPressed


	//�L�[�{�[�h�������ꂽ�Ƃ��̏���
	public void keyReleased(KeyEvent e){
		switch(e.getKeyCode()){
			case 38 :
				UP = false;
				break;
			case 40 :
				DOWN = false;
				break;
			case 37 :
				LEFT = false;
				break;
			case 39 :
				RIGHT = false;
				break;
			case 10 :	//Enter
			case 32 :	//Space
				START = false;
				break;
		}
		switch(e.getKeyChar()){
			case 'z' :
			case 'Z' :
				TURN_L = false;
				break;
			case 'x' :
			case 'X' :
				TURN_R = false;
				break;
		}
	}//end keyReleased()

}