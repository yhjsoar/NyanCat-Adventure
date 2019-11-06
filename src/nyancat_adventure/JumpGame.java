package nyancat_adventure;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JumpGame extends JPanel implements KeyListener{
	private frame f;
	
	private Image img;
	private Image nyancat;
	private Image yee;
	
	private int yee_h=0;
	
	private int nyanH, nyanW;
	private int nyanX, nyanY;
	
	int ySpeed = 0;
	int gravity = 1;
	
	int score = 0;
	public static int scoreaddition = 1;

	ArrayList<Block> BlockList = new ArrayList<Block>();
	ArrayList<Block> BlockListSave = new ArrayList<Block>();
	Block BlockSave;
	private boolean bs;
	private boolean[] nowX;
	private boolean[] nowXY;
	private boolean[][] Xitem;
	
	private boolean jumping = false;
	
	ArrayList<Item> ItemList = new ArrayList<Item>();
	private int itemi;
	
	private String[] nyan_file;
	
	private JLabel Soundon;
	private JLabel Soundoff;
	private int musico = 1;
	JumpGame(frame f){
		setLayout(null);
		this.f = f;
		
		nyan_file = new String[8];
		nyan_file[0] = "nyan.gif";
		nyan_file[1] = "nyan_pika.gif";
		nyan_file[2] = "nyan_pencil.gif";
		nyan_file[3] = "nyan_gameboy.gif";
		nyan_file[4] = "nyan_santa.gif";
		nyan_file[5] = "nyan_zombie.gif";
		nyan_file[6] = "nyan_sq.gif";
		nyan_file[7] = "¼ö¿ìdn¿ï.png";
		
		nyancat = new ImageIcon("nyan.gif").getImage();
		nyanH = nyancat.getHeight(this);
		nyanW = nyancat.getWidth(this);
		nyanX = 400+nyanW;
		nyanY = 505+nyanH;
		
		yee = new ImageIcon("YEE.png").getImage();
		
		Soundon = new JLabel(new ImageIcon("sound_on.png"));
		Soundon.setOpaque(false);
		Soundon.setBorder(null);
		Soundon.setSize(60, 60);
		Soundon.setLocation(850, 20);
		Soundon.setVisible(true);
		this.add(Soundon);
		
		Soundoff = new JLabel(new ImageIcon("sound_off.png"));
		Soundoff.setOpaque(false);
		Soundoff.setBorder(null);
		Soundoff.setSize(60, 60);
		Soundoff.setLocation(850, 20);
		Soundoff.setVisible(false);
		this.add(Soundoff);
		
		nowX = new boolean[700];
		nowXY = new boolean[700];
		Xitem = new boolean[nyanW][];
		for(int i=0;i<700;i++) {
			Xitem = new boolean[i][700];
		}
		
		int x = 0;
		for(int i=0;i<100;i++) {
			BlockList.add(new Block(x, 550, "background", this));
			x+=1097;
		}
		x=59*10;
		for(int i=0;i<400;i++) {
			Random r = new Random();
			int value = r.nextInt(20);
			if(value == 1 || value == 0) {
				BlockList.add(new Block(x, 500, "huddle1", this));
			}else if(value == 2) {
				BlockList.add(new Block(x, 450, "huddle2", this));
			}
			x+=59;
		}
		x=59*10;
		for(int i=0;i<300;i++) {
			Random r= new Random();
			int value = r.nextInt(3);
			if(value==0) {
				ItemList.add(new Item(x, 370, this));
			}
			x+=59*8;
		}
		this.addKeyListener(this);
		this.setFocusable(true);
		this.setVisible(true);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		img = new ImageIcon("background.gif").getImage();
		g.clearRect(0,0,WIDTH,HEIGHT);
		g.drawImage(img, 0,  0,  this);
		g.drawImage(img, -30, 335, this);
		
		score+=scoreaddition;
		g.setFont(new Font("Half Bold Pixel-7", Font.PLAIN, 30));
		g.setColor(Color.WHITE);
		g.drawString("SCORE: "+score, 50, 50);
		
		for(int i=0;i<BlockList.size();i++) {
			BlockList.get(i).paintComponent(g);			
		}
		for(int i=0;i<ItemList.size();i++) {
			ItemList.get(i).paintComponent(g);
		}
		g.drawImage(nyancat, nyanX-nyanW, nyanY-nyanH, this);
		bs = false;
		for(int i=0;i<700;i++) {
			nowX[i]=false;
			nowXY[i]=false;
		}
		for(int i=0;i<nyanW;i++) {
			for(int j=0;j<700;j++) {
				Xitem[i][j] = false;
			}
		}
		for(int i=0;i<BlockList.size();i++) {
			if(BlockList.get(i).getBlockX()<=nyanX&&BlockList.get(i).getBlockX2()>=nyanX) {
				for(int j=BlockList.get(i).getBlockY();j<=BlockList.get(i).getBlockY2();j++){
					if(i>=10) bs = true;
					BlockSave = BlockList.get(i);
					nowX[j] = true;
				}
			}
			if(BlockList.get(i).getBlockX()<=(nyanX-nyanW+10)&&BlockList.get(i).getBlockX2()>=(nyanX-nyanW+10)) {
				for(int j=BlockList.get(i).getBlockY();j<=BlockList.get(i).getBlockY2();j++){
					nowXY[j] = true;
				}
			}
		}
		itemi=-1;
		
		for(int j=0;j<nyanW;j++) {
			for(int i=0;i<ItemList.size();i++) {
				if(ItemList.get(i).getItemX()<=nyanX-j&&ItemList.get(i).getItemX2()>=nyanX-j) {
					for(int k=ItemList.get(i).getItemY();k<=ItemList.get(i).getItemY2();k++) {
						Xitem[j][k] = true;
					}
					itemi = i;
				}
			}
		}
		if(nyanX<0) {
			if(yee_h==0) {
				f.effect = new MainSound("yee.wav", false);
				f.effect.play_MainSound();
			}
			yee_h+=5;
			g.drawImage(yee, 450-yee_h, 300-yee_h, 450+yee_h, 300+yee_h, 0, 0, yee.getWidth(this), yee.getHeight(this), this);
			
			if(yee_h>=300) {
				f.change("gameover");
			}
		}
		if(collideitem(nyanY)) {
			getItem(ItemList.get(itemi).getitem());
			ItemList.remove(itemi);
		}
		if(collideX(nyanY)) {
			if(bs) {
				nyanX = BlockSave.getBlockX();
			}
		}
		if(jumping) {
			nyanY+=ySpeed;
			ySpeed+=gravity;
			
			if(nowXY[nyanY]) {
				ySpeed = 0;
				jumping = false;
				for(int i=300;i<700;i++) {
					if(nowXY[i]==true) {
						nyanY = i;
						break;
					}
				}
			}
		}
		else if(!collideY(nyanY)) {
			nyanY += ySpeed;
			ySpeed += gravity;
			if(nowXY[nyanY]) {
				ySpeed = 0;
				jumping = false;
				for(int i=300;i<700;i++) {
					if(nowXY[i]==true) {
						nyanY = i;
						break;
					}
				}
			}
		}
		
	}
	public boolean collideX(int nowy) {
		if(nowy>700) {
			f.change("gameover");
		}
		if(nowX[nowy+3]) {
			return true;
		}
		else return false;
	}
	public boolean collideY(int nowy) {
		if(nowy>700) {
			f.change("gameover");
		}
		if(nowXY[nowy+3])
			return true;
		else return false;
	}
	public boolean collideitem(int nowy) {
		if(nowy>700) return false;
		for(int i=20;i<nyanW-20;i++) {
			for(int j=nowy-nyanH+20;j<=nowy-20;j++) {
				if(Xitem[i][j])
					return true;
			}
			
		}
		return false;
	}
	public void scoreReset() {
		score=0;
	}
	public int getScore() {
		return score;
	}
	public void multiplyScore(int multiplier, int delay){
		try {
			scoreaddition=multiplier;
			Thread.sleep(delay);
			scoreaddition=1;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void addScore(int num) {
		score+=num;
	}
	public void MoveStart() {
		for(int i=0;i<BlockList.size();i++) {
			BlockList.get(i).start();
		}
		for(int i=0;i<ItemList.size();i++) {
			ItemList.get(i).start();
		}
	}
	public void NyanReset() {
		nyanX = 400+nyanW;
		nyanY = 505+nyanH;
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==KeyEvent.VK_ESCAPE) {
			if(musico==1) {
				sof();
			}else {
				so();
			}
		}
		if(arg0.getKeyCode()==KeyEvent.VK_SPACE) {
			if(!nowXY[nyanY]) return;
			ySpeed = -16;
			jumping = true;
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
	public void change_nyan(int pos) {
		if(pos==7) {
			Random r = new Random();
			int value= r.nextInt(8);
			nyancat = new ImageIcon(nyan_file[value]).getImage();
			nyancat = new ImageIcon(nyan_file[pos]).getImage();	
		}
		else {
			nyancat = new ImageIcon(nyan_file[pos]).getImage();	
		}
		
		nyanH = nyancat.getHeight(this);
		nyanW = nyancat.getWidth(this);
		nyanX = 400+nyanW;
		nyanY = 505+nyanH;
	}
	public void getItem(String nowitem) {
		if(nowitem.equals("candy")) {
			score+=100;
		}else if(nowitem.equals("fast")) {
			f.BLOCK_SPEED+=3;
			Timer t = new Timer(true);
			TimerTask task1 = new MyTimeTask();
			t.schedule(task1, 10000);
		}else if(nowitem.equals("slow")) {
			if(f.BLOCK_SPEED>=4) {
				f.BLOCK_SPEED-=3;
				Timer t = new Timer(true);
				TimerTask task2 = new MyTimeTask2();
				t.schedule(task2, 10000);
			}
		}else if(nowitem.equals("mul2")) {
			scoreaddition=2;
			Timer t = new Timer(true);
			TimerTask task3 = new MyTimeTask3();
			t.schedule(task3, 10000);
		}else if(nowitem.equals("mul3")) {
			scoreaddition=3;
			Timer t = new Timer(true);
			TimerTask task4 = new MyTimeTask3();
			t.schedule(task4, 10000);
		}
	}
	public void so() {
		Soundoff.setVisible(false);
		Soundon.setVisible(true);
		f.mus.play_MainSound();
		musico = 1;
	}
	public void sof() {
		Soundon.setVisible(false);
		Soundoff.setVisible(true);
		f.mus.stop_MainSound();
		musico = -1;
	}
}

class MyTimeTask extends TimerTask{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		frame.BLOCK_SPEED-=3;
	}
	
}
class MyTimeTask2 extends TimerTask{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		frame.BLOCK_SPEED+=3;
	}
	
}
class MyTimeTask3 extends TimerTask{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		JumpGame.scoreaddition = 1;
	}
	
}
