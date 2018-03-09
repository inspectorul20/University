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

public Table()
{

	JFrame frame = new JFrame("Puzzle");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	JPanel p1 = new JPanel();
	frame.setContentPane(p1);
	GridLayout layout1 = new GridLayout(3,4);
	p1.setLayout(layout1);

	int i = 0;
	for(int row =0 ; row < 3; row++){
	  for(int col=0; col < 4; col++){
		tiles[i] = new Tile(col,row,new ImageIcon("bart"+i+".jpg"));
		p1.add(tiles[i]);
		tiles[i].addActionListener(this);
	  i++;
	 }
	}
        blankTile = tiles[0];

	frame.setSize(111*4,121*3);
	frame.setVisible(true);
}


	public void actionPerformed(ActionEvent e)
	{
		Tile tile = (Tile)e.getSource();
        if((tile.getRow()==blankTile.getRow() && (tile.getCol()==blankTile.getCol()-1 || tile.getCol()==blankTile.getCol()+1)) 
			|| (tile.getCol()==blankTile.getCol() && (tile.getRow()==blankTile.getRow()-1 || tile.getRow()==blankTile.getRow()+1))){
		Icon tempIcon =tile.getIcon();
		tile.setIcon(blankTile.getIcon());
		blankTile.setIcon(tempIcon);
		blankTile = tile;
		}
	}
}










