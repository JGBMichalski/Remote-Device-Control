package client;

import java.awt.Robot;
import java.awt.event.InputEvent;

public class Mouse {
	Robot robot;
	
	public Mouse() {
		try {
			robot = new Robot();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void move(int x, int y) {
		try {
            robot.mouseMove(x, y);
 
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public void leftClick() {
        try {            
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public void rightClick() {
        try {
            robot.mousePress(InputEvent.BUTTON3_MASK);
            robot.mouseRelease(InputEvent.BUTTON3_MASK);
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public void middleClick() {
        try {
            robot.mousePress(InputEvent.BUTTON2_MASK);
            robot.mouseRelease(InputEvent.BUTTON2_MASK);
 
        } catch (Exception e) {
            e.printStackTrace();
        }
 
    }
	
	 public void doubleClick() {
	        try {
	            robot.mousePress(InputEvent.BUTTON1_MASK);
	            robot.mouseRelease(InputEvent.BUTTON1_MASK);
	            robot.mousePress(InputEvent.BUTTON1_MASK);
	            robot.mouseRelease(InputEvent.BUTTON1_MASK);
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	 
	    }
 
}
