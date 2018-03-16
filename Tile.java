import javax.swing.JButton;
import javax.swing.ImageIcon;

public class Tile extends JButton{

private int col, row, tileIndex;

  public Tile(int colIndex,int rowIndex, int index, ImageIcon img){
	super(img);
	col= colIndex;
	row= rowIndex;
	tileIndex = index;
  }

  public int getRow(){
	return row;
  }

  public int getCol(){
	return col;
  }
  
  public int getTileIndex(){
	  return tileIndex;
  }
  
  public void setTileIndex(int index){
	  tileIndex = index;
  }
} 
