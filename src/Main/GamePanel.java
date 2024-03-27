package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener , KeyListener{
	
	private ImageIcon floor , cloud;
	private int floor1 = 0 , floor2  = 700;
	private int cloud1x = 20 , cloud2x  = 230, cloud3x = 400;
	private int cloud1y = 0 , cloud2y  = 7, cloud3y = 5;
	private int Kinesthetic = 0;
	private Timer timer;
	public MCharacter character;
	private int CharacterY = 99;
	private boolean delay = false;
	private ArrayList<Enemy> enemies;
	
	
	GamePanel(){
		setBackground(Color.white);
		setPreferredSize(new Dimension(500,150));
		floor = new ImageIcon(this.getClass().getResource("/pic/floor.png"));
		cloud = new ImageIcon(this.getClass().getResource("/pic/cloud.png"));
		timer = new Timer(50, this);
		character = new MCharacter();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		enemies = new ArrayList<>();
		enemies.add(new Enemy(520));
		enemies.add(new Enemy(900));
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		floor.paintIcon(this, g, floor1, 140);
		floor.paintIcon(this, g, floor2, 140);
		cloud.paintIcon(this, g, cloud1x, cloud1y);
		cloud.paintIcon(this, g, cloud2x, cloud2y);
		cloud.paintIcon(this, g, cloud3x, cloud3y);
		if(timer.isRunning())
			character.draw(Kinesthetic).paintIcon(this, g, 15 - (((!character.getF() &&
					Kinesthetic == 0 || Kinesthetic == -1 ) )? 5 : 0) , CharacterY -= Kinesthetic*19);
		else
			character.draw(5).paintIcon(this, g, 15  , 99);
		if(CharacterY < 20) {
			Kinesthetic = -1;
		}
		if(CharacterY == 100) {
			Kinesthetic = 0;
			CharacterY = 99;
			delay = false;
		}
		for(Enemy e : enemies)
			e.draw().paintIcon(this, g, e.x, e.y);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		floor1 -= 20;
		for(Enemy en : enemies) 
			en.x -= 15;
		if(enemies.get(0).x < -50 || enemies.size() == 1) {
			enemies.remove(0);
			enemies.add(new Enemy(900));
		}
		if(new Rectangle( 15 - (((!character.getF() &&
				Kinesthetic == 0 || Kinesthetic == -1 ) )? 5 : 0), CharacterY, character.draw(Kinesthetic).getIconWidth()-((Kinesthetic == 0)?0:5)
					, character.draw(Kinesthetic).getIconHeight()).intersects(new Rectangle(
							enemies.get(0).x+5, enemies.get(0).y, 25, 38))) 
		{
			
			System.out.println("you lost !");
			timer.stop();
			reset();
			return;
		}
		if(floor1 <= -700)
			floor1 = 700;
		floor2 -= 20;
		if(floor2 <= -700)
			floor2 = 700;
		cloud1x -= 15;
		if(cloud1x < -53) {
			cloud1x = 501;
			cloud1y = new Random().nextInt(11);
		}
		cloud2x -= 15;
		if(cloud2x < -53) {
			cloud2x = 501;
			cloud2y = new Random().nextInt(11);
		}
		cloud3x -= 15;
		if(cloud3x < -53) {
			cloud3x = 501;
			cloud3y = new Random().nextInt(11);
		}
		repaint();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == 32 && !delay) {
			if(!timer.isRunning())
				timer.start();
			CharacterY = 100;
			Kinesthetic = 1;
			delay = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_R) {
			reset();
			repaint();
		}
		
	}
	public void reset() {
		timer.stop();
		floor1 = 0 ;
		floor2  = 700;
		cloud1x = 20 ; cloud2x  = 230; cloud3x = 400;
		cloud1y = 0 ; cloud2y  = 7; cloud3y = 5;
		Kinesthetic = 0;
		CharacterY = 99;
		enemies.clear();
		enemies.add(new Enemy(520));
		enemies.add(new Enemy(900));
		delay = false;
	}
	@Override public void keyReleased(KeyEvent arg0) {}
	@Override public void keyTyped(KeyEvent arg0) {}
}
