package entities;

public class Player {
	
	String name;
	long highScore;
	long overallScore;
	
	public Player (String n, long hs, long os){
		name = n;
		highScore = hs;
		overallScore = os;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getHighScore() {
		return highScore;
	}

	public void setHighScore(long highScore) {
		this.highScore = highScore;
	}

	public long getOverallScore() {
		return overallScore;
	}

	public void setOverallScore(long overallScore) {
		this.overallScore = overallScore;
	}
	
	public String toStringNoComma(){
		String n = String.format("%.20s", name);
		String info = String.format(" %-25s%,-18d%,d", n,highScore,overallScore);

		return info;
	}
	
	@Override
	public String toString(){
		return name + "," + highScore + "," + overallScore;
		
	}

}
