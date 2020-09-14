import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * Invoke the GUI, access files, receive/send info from the GUI (including password checking messages).
 * @author nanaa
 */

public class FXDriver extends Application {
	   
	/**
	 * File access object, to be used in selecting a password file.
	 * Used in the {@link #chooseAndDisplayFile() chooseAndDisplayFile} method.
	 */
	static FileChooser fileChooser;
	
	/**
	 * Reference to the JavaFX Stage used to invoke the GUI.
	 */
	static Stage stageRef;
	
	/**
	 * List of parsed lines from a given file.
	 * Used in the {@link #chooseAndDisplayFile() chooseAndDisplayFile} method to store result of file input to {@link #fileToList(File f) fileToList}.
	 */
	static ArrayList<String> fileLines;
	
	/**
	 * Reference to the used GUI object.
	 */
	static PasswordCheckerGUI root;
	
	
	
	public static void main(String[] args) {
		launch(args);   
	}
		   
	
	
	
	
	/**
	 * Initialization of the GUI.
	 */
	
	
	@Override
	public void start(Stage stage) {
		
		try {
			root = new PasswordCheckerGUI();
			stageRef = stage;
	        stage.setScene(new Scene(root, 525, 450));
			stage.setTitle("Password Checker");
			stage.show();
			fileChooser = new FileChooser();
		} catch (OutOfMemoryError e) {
			System.out.println(e.getMessage());
			System.out.println("Could not invoke GUI. Check memory.");
		}
		
		
	}
	
	
	
	
	
	/**
	 * Utility method to convert a text file to an arraylist by line (delimiter is as used by Scanner.nextLine())
	 * @return result - String arraylist which contains lines from the passed text file
	 * @param f File which the list is parsed from
	 */
	
	
	private static  ArrayList<String> fileToList(File f){
		
		ArrayList<String> result = new ArrayList<String>();
		 
		try (Scanner s = new Scanner(new FileReader(f))) {
		    while (s.hasNext()) {
		        result.add(s.nextLine()); 
		    }
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		return result;
		
		
	}
	
	
	
	
	
	/**
	 * Single password testing method which creates a dialog with one of the following:
	 * 	<br/>- a "valid" message when password meets all requirements
	 * 	<br/>- an "OK" message when password does not reach the recommended length requirement
	 * 	<br/>- an error message thrown by the predefined password exceptions
	 * 
	 * Reuses 
	 * @param s The password string which is being tested
	 */
	
	
	public static void testPassword(String s){ 
		
		fileLines = new ArrayList<String>();
		
		try{
			if(PasswordCheckerUtility.isValidPassword(s)) {
				if(PasswordCheckerUtility.isWeakPassword(s)) {
					fileLines.add("Password is OK but weak");
				} else {
					fileLines.add("Password is valid");
				}
			}
		} catch (Exception e) { 
			fileLines.add(e.getMessage());
		}
		
		root.showDialog(fileLines);
		
		
	}
	
	
	
	
	
	/**
	 * Let the user choose a password file and display the results of testing each line in a modal window from the GUI class.
	 */
	
	
	public static void chooseAndDisplayFile() {
		
		fileLines = new ArrayList<String>();
		
		try {
			File file = fileChooser.showOpenDialog(stageRef);
			fileLines = fileToList(file); 
			fileLines = PasswordCheckerUtility.invalidPasswords(fileLines);
			root.showDialog(fileLines);
		} catch (Exception e) { 
			root.showDialog("Could not access file.");
		}
		
		
	}
	
	
	
}
