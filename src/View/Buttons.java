package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.Farm;
import buildings.Market;
import engine.City;
import units.Archer;
import units.Army;
import units.Infantry;
import units.Status;
import units.Unit;

public class Buttons extends JLabel implements ActionListener {

	private GUI parent;
	private JButton cairo;
	private JButton rome;
	private JButton sparta;
	private JTextArea armyInfo  ;
	private JButton Attack;
	BattleView b ; 
	

	public Buttons(GUI parent) {
		this.parent = parent;
		this.setLayout(null);
		this.setBounds(0, 0, 1700, 700);

		cairo = new JButton("Cairo");
		this.add(cairo);
		cairo.addActionListener(this);
		cairo.setFont(new Font("Arial", Font.PLAIN, 15));
		cairo.setBounds(400, 400, 200, 50);
		cairo.setBackground(Color.white);
		cairo.setForeground(Color.darkGray);

		rome = new JButton("Rome");
		this.add(rome);
		rome.addActionListener(this);
		rome.setBounds(650, 400, 200, 50);
		rome.setFont(new Font("Arial", Font.PLAIN, 15));
		rome.setBackground(Color.white);
		rome.setForeground(Color.darkGray);

		sparta = new JButton("Sparta");
		this.add(sparta);
		sparta.addActionListener(this);
		sparta.setBounds(900, 400, 200, 50);
		sparta.setFont(new Font("Arial", Font.PLAIN, 15));
		sparta.setBackground(Color.white);
		sparta.setForeground(Color.darkGray);
		
		
//controlled armies info (for every army idle  / marching/ besieging )
		
		armyInfo = new JTextArea("city info");
		this.add(armyInfo);
		armyInfo.setBounds(1000, 500, 400, 300);
		armyInfo.setFont(new Font("Arial", Font.PLAIN, 15));
		armyInfo.setEditable(false);
		//armyInfo.setText(displayControlledArmies());
		armyInfo.setBackground(Color.white);
		armyInfo.setForeground(Color.darkGray);

			
		Attack = new JButton("  Attack  ");
		Attack.setBounds(130, 660, 150, 70);
		this.add(Attack);
		Attack.addActionListener(this);
		Attack.setFont(new Font("Arial", Font.PLAIN, 20));
		Attack.setBackground(Color.white);
		Attack.setForeground(Color.darkGray);

		// pubic String display
		/*
		 * add here Jtext Area as the one we did in CitiesView class to show city's info
		 * , and put it on the right most as a box keda ala gamb then make a method like
		 * display(CitiesView Class) and make it print all info about the amries (all
		 * controlled armies)
		 */
		this.revalidate();
		this.repaint();

	}
	
	
	public void displayControlledArmies() {
		String s ="";
		
		for (int i = 0; i < parent.getGame().getPlayer().getControlledArmies().size(); i++) {
			Army a =  parent.getGame().getPlayer().getControlledArmies().get(i);
			
			s += a +"/n" + "Current Status :" + a.getCurrentStatus()+ "\n" + 
					"Current Location: "+ a.getCurrentLocation() + "\n" +
					"Target: "+ a.getTarget() + "\n";
			if (a.getCurrentStatus().equals(Status.MARCHING)) {
				s+= "target City " + a.getTarget() + a.getDistancetoTarget();
				
			}
			else if (a.getCurrentStatus().equals(Status.BESIEGING)) {
				for (City c :parent.getGame().getAvailableCities()) {
					if (c.getDefendingArmy()==a){
							s+= "city is underSiege" +"number of turns city underSiege" + c.getTurnsUnderSiege();
					}
				}
			}
			
			for (int j = 0; j < a.getUnits().size(); j++) {
				Unit unit=a.getUnits().get(j);
				
				if (unit instanceof Archer) {

					s += "Unit Type: Archer" + "\n" + "Current Soldier Count: "
							+ unit.getCurrentSoldierCount()+ "\n" + "Unit's Level: "+ unit.getLevel()
							+ "\n " + "Max Soldier Count:" + unit.getMaxSoldierCount();

				} else if (unit instanceof Infantry) {

					s += "Unit Type: Infantry" + "\n" + "Current Soldier Count: "
							+ unit.getCurrentSoldierCount()+ "\n" 
							+ "Unit's Level: "+ unit.getLevel()+ "\n " 
							+ "Level:"+ unit.getLevel();

				} else {
					s += "Unit Type: Cavalry" + "\n" + "Current Soldier Count: "
							+ unit.getCurrentSoldierCount()
							+ "\n" + "Unit's Level: "
							+ unit.getLevel()
							+ "\n " + "Max Soldier Count:" + unit.getMaxSoldierCount();
				}
			}
		}
		armyInfo.setText(s); 
	}
	//

	@Override
	public void actionPerformed(ActionEvent e) {
		this.revalidate();
		this.repaint();
		if (e.getSource() == cairo) {
			boolean flag=true ;
			for (int i=0 ; i<parent.getGame().getPlayer().getControlledCities().size();i++){
				if (parent.getGame().getPlayer().getControlledCities().get(i).getName().equals("cairo") || parent.getsView().getCity().equalsIgnoreCase("cairo") ) {
					parent.setVisib("cairo");
					
					flag=false; 
				}
				}
			if (flag==true)
				JOptionPane.showMessageDialog(this, "City is not controlled ", "Error", JOptionPane.ERROR_MESSAGE);
			this.revalidate();
			this.repaint();
		}

		if (e.getSource() == rome) {
			boolean flag=true ;
			for (int i=0 ; i<parent.getGame().getPlayer().getControlledCities().size();i++){
				if (parent.getGame().getPlayer().getControlledCities().get(i).getName().equalsIgnoreCase("rome") || parent.getsView().getCity().equalsIgnoreCase("rome") ) {
					parent.setVisib("Rome");
					flag=false; 
				}
			}
			if (flag==true)
				JOptionPane.showMessageDialog(this, "City is not controlled ", "Error", JOptionPane.ERROR_MESSAGE);
				
		}

		if (e.getSource() == sparta) {
			boolean flag=true ;
			for (int i=0 ; i<parent.getGame().getPlayer().getControlledCities().size();i++){
				if (parent.getGame().getPlayer().getControlledCities().get(i).getName().equalsIgnoreCase("sparta") || parent.getsView().getCity().equalsIgnoreCase("sparta") ) {
					parent.setVisib("Sparta");
					flag = false ;
				}
			}
			if (flag==true)
				JOptionPane.showMessageDialog(this, "City is not controlled ", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		if (e.getSource()== Attack ) {
			b = new BattleView();
			b.setVisible(true);
			b.setBounds(0, 0, 2000, 2000);
			b.setExtendedState(JFrame.MAXIMIZED_BOTH);
			}
		
			
			
			
	}
	
	

}
