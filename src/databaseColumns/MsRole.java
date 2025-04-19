package databaseColumns;

import java.util.HashMap;

import Database.ManipulateData;

public abstract class MsRole extends ManipulateData{
	String roleID;
	String roleTitle;
	

	public MsRole(String roleID, String roleTitle) {
		super("MsRole");
		this.roleID = roleID;
		this.roleTitle = roleTitle;
	}
	
	@Override
	public void sendData() {
		HashMap<String,String> res = new HashMap<>();
		
		res.put("roleID", roleID);
		res.put("roleTitle", roleTitle);
		
		this.insertData(res);
	}

	public String getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	public String getRoleTitle() {
		return roleTitle; 
	}

	public void setRoleTitle(String roleTitle) {
		this.roleTitle = roleTitle;
	}

}
