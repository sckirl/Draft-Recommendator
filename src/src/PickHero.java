package src;
import java.util.ArrayList;

import Database.ConnectTo;
import Database.ManipulateData;

public class PickHero extends Main{
	
	 ArrayList<String> heroList = new ArrayList();
	 ManipulateData heros = new ConnectTo("MsHeros");
	
	public PickHero() {
		super();
		
		for (String heroName : heros.getData("heroName")) {
			this.heroList.add(heroName);
		}
		
	}
	

}
