package ZiostSpaceInvaders;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;



public class Game extends JFrame  {
	Image dbImage;
	Graphics dbg;
	static Image image = null;
	public static int Width = 500;
	public static int Height = 650;
	public static Board board;
	JPanel gamePanel = new JPanel();
    Image background = Toolkit.getDefaultToolkit().createImage("ha.png");
	public Game(){		
		setTitle("Crusher");
		setSize(Width,Height);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(new AL());
	}
	  public static Image imageLoad(String i){
	    	try {
				image = ImageIO.read(Game.class.getResource(i));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Error");
			}
	    	return image;
	    }
	public class AL extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e){
			board.player.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e){
			board.player.keyReleased(e);
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
		board.player.draw(g);
		Game.board.draw(g);
      	
		repaint();
	}

	public static void main(String[] args) {
		Game game = new Game();

		board = new Board();
		Thread b1 = new Thread(board);
		Thread p1 = new Thread(board.player);

		b1.start();
		p1.start();
	}
}