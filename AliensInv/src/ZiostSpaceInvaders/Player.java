package ZiostSpaceInvaders;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Player implements Runnable {

	private static final int MAX_BULLETS = 5;
	int x, y, xDirection, id;
	int bx, by;
	boolean readyToFire, shot = false;
	private static Image image;
	ArrayList<Rectangle> bullets;
	private int width = 40;
	private int height = 10;

	public Player() {
		readyToFire = true;
		x = 175;
		y = 575;
		bullets = new ArrayList<Rectangle>();
	}

	public void draw(Graphics g) {
		g.drawImage(Game.imageLoad("../player.png"),  x, y, null);
//		g.setColor(Color.RED);
//		g.fillRect(x, y + 30, 100, 15);
//		g.fillRect(x+45, y, 10, 40);
		for (int i = 0; i < bullets.size(); i++) {
			g.drawImage(Game.imageLoad("../r.png")
					,bullets.get(i).x,bullets.get(i).y,null);
		}
//		g.setColor(Color.RED);
//		for (int i = 0; i < bullets.size(); i++) {
//			g.fillRect(bullets.get(i).x, bullets.get(i).y,
//					bullets.get(i).width, bullets.get(i).height);
//		}
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y + 30, 100, 15);
	}
	public Rectangle getBaseBounds(){
		return new Rectangle(x+45,y,10,40);
	}

	public void shoot() {
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).y--;
		}
	}

	public void move() {
		x += xDirection;
		if (x <= 20)
			x = 20;
		if (x >= 454)
			x = 454;
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == e.VK_LEFT) {
			setXDirection(-1);
		}
		if (keyCode == e.VK_RIGHT) {
			setXDirection(1);
		}
		if (keyCode == e.VK_SPACE) {
			if (bullets.size() < MAX_BULLETS) {
			}
			if (readyToFire) {
				by = y - 5;
				bx = x ;
				Rectangle bullet = new Rectangle(bx , by, 2, 20);
				bullets.add(bullet);
				Rectangle bullet2 = new Rectangle(bx+85, by, 2, 20);
				bullets.add(bullet2);
			}
		}
	}
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == e.VK_LEFT) {
			setXDirection(0);
		}
		if (keyCode == e.VK_RIGHT) {
			setXDirection(0);
		}
		if (keyCode == e.VK_SPACE) {
			for (int i = 0; i < bullets.size(); i++) {
				if (bullets.get(i).y < -5) {
					bullets.remove(i);
				}
			}
		}
	}

	public Player(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setXDirection(int xdir) {
		this.xDirection = xdir;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				shoot();
				move();

				Thread.sleep(7);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}