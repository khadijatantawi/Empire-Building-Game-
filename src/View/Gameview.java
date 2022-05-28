package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Gameview extends JLabel implements ActionListener{
	private GUI parent;
	private JLabel name;
	private JLabel Gold;
	private JLabel Food;
	private JLabel turn;
	private JButton endTurn;
	
	public Gameview(GUI parent) {
		this.parent = parent;
		this.setLayout(new GridLayout(5, 1));
  	    // this.setVisible(true);
	
  	   name = new JLabel("Name:");
	   this.add(name);
	   
	   Gold = new JLabel("Gold:");
	   this.add(Gold);
	   
	   Food = new JLabel("Food:");
	   this.add(Food);
	   
	   turn = new JLabel("Turn Count:");
	   this.add(turn);
	   
	   endTurn = new JButton("End Turn");
	   endTurn.setBounds(200, 450, 100, 50);
	   this.add(endTurn);
	   endTurn.addActionListener(this);
	   endTurn.setFont(new Font("Arial", Font.PLAIN, 20));
	   endTurn.setBackground(Color.black);
	   endTurn.setForeground(Color.white);
	   
	//updateLabel();

	}

	public void updateLabel() {
		name.setText("Name: " + parent.getGame().getPlayer().getName());
		Gold.setText("Gold: " + parent.getGame().getPlayer().getTreasury());
		Food.setText("Food: " + parent.getGame().getPlayer().getFood());
		turn.setText("Turn Count: " + parent.getGame().getCurrentTurnCount());
}

	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println(e.getSource() == endTurn);
		if(e.getSource()== endTurn) {
	   parent.getGame().endTurn();
	   parent.updateAll();	
		}
		
	}
}
