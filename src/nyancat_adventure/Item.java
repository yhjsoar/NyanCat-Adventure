package nyancat_adventure;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Item extends Thread{
	private Image item;
	private int x, y;
	private int imgH, imgW;
	public JumpGame jg;
	
	private String[] itemfile;
	private String nowitem;
	
	public Item(int x, int y, JumpGame jg) {
		this.x =x;
		this.y = y;
		this.jg = jg;
		
		itemfile = new String[5];
		Random r = new Random();
		int value = r.nextInt(100);
		if(value<=70&&value>=0) {
			item = new ImageIcon("candy.png").getImage();
			nowitem = "candy";
		}
		else if(value>70&&value<=85) {
			item = new ImageIcon("fast.png").getImage();
			nowitem = "fast";
		}
		else if(value>85&&value<=92) {
			item = new ImageIcon("slowdown.png").getImage();
			nowitem = "slow";
		}
		else if(value>92&&value<97) {
			item = new ImageIcon("score2.png").getImage();
			nowitem = "mul2";
		}
		else {
			item = new ImageIcon("score3.png").getImage();
			nowitem = "mul3";
		}
	}
	
	public void move() {
		x-=frame.BLOCK_SPEED;
	}
	
	public void paintComponent(Graphics g) {
		imgH = item.getHeight(jg);
		imgW = item.getWidth(jg);
		g.drawImage(item, x, y,null);
	}
	
	public int getItemX() {
		return x;
	}
	public int getItemY() {
		return y;
	}
	public int getItemX2() {
		return x+imgW;
	}
	public int getItemY2() {
		return y+imgH;
	}
	public String getitem() {
		return nowitem;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				move();
				Thread.sleep(frame.SLEEP_TIME);
			}
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
