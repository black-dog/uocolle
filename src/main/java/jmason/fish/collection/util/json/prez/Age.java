package jmason.fish.collection.util.json.prez;

public class Age {
	public Integer max;
	public Integer min;
	public Integer score;
	
	@Override
	public String toString() {
		return "max:min:score="+max+":"+min+":"+score+"\r\n";
	}
}
