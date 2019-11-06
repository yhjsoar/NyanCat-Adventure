package nyancat_adventure;

public class ResultObject implements Comparable<ResultObject>,java.io.Serializable{
	String name;
	int score;
	
	ResultObject(String name, int score){
		this.name = name;
		this.score = score;
	}
	public String getName() {
		return name;
	}
	public int getScore() {
		return score;
	}
	@Override
	   public int compareTo(ResultObject r) {
	      if(this.score>r.score)
	         return -1;
	      else if(this.score==r.score)
	         return 0;
	      else
	         return 1;
	   }
}
