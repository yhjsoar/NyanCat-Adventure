package nyancat_adventure;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;

public class frame extends JFrame{
	public static int BLOCK_SPEED = 10;
	public static final int SLEEP_TIME = 30;
	public select sel = null;
	public option op = null;
	public JumpGame jg = null;
	public ScoreBoard sb = null;
	public ScoreBoard_GameOver sb_go = null;
	public GameOver go = null;
	public Instruction is = null;
	public Select_Character sc = null;
	MainSound mus;
	MainSound effect;
	public String nowPanel;
	
	public ArrayList<Rank> RankList;
	public frame() {
		this.setTitle("nyancat Adventure");
		sel = new select(this);
		op = new option(this);
		jg = new JumpGame(this);
		sb = new ScoreBoard(this);
		
		go = new GameOver(this);
		is = new Instruction(this);
		sc = new Select_Character(this);
		this.add(sel);
		this.nowPanel = "game";
		this.setLocation(500, 200);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image ig = tk.getImage("yee2.png");
		Cursor myCursor = tk.createCustomCursor(ig, new Point(10, 10), "dynamite stick");
		setCursor(myCursor);
		
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 700);
		this.setVisible(true);
	}
	
	
	public void change(String panelName) {
		if(panelName.equals("option")) {
			nowPanel = "option";
			getContentPane().removeAll();
			getContentPane().add(op);
			revalidate();
			repaint();
			op.setFocusable(true);              
	        op.requestFocus();
		} 
		else if(panelName.equals("JumpStart")) {
			nowPanel = "jump";
			jg.NyanReset();
			getContentPane().removeAll();
			getContentPane().add(jg);
			revalidate();
			repaint();
			jg.setFocusable(true);
			jg.requestFocus();
			jg.MoveStart();
		} else if(panelName.equals("select_character")) {
			getContentPane().removeAll();
			getContentPane().add(sc);
			revalidate();
			repaint();
			sc.setFocusable(true);             
	        sc.requestFocus();
		} else if(panelName.equals("manual")) {
			getContentPane().removeAll();
			getContentPane().add(is);
			revalidate();
			repaint();
			is.setFocusable(true);             
	        is.requestFocus();
		} else if(panelName.equals("score")) {
			nowPanel = "score";
			getContentPane().removeAll();
			getContentPane().add(sb);
			revalidate();
			repaint();
			sb.setFocusable(true);             
	        sb.requestFocus();
		} else if(panelName.equals("score_over")) {
			nowPanel = "score_over";
			getContentPane().removeAll();
			sb_go = new ScoreBoard_GameOver(this);
			getContentPane().add(sb_go);
			revalidate();
			repaint();
			sb_go.setFocusable(true);             
	        sb_go.requestFocus();
		} else if(panelName.equals("gameover")){
			nowPanel = "gameover";
			getContentPane().removeAll();
			getContentPane().add(go);
			revalidate();
			repaint();
			go.setFocusable(true);             
	        go.requestFocus();
		} else {
			nowPanel = "select";
			getContentPane().removeAll();
			getContentPane().add(sel);
			revalidate();
			repaint();
			sel.setFocusable(true);              
	        sel.requestFocus();
		}
	}
	
	public static void main(String[] args) {
		frame f = new frame();
		Server server = new Server();
		server.start();
	}
}
