package View;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import engine.City;
import engine.Game;

public class GUI extends JFrame {
	private Startview sView;
	private Gameview gView;
	private CitiesView cityView;
	private Buttons Citybutton;
	private Game game;
	private BattleView battle;
 
	
	public GUI() {
		this.setTitle("Empire");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.getContentPane().setLayout(null);
		this.setSize(1700, 800);
		this.setVisible(true);
		
		sView = new Startview(this);
		sView.setBounds(0, 0, 1700, 800);
		gView = new Gameview(this);
	    gView.setBounds(10, 20, 230, 150);
		this.getContentPane().add(sView);	
		this.revalidate();
		this.repaint();
		//updateAll();	
		
		}
	
	
	public void test(String playerName, String cityName) {
		System.out.println(playerName + " " + cityName);
		sView.setVisible(false);
		try {
			game = new Game(playerName, cityName);
		} catch (IOException e) {
           
		}
		gView.setVisible(true);
	    Citybutton = new Buttons(this);
	    Citybutton.setBounds(0,0, 1600, 1600);
	    this.getContentPane().add(Citybutton);
		this.getContentPane().add(gView);
		gView.setBorder(BorderFactory.createLineBorder(Color.darkGray));
		updateAll();
		Citybutton.displayControlledArmies();
		
	}
	
	public void backToMapView() {
			cityView.setVisible(false);
			Citybutton.setVisible(true);
		}
	
	public void endGame () {
		
	}
	public void updateAll() {
		gView.updateLabel(); 
		//cityView.Display2();
		//Citybutton.displayControlledArmies();
		battle.displayUnits();
		battle.battlelog();
		
		
	}
	
	public void setVisib(String city) {
		Citybutton.setVisible(false);
		cityView = new CitiesView(this);
		cityView.setBounds(0, 0, 1700, 800);
		this.getContentPane().add(cityView);
		cityView.setBorder(BorderFactory.createLineBorder(Color.darkGray));
		cityView.setVisible(true);
		cityView.Display(city);
		this.revalidate();
		this.repaint();
		
}
	
	public static void main(String [] args) {
		GUI view = new GUI();
		
		
	}
	
	public Game getGame() {
		return game;
	}


	public void setGame(Game game) {
		this.game = game;
	}


	public Startview getsView() {
		return sView;
	}
	public void setsView(Startview sView) {
		this.sView = sView;
	}
	



}
