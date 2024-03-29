package com.wonde.math;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class MathCrusherMain extends JFrame{
	
	/** 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String name = null;
	private static int age = 0;

	public MathCrusherMain() {
		
		initUI(); 
	}
	
	public void exitGame() {
		System.exit(0);
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
			JFrame numberAnimator = new MathCrusherMain();
		    numberAnimator.setVisible(true);
		        
//		    String input = JOptionPane.showInputDialog("Enter age");
//		    age = Integer.parseInt(input);
		    age = 20;
		   	
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
