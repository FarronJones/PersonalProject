//package
package Main;
//imports
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//KeyHandler class
public class KeyHandler implements KeyListener {
	//declare booleans to use for keyPressed and keyReleased
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	//keyPressed method
	@Override
	public void keyPressed(KeyEvent e) {
		//return number of key thats pressed
		int code = e.getKeyCode();
		//if user press W key
		if(code == KeyEvent.VK_W) {
			upPressed = true;
		}//end if
		//if user press S key
		if(code == KeyEvent.VK_S) {
			downPressed = true;
					
		}//end if
		//if user press A key
		if(code == KeyEvent.VK_A) {
			leftPressed = true;
		}//end if
		//if user press D key
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
		}//end if
		
	}//end keyPressed method
	//keyReleased method
	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		//if user press W key
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}//end if
		//if user press S key
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}//end if
		//if user press A key
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}//end if
		//if user press D key
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}//end if
		
	}//end keyReleased method

}//end class
