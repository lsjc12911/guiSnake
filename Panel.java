package NOFPSsnake;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Panel extends JPanel implements KeyListener {
	
	boolean up = false, down = false, left = false, right = false;
	
	ArrayList<Body> snake;
	ArrayList<Apple> apple;
	Body b;
	Apple a;
	
	int appleCount = 1;
	int eatCount;;
	
	int score;
	
	int prevX, prevY;
	
	Timer timer;
	
	public Panel() {
		snake = new ArrayList<>();
		apple = new ArrayList<>();
		b = new Body(10, 10);
		snake.add(b);
		
		genApple();
		
		setFocusable(true);
		addKeyListener(this);
		
		timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				setHead();
				checkBorder();
				ate();
//				checkDeath();
				repaint();
			}
		}, 0, 60);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		//drawLine (시작x, 시작y, x길이, 연결 끝지점 y)
		for(int i = 0; i <= 500/10; i++) {
			g2.drawLine(0, i * 10, 860, i * 10);
		}
		for(int i = 0; i <= 860/10; i++) {
			g2.drawLine(i * 10, 0, i * 10, 500);
		}
		for(int i = 0; i < snake.size(); i++) {
			snake.get(i).drawSnake(g2);
		}
		for(int i = 0; i < apple.size(); i++) {
			apple.get(i).drawApple(g2);
		}
	}
	
	public void genApple() {
		Random ran = new Random();
		
		int j = apple.size();
		while(j < appleCount) {
			int ranX = ran.nextInt(86);
			int ranY = ran.nextInt(50);
			int exitCount = 0;
			
			for(int i = 0; i < snake.size(); i++) {
				if(ranX != snake.get(i).getxx()) exitCount += 1;
				if(ranY != snake.get(i).getyy()) exitCount += 1;
			}
				if(exitCount == snake.size() * 2) {
				a = new Apple(ranX, ranY);
				apple.add(a);
				j += 1;
				}
//			if(eatCount < 80) {
//				a = new Apple(eatCount, 0);
//				apple.add(a);
//				j += 1;
//			}else {
//				eatCount = 0;
//				a = new Apple(eatCount, 1);
//				apple.add(a);
//				j += 1;
//			}
		}
	}
	
	public void checkDeath() {
		for(int i = 1; i < snake.size(); i++) {
			if(snake.get(0).getxx() == snake.get(i).getxx() && snake.get(0).getyy() == snake.get(i).getyy()) {
				JOptionPane.showMessageDialog(null, "오요 내쉐키 내쉐키 ㅠㅠ 잘했오요!!");
				timer.cancel();
			}
		}
	}
	
	
	public void checkBorder() {
		if(snake.get(0).getxx() < 0) {
			snake.get(0).setxx(85);
		}
		if(snake.get(0).getxx() > 85) {
			snake.get(0).setxx(0);
		}
		if(snake.get(0).getyy() < 0) {
			snake.get(0).setyy(49);
		}
		if(snake.get(0).getyy() > 49) {
			snake.get(0).setyy(0);
		}
	}
	
	
	public void setHead() {
		prevX = snake.get(snake.size()-1).getxx();
		prevY = snake.get(snake.size()-1).getyy();
		setBody();
		if(up) snake.get(0).setyy(snake.get(0).getyy() - 1);
		if(down) snake.get(0).setyy(snake.get(0).getyy() + 1);
		if(left) snake.get(0).setxx(snake.get(0).getxx() - 1);
		if(right) snake.get(0).setxx(snake.get(0).getxx() + 1);
	}
	
	public void setBody() {
		if(snake.size() > 1) {
			for(int i = snake.size()-1; i > 0; i--) {
				snake.get(i).setxx(snake.get(i-1).getxx());
				snake.get(i).setyy(snake.get(i-1).getyy());
			}
		}
	}
	
	public void ate() {
		int sX = snake.get(0).getxx();
		int sY = snake.get(0).getyy();
		
		for(int i = 0; i < apple.size(); i++) {
			if(sX == apple.get(i).getxx() && sY == apple.get(i).getyy()) {
				apple.remove(i);
				score += 10;
				eatCount += 1;
				System.out.println("eatCount: " + eatCount);
				if(eatCount > 0) {
					if(eatCount % 10 == 0 && appleCount < 5) {
						appleCount += 1;
					}
				}
				genApple();
				b = new Body(prevX, prevY);
				snake.add(b);
				System.out.println(snake.size());
				System.out.println(apple.size());
				break;
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP && down != true) {
//			System.out.println("b.up" + b.up);
			up = true;
			down = false;
			left = false;
			right = false;
			repaint();
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN && up != true) {
//			System.out.println("b.down" + b.down);
			up = false;
			down = true;
			left = false;
			right = false;
			repaint();
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT && right != true) {
//			System.out.println("b.left" + b.left);
			up = false;
			down = false;
			left = true;
			right = false;
			repaint();
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT && left != true) {
//			System.out.println("b.right" + b.right);
			up = false;
			down = false;
			left = false;
			right = true;
			repaint();
		}
	}
	
	public void keyTyped(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
}
