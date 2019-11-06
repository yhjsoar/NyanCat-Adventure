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

public class option extends JPanel implements KeyListener{
	private frame f;
	private Image img;
	
	private JLabel nca;
	private JLabel on;
	private JLabel off;
	private JLabel rainbow;
	private JLabel right;
	private JLabel left;
	
	private String[] musiclist;
	private int nowmusic;
	private int musico;
	
	private JLabel[] label;
	
	private int pos=0;
	
	option(frame f){
		
		
		setLayout(null);
		this.f = f;
		
		img = new ImageIcon("background.gif").getImage();
		nca = new JLabel(new ImageIcon("NCA.png"));
		rainbow = new JLabel(new ImageIcon("rainbow.png"));
		on = new JLabel(new ImageIcon("option_on.png"));
		off = new JLabel(new ImageIcon("option_off.png"));
		right = new JLabel(new ImageIcon("right.png"));
		left = new JLabel(new ImageIcon("left.png"));
		
		nca.setSize(1000,200);
		nca.setVisible(true);
		this.add(nca);
		nca.setLocation(-5, 30);
		
		rainbow.setLocation(0, 380+80*pos);
		rainbow.setSize(1000,50);
		rainbow.setVisible(true);
		this.add(rainbow);
		
		on.setSize(96,  50);
		on.setVisible(true);
		on.setLocation(525, 375);
		this.add(on);
		
		off.setSize(96,  50);
		off.setVisible(false);
		off.setLocation(525, 375);
		this.add(off);
		
		right.setSize(29, 50);
		right.setVisible(true);
		right.setLocation(650, 460);
		this.add(right);
		
		left.setSize(29, 50);
		left.setVisible(true);
		left.setLocation(325, 460);
		this.add(left);
		
		musico=1; 
		nowmusic = 0;
		
		label = new JLabel[4];
		label[0] = new JLabel("option");
		label[1] = new JLabel("Music");
		label[2] = new JLabel("original");
		label[3] = new JLabel("back");
		
		musiclist = new String[7];
		musiclist[0] = "nyancat_music.wav";
		musiclist[1] = "nyancat_piano.wav";
		musiclist[2] = "nyancat_orchestrate.wav";
		musiclist[3] = "nyancat_rock.wav";
		musiclist[4] = "nyancat_metal.wav";
		musiclist[5] = "nyancat_extreme.wav";
		musiclist[6] = "nyancat_mario.wav";
	
		this.addKeyListener(this);
		this.setFocusable(true);
		setVisible(true);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (img != null) {
			g.clearRect(0,0,WIDTH,HEIGHT);
			g.drawImage(img, 0,  0,  this);
			g.drawImage(img, -30, 335, this);
			
			for(int i=0;i<4;i++) {
				label[i].setOpaque(false);
				label[i].setBorder(null);
				label[i].setHorizontalAlignment(JLabel.CENTER);
				if(i==1) {
					label[i].setBounds(330, 300+80*i, 200, 50);
				}else {
					label[i].setBounds(300, 300+80*i, 400, 50);
				}
				label[i].setFont(new Font("Half Bold Pixel-7", Font.PLAIN, 42));
				if(i==0) {
					label[i].setForeground(new Color(43, 214,214));
				}else {
					label[i].setForeground(Color.WHITE);
				}
				this.add(label[i]);
			}
		}
	}
	public void change_music(int nownum) {
		switch(nownum){
		case 0:
			label[2].setText("original");
			break;
		case 1:
			label[2].setText("piano");
			break;
		case 2:
			label[2].setText("orchestra");
			break;
		case 3:
			label[2].setText("rock");
			break;
		case 4:
			label[2].setText("metal");
			break;
		case 5:
			label[2].setText("extreme");
			break;
		case 6:
			label[2].setText("mario");
			break;
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			if(pos==2) {
				pos=0;
			}
			else pos++;
			rainbow.setLocation(0, 380+80*pos);
			rainbow.setVisible(true);
			f.effect = new MainSound("click.wav", false);
			f.effect.play_MainSound();
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP) {
			if(pos==0) {
				pos=2;
			}
			else pos--;
			rainbow.setLocation(0, 380+80*pos);
			rainbow.setVisible(true);
			f.effect = new MainSound("click.wav", false);
			f.effect.play_MainSound();
		}
		else if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			if(pos==0) {
				if(musico == 1) {
					f.mus.stop_MainSound();
					musico=0;
					f.jg.sof();
					on.setVisible(false);
					off.setVisible(true);
				}
				else if(musico ==0) {
					f.mus.play_MainSound();
					musico=1;
					f.jg.so();
					off.setVisible(false);
					on.setVisible(true);
				}
			}else if(pos==2) {
				f.change("select");
			}
		}
		else if(pos==1) {
			if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
				if(nowmusic == 6) {
					nowmusic = 0;
				}
				else {
					nowmusic++;
				}
				f.mus.stop_MainSound();
				f.mus = new MainSound(musiclist[nowmusic], true);
				f.mus.play_MainSound();
				change_music(nowmusic);
				if(musico ==0) {
					musico=1;
					off.setVisible(false);
					on.setVisible(true);
				}
			} else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
				if(nowmusic == 0) {
					nowmusic = 6;
				}
				else {
					nowmusic--;
				}
				f.mus.stop_MainSound();
				f.mus = new MainSound(musiclist[nowmusic], true);
				f.mus.play_MainSound();
				change_music(nowmusic);
				if(musico ==0) {
					musico=1;
					off.setVisible(false);
					on.setVisible(true);
				}
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
