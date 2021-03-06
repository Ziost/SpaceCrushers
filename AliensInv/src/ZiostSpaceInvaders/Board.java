package ZiostSpaceInvaders;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	public static ArrayList<Aliens> aliensList = new ArrayList<>();
	private Aliens al;
	Player player;
	Random rand = new Random();

	public Board() {
		player = new Player();

	}

	public void born() {
		al = new Aliens(rand.nextInt(400) + 2, 0);
		aliensList.add(al);
	}

	public void draw(Graphics g) {
		for (int i = 0; i < aliensList.size(); i++) {
			Aliens a = aliensList.get(i);
			boolean draw = true;
			if (Game.inGame) {
				if (a.isVisible()) {
					if (player.bullets.size() > 0) {
						for (int j = 0; j < player.bullets.size(); j++) {
							if (a.getBounds().intersects(player.bullets.get(j))) {
								System.out.println("Collide");
								a.setVisible(false);
								player.bullets.remove(j);
								g.drawImage(
										Game.imageLoad("../explosion1.png"),
										a.getX(), a.getY(), null);
								draw = false;
							}
						}
					}
					if (player.getBounds().intersects(a.getBounds())
							|| player.getBaseBounds().intersects(a.getBounds())) {
						System.out.println("GAME OVER");
						Game.inGame = false;
					}
					if (draw) {
						g.drawImage(a.getImage(), a.getX(), a.getY(), this);
					}
				} else {
					aliensList.remove(i);
				}
			}
		}
	}

	@Override
	public void run() {
		try {
			born();
			while (true) {
				Aliens last = aliensList.get(aliensList.size() - 1);
				if (last.getY() > 50) {
					born();
				}
				for (int i = 0; i < aliensList.size(); i++) {
					Aliens a = aliensList.get(i);
					if (a.isVisible()) {
						a.move();
					} else {
						aliensList.remove(i);
					}
				}
				Thread.sleep(7);
			}
		} catch (Exception e) {
			System.err.println("Exception " + aliensList.size()
					+ e.getMessage());
		}
	}
}