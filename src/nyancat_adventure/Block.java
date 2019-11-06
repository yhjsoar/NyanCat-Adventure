package nyancat_adventure;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Block extends Thread{
	private Image BlockBasicImage;
	private String type;
	private int x, y;
	private int imgH, imgW;
	public JumpGame jg;
	
	public Block(int x, int y, String type, JumpGame jg) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.jg = jg;
	
	}
	
	public void paintComponent(Graphics g) {
		if(type.equals("background")) {
			BlockBasicImage= new ImageIcon("block.png").getImage();
		}else if(type.equals("huddle1")) {
			BlockBasicImage= new ImageIcon("blocksmall1.png").getImage();
		}else {
			BlockBasicImage= new ImageIcon("blocksmall2.png").getImage();
		}
		imgH = BlockBasicImage.getHeight(jg);
		imgW = BlockBasicImage.getWidth(jg);
		g.drawImage(BlockBasicImage, x, y, null);
	}
	
	public void move() {
		x -= frame.BLOCK_SPEED;
	}
	
	public int getBlockX() {
		return x;
	}
	public int getBlockY() {
		return y;
	}
	public int getBlockX2() {
		return x+imgW;
	}
	public int getBlockY2() {
		return y+imgH;
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
