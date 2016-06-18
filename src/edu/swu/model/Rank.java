package edu.swu.model;

public class Rank {
	private String RankID;
	private String RankName;
	private String MateID;
	private String MateConsume;
	
	public Rank() {
		super();
	}
	public Rank(String rankID) {
		super();
		RankID = rankID;
	}
	
	public Rank(String rankID, String rankName, String mateID, String mateConsume) {
		super();
		RankID = rankID;
		RankName = rankName;
		MateID = mateID;
		MateConsume = mateConsume;
	}
	public String getRankID() {
		return RankID;
	}
	public void setRankID(String rankID) {
		RankID = rankID;
	}
	public String getRankName() {
		return RankName;
	}
	public void setRankName(String rankName) {
		RankName = rankName;
	}
	public String getMateID() {
		return MateID;
	}
	public void setMateID(String mateID) {
		MateID = mateID;
	}
	public String getMateConsume() {
		return MateConsume;
	}
	public void setMateConsume(String mateConsume) {
		MateConsume = mateConsume;
	}
	
}
