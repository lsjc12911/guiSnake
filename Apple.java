package NOFPSsnake;

import java.awt.Color;
import java.awt.Graphics2D;

public class Apple {
	
	private int xx, yy;
	private int width = 10, height = 10;
	
	boolean up, down, left, right;
	
	public Apple(int xCoor, int yCoor) {
		xx = xCoor;
		yy = yCoor;
	}

	public int getxx() {
		return xx;
	}

	public void setxx(int xx) {
		this.xx = xx;
	}

	public int getyy() {
		return yy;
	}

	public void setyy(int yy) {
		this.yy = yy;
	}
	
	public void drawApple(Graphics2D g2) {
		g2.setColor(Color.red);
		g2.fillRect(xx * width, yy * height, width, height);
	}
	

}
