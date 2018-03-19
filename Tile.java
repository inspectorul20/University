import javax.swing.JButton;
import javax.swing.ImageIcon;

/**
This class takes the actions of a JButton and helps the switch of images by taking the coordinates of each tile in the puzzle
It is treated as a JButton because it extends a JButton
*/

public class Tile extends JButton{

/**
Created a special JButton with more parameters, such as columns and rows.

@param colIndex A variable of type integer. Represents column index of the tile.
@param rowIndex A variables of type integer. Represents row index of the tile.
@param index A variable of type integer. Represents index of the tile.
@param img A variable of type ImageIcon. Represents the associated image of the tile.
*/

private int col, row, tileIndex;

  public Tile(int colIndex,int rowIndex, int index, ImageIcon img){
	super(img);
	col= colIndex;
	row= rowIndex;
	tileIndex = index;
  }

/**
Gets the number of rows and returns the value.
@return an integer data type.
*/
  public int getRow(){
	return row;
  }

/**
Gets the number of columns and returns the value.
@return an integer data type.
*/

  public int getCol(){
	return col;
  }
  
/**
Gets the number of TileIndex and returns the value.
@return an integer data type.
*/

  public int getTileIndex(){
	  return tileIndex;
  }

/**
Sets tileIndex to index in order to find out the new index of position
@param index A variable of type integer. Represents index of the tile.
*/  

  public void setTileIndex(int index){
	  tileIndex = index;
  }
}
