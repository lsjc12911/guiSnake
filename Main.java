package NOFPSsnake;

import java.awt.Color;

import javax.swing.JFrame;

public class Main extends JFrame {
	
	Panel panel = new Panel();
	
	public Main() {
		setLayout(null);
		frameLayout();
		panelLayout();
		addKeyListener(panel);
	}
	
	public void frameLayout() {
		setTitle("Snake");
		setSize(900, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void panelLayout() {
		panel.setBounds(10, 50, 860, 500);
		panel.setBackground(Color.BLACK);
		panel.setVisible(true);
		add(panel);
	}

	public static void main(String[] args) {
		new Main();
	}
}
