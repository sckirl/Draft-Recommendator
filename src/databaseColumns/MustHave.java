package databaseColumns;

import java.util.HashMap;

import Database.ManipulateData;

public class MustHave extends ManipulateData{
	boolean AoE;
	boolean blink;
	boolean crowdControl;
	boolean damage;
	boolean heal;
	boolean mobility;
	boolean mustHave;
	public MustHave(boolean aoE, boolean blink, boolean crowdControl, boolean damage, boolean heal, boolean mobility,
			boolean mustHave) {
		super("MustHave");
		AoE = aoE;
		this.blink = blink;
		this.crowdControl = crowdControl;
		this.damage = damage;
		this.heal = heal;
		this.mobility = mobility;
		this.mustHave = mustHave;
	}
	
	@Override
	public void sendData() {
		HashMap<String,String> res = new HashMap<>();
		// TODO potential problem, boolean.toString might be true or false, but input is 1 or 0
		
		res.put("AoE", Boolean.toString(AoE));
		res.put("blink", Boolean.toString(blink));
		res.put("crowdControl", Boolean.toString(crowdControl));
		res.put("damage", Boolean.toString(damage));
		res.put("heal", Boolean.toString(heal));
		res.put("mobility", Boolean.toString(mobility));
		res.put("mustHave", Boolean.toString(mustHave));
		
		this.insertData(res);
	}
	
	public boolean isAoE() {
		return AoE;
	}
	public void setAoE(boolean aoE) {
		AoE = aoE;
	}
	public boolean isBlink() {
		return blink;
	}
	public void setBlink(boolean blink) {
		this.blink = blink;
	}
	public boolean isCrowdControl() {
		return crowdControl;
	}
	public void setCrowdControl(boolean crowdControl) {
		this.crowdControl = crowdControl;
	}
	public boolean isDamage() {
		return damage;
	}
	public void setDamage(boolean damage) {
		this.damage = damage;
	}
	public boolean isHeal() {
		return heal;
	}
	public void setHeal(boolean heal) {
		this.heal = heal;
	}
	public boolean isMobility() {
		return mobility;
	}
	public void setMobility(boolean mobility) {
		this.mobility = mobility;
	}
	public boolean isMustHave() {
		return mustHave;
	}
	public void setMustHave(boolean mustHave) {
		this.mustHave = mustHave;
	}
	
	
}
