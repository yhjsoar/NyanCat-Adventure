package nyancat_adventure;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ScoreBoard_GameOver extends JPanel implements KeyListener{
   private frame f;
   
   private Image img;
   
   private JLabel scoreboard_go;
   private JLabel backtomenu;
   
   private ArrayList<Rank> RankList;
   String path=System.getProperty("user.dir")+"\\scoreboard_data.csv";
   
   
   ScoreBoard_GameOver(frame f){
	   this.f = f;
	      
	   RankList=new ArrayList<Rank>();
	      
	   try {
	      InputStream fis=new FileInputStream(path);
	      Reader isr=new InputStreamReader(fis);
	      BufferedReader br=new BufferedReader(isr);
	      
	      String line;
	      int i=1;
	      while((line=br.readLine())!=null) {
	         String[] tmp=line.split(",");
	         String name=tmp[0];
	         int score=Integer.parseInt(tmp[1]);
	         RankList.add(new Rank(i++,name,score));
	      }
	      br.close();
	      isr.close();
	      fis.close();
	   	} catch(FileNotFoundException e) {
		  e.printStackTrace();
	   	} catch(IOException e) {
		  e.printStackTrace();
	   	}
	      
	   	scoreboard_go = new JLabel("< ScoreBoard >");
	   	backtomenu = new JLabel("Exit");
	   	setVisible(true);
	   	
	   	this.addKeyListener(this);
		this.setFocusable(true);
   }
   
   public void paintComponent(Graphics g) {
	   super.paintComponent(g);
	      img= new ImageIcon("background.gif").getImage();
	      g.clearRect(0,0,WIDTH,HEIGHT);
	      g.drawImage(img, 0,  0,  this);
	      g.drawImage(img, -30, 335, this);
	   
	      scoreboard_go.setOpaque(false);
	      scoreboard_go.setBorder(null);
	      scoreboard_go.setHorizontalAlignment(JLabel.CENTER);
	      scoreboard_go.setBounds(190, 00, 600, 200);
	      scoreboard_go.setFont(new Font("Half Bold Pixel-7", Font.BOLD, 60));
	      scoreboard_go.setForeground(new Color(43, 214,214));
	      this.add(scoreboard_go);
	      
	      backtomenu.setOpaque(false);
	      backtomenu.setBorder(null);
	      backtomenu.setHorizontalAlignment(JLabel.CENTER);
	      backtomenu.setBounds(300, 540, 400, 50);
	      backtomenu.setFont(new Font("Half Bold Pixel-7", Font.BOLD, 35));
	      backtomenu.setForeground(Color.WHITE);
	      this.add(backtomenu);
	      
	      int index;
	      for(int i=0;i<2;i++) {
	         for(int j=0;j<5;j++) {
	            index=i*5+j;
	            RankList.get(index).grade.setOpaque(false);
	            RankList.get(index).grade.setBorder(null);
	            RankList.get(index).grade.setHorizontalAlignment(JLabel.LEFT);
	            RankList.get(index).grade.setBounds(165+i*400, 170+70*j, 200, 50);
	            RankList.get(index).grade.setFont(new Font("Half Bold Pixel-7", Font.BOLD, 35));
	            RankList.get(index).grade.setForeground(Color.WHITE);
	            this.add(RankList.get(index).grade);
	            
	            RankList.get(index).name.setOpaque(false);
	            RankList.get(index).name.setBorder(null);
	            RankList.get(index).name.setHorizontalAlignment(JLabel.LEFT);
	            RankList.get(index).name.setBounds(235+i*400, 170+70*j, 200, 50);
	            RankList.get(index).name.setFont(new Font("Half Bold Pixel-7", Font.BOLD, 35));
	            RankList.get(index).name.setForeground(Color.WHITE);
	            this.add(RankList.get(index).name);
	            
	            RankList.get(index).score.setOpaque(false);
	            RankList.get(index).score.setBorder(null);
	            RankList.get(index).score.setHorizontalAlignment(JLabel.LEFT);
	            RankList.get(index).score.setBounds(385+i*400, 170+70*j, 200, 50);
	            RankList.get(index).score.setFont(new Font("Half Bold Pixel-7", Font.BOLD, 35));
	            RankList.get(index).score.setForeground(Color.WHITE);
	            this.add(RankList.get(index).score);
	         }
	      }
	   }
   
@Override
public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
	if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
		f.mus.stop_MainSound();
        System.exit(0);
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