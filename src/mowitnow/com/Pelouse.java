package mowitnow.com;

public class Pelouse {
	
	private static Pelouse instance = null;
	
	//default values
	private int maxY = 100;
	private int maxX = 100;
	
	public int getMaxY() {
		return maxY;
	}

	public int getMaxX() {
		return maxX;
	}
	
	private Pelouse() {}
	
	public void init(int x, int y) {
		this.maxX = x;
		this.maxY = y;
	}

    public static Pelouse getInstance() {
      if(instance == null) {
         instance = new Pelouse();
      }
      return instance;
    }
	
	
	
}
