package ZiostSpaceInvaders;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class Game extends JFrame  {
	Image dbImage;
	Graphics dbg;
	public static Boolean inGame = true;
	static Image image = null;
	public static int Width = 500;
	public static int Height = 650;
	public static Board board;
	private static Image bg;
	JPanel gamePanel = new JPanel();
	public Game()  {		
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
	  public static Image bgImageLoad(String s){
		  try {
			bg = ImageIO.read(Game.class.getResource(s));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return bg;
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
		if(inGame){
		dbImage = bgImageLoad("../1.png");
		}
		else {
			dbImage = bgImageLoad("../gameOver.png");
		}
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