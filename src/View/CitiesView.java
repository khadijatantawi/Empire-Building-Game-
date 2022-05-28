package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import buildings.*;
import engine.City;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import units.*;

public class CitiesView extends JLabel implements ActionListener {

	private GUI parent;
	private JTextArea Info;
	private JButton UpgradeMilBuilding;
	private JButton UpgradeEcoBuilding;
	private JButton IntiateArmy;
	private JButton NewBuilding;
	private JButton RecruitUnit;
	// private JButton ControlledCities;
	private JButton ControlledArmies;
	private JButton AvailableCities;
	ArrayList<String> types;

	public CitiesView(GUI parent) {
		this.parent = parent;

		Info = new JTextArea("");
		this.add(Info);
		Info.setBounds(400, 10, 300, 300);
		Info.setEditable(false);

		UpgradeMilBuilding = new JButton("Upgrade Military Building");
		this.add(UpgradeMilBuilding);
		UpgradeMilBuilding.setBounds(400, 350, 250, 50);
		UpgradeMilBuilding.setFont(new Font("Arial", Font.PLAIN, 15));
		UpgradeMilBuilding.setBackground(Color.black);
		UpgradeMilBuilding.setForeground(Color.black);
		UpgradeMilBuilding.addActionListener(this);

		UpgradeEcoBuilding = new JButton("Upgrade Economical Building");
		this.add(UpgradeEcoBuilding);
		UpgradeEcoBuilding.setBounds(400, 410, 250, 50);
		UpgradeEcoBuilding.setFont(new Font("Arial", Font.PLAIN, 15));
		UpgradeEcoBuilding.setBackground(Color.black);
		UpgradeEcoBuilding.setForeground(Color.black);
		UpgradeEcoBuilding.addActionListener(this);

		IntiateArmy = new JButton(" Intiate Army");
		this.add(IntiateArmy);
		IntiateArmy.setBounds(400, 470, 250, 50);
		IntiateArmy.setFont(new Font("Arial", Font.PLAIN, 15));
		IntiateArmy.setBackground(Color.black);
		IntiateArmy.setForeground(Color.black);

		NewBuilding = new JButton("Create New Building");
		this.add(NewBuilding);
		NewBuilding.setBounds(400, 530, 250, 50);
		NewBuilding.setFont(new Font("Arial", Font.PLAIN, 15));
		NewBuilding.setBackground(Color.black);
		NewBuilding.setForeground(Color.black);
		NewBuilding.addActionListener(this);

		RecruitUnit = new JButton("Recruit Unit");
		this.add(RecruitUnit);
		RecruitUnit.setBounds(400, 590, 250, 50);
		RecruitUnit.setFont(new Font("Arial", Font.PLAIN, 15));
		RecruitUnit.setBackground(Color.black);
		RecruitUnit.setForeground(Color.black);
		RecruitUnit.addActionListener(this);

		/*
		 * ControlledCities = new JButton("ControlledCities");
		 * this.add(ControlledCities); ControlledCities.setBounds(400, 700, 250, 50);
		 * ControlledCities.addActionListener(this); ControlledCities.setFont(new
		 * Font("Arial", Font.PLAIN, 15)); ControlledCities.setBackground(Color.white);
		 * ControlledCities.setForeground(Color.darkGray);
		 */

		ControlledArmies = new JButton("ControlledArmies");
		this.add(ControlledArmies);
		ControlledArmies.setBounds(400, 650, 250, 50);
		ControlledArmies.addActionListener(this);
		ControlledArmies.setFont(new Font("Arial", Font.PLAIN, 15));
		ControlledArmies.setBackground(Color.white);
		ControlledArmies.setForeground(Color.darkGray);
		ControlledArmies.addActionListener(this);

		AvailableCities = new JButton("AvailableCities");
		this.add(AvailableCities);
		AvailableCities.setBounds(400, 710, 250, 50);
		AvailableCities.addActionListener(this);
		AvailableCities.setFont(new Font("Arial", Font.PLAIN, 15));
		AvailableCities.setBackground(Color.white);
		AvailableCities.setForeground(Color.darkGray);
		AvailableCities.addActionListener(this);

		this.revalidate();
		this.repaint();

	}

	int cIndex;

	public void Display(String city) {

		String s = "";
		for (int i = 0; i < parent.getGame().getPlayer().getControlledCities().size(); i++) {
			// System.out.println(parent.getGame().getPlayer().getControlledCities().get(i).getName());
			if (parent.getGame().getPlayer().getControlledCities().get(i).getName().equalsIgnoreCase(city)) {

				cIndex = i;
				s += "City Name: " + city + "\n" + "UnderSiege :"
						+ parent.getGame().getPlayer().getControlledCities().get(i).isUnderSiege() + "\n";

				for (int j = 0; j < parent.getGame().getPlayer().getControlledCities().get(i).getMilitaryBuildings()
						.size(); j++) {
					if (parent.getGame().getPlayer().getControlledCities().get(i).getMilitaryBuildings()
							.get(j) instanceof ArcheryRange) {
						s += "Military Buildings: " + "ArcheryRange" + "\n" + "Cost:"
								+ parent.getGame().getPlayer().getControlledCities().get(i).getMilitaryBuildings()
										.get(j).getCost()
								+ "\n " + "Level:"
								+ parent.getGame().getPlayer().getControlledCities().get(i).getMilitaryBuildings()
										.get(j).getLevel()
								+ "\n" + "Upgrade Level: "
								+ parent.getGame().getPlayer().getControlledCities().get(i).getMilitaryBuildings()
										.get(j).getUpgradeCost()
								+ "\n" + "Cool Down: "
								+ parent.getGame().getPlayer().getControlledCities().get(i).getMilitaryBuildings()
										.get(j).isCoolDown()
								+ "\n" + "Recruitment Cost: "
								+ parent.getGame().getPlayer().getControlledCities().get(i).getMilitaryBuildings()
										.get(j).getRecruitmentCost()
								+ "\n" + "Maximum Recruit: " + parent.getGame().getPlayer().getControlledCities().get(i)
										.getMilitaryBuildings().get(j).getMaxRecruit();

					} else if (parent.getGame().getPlayer().getControlledCities().get(i).getMilitaryBuildings()
							.get(j) instanceof Barracks) {
						s += "Military Buildings: " + "Barracks" + "\n" + "Cost:"
								+ parent.getGame().getPlayer().getControlledCities().get(i).getMilitaryBuildings()
										.get(j).getCost()
								+ "\n " + "Level:"
								+ parent.getGame().getPlayer().getControlledCities().get(i).getMilitaryBuildings()
										.get(j).getLevel()
								+ "\n" + "Upgrade Level: "
								+ parent.getGame().getPlayer().getControlledCities().get(i).getMilitaryBuildings()
										.get(j).getUpgradeCost()
								+ "\n" + "Cool Down: "
								+ parent.getGame().getPlayer().getControlledCities().get(i).getMilitaryBuildings()
										.get(j).isCoolDown()
								+ "\n" + "Recruitment Cost: "
								+ parent.getGame().getPlayer().getControlledCities().get(i).getMilitaryBuildings()
										.get(j).getRecruitmentCost()
								+ "\n" + "Maximum Recruit: " + parent.getGame().getPlayer().getControlledCities().get(i)
										.getMilitaryBuildings().get(j).getMaxRecruit();

					} else {
						s += "Military Buildings: " + "Stable" + "\n" + "Cost:"
								+ parent.getGame().getPlayer().getControlledCities().get(i).getMilitaryBuildings()
										.get(j).getCost()
								+ "\n " + "Level:"
								+ parent.getGame().getPlayer().getControlledCities().get(i).getMilitaryBuildings()
										.get(j).getLevel()
								+ "\n" + "Upgrade Level: "
								+ parent.getGame().getPlayer().getControlledCities().get(i).getMilitaryBuildings()
										.get(j).getUpgradeCost()
								+ "\n" + "Cool Down: "
								+ parent.getGame().getPlayer().getControlledCities().get(i).getMilitaryBuildings()
										.get(j).isCoolDown()
								+ "\n" + "Recruitment Cost: "
								+ parent.getGame().getPlayer().getControlledCities().get(i).getMilitaryBuildings()
										.get(j).getRecruitmentCost()
								+ "\n" + "Maximum Recruit: " + parent.getGame().getPlayer().getControlledCities().get(i)
										.getMilitaryBuildings().get(j).getMaxRecruit();

					}

				}

				for (int j = 0; j < parent.getGame().getPlayer().getControlledCities().get(i).getEconomicalBuildings()
						.size(); j++) {
					if (parent.getGame().getPlayer().getControlledCities().get(i).getEconomicalBuildings()
							.get(j) instanceof Farm) {
						s += "Military Buildings: " + "ArcheryRange" + "\n" + "Cost:" + parent.getGame().getPlayer()
								.getControlledCities().get(i).getEconomicalBuildings().get(j).getCost() + "\n "
								+ "Level:"
								+ parent.getGame().getPlayer().getControlledCities().get(i).getEconomicalBuildings()
										.get(j).getLevel()
								+ "\n" + "Upgrade Level: "
								+ parent.getGame().getPlayer().getControlledCities().get(i).getEconomicalBuildings()
										.get(j).getUpgradeCost()
								+ "\n" + "Cool Down: " + parent.getGame().getPlayer().getControlledCities().get(i)
										.getEconomicalBuildings().get(j).isCoolDown();

					} else if (parent.getGame().getPlayer().getControlledCities().get(i).getEconomicalBuildings()
							.get(j) instanceof Market) {
						s += "Military Buildings: " + "Barracks" + "\n" + "Cost:" + parent.getGame().getPlayer()
								.getControlledCities().get(i).getEconomicalBuildings().get(j).getCost() + "\n "
								+ "Level:"
								+ parent.getGame().getPlayer().getControlledCities().get(i).getEconomicalBuildings()
										.get(j).getLevel()
								+ "\n" + "Upgrade Level: "
								+ parent.getGame().getPlayer().getControlledCities().get(i).getEconomicalBuildings()
										.get(j).getUpgradeCost()
								+ "\n" + "Cool Down: " + parent.getGame().getPlayer().getControlledCities().get(i)
										.getEconomicalBuildings().get(j).isCoolDown();

					}

				}
			}
			s += "Defending Army's Current Location: " + parent
					.getGame().getPlayer().getControlledCities().get(cIndex).getDefendingArmy().getCurrentLocation()
					+ "\n" + "Defending Army's Current Status: "
					+ parent.getGame().getPlayer().getControlledCities().get(cIndex).getDefendingArmy()
							.getCurrentStatus()
					+ "\n" + "Target City: "
					+ parent.getGame().getPlayer().getControlledCities().get(cIndex).getDefendingArmy().getTarget();
		}
		// System.out.println("s: " + s);
		Info.setText(s);
	}

	public void Display2() {
		String s = "";
		for (int i = 0; i < parent.getGame().getPlayer().getControlledArmies().size(); i++) {
			s += "Current Status :" + parent.getGame().getPlayer().getControlledArmies().get(i).getCurrentStatus()
					+ "\n" + "Current Location: "
					+ parent.getGame().getPlayer().getControlledArmies().get(i).getCurrentLocation() + "\n" + "Target: "
					+ parent.getGame().getPlayer().getControlledArmies().get(i).getTarget() + "\n";

			for (int j = 0; j < parent.getGame().getPlayer().getControlledArmies().get(i).getUnits().size(); j++) {
				if (parent.getGame().getPlayer().getControlledArmies().get(i).getUnits().get(j) instanceof Archer) {

					s += "Unit Type: Archer" + "\n" + "Current Soldier Count: "
							+ parent.getGame().getPlayer().getControlledArmies().get(i).getUnits().get(j)
									.getCurrentSoldierCount()
							+ "\n" + "Unit's Level: "
							+ parent.getGame().getPlayer().getControlledArmies().get(i).getUnits().get(j).getLevel()
							+ "\n " + "Max Soldier Count:" + parent.getGame().getPlayer().getControlledArmies().get(i)
									.getUnits().get(j).getMaxSoldierCount();

				} else if (parent.getGame().getPlayer().getControlledArmies().get(i).getUnits()
						.get(j) instanceof Infantry) {

					s += "Unit Type: Infantry" + "\n" + "Current Soldier Count: "
							+ parent.getGame().getPlayer().getControlledArmies().get(i).getUnits().get(j)
									.getCurrentSoldierCount()
							+ "\n" + "Unit's Level: "
							+ parent.getGame().getPlayer().getControlledArmies().get(i).getUnits().get(j).getLevel()
							+ "\n " + "Level:"
							+ parent.getGame().getPlayer().getControlledArmies().get(i).getUnits().get(j).getLevel();

				} else {
					s += "Unit Type: Cavalry" + "\n" + "Current Soldier Count: "
							+ parent.getGame().getPlayer().getControlledArmies().get(i).getUnits().get(j)
									.getCurrentSoldierCount()
							+ "\n" + "Unit's Level: "
							+ parent.getGame().getPlayer().getControlledArmies().get(i).getUnits().get(j).getLevel()
							+ "\n " + "Max Soldier Count:" + parent.getGame().getPlayer().getControlledArmies().get(i)
									.getUnits().get(j).getMaxSoldierCount();

				}
			}
		}

		Info.append(s);
	}

	DefaultComboBoxModel<String> typesCombo = new DefaultComboBoxModel<String>();
	String[] unitsArray = new String[10];

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == UpgradeMilBuilding) {
			String building = (String) JOptionPane.showInputDialog(this, "Which building do you want to Upgrade");
			System.out.print(cIndex);
			for (int i = 0; i < parent.getGame().getPlayer().getControlledCities().get(cIndex).getMilitaryBuildings()
					.size(); i++) {
				if (building.equalsIgnoreCase("ArcheryRange") && parent.getGame().getPlayer().getControlledCities()
						.get(cIndex).getMilitaryBuildings().get(i) instanceof ArcheryRange) {
					try {
						parent.getGame().getPlayer().upgradeBuilding(parent.getGame().getPlayer().getControlledCities()
								.get(cIndex).getMilitaryBuildings().get(i));
					} catch (NotEnoughGoldException e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					} catch (BuildingInCoolDownException e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					} catch (MaxLevelException e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					Display(parent.getGame().getPlayer().getControlledCities().get(cIndex).getName());

				} else if (building.equalsIgnoreCase("Barracks") && parent.getGame().getPlayer().getControlledCities()
						.get(cIndex).getMilitaryBuildings().get(i) instanceof Barracks) {
					try {
						parent.getGame().getPlayer().upgradeBuilding(parent.getGame().getPlayer().getControlledCities()
								.get(cIndex).getMilitaryBuildings().get(i));
					} catch (NotEnoughGoldException e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					} catch (BuildingInCoolDownException e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					} catch (MaxLevelException e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					Display(parent.getGame().getPlayer().getControlledCities().get(cIndex).getName());

				} else if (building.equalsIgnoreCase("Stable") && parent.getGame().getPlayer().getControlledCities()
						.get(cIndex).getMilitaryBuildings().get(i) instanceof Stable) {
					try {
						parent.getGame().getPlayer().upgradeBuilding(parent.getGame().getPlayer().getControlledCities()
								.get(cIndex).getMilitaryBuildings().get(i));
					} catch (NotEnoughGoldException e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					} catch (BuildingInCoolDownException e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					} catch (MaxLevelException e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					}

					Display(parent.getGame().getPlayer().getControlledCities().get(cIndex).getName());
				}
			}
			this.repaint();
			this.revalidate();

		}

		if (e.getSource() == UpgradeEcoBuilding) {
			String building = (String) JOptionPane.showInputDialog(this, "Which building do you want to Upgrade");
			for (int i = 0; i < parent.getGame().getPlayer().getControlledCities().get(cIndex).getEconomicalBuildings()
					.size(); i++) {
				if (building.equalsIgnoreCase("Farm") && parent.getGame().getPlayer().getControlledCities().get(cIndex)
						.getEconomicalBuildings().get(i) instanceof Farm) {
					try {
						parent.getGame().getPlayer().upgradeBuilding(parent.getGame().getPlayer().getControlledCities()
								.get(cIndex).getEconomicalBuildings().get(i));
					} catch (NotEnoughGoldException e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					} catch (BuildingInCoolDownException e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					} catch (MaxLevelException e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					}

					Display(parent.getGame().getPlayer().getControlledCities().get(cIndex).getName());

				} else if (building.equalsIgnoreCase("Market") && parent.getGame().getPlayer().getControlledCities()
						.get(cIndex).getEconomicalBuildings().get(i) instanceof Market) {
					try {
						parent.getGame().getPlayer().upgradeBuilding(parent.getGame().getPlayer().getControlledCities()
								.get(cIndex).getEconomicalBuildings().get(i));
					} catch (NotEnoughGoldException e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					} catch (BuildingInCoolDownException e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					} catch (MaxLevelException e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					Display(parent.getGame().getPlayer().getControlledCities().get(cIndex).getName());
				}
			}
			this.repaint();
			this.revalidate();
		}
		int i = -1;
		if (e.getSource() == RecruitUnit) {
			String[] units = new String[] { "Archer", "Cavalary", "Infantry" };

			String unit = (String) JOptionPane.showInputDialog(null, "Please Select",
					"\"Which type of Unit do you want to Recruit", JOptionPane.QUESTION_MESSAGE, null, units, null);

			// JOptionPane option = new JOptionPane ("Please Select",
			// JOptionPane.QUESTION_MESSAGE,JOptionPane.DEFAULT_OPTION, null,units, null);
			try {

				parent.getGame().getPlayer().recruitUnit(unit,
						parent.getGame().getPlayer().getControlledCities().get(cIndex).getName());
			} catch (BuildingInCoolDownException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			} catch (MaxRecruitedException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}

			if (unit.equalsIgnoreCase("Archer")) {
				i = 0;
				for (City c : parent.getGame().getPlayer().getControlledCities()) {
					if (c.getName().equalsIgnoreCase(parent.getsView().getCity())) {
						for (Building b : c.getMilitaryBuildings()) {
							if (b instanceof ArcheryRange) {
								try {
									((ArcheryRange) b).recruit();
								} catch (BuildingInCoolDownException | MaxRecruitedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
					}
				}
			}
			if (unit.equalsIgnoreCase("Cavalry")) {
				i = 1;
				for (City c : parent.getGame().getPlayer().getControlledCities()) {
					if (c.getName().equalsIgnoreCase(parent.getsView().getCity())) {
						for (Building b : c.getMilitaryBuildings()) {
							if (b instanceof Stable) {
								try {
									((Stable) b).recruit();
								} catch (BuildingInCoolDownException | MaxRecruitedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
					}

				}
			}
			if (unit.equalsIgnoreCase("Infantry")) {
				i = 2;
				for (City c : parent.getGame().getPlayer().getControlledCities()) {
					if (c.getName().equalsIgnoreCase(parent.getsView().getCity())) {
						for (Building b : c.getMilitaryBuildings()) {
							if (b instanceof Barracks) {
								try {
									((Barracks) b).recruit();
								} catch (BuildingInCoolDownException | MaxRecruitedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
					}

				}
			}

			if (unitsArray[i] != null && i > 0)
				unitsArray[i] = unit;

			this.Display2();
			// if (typesCombo.getIndexOf(unit)==-1)

			this.repaint();
			this.revalidate();

		}

		if (e.getSource() == NewBuilding) {
			String building = (String) JOptionPane.showInputDialog(this, "Which type of building do you want to build");
			System.out.println(building);
			try {
				parent.getGame().getPlayer().build(building,
						parent.getGame().getPlayer().getControlledCities().get(cIndex).getName());
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			System.out.println(
					parent.getGame().getPlayer().getControlledCities().get(cIndex).getEconomicalBuildings().size());
			JButton buildingButton = new JButton(parent.getGame().getPlayer().getControlledCities().get(cIndex)
					.getEconomicalBuildings().get(0).getClass().getName());
			buildingButton.setBounds(700, 500, 200, 50);
			this.add(buildingButton);
			Display(parent.getGame().getPlayer().getControlledCities().get(cIndex).getName());
			this.repaint();
			this.revalidate();
		}

		if (e.getSource() == IntiateArmy) {

			// types=new ArrayList <String>() ;
			// for ( int i=0 ;i< typesCombo.getSize();i++) {

			String unitsRecruited = (String) JOptionPane.showInputDialog(null, "Please Select",
					"choose unit to intiate Army", JOptionPane.QUESTION_MESSAGE, null, unitsArray, null);

//			 should ask the player in a combo box which unit from the recruited units he
//			 wants to intiate army with.
//			option.add(typesCombo);

		}

		if (e.getSource() == ControlledArmies) {
			Display2();
			repaint();
			revalidate();
		}

		if (e.getSource() == AvailableCities) {
			// make this button make you return to the page before it again.
			parent.backToMapView();
		}

	}
}
