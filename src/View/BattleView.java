package View;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import engine.City;
import exceptions.FriendlyFireException;
import units.*;

public class BattleView extends JFrame implements ActionListener {

	private GUI parent;

	private JTextField playerName , defendingCity;
	private JComboBox<String> targetCity;
	private JTextArea defendingCityUnits;
	private JButton autoResolve , Attack;
	private JRadioButton archerp, cavalaryp, infantryp, archerd, cavalaryd, infantryd;
	private ButtonGroup bg, bg1;
	private JTextArea playerArmy, targetArmy, battleflow;

	public BattleView() {

		this.setTitle("Battle");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.getContentPane().setLayout(null);
		this.setBounds(0, 0, 2000, 2000);
		this.setVisible(true);
		this.pack();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// this.parent = parent;
		// this.getContentPane().setLayout(new FlowLayout());

		// PLAYER CITY
		// String name = parent.getGame().getPlayer().getName();
		playerName = new JTextField("player Name");
		playerName.setFont(new Font("Arial", Font.PLAIN, 15));
		playerName.setBounds(50, 50, 150, 50);
		playerName.setEditable(false);
		playerName.setBackground(Color.white);
		playerName.setForeground(Color.black);
		

		this.add(playerName);
		// this.getContentPane().add(playerName);

		// EACH UNIT IN PLAYERS ARMY
		playerArmy = new JTextArea("player Army");
		playerArmy.setFont(new Font("Arial", Font.PLAIN, 15));
		playerArmy.setBounds(50, 150, 200, 300);
		playerArmy.setBackground(Color.white);
		playerArmy.setForeground(Color.black);
		// displayUnits();
		this.add(playerArmy);
		playerName.setEditable(false);

		// TO GET ATTACKED TARGET CITY PLAYER MUST CHOSE ONE
		// String[] cities = { "select Target City", "cairo", "Rome", "Sparta" };
		targetCity = new JComboBox<String>();
		targetCity.setToolTipText("select Target city");
		targetCity.addItem("cairo");
		targetCity.addItem("rome");
		targetCity.addItem("sparta");
		targetCity.setBounds(1100, 25, 200, 50);
		targetCity.addActionListener(this);
		targetCity.setFont(new Font("Arial", Font.PLAIN, 15));
		targetCity.setBackground(Color.white);
		targetCity.setForeground(Color.black);
		this.add(targetCity);
		// System.out.println(targetCity.getSelectedItem());

		// NAME OF ATTACKED CITY
		defendingCity = new JTextField("defending City");
		defendingCity.setFont(new Font("Arial", Font.PLAIN, 15));
		defendingCity.setBounds(1125, 75, 150, 25);
		defendingCity.setBackground(Color.white);
		defendingCity.setForeground(Color.black);
		defendingCity.setEditable(false);
		this.add(defendingCity);

		// EACH UNIT IN TARGET CITY ARMY
		targetArmy = new JTextArea("");
		targetArmy.setFont(new Font("Arial", Font.PLAIN, 15));
		targetArmy.setBounds(1100, 150, 200, 300);
		targetArmy.setBackground(Color.white);
		targetArmy.setForeground(Color.black);
		this.add(targetArmy);

		// AUTO RESOLVE
		autoResolve = new JButton("Auto Resolve");
		System.out.println("auto resolve");
		autoResolve.setBounds(1100, 700, 200, 50);
		autoResolve.addActionListener(this);
		this.add(autoResolve);
		autoResolve.setFont(new Font("Arial", Font.PLAIN, 15));
		autoResolve.setBackground(Color.white);
		autoResolve.setForeground(Color.black);


		// BATTLE FLOW LOG
		battleflow = new JTextArea(" battle flow");
		battleflow.setFont(new Font("Arial", Font.PLAIN, 15));
		battleflow.setBounds(500, 150, 350, 300);
		battleflow.setBackground(Color.white);
		battleflow.setForeground(Color.black);
		this.add(battleflow);

		// ATTACK

		Attack = new JButton("Attack");
		this.add(Attack);
		Attack.addActionListener(this);
		Attack.setFont(new Font("Arial", Font.PLAIN, 15));
		Attack.setBounds(900, 700, 200, 50);
		Attack.setBackground(Color.white);
		Attack.setForeground(Color.darkGray);

		// BUTTON GROUP FOR PLAYER UNITS

		bg = new ButtonGroup();
		archerp = new JRadioButton("archer");
		archerp.setActionCommand("archer");
		archerp.setBounds(300, 150, 100, 50);

		cavalaryp = new JRadioButton("cavalary");
		cavalaryp.setActionCommand("cavalary");
		cavalaryp.setBounds(300, 250, 100, 50);

		infantryp = new JRadioButton("infantry");
		infantryp.setActionCommand("infantry");
		infantryp.setBounds(300, 350, 100, 50);

		this.add(archerp);
		this.add(infantryp);
		this.add(cavalaryp);

		archerp.addActionListener(this);
		infantryp.addActionListener(this);
		cavalaryp.addActionListener(this);

		bg.add(archerp);
		bg.add(infantryp);
		bg.add(cavalaryp);

		// BUTTON GROUP FOR ATTACKED UNITS
		bg1 = new ButtonGroup();
		archerd = new JRadioButton("archer");
		archerd.setActionCommand("archer");
		archerd.setBounds(900, 150, 100, 50);

		cavalaryd = new JRadioButton("cavalary");
		cavalaryd.setActionCommand("cavalary");
		cavalaryd.setBounds(900, 250, 100, 50);

		infantryd = new JRadioButton("infantry");
		infantryd.setActionCommand("infantry");
		infantryd.setBounds(900, 350, 100, 50);

		this.add(archerd);
		this.add(infantryd);
		this.add(cavalaryd);

		archerd.addActionListener(this);
		infantryd.addActionListener(this);
		cavalaryd.addActionListener(this);

		bg1.add(archerd);
		bg1.add(infantryd);
		bg1.add(cavalaryd);


		this.revalidate();
		this.repaint();

	}

	// display army units:
	public void displayUnits() {
		String s = "";

		for (int i = 0; i < parent.getGame().getPlayer().getControlledArmies().size(); i++) {
			Army a = parent.getGame().getPlayer().getControlledArmies().get(i);
			s += "Current Status :" + a.getCurrentStatus() + "\n" + "Current Location: " + a.getCurrentLocation() + "\n"
					+ "Target: " + a.getTarget() + "\n";

			for (int j = 0; j < a.getUnits().size(); j++) {
				Unit unit = a.getUnits().get(j);

				if (a.getUnits().get(j) instanceof Archer) {
					s += "Unit Type: Archer" + "\n" + "Current Soldier Count: " + unit.getCurrentSoldierCount() + "\n"
							+ "Unit's Level: " + unit.getLevel() + "\n " + "Max Soldier Count:"
							+ unit.getMaxSoldierCount();

				} else if (a.getUnits().get(j) instanceof Infantry) {

					s += "Unit Type: Infantry" + "\n" + "Current Soldier Count: " + unit.getCurrentSoldierCount() + "\n"
							+ "Unit's Level: " + unit.getLevel() + "\n " + "Level:" + unit.getLevel();

				} else {
					s += "Unit Type: Cavalry" + "\n" + "Current Soldier Count: " + unit.getCurrentSoldierCount() + "\n"
							+ "Unit's Level: " + unit.getLevel() + "\n " + "Max Soldier Count:"
							+ unit.getMaxSoldierCount();
				}
			}
		}
		// cityUnits.setText(s);
		playerArmy.setText(s);
		System.out.println(s);

	}

	public void displayDefendingArmy(City attacked) {
		String s = "";

		Army a = attacked.getDefendingArmy();
		s += "Current Status :" + a.getCurrentStatus() + "\n" + "Current Location: " + a.getCurrentLocation() + "\n"
				+ "Target: " + a.getTarget() + "\n";

		for (int j = 0; j < a.getUnits().size(); j++) {
			Unit unit = a.getUnits().get(j);

			if (a.getUnits().get(j) instanceof Archer) {

				s += "Unit Type: Archer" + "\n" + "Current Soldier Count: " + unit.getCurrentSoldierCount() + "\n"
						+ "Unit's Level: " + unit.getLevel() + "\n " + "Max Soldier Count:" + unit.getMaxSoldierCount();

			} else if (a.getUnits().get(j) instanceof Infantry) {

				s += "Unit Type: Infantry" + "\n" + "Current Soldier Count: " + unit.getCurrentSoldierCount() + "\n"
						+ "Unit's Level: " + unit.getLevel() + "\n " + "Level:" + unit.getLevel();

			} else {
				s += "Unit Type: Cavalry" + "\n" + "Current Soldier Count: " + unit.getCurrentSoldierCount() + "\n"
						+ "Unit's Level: " + unit.getLevel() + "\n " + "Max Soldier Count:" + unit.getMaxSoldierCount();
			}
		}
		defendingCityUnits.setText(s);
		// return s;
	}

	
	
	/*
	 * public int currentSoldierCountOfUnit(Army a, Unit u) { for(int
	 * i=0;i<a.getUnits().size();i++) { if (u instanceof Archer) { if
	 * (a.getUnits().get(i) instanceof Archer ) { return
	 * a.getUnits().get(i).getCurrentSoldierCount(); } }
	 * 
	 * else if (u instanceof Cavalry) { if (a.getUnits().get(i) instanceof Cavalry )
	 * { return a.getUnits().get(i).getCurrentSoldierCount(); } } else if (u
	 * instanceof Infantry) { return a.getUnits().get(i).getCurrentSoldierCount(); }
	 * } }
	 */
	int soldiersLost;
	
	public void battlelog() {
		String s = "";
		String attackerUnit = bg.getSelection().getActionCommand();
		String attackedUnit = bg1.getSelection().getActionCommand();

		s += "Current Turn" + parent.getGame().getCurrentTurnCount() + "\n" + "Attacker Unit" + attackerUnit + "/n"
				+ "Defending Unit" + attackedUnit + "/n" + "soldiers lost:" + soldiersLost;

		battleflow.setText(s);
		
	}

	public void update() {
		String target = (String) targetCity.getSelectedItem();
		defendingCity.setText(target);
		this.displayUnits();
		this.battlelog();
		
		
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == targetCity) {
			String target = (String) targetCity.getSelectedItem();
			System.out.println(target);
			boolean flag = true;
			for (City c: parent.getGame().getPlayer().getControlledCities()) {
				if (c.getName().equals(target)) {
					JOptionPane.showMessageDialog(this, "City is controlled", "Error", JOptionPane.ERROR_MESSAGE);
					flag = false;
				}
				defendingCity.setText(target);
			}
			update();
			for (int j = 0; j < parent.getGame().getAvailableCities().size() && flag == true; j++) {
				City c = parent.getGame().getAvailableCities().get(j);
				if (c.getName().equals(target))
					displayDefendingArmy(c);
			}
			this.revalidate();
			this.repaint();

		}
		
		String city = parent.getsView().getCity();

		if (e.getSource() == Attack) {
			String attacker = bg.getSelection().getActionCommand();
			String defender = bg1.getSelection().getActionCommand();
			
			if (parent.getGame().isGameOver()) {
				if (parent.getGame().getAvailableCities().size()==parent.getGame().getPlayer().getControlledCities().size())
					JOptionPane.showMessageDialog(this, "You Won", "Error", JOptionPane.ERROR_MESSAGE);
				else 
						JOptionPane.showMessageDialog(this, "You Lost", "Error", JOptionPane.ERROR_MESSAGE);
				
				

			} else if (bg.getSelection().equals(null))
				JOptionPane.showMessageDialog(this, "Choose attacker unit", "Error", JOptionPane.ERROR_MESSAGE);
			else if (bg1.getSelection().equals(null))
				JOptionPane.showMessageDialog(this, "Choose defender unit", "Error", JOptionPane.ERROR_MESSAGE);
			
			else if (defender.equalsIgnoreCase("archer")) {
				if (attacker.equalsIgnoreCase("archer")) {
					for (int i = 0; i < parent.getGame().getPlayer().getControlledArmies().size(); i++) {
						Army c = parent.getGame().getPlayer().getControlledArmies().get(i);
						for (int k = 0; k < c.getUnits().size(); k++) {
							Unit u = c.getUnits().get(k);
							if (u instanceof Archer) {
								for (int j = 0; j < parent.getGame().getAvailableCities().size(); j++) {
									City attacked = parent.getGame().getAvailableCities().get(j);
									if (attacked.getName().equals(targetCity.getSelectedItem())) {
										for (int l = 0; l < attacked.getDefendingArmy().getUnits().size(); l++) {
											Unit u1 = attacked.getDefendingArmy().getUnits().get(l);
											if (u1 instanceof Archer) {
												int soldiers = u1.getCurrentSoldierCount();
												try {
													u.attack(u1);
												} catch (FriendlyFireException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
												int soldiersCountAfterAttack = u1.getCurrentSoldierCount();
												soldiersLost = soldiers - soldiersCountAfterAttack;
											}
										}
									}
								}
							}
						}
					}
				}

				if (attacker.equalsIgnoreCase("cavalry")) {
					for (int i = 0; i < parent.getGame().getPlayer().getControlledArmies().size(); i++) {
						Army c = parent.getGame().getPlayer().getControlledArmies().get(i);

						for (int k = 0; k < c.getUnits().size(); k++) {
							Unit u = c.getUnits().get(k);
							if (u instanceof Cavalry) {
								for (int j = 0; j < parent.getGame().getAvailableCities().size(); j++) {
									City attacked = parent.getGame().getAvailableCities().get(j);
									if (attacked.getName().equals(targetCity.getSelectedItem())) {
										for (int l = 0; l < attacked.getDefendingArmy().getUnits().size(); l++) {
											Unit u1 = attacked.getDefendingArmy().getUnits().get(l);
											if (u1 instanceof Archer) {
												int soldiers = u1.getCurrentSoldierCount();
												try {
													u.attack(u1);
												} catch (FriendlyFireException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
												int soldiersCountAfterAttack = u1.getCurrentSoldierCount();
												soldiersLost = soldiers - soldiersCountAfterAttack;
											}
										}
									}
								}
							}
						}
					}
				}

				if (attacker.equalsIgnoreCase("Infantry")) {
					for (int i = 0; i < parent.getGame().getPlayer().getControlledArmies().size(); i++) {
						Army c = parent.getGame().getPlayer().getControlledArmies().get(i);
						for (int k = 0; k < c.getUnits().size(); k++) {
							Unit u = c.getUnits().get(k);
							if (u instanceof Infantry) {
								for (int j = 0; j < parent.getGame().getAvailableCities().size(); j++) {
									City attacked = parent.getGame().getAvailableCities().get(j);
									if (attacked.getName().equals(targetCity.getSelectedItem())) {
										for (int l = 0; l < attacked.getDefendingArmy().getUnits().size(); l++) {
											Unit u1 = attacked.getDefendingArmy().getUnits().get(l);
											if (u1 instanceof Archer) {
												int soldiers = u1.getCurrentSoldierCount();
												try {
													u.attack(u1);
												} catch (FriendlyFireException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
												int soldiersCountAfterAttack = u1.getCurrentSoldierCount();
												soldiersLost = soldiers - soldiersCountAfterAttack;
											}
										}
									}
								}
							}
						}
					}
				}
				this.update();
			} else if (defender.equalsIgnoreCase("cavalry")) {
				if (attacker.equalsIgnoreCase("archer")) {
					for (int i = 0; i < parent.getGame().getPlayer().getControlledArmies().size(); i++) {
						Army c = parent.getGame().getPlayer().getControlledArmies().get(i);
						for (int k = 0; k < c.getUnits().size(); k++) {
							Unit u = c.getUnits().get(k);
							if (u instanceof Archer) {
								for (int j = 0; j < parent.getGame().getAvailableCities().size(); j++) {
									City attacked = parent.getGame().getAvailableCities().get(j);
									if (attacked.getName().equals(targetCity.getSelectedItem())) {
										for (int l = 0; l < attacked.getDefendingArmy().getUnits().size(); l++) {
											Unit u1 = attacked.getDefendingArmy().getUnits().get(l);
											if (u1 instanceof Cavalry) {
												int soldiers = u1.getCurrentSoldierCount();
												try {
													u.attack(u1);
												} catch (FriendlyFireException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
												int soldiersCountAfterAttack = u1.getCurrentSoldierCount();
												soldiersLost = soldiers - soldiersCountAfterAttack;
											}
										}
									}
								}
							}
						}
					}
				}

				if (attacker.equalsIgnoreCase("cavalry")) {
					for (int i = 0; i < parent.getGame().getPlayer().getControlledArmies().size(); i++) {
						Army c = parent.getGame().getPlayer().getControlledArmies().get(i);
						for (int k = 0; k < c.getUnits().size(); k++) {
							Unit u = c.getUnits().get(k);
							if (u instanceof Cavalry) {
								for (int j = 0; j < parent.getGame().getAvailableCities().size(); j++) {
									City attacked = parent.getGame().getAvailableCities().get(j);
									if (attacked.getName().equals(targetCity.getSelectedItem())) {
										for (int l = 0; l < attacked.getDefendingArmy().getUnits().size(); l++) {
											Unit u1 = attacked.getDefendingArmy().getUnits().get(l);
											if (u1 instanceof Cavalry) {
												int soldiers = u1.getCurrentSoldierCount();
												try {
													u.attack(u1);
												} catch (FriendlyFireException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
												int soldiersCountAfterAttack = u1.getCurrentSoldierCount();
												soldiersLost = soldiers - soldiersCountAfterAttack;
											}
										}
									}
								}
							}
						}
					}
				}

				if (attacker.equalsIgnoreCase("Infantry")) {
					for (int i = 0; i < parent.getGame().getPlayer().getControlledArmies().size(); i++) {
						Army c = parent.getGame().getPlayer().getControlledArmies().get(i);
						for (int k = 0; k < c.getUnits().size(); k++) {
							Unit u = c.getUnits().get(k);
							if (u instanceof Infantry) {
								for (int j = 0; j < parent.getGame().getAvailableCities().size(); j++) {
									City attacked = parent.getGame().getAvailableCities().get(j);
									if (attacked.getName().equals(targetCity.getSelectedItem())) {
										for (int l = 0; l < attacked.getDefendingArmy().getUnits().size(); l++) {
											Unit u1 = attacked.getDefendingArmy().getUnits().get(l);
											if (u1 instanceof Cavalry) {
												int soldiers = u1.getCurrentSoldierCount();
												try {
													u.attack(u1);
												} catch (FriendlyFireException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
												int soldiersCountAfterAttack = u1.getCurrentSoldierCount();
												soldiersLost = soldiers - soldiersCountAfterAttack;
											}
										}
									}
								}
							}
						}
					}
				}
				this.update();

			} else if (defender.equalsIgnoreCase("infantry")) {
				if (attacker.equalsIgnoreCase("archer")) {
					for (int i = 0; i < parent.getGame().getPlayer().getControlledArmies().size(); i++) {
						Army c = parent.getGame().getPlayer().getControlledArmies().get(i);
						for (int k = 0; k < c.getUnits().size(); k++) {
							Unit u = c.getUnits().get(k);
							if (u instanceof Archer) {
								for (int j = 0; j < parent.getGame().getAvailableCities().size(); j++) {
									City attacked = parent.getGame().getAvailableCities().get(j);
									if (attacked.getName().equals(targetCity.getSelectedItem())) {
										for (int l = 0; l < attacked.getDefendingArmy().getUnits().size(); l++) {
											Unit u1 = attacked.getDefendingArmy().getUnits().get(l);
											if (u1 instanceof Infantry) {
												int soldiers = u1.getCurrentSoldierCount();
												try {
													u.attack(u1);
												} catch (FriendlyFireException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
												int soldiersCountAfterAttack = u1.getCurrentSoldierCount();
												soldiersLost = soldiers - soldiersCountAfterAttack;
											}
										}
									}
								}
							}
						} 
					}
				}

				if (attacker.equalsIgnoreCase("cavalry")) {
					for (int i = 0; i < parent.getGame().getPlayer().getControlledArmies().size(); i++) {
						Army c = parent.getGame().getPlayer().getControlledArmies().get(i);

						for (int k = 0; k < c.getUnits().size(); k++) {
							Unit u = c.getUnits().get(k);
							if (u instanceof Cavalry) {
								for (int j = 0; j < parent.getGame().getAvailableCities().size(); j++) {
									City attacked = parent.getGame().getAvailableCities().get(j);
									if (attacked.getName().equals(targetCity.getSelectedItem())) {
										for (int l = 0; l < attacked.getDefendingArmy().getUnits().size(); l++) {
											Unit u1 = attacked.getDefendingArmy().getUnits().get(l);
											if (u1 instanceof Infantry) {
												int soldiers = u1.getCurrentSoldierCount();
												try {
													u.attack(u1);
												} catch (FriendlyFireException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
												int soldiersCountAfterAttack = u1.getCurrentSoldierCount();
												soldiersLost = soldiers - soldiersCountAfterAttack;
											}
										}
									}
								}
							}
						}
					}
				}

				if (attacker.equalsIgnoreCase("Infantry")) {
					for (int i = 0; i < parent.getGame().getPlayer().getControlledArmies().size(); i++) {
						Army c = parent.getGame().getPlayer().getControlledArmies().get(i);

						for (int k = 0; k < c.getUnits().size(); k++) {
							Unit u = c.getUnits().get(k);
							if (u instanceof Infantry) {
								for (int j = 0; j < parent.getGame().getAvailableCities().size(); j++) {
									City attacked = parent.getGame().getAvailableCities().get(j);
									if (attacked.getName().equals(targetCity.getSelectedItem())) {
										for (int l = 0; l < attacked.getDefendingArmy().getUnits().size(); l++) {
											Unit u1 = attacked.getDefendingArmy().getUnits().get(l);
											if (u1 instanceof Infantry) {
												int soldiers = u1.getCurrentSoldierCount();
												try {
													u.attack(u1);
												} catch (FriendlyFireException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
												int soldiersCountAfterAttack = u1.getCurrentSoldierCount();
												soldiersLost = soldiers - soldiersCountAfterAttack;
											}
										}
									}
								}
							}
						}
					}
				}
				this.update();
			}

			parent.getGame().endTurn();
			parent.updateAll();

		}


		if (e.getSource() == autoResolve) {
			
			if (parent.getGame().isGameOver()) {
				
				if (parent.getGame().getAvailableCities().size()==parent.getGame().getPlayer().getControlledCities().size())
					JOptionPane.showMessageDialog(this, "You Won", "Error", JOptionPane.ERROR_MESSAGE);
				else 
						JOptionPane.showMessageDialog(this, "You Lost", "Error", JOptionPane.ERROR_MESSAGE);
				
				
			
			} else {

				for (City c : parent.getGame().getPlayer().getControlledCities()) {
					if (c.getName().equals(parent.getsView().getCity())) {
						
						for (City d : parent.getGame().getAvailableCities()) {
							if (d.getName().equals(targetCity.getSelectedItem())) {
								try {
									parent.getGame().autoResolve(c.getDefendingArmy(), d.getDefendingArmy());
								} catch (FriendlyFireException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
					}
				}
				parent.getGame().endTurn();
				parent.updateAll();

			}

		}
	}
}
