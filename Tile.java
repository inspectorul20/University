import javax.swing.JButton;
import javax.swing.ImageIcon;

public class Tile extends JButton{

private int col, row;

  public Tile(int colIndex,int rowIndex, ImageIcon img){
	super(img);
	col= colIndex;
	row= rowIndex;
  }

  public int getRow(){
	return row;
  }

  public int getCol(){
	return col;
  }
}

