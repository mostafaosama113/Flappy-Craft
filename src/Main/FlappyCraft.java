package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class FlappyCraft extends JFrame implements WindowListener{

	static FlappyCraft game;
	static GamePanel gamePanel;
	private int x , y;
	
	public static void main(String [] args) {
		game = new FlappyCraft();
		
	}
	
	FlappyCraft(){
		addWindowListener(this);	
		setLayout(null);
		setSize(500,190);
		setTitle("Flappy craft");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setOpacity(0);
		setResizable(false);
		///////////////////////////////bar////////////////////////////
		JPanel bar = new JPanel();
		bar.setBackground(Color.white);
		bar.setBounds(0,0,500,40);
		bar.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				x = e.getX();
				y = e.getY();
			}
			@Override public void mouseReleased(MouseEvent arg0) {}
			@Override public void mouseExited(MouseEvent arg0) {}
			@Override public void mouseEntered(MouseEvent arg0) {}
			@Override public void mouseClicked(MouseEvent arg0) {}
		});
		bar.addMouseMotionListener(new MouseMotionListener() {
			
			@Override public void mouseMoved(MouseEvent arg0) { }
			@Override
			public void mouseDragged(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e))
					setLocation(e.getXOnScreen() - x , e.getYOnScreen() - y);
			}
		});
		JLabel exit = new JLabel("X ");
		bar.setLayout(new BorderLayout());
		bar.add(exit , BorderLayout.EAST);
		exit.setFont(new Font("Comic Sans MS" , Font.BOLD , 25));
		exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		exit.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				for(float i = 1 ; i >= 0 ; i-=0.02) {
					setOpacity(i);
					try {Thread.sleep(2);}catch (Exception e) {}
				}
				System.exit(0);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				exit.setForeground(Color.black);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				exit.setForeground(Color.gray);	
			}
			@Override public void mouseReleased(MouseEvent arg0) {}
			@Override public void mouseClicked(MouseEvent arg0) {}
		});
		///////////////////////////////bar////////////////////////////
		gamePanel = new GamePanel();
		gamePanel.setBounds(0,40,500,150);
		gamePanel.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				x = e.getX();
				y = e.getY();
			}
			@Override public void mouseReleased(MouseEvent arg0) {}
			@Override public void mouseExited(MouseEvent arg0) {}
			@Override public void mouseEntered(MouseEvent arg0) {}
			@Override public void mouseClicked(MouseEvent arg0) {}
		});
		gamePanel.addMouseMotionListener(new MouseMotionListener() {
			
			@Override public void mouseMoved(MouseEvent arg0) { }
			@Override
			public void mouseDragged(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e))
					setLocation(e.getXOnScreen() - x , e.getYOnScreen() - y-40);
			}
		});
		add(bar);
		add(gamePanel);
		setVisible(true);
	}

	

	@Override
	public void windowClosing(WindowEvent arg0) {
		for(float i = 1 ; i >= 0 ; i -= 0.02) {
			setOpacity(i);
			try {Thread.sleep(2);}catch (Exception e) {}
		}
		System.exit(0);
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		for(float i = 0 ; i <= 1 ; i += 0.02) {
			setOpacity(i);
			try {Thread.sleep(2);}catch (Exception e) {}
		}
	}
	@Override public void windowActivated(WindowEvent arg0) {}
	@Override public void windowClosed(WindowEvent arg0) {}
	@Override public void windowDeactivated(WindowEvent arg0) {}
	@Override public void windowDeiconified(WindowEvent arg0) {}
	@Override public void windowIconified(WindowEvent arg0) {}
}
