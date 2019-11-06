package nyancat_adventure;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOver extends JPanel implements KeyListener{
	private frame f;
	
	private Image img;
	
	private JLabel GameOver;
	private JLabel yourScore;
	
	private JLabel Continue;
	private Image enter;
	
	int namenum=0;
	String name="";
	
	GameOver(frame f){
		this.f = f;
		
		GameOver = new JLabel("Game Over");
		
		yourScore = new JLabel("Your Score: "+f.jg.score);
		
		enter = new ImageIcon("enter_icon.png").getImage();
		Continue = new JLabel("Continue");
		
		
		this.addKeyListener(this);
		this.setFocusable(true);
		setVisible(true);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		img = new ImageIcon("background.gif").getImage();
		g.clearRect(0,0,WIDTH,HEIGHT);
		g.drawImage(img, 0,  0,  this);
		g.drawImage(img, -30, 335, this);
		
		g.setFont(new Font("Half Bold Pixel-7", Font.PLAIN, 40));
		g.setColor(Color.WHITE);
		if(namenum==0) {
			g.drawString("Enter Your Name: ___"+name, 240, 450);
		} else if(namenum == 1) {
			g.drawString("Enter Your Name: "+name+"__", 240, 450);
		} else if(namenum==2) {
			g.drawString("Enter Your Name: "+name+"_", 240, 450);
		} else if(namenum==3) {
			g.drawString("Enter Your Name: "+name, 240, 450);
		}
		
		GameOver.setOpaque(false);
		GameOver.setBorder(null);
		GameOver.setHorizontalAlignment(JLabel.CENTER);
		GameOver.setBounds(100, 150, 800, 100);
		GameOver.setFont(new Font("Half Bold Pixel-7", Font.BOLD, 80));
		GameOver.setForeground(new Color(43, 214,214));
		this.add(GameOver);
		
		Continue.setOpaque(false);
		Continue.setBorder(null);
		Continue.setBounds(780, 585, 200, 40);
		Continue.setFont(new Font("Half Bold Pixel-7", Font.PLAIN, 25));
		Continue.setForeground(Color.white);
		this.add(Continue);
		g.drawImage(enter, 920, 580, this);
		
		yourScore.setText("Your Score: "+f.jg.score);
		yourScore.setOpaque(false);
		yourScore.setBorder(null);
		yourScore.setHorizontalAlignment(JLabel.CENTER);
		yourScore.setBounds(180, 350, 600, 40);
		yourScore.setFont(new Font("Half Bold Pixel-7", Font.BOLD, 40));
		yourScore.setForeground(Color.white);
		this.add(yourScore);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(namenum==3) {
				ResultObject ro = new ResultObject(name, f.jg.score);
				Client c = new Client(ro);
				c.run();
				f.change("score_over");
			}
		}
		else if(e.getKeyCode() >= KeyEvent.VK_A && e.getKeyCode() <= KeyEvent.VK_Z) {
			if(namenum<3) {
				char ch;
				int num;
				num = e.getKeyCode()-KeyEvent.VK_A+'A';
				ch = (char) num;
				name+=Character.toString(ch);
				namenum++;
			}
		}
		else if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
			if(namenum>0) {
				name = name.substring(0,--namenum);
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}