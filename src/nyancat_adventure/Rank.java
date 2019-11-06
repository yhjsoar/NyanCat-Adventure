package nyancat_adventure;

import javax.swing.JLabel;

public class Rank {
	JLabel grade;
	JLabel name;
	JLabel score;
	
	Rank(int grade, String name, int score){
		this.grade=new JLabel(Integer.toString(grade));
		this.name=new JLabel(name);
		this.score=new JLabel(Integer.toString(score));
	}
	
}
