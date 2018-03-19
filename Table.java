import javax.swing.*;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.*;

/**
A class for creating the Puzzle game. The class responsable for the game's dynamic.
*/

public class Table implements ActionListener{

private Tile[] tiles = new Tile[12];
private Tile blankTile;
private Score scoreManager = new Score();
private JFrame frame;
private boolean isRandomising = false;

/**
Constructor that defines what the Table class should look like
*/

public Table()
{

	frame = new JFrame("Puzzle - number of clicks: 0");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	JPanel p1 = new JPanel();
	frame.setContentPane(p1);
	GridLayout layout1 = new GridLayout(3,4);
	p1.setLayout(layout1);

	int i = 0;
	for(int row =0 ; row < 3; row++){
	  for(int col=0; col < 4; col++){
		tiles[i] = new Tile(col,row,i,new ImageIcon("bart"+i+".jpg")); //Created the tile so we can add them to the array. We assign an action listener to each Tile.
		p1.add(tiles[i]);
		tiles[i].addActionListener(this);
	  i++;
	 }
	}
        blankTile = tiles[0];

	frame.setSize(111*4,121*3);
	frame.setVisible(true);
	
	randomize();
}

/**
Method which checks if the game is finished
*/


 private void checkGameSolved(){
	 for(int i = 0; i<tiles.length;i++){
		 if(tiles[i].getTileIndex()!=i)
			 return; //if the game is not finished we don't show score
	 }
	 //the game is finished: we can display the scores
	 scoreManager.displayScoreBoard();
 }


/**
Method that deals with the click event.
@param ActionEvent e A variable of type ActionEvent.
*/



	public void actionPerformed(ActionEvent e)
	{
		Tile tile = (Tile)e.getSource();
        if((tile.getRow()==blankTile.getRow() && (tile.getCol()==blankTile.getCol()-1 || tile.getCol()==blankTile.getCol()+1)) 
			|| (tile.getCol()==blankTile.getCol() && (tile.getRow()==blankTile.getRow()-1 || tile.getRow()==blankTile.getRow()+1))){
		Icon tempIcon =tile.getIcon();
		tile.setIcon(blankTile.getIcon());
		blankTile.setIcon(tempIcon);
		
		int tempIndex = blankTile.getTileIndex();
		blankTile.setTileIndex(tile.getTileIndex());
		tile.setTileIndex(tempIndex);
		
		blankTile = tile;
		
		if(!isRandomising){
			scoreManager.incrementScore();//increment the score
			frame.setTitle("Puzzle - number of clicks: "+scoreManager.getScore());
			checkGameSolved();
			}
		}
	}

/**
Method which will randomize the puzzle
*/

	
	private void randomize(){
		//get a random number of iteration trials between 50 and 200
		isRandomising = true;
		int maxIteration = (int)(Math.random() * ((200 - 50) + 1)) + 50;
	   System.out.println("iterations "+ maxIteration);
		for(int iteration =0; iteration<maxIteration;iteration++ ){
			//click on a random tile of the game
			int indexOfTile = (int) Math.floor(Math.random() * 12);
			System.out.println("click "+ indexOfTile);
			tiles[indexOfTile].doClick();
		}
		isRandomising = false;
	}
}



