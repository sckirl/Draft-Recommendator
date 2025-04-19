package databaseColumns;

import java.util.HashMap;

import Database.ManipulateData;

public class MsCounter extends ManipulateData{
	String abilityToCounter;
	String counterAbility;
	String counterID;
	
	public MsCounter(String abilityToCounter, String counterAbility, String counterID) {
		super("MsCounter");
		this.abilityToCounter = abilityToCounter;
		this.counterAbility = counterAbility;
		this.counterID = counterID;
	}
	
	@Override
	public void sendData() {
		HashMap<String,String> res = new HashMap<>();
		
		res.put("abilityToCounter", abilityToCounter);
		res.put("counterAbility", counterAbility);
		res.put("counterID", counterID);
		
		this.insertData(res);
	}
	
	public String getAbilityToCounter() {
		return abilityToCounter;
	}
	public void setAbilityToCounter(String abilityToCounter) {
		this.abilityToCounter = abilityToCounter;
	}
	public String getCounterAbility() {
		return counterAbility;
	}
	public void setCounterAbility(String counterAbility) {
		this.counterAbility = counterAbility;
	}
	public String getCounterID() {
		return counterID;
	}
	public void setCounterID(String counterID) {
		this.counterID = counterID;
	}
	
	
}
