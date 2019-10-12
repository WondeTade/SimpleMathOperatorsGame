package com.wonde.math;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Containers {
	
	public JPanel panel = new JPanel();
	public JPanel headerPanel = new JPanel();
	private JLabel playerScore;
	private JLabel playerLevel;
	private JLabel mathOperator;
	public JButton resetButton;
	public JButton quiteButton;

	private int level = 1;
	private int score = 0;

	int firstCapturedNum = 0;
	int secondCapturedNum = 0;

	private final int WINDOW_WIDTH = 1000;
	int mouseCount = 0;
	String name;
	int age;
	
	public Containers(String name, int age) {

		JLabel player, playerName, scoreLabel, levelLabel;
		headerPanel.setLocation(0, 0);
		headerPanel.setSize(WINDOW_WIDTH, 50);
		headerPanel.setBackground(Color.BLUE);
		headerPanel.setVisible(true);

		quiteButton = new JButton("Quit");
		quiteButton.setLocation(890, 10);
		quiteButton.setSize(100, 30);
		quiteButton.setVisible(true);

		resetButton = new JButton("Reset");
		resetButton.setLocation(780, 10);
		resetButton.setSize(100, 30);
		resetButton.setVisible(true);

		player = new JLabel("PLAYER: ");
		player.setForeground(Color.WHITE);
		player.setFont(new Font("serif", Font.BOLD, 15));
		player.setSize(100, 30);
		player.setLocation(10, 10);
		player.setVisible(true);

		playerName = new JLabel();
		playerName.setForeground(Color.WHITE);
		playerName.setFont(new Font("serif", Font.BOLD, 15));
		playerName.setSize(120, 30);
		playerName.setText(this.name + " Age " + this.age);
		playerName.setLocation(120, 10);
		playerName.setVisible(true);

		scoreLabel = new JLabel("SCORE: ");
		scoreLabel.setSize(100, 30);
		scoreLabel.setForeground(Color.GREEN);
		scoreLabel.setFont(new Font("serif", Font.BOLD, 20));
		scoreLabel.setLocation(250, 10);
		scoreLabel.setVisible(true);

		playerScore = new JLabel("" + this.score);
		playerScore.setFont(new Font("serif", Font.BOLD, 20));
		playerScore.setSize(60, 30);
		playerScore.setLocation(360, 10);
		playerScore.setForeground(Color.GREEN);
		playerScore.setVisible(true);

		levelLabel = new JLabel("LEVEL: ");
		levelLabel.setSize(100, 30);
		levelLabel.setForeground(Color.WHITE);
		levelLabel.setFont(new Font("serif", Font.BOLD, 15));
		levelLabel.setLocation(430, 10);
		levelLabel.setVisible(true);

		playerLevel = new JLabel("" + this.level);
		playerLevel.setForeground(Color.WHITE);
		playerLevel.setFont(new Font("serif", Font.BOLD, 15));
		playerLevel.setSize(60, 30);
		playerLevel.setLocation(540, 10);
		playerLevel.setVisible(true);

		mathOperator = new JLabel("OPERATOR: +");
		mathOperator.setForeground(Color.YELLOW);
		mathOperator.setFont(new Font("serif", Font.BOLD, 20));
		mathOperator.setSize(150, 30);
		mathOperator.setLocation(620, 10);
		mathOperator.setVisible(true);

		headerPanel.add(player);
		headerPanel.add(playerName);
		headerPanel.add(scoreLabel);
		headerPanel.add(playerScore);
		headerPanel.add(levelLabel);
		headerPanel.add(playerLevel);
		headerPanel.add(mathOperator);
		headerPanel.add(resetButton);
		headerPanel.add(quiteButton);

		panel.setLayout(new GridLayout(5, 5));
		panel.setLocation(0, 50);
		panel.setSize(1000, 600);
		panel.setBackground(Color.gray);
		quitAndResetButtonsActionListener();
	}
	
	public void quitAndResetButtonsActionListener() {

		quiteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int input = JOptionPane.showConfirmDialog(null, "Sure? \nYOU WANT TO CLOSE THE GAME? ", "QUITE!!!",
						JOptionPane.YES_NO_OPTION);
				if (input == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}

		});

		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int input = JOptionPane.showConfirmDialog(null, "Sure? \nYOU WANT TO RESET THE GAME? ", "RESET!",
						JOptionPane.YES_NO_OPTION);
				if (input == JOptionPane.YES_OPTION) {
//					gameReset();
				}
			}
		});
	}


}
