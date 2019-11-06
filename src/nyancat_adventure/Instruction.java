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

public class Instruction extends JPanel implements KeyListener{
	private frame f;
	
	private Image img;
	private Image candy;
	private Image fast;
	private Image slow;
	private Image mul2;
	private Image mul3;
	
	private JLabel Instructions;
	private JLabel Control;
	private JLabel Collectables; 
	
	private JLabel Points;
	private JLabel Mul_2;
	private JLabel Mul_3;
	private JLabel Fastup;
	private JLabel Slowdown;
	
	private Image space;
	
	private JLabel Press;
	
	private Image enter;
	private Image backspace;
	
	private JLabel Continue;
	private JLabel Back;
	Instruction(frame f){
		this.f = f;
		setLayout(null);
		
		Instructions = new JLabel("Instructions");
		Control = new JLabel("Controls:");
		Collectables = new JLabel("Collectables:");
		
		Points = new JLabel("Points");
		Mul_2 = new JLabel("Score Multiplier(*2)");
		Mul_3 = new JLabel("Score Multiplier(*3)");
		Fastup = new JLabel("Nyancat runs faster");
		Slowdown = new JLabel("Nyancat runs slower");
		
		candy = new ImageIcon("candy.png").getImage();
		fast = new ImageIcon("fast.png").getImage();
		slow = new ImageIcon("slowdown.png").getImage();
		mul2 = new ImageIcon("score2.png").getImage();
		mul3 = new ImageIcon("score3.png").getImage();
		
		space = new ImageIcon("space_icon.png").getImage();
		
		Press = new JLabel("Press");
	
		enter = new ImageIcon("enter_icon.png").getImage();
		backspace = new ImageIcon("backspace_icon.png").getImage();
		
		Continue = new JLabel("Continue");
		Back = new JLabel("Back");
		
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
		
		Instructions.setOpaque(false);
		Instructions.setBorder(null);
		Instructions.setHorizontalAlignment(JLabel.CENTER);
		Instructions.setBounds(200, 20, 600, 200);
		Instructions.setFont(new Font("Half Bold Pixel-7", Font.BOLD, 40));
		Instructions.setForeground(new Color(43, 214,214));
		this.add(Instructions);
		
		Control.setOpaque(false);
		Control.setBorder(null);
		Control.setHorizontalAlignment(JLabel.CENTER);
		Control.setBounds(50, 170, 300, 40);
		Control.setFont(new Font("Half Bold Pixel-7", Font.PLAIN, 35));
		Control.setForeground(Color.white);
		this.add(Control);
		
		g.drawImage(space, 110, 220, this);
		
		Press.setOpaque(false);
		Press.setBorder(null);
		Press.setBounds(200, 225, 400, 40);
		Press.setFont(new Font("Half Bold Pixel-7", Font.PLAIN, 25));
		Press.setForeground(Color.white);
		this.add(Press);
		
		Collectables.setOpaque(false);
		Collectables.setBorder(null);
		Collectables.setHorizontalAlignment(JLabel.CENTER);
		Collectables.setBounds(550, 170, 300, 40);
		Collectables.setFont(new Font("Half Bold Pixel-7", Font.PLAIN, 35));
		Collectables.setForeground(Color.white);
		this.add(Collectables);
		
		g.drawImage(candy, 570, 220, this);
		g.drawImage(mul2, 550, 280, this);
		g.drawImage(mul3, 550, 340, this);
		g.drawImage(fast, 573, 400, this);
		g.drawImage(slow, 573, 460, this);
		
		Points.setOpaque(false);
		Points.setBorder(null);
		Points.setBounds(660, 220, 400, 40);
		Points.setFont(new Font("Half Bold Pixel-7", Font.PLAIN, 25));
		Points.setForeground(Color.white);
		this.add(Points);
		
		Mul_2.setOpaque(false);
		Mul_2.setBorder(null);
		Mul_2.setBounds(660, 285, 400, 40);
		Mul_2.setFont(new Font("Half Bold Pixel-7", Font.PLAIN, 25));
		Mul_2.setForeground(Color.white);
		this.add(Mul_2);
	
		Mul_3.setOpaque(false);
		Mul_3.setBorder(null);
		Mul_3.setBounds(660, 345, 400, 40);
		Mul_3.setFont(new Font("Half Bold Pixel-7", Font.PLAIN, 25));
		Mul_3.setForeground(Color.white);
		this.add(Mul_3);
		
		Fastup.setOpaque(false);
		Fastup.setBorder(null);
		Fastup.setBounds(660, 405, 400, 40);
		Fastup.setFont(new Font("Half Bold Pixel-7", Font.PLAIN, 25));
		Fastup.setForeground(Color.white);
		this.add(Fastup);
		
		Slowdown.setOpaque(false);
		Slowdown.setBorder(null);
		Slowdown.setBounds(660, 465, 400, 40);
		Slowdown.setFont(new Font("Half Bold Pixel-7", Font.PLAIN, 25));
		Slowdown.setForeground(Color.white);
		this.add(Slowdown);
		
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
			f.change("select_character");
		}
		else if(arg0.getKeyCode()==KeyEvent.VK_ENTER) {
			f.change("JumpStart");
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
