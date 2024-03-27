 package Main;

import javax.swing.ImageIcon;

public class Enemy {
	public int x , y;
	
	public  ImageIcon draw() {
		return new ImageIcon(new MCharacter().getClass().getResource("/pic/creeper.png"));
	}
	public Enemy(int x) {
		this.x = x;
		this.y = 102;
	}
	
}
