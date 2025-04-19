package src;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import Database.ConnectTo;
import Database.ManipulateData;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application implements EventHandler<MouseEvent>{
	// TODO split this thing into multiple classes to make it clean
	
    // Final / Absolute variables
	// this is for adjustments, settings etc
    final int WIDTH = 1370;
    final int HEIGHT = 690;
    final int AVATAR_WIDTH = 200; 
    final int AVATAR_HEIGHT = 100;
    final int HEROS_GRID_WIDTH = 510;
    final int HEROS_GRID_HEIGHT = 500;
    final int BAN_CIRCLES = 3;
    final int RECOMMEND_CIRCLES = 5;
    final int INITIAL_TIME = 30;
    
    // Colors colors colors
    final Color PRIMARY_BLUE = Color.DARKBLUE;
    final Color PRIMARY_RED = Color.DARKRED;
    final Color SECONDARY_BLUE = Color.CORNFLOWERBLUE;
    final Color SECONDARY_RED = Color.INDIANRED;
    
	// Background stuff
	Image backgroundImage = new Image("file:./assets/background.jpeg"); 
    ImageView backgroundView = new ImageView(backgroundImage);
    
    Image endImage = new Image("file:./vid/afterChoose.jpeg"); 
    ImageView endView = new ImageView(endImage);
	
	// Root Pane
    StackPane root = new StackPane();
    VBox leftTeamPanel, rightTeamPanel = new VBox();
	
	// Main Overlay Layout
    BorderPane overlay = new BorderPane();
    
    BorderPane addBorder = new BorderPane();
    GridPane gridStack = new GridPane();
    
    Recommendations recommend = new Recommendations();
    Rectangle minMaxRect = new Rectangle(500, 15);
    
    // Classes needs
    ManipulateData herosDatabase = new ConnectTo("MsHeros");
    ArrayList<String> heroList = new ArrayList<String>();
    ArrayList<String> idList = new ArrayList<String>();
    HashMap<String, String> nameID = new HashMap<String, String>();
    
    
    boolean yourTurn = true;
    int chooseIdx = 0;
    AtomicInteger currentTime = new AtomicInteger(INITIAL_TIME);// Use an AtomicInteger to make the variable accessible within timer's lambda
    private Timeline timeline;
    ArrayList<String> chosenHeros = new ArrayList<String>();
	private Stage primaryStage;
    
    private HBox createBan(Pos position) {
    	
        HBox banDisplay = new HBox(10);
        
        banDisplay.setAlignment(position);
        banDisplay.setPadding(new Insets(5));

        // Add banned hero placeholders
        for (int i = 0; i < this.BAN_CIRCLES; i++) {
            Circle bannedHero = new Circle(25, this.PRIMARY_RED); // Placeholder for banned hero
			 
            bannedHero.setStroke(this.SECONDARY_RED);
            banDisplay.getChildren().add(bannedHero);
        }

        return banDisplay;
    }
    
    
    private StackPane createTimer() {
        Text countdown = new Text("00:" + currentTime.get());
        countdown.setFill(this.SECONDARY_RED);
        countdown.setFont(Font.font("Arial", 24));

        StackPane timerContainer = new StackPane();

        // Stop existing timeline if it exists
        if (timeline != null) {
            timeline.stop();
        }

        // Reset the current time to INITIAL_TIME
        currentTime.set(INITIAL_TIME);
        countdown.setText("00:" + currentTime.get());

        timeline = new Timeline(
            new KeyFrame(Duration.seconds(1), event -> {
                if (currentTime.get() == 1) {
                    countdown.setText("Time's up!");
                    countdown.setStroke(Color.YELLOW);
                    
                   
                    
                    // get random hero when timer hits 0;
                    Random rand = new Random();
                    this.chooseSwitch(
                        this.heroList.get(rand.nextInt(heroList.size()))
                    );
                    this.addBorder.setTop(this.createTimer()); 
                    
                    
                    
                } else {
                    countdown.setText("00:" + 
                        Integer.toString(currentTime.decrementAndGet())
                    );
                }
            })
        );

        timeline.setCycleCount(INITIAL_TIME);
        timeline.play();

        timerContainer.getChildren().add(countdown);
        timerContainer.setPadding(new Insets(20));
        StackPane.setAlignment(timerContainer, Pos.TOP_CENTER);
        return timerContainer;
    }

    private VBox createTeamPanel(String teamName, Pos alignment) {
        VBox teamPanel = new VBox();
        HBox banPanel = createBan(alignment);
        
        teamPanel.setAlignment(alignment);
        teamPanel.setPrefWidth(200);
        banPanel.setPadding(new Insets(5));
        
        teamPanel.setSpacing(10);
        teamPanel.getChildren().add(banPanel); // add ban panel
        teamPanel.setPadding(new Insets(20));
        // Adding Player Slots (Hero Icons, Player Names)
        for (int i = 0; i < 5; i++) {
            // create each players in the team
        	StackPane profileContainer = createTeamMember(teamName, 
        													alignment, 
        													"Player " + Integer.toString(i+1));
            teamPanel.getChildren().add(profileContainer);
        }
        
        VBox.setMargin(banPanel, new Insets(5));
        return teamPanel;
    }
    
    private StackPane createTeamMember(String teamName, Pos alignment, String playerName) {
    	Color teamColor = (teamName.equals("Blue")) ? this.PRIMARY_BLUE : this.PRIMARY_RED;
    	Color nameColor = (teamName.equals("Blue")) ? this.SECONDARY_BLUE : this.SECONDARY_RED;
        Pos namePos = (teamName.equals("Blue")) ? Pos.BOTTOM_LEFT: Pos.BOTTOM_RIGHT;
    	
    	// hero avatar image to load
        String imageLocation = String.format("file:./assets/initial-%s.png", teamName);
        Image heroImage = new Image(imageLocation);
        ImageView heroIcon = new ImageView(heroImage);
        Rectangle heroRect = new Rectangle(this.AVATAR_WIDTH, 
							        		this.AVATAR_HEIGHT, 
							        		teamColor); // Placeholder for hero avatar
        
        // Player Name
        Label playerLbl = new Label(teamName + " " + playerName);
        playerLbl.setTextFill(nameColor);
        playerLbl.setFont(Font.font("Arial", 13));
        playerLbl.setPadding(new Insets(5));
        
        // Wrapping ImageView in a StackPane to add background color
        StackPane profileContainer = new StackPane();
        profileContainer.setPrefSize(this.AVATAR_WIDTH, 
        							this.AVATAR_HEIGHT); 
        profileContainer.getChildren().addAll(heroRect, heroIcon, playerLbl);
        
        // Put all of their positions accordingly
        StackPane.setAlignment(playerLbl, namePos);
        StackPane.setAlignment(heroIcon, alignment);
        StackPane.setAlignment(heroRect, alignment);
        
        return profileContainer;
    }
    
    @SuppressWarnings("exports")
	public ImageView createLoadingIcon() {
    	// load the image
    	String path = this.yourTurn ? "file:./assets/loading-blue.png" : "file:./assets/loading-red.png";
        Image loadingImg = new Image(path, 150, 150, true, true); 
        ImageView loadingIcon = new ImageView(loadingImg);
        RotateTransition rotate = new RotateTransition();
        
        // rotate the image for loading
        rotate.setAxis(Rotate.Z_AXIS);    
        rotate.setByAngle(360); 
        rotate.setCycleCount(500);  
        rotate.setDuration(Duration.millis(3000));  
        rotate.setNode(loadingIcon);
        rotate.play();
        
        loadingIcon.setOnMouseClicked(this);
        
		return loadingIcon;
    }
    
   
    
    @SuppressWarnings("exports")
	public StackPane createHeroGrids() {
    	
		 StackPane window = new StackPane();
		 ScrollPane herosContainer = new ScrollPane();
		 GridPane heroPane = new GridPane();
		 
		 // Style and sizes
		 window.setStyle("-fx-background-color:BLACK");
		 
		 window.setMinWidth(this.HEROS_GRID_WIDTH);
		 window.setMinHeight(this.HEROS_GRID_HEIGHT);
		 window.setMaxWidth(this.HEROS_GRID_WIDTH);
		 window.setMaxHeight(this.HEROS_GRID_HEIGHT);
		 herosContainer.setStyle("-fx-background-color:BLACK");

		 heroPane.setStyle("-fx-background-color:BLACK");
		 heroPane.setAlignment(Pos.BASELINE_CENTER);
		 
		 heroPane.setMaxWidth(this.HEROS_GRID_WIDTH);
		 heroPane.setMaxHeight(this.HEROS_GRID_HEIGHT);
		 heroPane.setVgap(15);
		 heroPane.setHgap(15);
		 
		 herosContainer.setContent(heroPane);
		 
		 int row = 0;  

		 for (int i = 0; i < heroList.size(); i++) {
			 String heroName = this.heroList.get(i);
			 
			 Image heroImg = new Image(String.format("file:./assets/%s_Crop.jpg", heroName), 
					 					this.AVATAR_WIDTH, 
					 					this.AVATAR_HEIGHT, true, true);
	         ImageView imgView = new ImageView(heroImg);
	         imgView.setFitWidth(this.AVATAR_WIDTH);
	         imgView.setFitWidth(this.AVATAR_HEIGHT);
	         
	         // Set the hero Circle, when a circle is clicked, register the clicked circle
	         // and for each different character, they will have different method to execute.
			 Circle heroCircle = new Circle(34); // value straight from my ass
			 heroCircle.setFill(new ImagePattern(heroImg));
			 heroCircle.setStroke(this.SECONDARY_BLUE);
			 
			 heroCircle.setOnMousePressed(
					 evt -> chooseSwitch(heroName) // event Handler for hero stuff
					 );
			 
			 // change the row when certain number is hit, 
			 // so just "press enter" after certain number of characters
			 if (i % 6 == 0) row++; 
			 heroPane.addRow(row, heroCircle);
		 }

		 window.setAlignment(Pos.CENTER);
		 window.getChildren().addAll(herosContainer);
		 window.setMinSize(HEROS_GRID_WIDTH, HEROS_GRID_HEIGHT);
	     return window;
	 }
    
    public void chooseSwitch(String heroName) {

    	// the hero has already been chosen in ban or other heros, stop this method
    	if (this.chosenHeros.contains(heroName))
    		return;
    	
    	chosenHeros.add(heroName);
        this.idList.remove(this.nameID.get(heroName));
        
    	// ban until its all filled, then go to avatar update
    	if (this.chooseIdx < this.BAN_CIRCLES) {
    		// pick sound
        	String banLink = "./vfx/ban.mp3";
        	Media banMedia = new Media(new File(banLink).toURI().toString());
        	MediaPlayer banSound = new MediaPlayer(banMedia);
        	
        	updateBan(this.chooseIdx, heroName);
        	
        	banSound.setAutoPlay(true);
    	} else {
    		updateAvatar(this.chooseIdx - this.BAN_CIRCLES + 1, heroName);
    		updateRecomm(); // update the recommendation circles every choose cycle
    		
    		// pick sound
        	String pickLink = "./vfx/pick.mp3";
        	Media pickMedia = new Media(new File(pickLink).toURI().toString());
        	MediaPlayer pickSound = new MediaPlayer(pickMedia);
        	pickSound.setAutoPlay(true);
        	
        	 if ((this.chooseIdx > this.BAN_CIRCLES + 4) && this.yourTurn) {
             	Group endRoot = new Group();
                 endRoot.getChildren().add(endView);
                 endView.setFitWidth(WIDTH);
                 endView.setFitHeight(HEIGHT);
                 
                 this.primaryStage.setScene(new Scene(endRoot, WIDTH, HEIGHT));
             }
    	}
    	
    	
    	
		// one of the simplest way to switch the user to choose
		// Use the similar technique to change player like chess
        this.yourTurn = !this.yourTurn;
        

        // reset the time
        this.addBorder.setTop(this.createTimer());
        
        // replace the heroChoose grid thing into loading again, after choosing hero
        int col = 2;
		int row = 3;
		
        // Replace the existing product box in the GridPane
		gridStack.getChildren().removeIf( 
				node -> GridPane.getColumnIndex(node) == col && 
				GridPane.getRowIndex(node) == row);
		gridStack.add(this.createLoadingIcon(), col, row);
    }
    
    // TODO optimize this
	public void updateAvatar(int idx, String locationUpdate) {
    	if (this.chooseIdx > this.BAN_CIRCLES + 4)
    		return;
    	// DONT EVER ASK ME HOW ANY OF THIS FUKCING WORKS
    	// HOLY SHIT THIS FUCK ACTUALLY TACKY BUT WORKS WOOOOOO

    	StackPane profileContainer = 
    				(StackPane) leftTeamPanel.getChildren().get(idx);
    	Pos position = Pos.CENTER_LEFT;
    	
    	System.out.println(this.chooseIdx);
    	if (!this.yourTurn) {
    		// if it isn't the blue team that chooses, turns to red team
    		// after the red team chooses, the index adds up, make it make sense
    			
    		profileContainer = (StackPane) rightTeamPanel.getChildren().get(idx);
    		position = Pos.CENTER_RIGHT;
    		this.chooseIdx += 1;
    	}
    	
    	profileContainer.getChildren().removeIf(node -> node instanceof ImageView);
    	
    	 // Create a new ImageView with the new hero image
        String imageLocation = String.format("file:./assets/%s_full.jpg", locationUpdate);
        ImageView newHeroIcon = new ImageView(imageLocation);
        newHeroIcon.setFitWidth(this.AVATAR_WIDTH);
        newHeroIcon.setFitHeight(this.AVATAR_HEIGHT);
       
        // yes, replace the photo
        profileContainer.getChildren().add(newHeroIcon);
        
        // get the alignments from previous method thing
        StackPane.setAlignment(newHeroIcon, position);
    }
		
	public void updateBan(int idx, String locationUpdate) {
		 if (this.chooseIdx > this.BAN_CIRCLES-1)
		        return;

		    HBox banPanel;
		    Pos position = Pos.CENTER_LEFT;
		    
		    if (!this.yourTurn) {
		        // red team ban panel
		        banPanel = (HBox) rightTeamPanel.getChildren().get(0);
		        position = Pos.CENTER_RIGHT;
		        this.chooseIdx += 1;
		    } else {
		        // blue team ban panel
		        banPanel = (HBox) leftTeamPanel.getChildren().get(0);
		    }
		    
		    // Get the specific ban circle to update
		    Circle bannedHeroCircle = (Circle) banPanel.getChildren().get(idx);
		    
		    // Create a new ImageView with the banned hero image
		    String imageLocation = String.format("file:./assets/%s_Crop.jpg", locationUpdate);
		    Image bannedHeroImage = new Image(imageLocation);
		    
		    // Set the circle's fill to the banned hero image
		    bannedHeroCircle.setFill(new ImagePattern(bannedHeroImage));
		    
		    bannedHeroCircle.setStroke(this.SECONDARY_RED);

    }
	
	// TODO optimize this
		public void updateRecomm() {
	        
			int Lcol = 1;
			int Lrow = 3;
			
			int Rcol = 3;
			int Rrow = 3;
			
			if (this.yourTurn) {
				// Replace the existing product box in the GridPane
				gridStack.getChildren().removeIf( 
						node -> GridPane.getColumnIndex(node) == Rcol && 
						GridPane.getRowIndex(node) == Rrow);
			
				this.gridStack.add(
						recommend.createRecommendCircle(Pos.CENTER_RIGHT, this.idList), 
						Rcol, Rrow);
			} else {
				 // Replace the existing product box in the GridPane
				gridStack.getChildren().removeIf( 
						node -> GridPane.getColumnIndex(node) == Lcol && 
						GridPane.getRowIndex(node) == Lrow);
				
				this.gridStack.add(
						recommend.createRecommendCircle(Pos.CENTER_LEFT, this.idList), 
						Lcol, Lrow);
			}

	    }
    
    public void set() {
    	// Background Image
        backgroundView.setFitWidth(WIDTH);
        backgroundView.setFitHeight(HEIGHT);
        
     // Left and blue team Panel
        leftTeamPanel = createTeamPanel("Blue", Pos.CENTER_LEFT);
        rightTeamPanel = createTeamPanel("Red", Pos.CENTER_RIGHT);
        HBox recLeft = recommend.createRecommendCircle(Pos.CENTER_LEFT, this.idList);
        HBox recRight = recommend.createRecommendCircle(Pos.CENTER_RIGHT, this.idList);
        
        ImageView loadingIcon = createLoadingIcon();
        minMaxRect.setFill(PRIMARY_RED);
        
        this.gridStack.setAlignment(Pos.CENTER);
        this.gridStack.add(recLeft, 1, 3);
        this.gridStack.add(loadingIcon, 2, 3);
        this.gridStack.add(recRight, 3, 3);
        											// wasted HOURS in this broken
        // this.addBorder.setTop(this.createTimer()); // this timer is broken and ugly and impractical af
        this.addBorder.setTop(this.createTimer());
        this.addBorder.setCenter(this.gridStack);

        addBorder.setMaxWidth(500);
        
        // Adding Components to Overlay
        overlay.setLeft(leftTeamPanel);
        overlay.setRight(rightTeamPanel);
       
        // Adding to Root Pane
        root.getChildren().addAll(backgroundView,
        							overlay, 
        							addBorder);
        
    }
    
    @SuppressWarnings("exports")
	@Override
    public void start(Stage primaryStage) {
    	this.primaryStage = primaryStage;
    	// video video stuff
        String vidLink = "./vid/matchfound.mp4";
        Media vidMedia = new Media(new File(vidLink).toURI().toString());
        MediaPlayer vidPlay = new MediaPlayer(vidMedia);
        MediaView vidView = new MediaView(vidPlay);
        vidView.setFitHeight(HEIGHT + 100);
        vidView.setFitWidth(WIDTH);
        
        Group vidRoot = new Group(vidView);
        vidPlay.setAutoPlay(true);
        
        Scene vid = new Scene(vidRoot, WIDTH, HEIGHT);
        primaryStage.setScene(vid);
        primaryStage.show();
        
        
    	this.heroList = this.herosDatabase.getData("heroName");
    	this.idList = this.herosDatabase.getData("herosID");
    	
    	
    	// Start sound
    	String file = "./vfx/welcome.mp3";
    	Media media = new Media(new File(file).toURI().toString());
    	MediaPlayer welcome = new MediaPlayer(media);
    	
    	// backgorund sound
    	String bgSound = "./vfx/background.mp3";
    	Media backMedia = new Media(new File(bgSound).toURI().toString());
    	MediaPlayer backSound = new MediaPlayer(backMedia);
    	
    	for (int i=0; i < this.herosDatabase.getData("heroName").size(); i++) {
			this.nameID.put(this.heroList.get(i), this.idList.get(i));
		}
    	System.out.println(idList);
        set();

     // Scene and Stage Setup
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        
        welcome.setAutoPlay(true);
        backSound.setAutoPlay(true);
        primaryStage.setTitle("Draft Pick Recommendation");
        primaryStage.setHeight(HEIGHT);
        primaryStage.setWidth(WIDTH);
        
        vidPlay.setOnEndOfMedia(() -> {
            // Stop the video
            vidPlay.stop();
            
            // Set the main scene
            primaryStage.setScene(scene);
        });
    }


    public static void main(String[] args) {
        launch(args);
    }

	@SuppressWarnings("exports")
	@Override
	public void handle(MouseEvent event) {
		// TODO Auto-generated method stub
		switch (event.getEventType().getName()) {
		case "MOUSE_CLICKED":
			int col = 2;
			int row = 3;
			
            // Replace the existing product box in the GridPane
			gridStack.getChildren().removeIf( 
					node -> GridPane.getColumnIndex(node) == col && 
					GridPane.getRowIndex(node) == row);
			
			this.gridStack.add(createHeroGrids(), col, row);
			break;
			
		default:
			break;
		}
	}

}
