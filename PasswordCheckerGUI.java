import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * GUI design class which defines the GUI and its components.
 * @author nanaa
 *
 */

public class PasswordCheckerGUI extends BorderPane {

	/**
	 * The components of the GUI.
	 */
	
	private Button passFilesButton, singlePassButton, exitButton;
	private TextField passField1, passField2;
	private Label ruleLabel;

	
	
	/**
	 * Create the GUI window and place components.
	 */
	
	
	PasswordCheckerGUI() {
				
		Insets inset = new Insets(10); 
		
		//password inputs
	    passField1 = new TextField();
	    passField1.setPrefColumnCount(8);
	    passField2 = new TextField();
	    passField2.setPrefColumnCount(8);
	    
	    
	    ruleLabel = new Label("Use the following rules when creating your passwords:\n" + 
	    					  "\t1. Length must be at least 6 charcters; a strong password will contain at least 10 characters\n" + 
	    					  "\t2. Must contain at least one upper case alpha character\n" + 
	    					  "\t3. Must contain at least one lower case alpha character\n" + 
	    					  "\t4. Must contain at least one numeric character\n" + 
	    					  "\t5. May not have more than 2 of the same character in sequence");

		 
	    exitButton = new Button("E_xit");
	    exitButton.setMnemonicParsing(true);  
	    exitButton.setTooltip(new Tooltip("Select to close the application"));
        exitButton.setOnAction(
        		event -> {
            	 Platform.exit();
                 System.exit(0);
        		}
        	);
	    
        
	    passFilesButton = new Button("Check Passwords in _File");
	    passFilesButton.setMnemonicParsing(true);  
	    passFilesButton.setTooltip(new Tooltip("Select this to choose a file of passwords to test"));

	    
	    singlePassButton = new Button("Check _Password");
	    singlePassButton.setMnemonicParsing(true);  
	    singlePassButton.setTooltip(new Tooltip("Select this to check a single, entered password"));

	    //button listener appropriation
	    ButtonListener buttonListener = new ButtonListener();
	    passFilesButton.setOnAction(buttonListener);
	    singlePassButton.setOnAction(buttonListener);
	    
	    
	    HBox topBox = new HBox();
	    HBox.setMargin(ruleLabel, inset);
	    topBox.getChildren().addAll(ruleLabel);
	    setTop(topBox);
	    
	    //button layout
	    HBox bottomBox = new HBox();
	    HBox.setMargin(passFilesButton, inset);
	    HBox.setMargin(singlePassButton, inset);
	    HBox.setMargin(exitButton, inset);
	    bottomBox.getChildren().addAll(passFilesButton, singlePassButton, exitButton);
	    setBottom(bottomBox);
	    bottomBox.setAlignment(Pos.CENTER);
		
		//label and input layout 
		GridPane centerGridPane = new GridPane();
		centerGridPane.setAlignment(Pos.CENTER);
		centerGridPane.setHgap(30);
		centerGridPane.setVgap(50);
		centerGridPane.add(new Label("Password"), 0, 0);
		centerGridPane.add(passField1, 1, 0);
		centerGridPane.add(new Label("Re-type Password"), 0, 1);	
		centerGridPane.add(passField2, 1, 1);
		

	    setCenter(centerGridPane);
	    
	   
	}
	
	
	
	
	
	/**
	 * Create a dialog window and display the content of the passed list, with each element on a new line.
	 * @param list - A list of strings that is to be displayed on the modal
	 */
	
	
	public void showDialog(ArrayList<String> list) {
		
		Stage dialog = new Stage();
		dialog.initOwner(FXDriver.stageRef);
		dialog.initModality(Modality.APPLICATION_MODAL); 
		VBox vBox = new VBox();
		
		if(!(list == null)){ 
				for(String s : list){
					vBox.getChildren().add(new Label(s));
				}
		} else {
			vBox.getChildren().add(new Label("All passwords are valid."));
		}
		
		
		exitButton = new Button("_OK");
		exitButton.setMnemonicParsing(true); 
        exitButton.setOnAction(
		        		event -> {
		            	 dialog.close();
		        		}
		        	);
        
        vBox.getChildren().addAll(new Label("\n"), exitButton);
        vBox.setPadding(new Insets(10, 50, 50, 50));
        
		Scene scene = new Scene(vBox);
		dialog.setScene(scene);
		dialog.showAndWait();

		
	}
	
	
	
	
	
	/**
	 * Create a dialog window and display the content of the passed list, with each element on a new line.
	 * @param message A string that is to be displayed on the modal window
	 */
	
	
	public void showDialog(String message) {
		
		Stage dialog = new Stage();
		dialog.initOwner(FXDriver.stageRef);
		dialog.initModality(Modality.APPLICATION_MODAL); 
		VBox vBox = new VBox();
		vBox.getChildren().add(new Label(message)); 
		exitButton = new Button("_OK");
		exitButton.setMnemonicParsing(true); 
        exitButton.setOnAction(
        		event -> {
            	 dialog.close();
        		}
        	);
        vBox.getChildren().add(exitButton);
        vBox.setPadding(new Insets(10, 50, 50, 50));
		Scene scene = new Scene(vBox);
		dialog.setScene(scene);
		dialog.showAndWait();

		
	}
	
	
	
	
	
	/**
	 * Handle events fired by the buttons on the GUI.
	 */
	
		
	class ButtonListener implements EventHandler<ActionEvent>{
		
		/**
		 * Catch an event fired in the PaswordCheckerGUI class.
		 * @param event Event fired by the PasswordCheckerGUI class
		 */
		
		
		@Override
		public void handle(ActionEvent event) {
			
			Object source = event.getTarget();
			
			if (source.equals(passFilesButton)){ 
				FXDriver.chooseAndDisplayFile();
			} else if (source.equals(singlePassButton)) { 
				try{
					if (passField1.getText().equals(passField2.getText())) {
						FXDriver.testPassword(passField1.getText());
					} else throw new UnmatchedException("The passwords do not match.");
				} catch (UnmatchedException e) {
					showDialog(e.getMessage());
				}
			}

			
		}
			
			
	}
	
	
	
	
}
	