package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class MCharacter implements ActionListener{
	
	private  boolean forward;
	private  Timer t;
	public  ImageIcon draw(int x) {
		if(x == 0)
			if(forward)
				return new ImageIcon(new MCharacter().getClass().getResource("/pic/run1.png"));
			else
				return new ImageIcon(new MCharacter().getClass().getResource("/pic/run2.png"));
		else if(x == 1)
			return new ImageIcon(new MCharacter().getClass().getResource("/pic/jump up.png"));
		else if(x == -1)
			return new ImageIcon(new MCharacter().getClass().getResource("/pic/jump down.png"));
		else if(x == 4)
			return new ImageIcon(new MCharacter().getClass().getResource("/pic/lost.png"));
		else if(x == 5)
			return new ImageIcon(new MCharacter().getClass().getResource("/pic/main.png"));
		else 
			return null;
			
	}
	
	public MCharacter(){
		t = new Timer(150, this);
		t.start();
	}
	
	public boolean getF() {
		return forward;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		forward = !forward;
	}
}
