package Recommendations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import Database.ConnectTo;
import Database.ManipulateData;

public class ScoreSystem {
	/*
	 * Disclaimer: To make this clear, the minmax should be in MsHero object but turns out
	 * reading the database and making object of off every single hero in database is time consuming
	 * so this file reads directly from the database and needs this documentation for reading the HashMap
	 * 
	 * Get every single data from ManipulateData.getAllData();
	 * returns HashMap<String, ArrayList> 
	 * to call atk just call hero.get("atk").get(index of hero)
	 * ArrayList containing every single values from every single hero. 
	 * 
	 * Each methods in this file outputs indexes, indicating heros placement in the arraylist
	 * so to read every hero translate from heroID -> hero index
	 * 
	 * ex: get atk
	 * hero.get("atk").get(idx)
	 *
	 * i promise this is the most efficient way to do this, im sorry.
	 * TURNS OUT ITS NOT EFFICIENT. MINIMAX IS FAR FROM EFFICIENT
	 * also ignore the failed multithreading implementation
	 *
	 */
	
	static Double totalScore;
	
	String heroID;
	
	
	private ManipulateData heroData = new ConnectTo("MsHeros");
	private ManipulateData abilityData = new ConnectTo("MsAbilities");
	private ManipulateData mustHaveData = new ConnectTo("MustHave");
	private ManipulateData counterData = new ConnectTo("MsCounter");
	
	public static ArrayList<String> IDList = new ArrayList<String>();
	public static ArrayList<String> nameList = new ArrayList<String>();
	public static HashMap<String, String> idName = new HashMap<String, String>();
	public static HashMap<String, String> nameID = new HashMap<String, String>();
	public static HashMap<String, Integer> idIDX = new HashMap<String, Integer>();
	int res = 1;
	
	ArrayList<String> abilities = abilityData.getAllData().get("abilitiesID");
	
	
	private HashMap<String, ArrayList<String>> allData;

    public ScoreSystem() {
		super();
		IDList = heroData.getData("herosID");
		nameList = heroData.getData("heroname");
		allData = heroData.getAllData(); 
		
		
		for (int i = 0; i < IDList.size(); i++) {
			idName.put(IDList.get(i), nameList.get(i));
			nameID.put(nameList.get(i), IDList.get(i));
			
			idIDX.put(IDList.get(i), i);
		}
		
	}
    

	public String toID(int idx) {
    	return IDList.get(idx);
    }
    
    public static String toName(int idx) {
    	return nameList.get(idx);
    }
    
    public static String toName(String id) {
    	return idName.get(id);
    }


	private double getScore(String heroID) {
		// weighted score thing
		int idx = idIDX.get(heroID);

    	// calculate the score of each hero with percentage, for each ability importance
    	int atk = Integer.parseInt(allData.get("atk").get(idx));
    	int def = Integer.parseInt(allData.get("def").get(idx));
    	int hp = Integer.parseInt(allData.get("hp").get(idx));
		
    	// TODO make this totalScore to also make importance in counter
    	totalScore = (
            (atk * 0.4) +   // atk has 40% importance
            (def * 0.15) +  // def has 15% importance
            (hp * 0.10) + // hp has 10% importance
            (mustHave(heroID) * 0.40)  // 40% importance point if hero is mustHave
            //(canCounter(heroID) * 0.25) // 25% if can counter
        );
    	
    	// this print effects efficiency thing too, i cant even do formatting
    	System.out.println(heroID + " @idx" + idx + " : " + totalScore);
    	return totalScore;
    }
	
	
	// Make the selection of minimax of heros selection, the top heros will be in arraylist
	// get the values from minimax and get the best n heros
	public ArrayList<String> topHeros(int numTopHeros, int depth, ArrayList<String> idList) {
		// Use a TreeMap to automatically sort heroes by their scores. credit:
		// https://stackoverflow.com/questions/39607814/efficient-way-to-sort-keys-of-hashmap-java
		
		// Collect top n heroes
	    Map<Double, String> heroScores = new TreeMap<>(Collections.reverseOrder());
	    ArrayList<String> topHeroes = new ArrayList<>();
	    
    	double score = 0;
    	for (String hero : idList) {
	    	// Store the hero with its score
	    	score = minimax(hero, depth, true, idList);
	    	
	    	
	    	// GET ONLY THE TOP 3
		    // TODO the heroScores literally only added 2 heros, why idk
	    	heroScores.put(score, hero);
	    }
	    
	    for (String hero : heroScores.values()) {
	    	topHeroes.add(hero);
	    }
	    		  
	    
	    System.out.println("Top " + numTopHeros +" heroes: " + topHeroes);
	    
	    return topHeroes;
	}
    
    // minimax algorithm
    // HUGE credit to https://youtu.be/l-hh51ncgDI
    public double minimax(String hero, 
    		int depth, 
    		boolean maximizePlayer, 
    		ArrayList<String> idList) {
    	
        // depth = 0. get the score, this is the baseline score to work on 
        if (depth == 0) {
            return getScore(hero);
        }
        
        // maximizing player's turn, find the MAXIMUM score
        if (maximizePlayer) {
            double maxScore = Double.NEGATIVE_INFINITY;
            
            for (String heroID : idList) {
                if (heroID.equals(hero))
                	continue; // make sure not self-scoring hero and not checking chosen heros
                
                // get MAX score
                double score = minimax(heroID, depth - 1, false, idList);
                maxScore = Math.max(maxScore, score);
                
            }
            
            return maxScore;
        } 
        
        // minimizing player's turn, find the MINIMUM score
        else {
            double minScore = Double.POSITIVE_INFINITY;

            for (String heroID : idList) {
                if (heroID.equals(hero))
                	continue;
                
                // get MIN score
                double score = minimax(heroID, depth - 1, true, idList);
                minScore = Math.min(minScore, score);
            }
            
            return minScore;
        }
    }
    
    private int canCounter(String ID) {
    	int res = 1;
		int i = 0;
    	HashMap<String, String> counterMap = new HashMap<>();

        for (String counter : counterData.getData("counterAbility")) {
            
            counterMap.put(counter, 
            		this.counterData.getData("abilityToCounter").get(i));
            i++;
        }

        // Check if the hero's abilities can counter the target hero's abilities
        for (String ability : allData.get("herosID")) {
            String counterList = counterMap.get(ability);
            if (counterList != null && counterList.contains(ID)) {
            	res += 1000;
            }

        };
    	
		return res;
	}

    private int mustHave(String ID) {
    	// im so sorry in advance for the world to see this abomination of code
        int res = 1;
        Set<String> mustHave = mustHaveData.getAllData().keySet();

        int idx = this.idIDX.get(ID); 
        
        ArrayList<String> abilities= this.abilityData.getData("ability1");
        
        String[] p = this.abilityData.getData("passive").get(this.idIDX.get(ID)).split(" ");
    	String[] a1 = this.abilityData.getData("ability1").get(this.idIDX.get(ID)).split(" ");
    	String[] a2 = this.abilityData.getData("ability2").get(this.idIDX.get(ID)).split(" ");
    	String[] a3 = this.abilityData.getData("ability3").get(this.idIDX.get(ID)).split(" ");
    	
    	// this is very cancerous way to add arrays together,but it might just work
    	String[] AllAbilityData= new String[p.length +
                                            a1.length +
                                            a2.length +
                                            a3.length ];
    	
    	System.arraycopy(p, 0, AllAbilityData, 0, p.length);
    	System.arraycopy(a1, 0, AllAbilityData, p.length, a1.length);
    	System.arraycopy(a2, 0, AllAbilityData, p.length + a1.length, a2.length);
    	System.arraycopy(a3, 0, AllAbilityData, p.length + a1.length + a2.length, a3.length);

        for (String ability : AllAbilityData) {
        	if(mustHave.contains(ability))
                res += 1000;
            }
  
        return res;
    }
    
}
