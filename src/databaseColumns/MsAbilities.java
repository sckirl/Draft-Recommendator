package databaseColumns;

import java.util.HashMap;

import Database.ManipulateData;

public class MsAbilities extends ManipulateData{
	String  abilitiesID;
	String ability1;
	String ability2;
	String ability3;
	String passive;
	
	public MsAbilities(String abilitiesID, String ability1, String ability2, String ability3, String passive) {
		super("MsAbilities");
		this.abilitiesID = abilitiesID;
		this.ability1 = ability1;
		this.ability2 = ability2;
		this.ability3 = ability3;
		this.passive = passive;
	}
	
	@Override
	public void sendData() {
		HashMap<String,String> res = new HashMap<>();
		
		res.put("abilitiesID", abilitiesID);
		res.put("ability1", ability1);
		res.put("ability2", ability2);
		res.put("ability3", ability3);
		res.put("passive", passive);
	
		this.insertData(res);
	}

	public String getAbilitiesID() {
		return abilitiesID;
	}

	public void setAbilitiesID(String abilitiesID) {
		this.abilitiesID = abilitiesID;
	}

	public String getAbility1() {
		return ability1;
	}

	public void setAbility1(String ability1) {
		this.ability1 = ability1;
	}

	public String getAbility2() {
		return ability2;
	}

	public void setAbility2(String ability2) {
		this.ability2 = ability2;
	}

	public String getAbility3() {
		return ability3;
	}

	public void setAbility3(String ability3) {
		this.ability3 = ability3;
	}

	public String getPassive() {
		return passive;
	}

	public void setPassive(String passive) {
		this.passive = passive;
	}
	
	
}
