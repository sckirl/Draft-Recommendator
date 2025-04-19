package databaseColumns;

import java.util.ArrayList;
import java.util.HashMap;

import Database.ConnectTo;
import Database.ManipulateData;
import javafx.scene.layout.HBox;

public class MsHeros extends ManipulateData{
	String abilitiesID;
	int atk, def, hp;
	String herosID;
	String roleID;
	
	public MsHeros(String table, 
			String abilitiesID, 
			int atk, int def, int hp, 
			String herosID, String roleID) {
		super("MsHeros");
		this.abilitiesID = abilitiesID;
		this.atk = atk;
		this.def = def;
		this.hp = hp;
		this.herosID = herosID;
		this.roleID = roleID;
		
	}
	
	public void toObject(String ID) {
		// make the database input into actual object
		ManipulateData data = new ConnectTo("MsHeros");
		ArrayList<String> abilitiesList = data.getData("abilitiesID");
		ArrayList<String> atkList = data.getData("atk");
		ArrayList<String> defList = data.getData("def");
		ArrayList<String> hpList = data.getData("hp");
		ArrayList<String> herosList = data.getData("herosID");
		ArrayList<String> roleList = data.getData("roleID");
		
		int idx;
		
		for (int i = 0; i < herosList.size(); i++) {
            if (herosList.get(i).equalsIgnoreCase(ID)) {
                idx = i;
            }
		}
		
		return;
		
	}
	
	@Override
	public void sendData() {
		HashMap<String,String> res = new HashMap<>();
		
		res.put("abilitiesID", abilitiesID);
		res.put("atk", String.valueOf(atk));
		res.put("def", String.valueOf(def));
		res.put("hp", String.valueOf(hp));
		res.put("herosID", herosID);
		res.put("roleID", roleID);
		
		this.insertData(res);
	}

	public String getAbilitiesID() {
		return abilitiesID;
	}


	public void setAbilitiesID(String abilitiesID) {
		this.abilitiesID = abilitiesID;
	}


	public int getAtk() {
		return atk;
	}


	public void setAtk(int atk) {
		this.atk = atk;
	}


	public int getDef() {
		return def;
	}


	public void setDef(int def) {
		this.def = def;
	}


	public int getHp() {
		return hp;
	}


	public void setHp(int hp) {
		this.hp = hp;
	}


	public String getHerosID() {
		return herosID;
	}


	public void setHerosID(String herosID) {
		this.herosID = herosID;
	}


	public String getRoleID() {
		return roleID;
	}


	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
	
	
	
}
