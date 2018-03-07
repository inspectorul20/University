import javax.swing.*;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.*;

public class Table implements ActionListener{

private JButton[] buttons = new JButton[12];
private JButton blankButton;

public Table()

{

	JFrame frame = new JFrame("Puzzle");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	JPanel p1 = new JPanel();
	frame.setContentPane(p1);
	GridLayout layout1 = new GridLayout(3,4);
	p1.setLayout(layout1);

	for(int i =0 ; i<12 ;i++){
		buttons[i] = new JButton(new ImageIcon("bart"+i+".jpg"));
		p1.add(buttons[i]);
		buttons[i].addActionListener(this);
	}
        blankButton = buttons[0];

	frame.setSize(111*4,121*3);
	frame.setVisible(true);
}


	public void actionPerformed(ActionEvent e)
	{
		for(int i = 0 ; i < 12 ; i++){
			if(e.getSource() == buttons[i]){
				Icon tempIcon = buttons[i].getIcon();
				buttons[i].setIcon(blankButton.getIcon());
				blankButton.setIcon(tempIcon);
				blankButton = buttons[i];
			}
		}
	}
}
