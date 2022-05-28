package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Startview extends JLabel implements ActionListener{
	
	private GUI parent;
	private JLabel playerName;
	private JLabel cityName;
	//private JLabel empire;
	private JTextField name;
	private JButton Cairo;
	private JButton Rome;
	private JButton Sparta;
	private JButton start;
	private String city = "";
	


	public Startview(GUI parent) {
    	   this.parent = parent;
    	  //this.setSize(1700, 700);
    	   this.setVisible(true);
    	  
    	   this.setIcon(new ImageIcon("newback.jpg"));
    	  
          /* empire = new JLabel("Empire");
   	       empire.setBounds(1200, 60, 300, 200);
   	       this.add(empire);
   	       empire.setFont(new Font("Arial", Font., 75));
   	       empire.setForeground(Color.white); */
    	  
	       start = new JButton("  Play  ");
	       start.setBounds(130, 660, 150, 70);
	       this.add(start);
	       start.addActionListener(this);
	       start.setFont(new Font("Arial", Font.PLAIN, 20));
	       start.setBackground(Color.white);
	       start.setForeground(Color.darkGray);
   	   
    	   playerName = new JLabel("Player Name");
    	   playerName.setBounds(25, 150, 200, 50);
    	   this.add(playerName);
    	   playerName.setFont(new Font("Arial", Font.PLAIN, 26));
    	   playerName.setForeground(Color.white);
    	   
    	   name = new JTextField();
    	   name.setBounds(190, 160, 140, 35);
    	   name.addActionListener(this);
    	   this.add(name);
    	   name.setFont(new Font("Arial", Font.PLAIN, 20));
    	   name.setBackground(Color.white);
    	   
    	   cityName = new JLabel("Choose A City");
    	   cityName.setBounds(25, 330, 190, 50);
    	   this.add(cityName);
    	   cityName.setFont(new Font("Arial", Font.BOLD, 20));
    	   cityName.setForeground(Color.white);
    	   
    	   Cairo = new JButton("Cairo");
    	   Cairo.setBounds(30, 400, 100, 50);
    	   this.add(Cairo);
    	   Cairo.addActionListener(this);
    	   Cairo.setFont(new Font("Arial", Font.PLAIN, 20));
    	   Cairo.setBackground(Color.white);
    	   Cairo.setForeground(Color.darkGray);
    	   
    	   Rome = new JButton("Rome");
    	   Rome.setBounds(170, 400, 100, 50);
    	   Rome.addActionListener(this);
    	   this.add(Rome);
    	   Rome.setFont(new Font("Arial", Font.PLAIN, 20));
    	   Rome.setBackground(Color.white);
    	   Rome.setForeground(Color.darkGray);
    	  
    	   
    	   Sparta = new JButton("Sparta");
    	   Sparta.setBounds(310, 400, 100, 50);
    	   Sparta.addActionListener(this);
    	   this.add(Sparta);
    	   Sparta.setFont(new Font("Arial", Font.PLAIN, 20));
    	   Sparta.setBackground(Color.white);
    	   Sparta.setForeground(Color.darkGray);
    	   
    	 
       }


       public void actionPerformed(ActionEvent e) {
   		if (e.getSource() == start) {
   			System.out.println("Test");
   			if (name.getText().equals("")) {
   				JOptionPane.showMessageDialog(this, "You must input a name", "Error", JOptionPane.ERROR_MESSAGE);
   			}
   		 if (city.equals("")) {
				JOptionPane.showMessageDialog(this, "You must choose a city", "Error", JOptionPane.ERROR_MESSAGE);
			} 
   		 else {
   			parent.test(name.getText(), city);
   			
   		 }
   		}
   		 else if (e.getSource() == Cairo) {
   			city = "Cairo";
   			//parent.test(name.getText(), city);
   		}
   		else if (e.getSource() == Rome) {
   			city = "Rome";
   			//parent.test(name.getText(), city);
   		}
   		else if (e.getSource() == Sparta) {
   			city = "Sparta";
   			
   		}
   		

   		}
   	

       public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}

	
       
}
