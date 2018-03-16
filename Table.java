import javax.swing.*;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.*;

public class Table implements ActionListener{

private Tile[] tiles = new Tile[12];
private Tile blankTile;
private Score scoreManager = new Score();
private JFrame frame;
private boolean isRandomising = false;
public Table()
{

	frame = new JFrame("Puzzle - number of clicks: 0");//PWT removed type as variable defined above now + changed the title
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	JPanel p1 = new JPanel();
	frame.setContentPane(p1);
	GridLayout layout1 = new GridLayout(3,4);
	p1.setLayout(layout1);

	int i = 0;
	for(int row =0 ; row < 3; row++){
	  for(int col=0; col < 4; col++){
		tiles[i] = new Tile(col,row,i,new ImageIcon("bart"+i+".jpg"));//PWT added the index of tile too for looking up the order
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

 private void checkGameSolved(){
	 for(int i = 0; i<tiles.length;i++){
		 if(tiles[i].getTileIndex()!=i)
			 return;
	 }
	 //the game is finished: we can display the scores
	 scoreManager.displayScoreBoard();
 }

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
			frame.setTitle("Puzzle - number of clicks: "+scoreManager.getScore());//PWT removed te comment and updated the frame with a title that contains score
			checkGameSolved();
			}
		}
	}
	
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



