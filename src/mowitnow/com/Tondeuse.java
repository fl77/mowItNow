package mowitnow.com;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import mowitnow.exception.IllegalCardinalException;
import mowitnow.exception.IllegalPositionException;

public class Tondeuse {
	
	private Pelouse pelouse;
	
	private int currentY;

	private int currentX;
	
	private int currentAngle;
	
	private  Map<String, Integer> mapOrientation;

	/**
	 * mower Contructor
	 */
	public Tondeuse(){		
		pelouse = Pelouse.getInstance();
		populateMapOrientation();
	}
	
	/**
	 * maps the cardinal points into angles
	 */
	private void populateMapOrientation() {
		mapOrientation = new HashMap<>();
		mapOrientation.put("E", 0);
		mapOrientation.put("N", 90);
		mapOrientation.put("W", 180);
		mapOrientation.put("S", 270);
	}

	public int getCurrentX() {
		return currentX;
	}
	
	public void setCurrentX(int currentX) {
		this.currentX = currentX;
	}

	public int getCurrentY() {
		return currentY;
	}

	public void setCurrentY(int currentY) {
		this.currentY = currentY;
	}

	/**
	 * gets the current cardinal point,  
	 * @return
	 */
	public String getCurrentCardinal() {
		for (Entry<String,Integer> entry : mapOrientation.entrySet()) {
	        if (currentAngle == entry.getValue()) {
	            return entry.getKey();
	        }
	    }
		return null;
	}

	/**
	 * Sets the current angle from a cardinal point
	 * @param string for a cardinal point ("N","E","S", "W")
	 * @throws Exception 
	 */
	public void setCurrentAngle(String cardinal) {
		this.currentAngle = mapOrientation.get(cardinal);
	}

	/**
	 * Sets the mower initial position
	 * @param currentX - integer position at X axis
	 * @param currentY - integer position at Y axis
	 * @param cardinal - String for the cardinal point
	 * @throws IllegalCardinalException 
	 * @throws IllegalPositionException 
	 * @throws Exception 
	 */
	public void setCurrentPosition (int currentX, int currentY,  String cardinal) throws IllegalCardinalException, IllegalPositionException  {
		if (!"N".equals(cardinal) && !"E".equals(cardinal) && !"S".equals(cardinal) && !"W".equals(cardinal))
			throw new IllegalCardinalException("This Cardinal Point does not exists");
		else if (currentX > pelouse.getMaxX()|| currentX < 0 || currentY > pelouse.getMaxX() ||
				currentY < 0)
			throw new IllegalPositionException("Mower Position not allowed");
		
		setCurrentX(currentX);
		setCurrentY(currentY);
		setCurrentAngle(cardinal);
	}
	
	/**
	 * Executes a sequence of instructions from a line
	 * @param instructionsLine
	 */
	public void executeInstructionLine(String instructionsLine){
		if (instructionsLine == null)
			return;
		
		for (int i = 0; i< instructionsLine.length(); i++) {
			executeInstruction(instructionsLine.charAt(i));
		}
	}
	
	
	/**
	 * 
	 * @param c instruction value
	 * 			A : move forward
	 * 			D : turn right
	 * 			G : turn left
	 */
	private void executeInstruction(char c){
		switch(c){
		case 'A':
			move();
			break;
		case 'D':
			turn(true);
			break;
		case 'G':
			turn (false);
			break;
		}
	}

	
	/**
	 * move the its angle to the right or to the left 
	 * @param toTheRight - true if it should move to the right, false to the left
	 */
	private void turn(boolean toTheRight) {
		if (toTheRight){
			this.currentAngle -= 90;
		} else{
			this.currentAngle += 90;
		}
		
		if(this.currentAngle >= 360 ) 
			this.currentAngle = 360 - this.currentAngle;
		else if (this.currentAngle < 0)
			this.currentAngle = 360 + this.currentAngle;
	}

	/**
	 * move one space according to its orientation without passing the limits defined 
	 */
	private void move() {
		switch (currentAngle)
		{
		case 0:
			if (currentX < pelouse.getMaxX())
				currentX += 1;
			break;
		case 90:
			if (currentY < pelouse.getMaxY())
				currentY += 1;
			break;
		case 180:
			if (currentX > 0)
				currentX -= 1;
			break;
		case 270:
			if (currentY > 0)
				currentY -= 1;
			break;
		}
	}
	
	/**
	 * Returns the current position
	 * @return String containing current position X Y Cardinal 
	 */
	public String getCurrentPositionString() {
		return currentX + " " + currentY + " " + getCurrentCardinal();
	}
	
}
