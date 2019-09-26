package com.wonde.math;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Main extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String name = null;
	private static int age = 0;

	public Main() {
		
		initUI(); 
	}
	
	//initialize user interface 
	   private void initUI() {
	        
	        add(new WelcomePlayer());

	        setResizable(false);
	        setSize(1000, 650);
	        
	        setTitle("WelCome To MathsCrusher");    
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
	    }
	
	public static void main(String[] args) throws Exception{
		
		  EventQueue.invokeLater(() -> {
//		
			JFrame numberAnimator = new Main();
		    numberAnimator.setVisible(true);
		        
		    String input = JOptionPane.showInputDialog("Enter age");
		    age = Integer.parseInt(input);
		   	
//		    if (age < 18) 
//    		{
//    			int choice = JOptionPane.showConfirmDialog(null, "This Age group is included for now \n You want to take? ", 
//						"Age >= 18", JOptionPane.YES_NO_OPTION);
//    			if (choice == JOptionPane.YES_OPTION )
//    			{
//    				name = JOptionPane.showInputDialog("Enter Player's Name");
//    	    		if (name.isEmpty()) 
//    	    		{
//    	    			name = "No name";
//    	    		}
////    				numberAnimator.dispose();
//    				new Window(name, age);
//    			}
//    			else 
//    			{
//    				System.exit(0);
//    			}
//    		}
//		   	else
//		   	{
//		   	   name = JOptionPane.showInputDialog("Enter Player's Name");
//	    		if (name.isEmpty()) 
//	    		{
//	    			name = "No name";
//	    		}
//		   	}
		    name = JOptionPane.showInputDialog("Enter Player's Name");
		    if (name.isEmpty()) 
    		{
    			name = "No name";
    		}
	    		numberAnimator.dispose();
	    		new Window(name, age);
		  });
	}
}
