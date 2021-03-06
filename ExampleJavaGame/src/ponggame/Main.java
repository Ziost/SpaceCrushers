package ponggame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class Main extends JFrame{
	//Double buffering
	Image dbImage;
	Graphics dbg;
	
	//Ball object
	static Ball b = new Ball(193, 143);
	
	int GHEIGHT = 300;
	int GWIDTH = 400;
	
	Dimension screenSize = new Dimension(GWIDTH, GHEIGHT);
	
	public class AL extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent e){
			b.p1.keyPressed(e);
			b.p2.keyPressed(e);
		}
		
		@Override
		public void keyReleased(KeyEvent e){
			b.p1.keyReleased(e);
			b.p2.keyReleased(e);
		}
	}
	
	@Override
	public void paint(Graphics g){
		dbImage = createImage(getWidth(), getHeight());
		dbg = dbImage.getGraphics();
		draw(dbg);
		g.drawImage(dbImage, 0, 0, this);
	}
	
	public void draw(Graphics g){
		b.draw(g);
		
		b.p1.draw(g);
		b.p2.draw(g);
		
		g.setColor(Color.WHITE);
		g.drawString(""+b.p1Score, 15, 50);
		g.drawString(""+b.p2Score, 370, 50);
		repaint();
	}
	
	public Main(){
		//Game
		this.setTitle("Pong game");
		this.setSize(screenSize);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.DARK_GRAY);
		addKeyListener(new AL());
	}
	
	public static void main(String[] args) {
		Main m = new Main();
		
		Thread ball = new Thread(b);
		ball.start();
		Thread p1 = new Thread(b.p1);
		Thread p2 = new Thread(b.p2);
		p1.start();
		p2.start();
	}
}
