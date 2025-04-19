package src;

import java.util.ArrayList;
import Database.ConnectTo;
import Database.ManipulateData;
import Recommendations.ScoreSystem;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Recommendations{
	final int RECOMMENDATION_CIRCLES = 2;
	final int MINMAX_DEPTH = 1;
	
	ManipulateData hero = new ConnectTo("MsHeros");
	ManipulateData ability = new ConnectTo("MsAbilities");
	ScoreSystem scoreSystem = new ScoreSystem();
	private static ArrayList<String> idList = new ArrayList<String>();

	public Recommendations() {
		super();
	}

	@SuppressWarnings("exports")
	public HBox createRecommendCircle(Pos position, ArrayList<String> idList) {
		ArrayList<String> topHeros = scoreSystem.topHeros(RECOMMENDATION_CIRCLES, MINMAX_DEPTH, idList); // numOfHeros, depth
		
    	// trust me on this idk man
		int initalSize = 40;
        HBox recDisplay = new HBox(10);
        recDisplay.getChildren().add(new Circle());
        recDisplay.getChildren().add(new Circle());
        recDisplay.getChildren().add(new Circle());
        
        recDisplay.setAlignment(position);
        recDisplay.setPadding(new Insets(3));
        
        for (int j = 1; j <= RECOMMENDATION_CIRCLES; j++) {
        	String names = ScoreSystem.toName(topHeros.get(j-1));
        	
        	// make the recommendation hero smaller, depending on their placement
        	 Circle heroRecommendation = new Circle(initalSize, Color.MIDNIGHTBLUE); // Placeholder for banned hero
        	 initalSize -= 10;
        	 
        	 // Create a new ImageView with the new hero image
        	 Image heroImg = new Image(String.format("file:./assets/%s_Crop.jpg", 
        			 names));
        	 
             heroRecommendation.setFill(new ImagePattern(heroImg));
             heroRecommendation.setStroke(Color.CORNFLOWERBLUE);
             heroRecommendation.setStrokeWidth(3);
             
             if (position.toString().equals("CENTER_LEFT")) {
            	 // go from 3 to 0, smaller to bigger 
            	 recDisplay.getChildren().set(3-j, heroRecommendation);
             } else {
            	 // go from 0 to 3, bigger to smaller
            	 recDisplay.getChildren().set(j-1, heroRecommendation);
             } 
            	 
		} 

        return recDisplay;
    }

}
