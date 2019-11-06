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

public class Select_Character extends JPanel implements KeyListener{
	private frame f;
	
	private Image img;
	
	private JLabel Sel;
	
	private Image[] nyan;
	private JLabel[] nyan_lab;
	private JLabel[] selec;
	private JLabel[] yellow;
	
	private Image enter;
	private Image backspace;
	
	private JLabel Continue;
	private JLabel Back;
	
	public int nyan_num=0;
	
	public int pos=0;
	
	Select_Character(frame f){
		this.f = f;
		setLayout(null);
		
		Sel = new JLabel("Select Character");
	
		enter = new ImageIcon("enter_icon.png").getImage();
		backspace = new ImageIcon("backspace_icon.png").getImage();
		
		nyan = new Image[10];
		nyan_lab= new JLabel[10];
		selec = new JLabel[10];
		yellow = new JLabel[10];
		
		Continue = new JLabel("Continue");
		Back = new JLabel("Back");
		
		nyan_lab[7] = new JLabel();
		nyan_lab[7].setOpaque(false);
		nyan_lab[7].setBorder(null);
		nyan_lab[7].setLocation(440, 350);
		nyan_lab[7].setSize(60, 70);
		this.add(nyan_lab[7]);
		yellow[7] = new JLabel(new ImageIcon("select_yellow.png"));
		yellow[7].setSize(90, 60);
		selec[7] = new JLabel(new ImageIcon("select.png"));
		selec[7].setSize(90, 60);
		yellow[7].setLocation(435, 345);
		yellow[7].setVisible(false);
		this.add(yellow[7]);
		
		selec[7].setLocation(435, 345);
		selec[7].setVisible(false);
		this.add(selec[7]);
		for(int j=0;j<7;j++) {
			nyan_lab[j] = new JLabel();
			nyan_lab[j].setOpaque(false);
			nyan_lab[j].setBorder(null);
			nyan_lab[j].setLocation(140+100*j, 250);
			this.add(nyan_lab[j]);
			
			
			if(j==6) {
				nyan_lab[j].setSize(120, 70);
				yellow[j]= new JLabel(new ImageIcon("select2_yellow.png"));
				yellow[j].setSize(140, 60);
				selec[j] = new JLabel(new ImageIcon("select2.png"));
				selec[j].setSize(140, 60);
			}
			else{
				nyan_lab[j].setSize(60, 70);
				yellow[j]= new JLabel(new ImageIcon("select_yellow.png"));
				yellow[j].setSize(80, 60);
				selec[j] = new JLabel(new ImageIcon("select.png"));
				selec[j].setSize(80, 60);
			}
			yellow[j].setLocation(135+100*j, 245);
			yellow[j].setVisible(false);
			this.add(yellow[j]);
			
			selec[j].setLocation(135+100*j, 245);
			selec[j].setVisible(false);
			this.add(selec[j]);
		}
		yellow[0].setVisible(true);
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
		
		Sel.setOpaque(false);
		Sel.setBorder(null);
		Sel.setHorizontalAlignment(JLabel.CENTER);
		Sel.setBounds(200, 20, 600, 200);
		Sel.setFont(new Font("Half Bold Pixel-7", Font.BOLD, 40));
		Sel.setForeground(new Color(43, 214,214));
		this.add(Sel);
		
		nyan[0] = new ImageIcon("nyan.gif").getImage();
		nyan[1] = new ImageIcon("nyan_pika.gif").getImage();
		nyan[2] = new ImageIcon("nyan_pencil.gif").getImage();
		nyan[3] = new ImageIcon("nyan_gameboy.gif").getImage();
		nyan[4] = new ImageIcon("nyan_santa.gif").getImage();
		nyan[5] = new ImageIcon("nyan_zombie.gif").getImage();
		nyan[6] = new ImageIcon("nyan_sq.gif").getImage();
		nyan[7] = new ImageIcon("random_nyan.png").getImage();
		
		
		
		g.drawImage(nyan[0], 140, 250, 210, 300, 0, 0, nyan[0].getWidth(this), nyan[0].getHeight(this), this);
		g.drawImage(nyan[1], 240, 250, 310, 300, 0, 0, nyan[1].getWidth(this), nyan[1].getHeight(this), this);
		g.drawImage(nyan[2], 340, 250, 410, 300, 0, 0, nyan[2].getWidth(this), nyan[2].getHeight(this), this);
		g.drawImage(nyan[3], 440, 250, 510, 300, 0, 0, nyan[3].getWidth(this), nyan[3].getHeight(this), this);
		g.drawImage(nyan[4], 540, 250, 610, 300, 0, 0, nyan[4].getWidth(this), nyan[4].getHeight(this), this);
		g.drawImage(nyan[5], 640, 250, 710, 300, 0, 0, nyan[5].getWidth(this), nyan[5].getHeight(this), this);
		g.drawImage(nyan[6], 740, 250, 860, 300, 0, 0, nyan[6].getWidth(this), nyan[6].getHeight(this), this);
		
		g.drawImage(nyan[7], 440, 350, this);
		g.drawImage(backspace, 40, 580, this);
		g.drawImage(enter, 920, 580, this);
		
		Continue.setOpaque(false);
		Continue.setBorder(null);
		Continue.setBounds(780, 585, 200, 40);
		Continue.setFont(new Font("Half Bold Pixel-7", Font.PLAIN, 25));
		Continue.setForeground(Color.white);
		this.add(Continue);
		
		Back.setOpaque(false);
		Back.setBorder(null);
		Back.setBounds(110, 585, 200, 40);
		Back.setFont(new Font("Half Bold Pixel-7", Font.PLAIN, 25));
		Back.setForeground(Color.white);
		this.add(Back);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==KeyEvent.VK_BACK_SPACE) {
			f.change("select");
		} else if(arg0.getKeyCode()==KeyEvent.VK_ENTER) {
			f.jg.change_nyan(nyan_num);
			f.change("manual");
		} else if(arg0.getKeyCode()==KeyEvent.VK_RIGHT) {
			selec[pos].setVisible(false);
			if(pos==7) {
				pos=0;
			}
			else pos++;
			if(pos != nyan_num) {
				selec[pos].setVisible(true);
			}
			f.effect = new MainSound("click.wav", false);
			f.effect.play_MainSound();
		} else if(arg0.getKeyCode()==KeyEvent.VK_LEFT) {
			selec[pos].setVisible(false);
			if(pos==0) {
				pos=7;
			}
			else pos--;
			if(pos != nyan_num) {
				selec[pos].setVisible(true);
			}
			f.effect = new MainSound("click.wav", false);
			f.effect.play_MainSound();
		} else if(arg0.getKeyCode()==KeyEvent.VK_SPACE) {
			yellow[nyan_num].setVisible(false);
			nyan_num = pos;
			yellow[nyan_num].setVisible(true);
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
