import javax.swing.*;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/**
The score class deals with the scoreboard of the game
*/

public class Score implements ActionListener{


private JTextField fieldforscore;
private JPanel panelScores;
private int currentScore = 0;
private int highScoreLength = 10; //created two arrays that contain the names of the players and their scores, their length is 10 since there are 10 players there will be 10 scores
private Vector<String> names = new Vector<>();// dynamic array for storing the names
private Vector<Integer> scores = new Vector<Integer>();//dynamic array for storing the scores

/**
This is the main constructor which describes what the Score looks like, what is should do and its limits
*/
 
 public Score(){
  getScoresTxt();
 }

/**
Get scores 
@return currentScore A variable of type integer. Represents the current score.
*/  

 public int getScore(){
   return currentScore;
 } 

/**
Adds 1 to score
*/  
 
  public void incrementScore(){
   currentScore++;
 }
/**
Method which places the score on the scoreboard after you've finished the game.
*/  
  public void actionPerformed(ActionEvent e) //the event that happens is when we hit enter in the textfield
  { 
	int indexInScores = -1; // indicator of the index in the vectors where we need to put the current score. By default set to -1 to check if we actually find a position.
        for(int i = 0 ; i< scores.size();i++){
                //we check all the values in the vector to find where we can insert the current score.
		if(scores.elementAt(i)>currentScore){ //method elementAT() allows us to return the value at the index i
		   indexInScores = i;
                   break;
		}
        }
        if(indexInScores==-1){
          //we didn't find a score that is better than the current score, so we add the current score at the end of the vector
          indexInScores = scores.size();
        }

        //update the vectors with the actual values
        names.insertElementAt(fieldforscore.getText(), indexInScores);
        scores.insertElementAt(currentScore, indexInScores);
		updateScores(); // updates the interface
		fieldforscore.setEditable(false);
		fieldforscore.removeActionListener(this);		
	    putScoresTxt(); // updates the txt file
  }

/**
Method which updates the score in the interface.
*/ 
  private void updateScores(){
	 //remove everything
		panelScores.removeAll();
	 //put elements
	 for(int j = 0 ; j < Math.min(highScoreLength, names.size()); j++){ // loop which adds the values to panel 1 which deals with the names and scores -- we display up to 10 elements of the vector (min between 10 and the vector's size)
		panelScores.add(new JLabel(names.elementAt(j))); //takes element J in the vector
		panelScores.add(new JLabel(Integer.toString(scores.elementAt(j))));  //converts the integer into a string
	}
	//repaint
	panelScores.revalidate();
 }
/**
Method which saves the scores in a txt file.
*/  
 private void putScoresTxt(){
	try{
		PrintWriter writer = new PrintWriter("Scores.txt", "UTF-8");
		for (int i=0 ; i <names.size(); i++){
			writer.println(names.elementAt(i));
			writer.println(scores.elementAt(i));
		}
		writer.close();
	}
	catch(Exception ex){
		System.out.println("File not found..");
	}
 }

/**
Method which wil read the scores from the txt file and check if the name
*/ 

 private void getScoresTxt(){
		try{
			BufferedReader in = new BufferedReader(new FileReader("Scores.txt"));
			boolean isReadingName = true; //this is true because we need the first line to be a name
			String line;
			while((line = in.readLine()) != null) //goes through each line and checks if there is a name or a score
			{
			   if (isReadingName){  //the first line of the code is a name
				   names.add(line);
			   }
			   else {
				   scores.add(Integer.parseInt(line));
			   }
			   isReadingName = !isReadingName;//if a line is not a name, it is faulse and we will have a score
			}
			in.close();
		}
		catch(Exception ex){
			System.out.println("Something wrong happened....");
		}
}

/**
Method which describes the content of the scoreboard
*/  
  public void displayScoreBoard(){
	JFrame scoreboard = new JFrame("HIGH SCORES"); //created the frame of the Score menu
	
        JPanel panelforscore = new JPanel(); //main panel that contains panel1 and panel2
		BorderLayout layoutforpanelforscore = new BorderLayout();
        panelforscore.setLayout(layoutforpanelforscore);
       
        panelScores = new JPanel(); //create the panel with names and scores in the panel "pnaelforscore"
        panelforscore.add("North",panelScores);//added panel 1 to the main panel called panelforscore
        GridLayout layoutforpanel1 = new GridLayout(0,2);
        panelScores.setLayout(layoutforpanel1);
		updateScores();
        
		
        JPanel panel2 = new JPanel(); //create the panel "type your name" in the panel "pnaelforscore"
        panelforscore.add("Center",panel2);//added panel2 to the main panel called panelforscore PWT added center
        FlowLayout layoutforpanel2 = new FlowLayout(FlowLayout.LEFT);
        panel2.setLayout(layoutforpanel2);
        JLabel name = new JLabel("TYPE YOUR NAME:");
        panel2.add(name);        
	    fieldforscore = new JTextField(20);//change the constructor's argument to put the number of columns instead 
        fieldforscore.addActionListener(this); //takes the role of the event that was implemented (enter). When the player hits enter in the text field will update the score list.
               panel2.add(fieldforscore);
		JLabel labelforscore = new JLabel("Score: " + currentScore);
        panel2.add(labelforscore);
        

scoreboard.setContentPane(panelforscore);
scoreboard.setSize(500,300);
scoreboard.setVisible(true);
      
 }

}


