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


public class select extends JPanel implements KeyListener{
	private JLabel label[];
	private frame f;
	private Image img;
	private JLabel rainbow;
	private JLabel nca;
	
	
	private int pos=0;
	

	select(frame f){
		
		this.f = f;
		setLayout(null);
		
		rainbow = new JLabel(new ImageIcon("rainbow.png"));
		rainbow.setSize(1000,50);
		rainbow.setLocation(0, 300+pos*80);
		rainbow.setVisible(true);
		this.add(rainbow);
		
		nca = new JLabel(new ImageIcon("NCA.png"));
		nca.setSize(1000,200);
		nca.setVisible(true);
		this.add(nca);
		nca.setLocation(-5, 30);
		
		label = new JLabel[4];
		label[0] = new JLabel("start");
		label[1] = new JLabel("option");
		label[2] = new JLabel("Scoreboard");
		label[3] = new JLabel("quit");
		
		
		f.mus = new MainSound("nyancat_music.wav", true);
		f.mus.play_MainSound();
		
		this.addKeyListener(this);
		this.setFocusable(true);
		setVisible(true);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		img = new ImageIcon("background.gif").getImage();
		if (img != null) {
			g.clearRect(0,0,WIDTH,HEIGHT);
			g.drawImage(img, 0,  0,  this);
			g.drawImage(img, -30, 335, this);
			for(int i=0;i<4;i++) {
				label[i].setOpaque(false);
				label[i].setBorder(null);
				label[i].setHorizontalAlignment(JLabel.CENTER);
				label[i].setBounds(300, 300+80*i, 400, 50);
				label[i].setFont(new Font("Half Bold Pixel-7", Font.PLAIN, 42));
				label[i].setForeground(Color.WHITE);
				this.add(label[i]);
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == KeyEvent.VK_DOWN) {
			if(pos==3) {
				pos=0;
			}
			else pos++;
			rainbow.setLocation(0, 300+80*pos);
			rainbow.setVisible(true);
			f.effect = new MainSound("click.wav", false);
			f.effect.play_MainSound();
		}
		else if(arg0.getKeyCode() == KeyEvent.VK_UP) {
			if(pos==0) {
				pos=3;
			}
			else pos--;
			rainbow.setLocation(0, 300+80*pos);
			rainbow.setVisible(true);
			f.effect = new MainSound("click.wav", false);
			f.effect.play_MainSound();
		}
		else if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			if(pos==0) {
				f.change("select_character");
			}else if(pos==1) {
				f.change("option");
			}else if(pos==2) {
				f.change("score");
			}else if(pos==3) {
				f.mus.stop_MainSound();
				System.exit(0);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
