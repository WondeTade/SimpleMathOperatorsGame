package com.wonde.math;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.UIManager;

public class Window extends JWindow implements ActionListener {

	/**
	 * 
	 */

	private RandomNumberGenerator randNumber;
	private static final long serialVersionUID = 1L;
	private JWindow window = new JWindow();
	private JPanel panel = new JPanel();
	private JPanel headerPanel = new JPanel();
	private JLabel playerScore;
	private JLabel playerLevel;
	private JLabel mathOperator;
	private JButton resetButton;
	private JButton quiteButton;

	private int level = 1;
	private int row = 0;
	private int score = 0;

	int firstCapturedNum = 0;
	int secondCapturedNum = 0;

	private int currentScoreLevel_1 = 0;
	private int currentScoreLevel_2 = 0;
	private int currentScoreLevel_3 = 0;
	private int currentScoreLevel_4 = 0;
	private final int BUTTON_WIDTH = 200;
	private final int BUTTON_HEIGHT = 120;
	private final int WINDOW_WIDTH = 1000;
	private final int WINDOW_HEIGHT = 650;

	int mouseCount = 0;
	String name;
	int age;

	private JButton[][] button_array = new JButton[row][row];
	private int[][] randomNumberList;
	private JButton[] clickedButton_array;
	
	private Color[] colorList = {Color.LIGHT_GRAY, Color.CYAN, Color.MAGENTA, Color.ORANGE, Color.YELLOW};

	List<Integer> numList = new ArrayList<>();

	public Window(String name, int age) {
		this.age = age;
		this.name = name;
		windowCreator(name, age);
	}

	public JWindow windowCreator(String name, int age) {
		createContainers(name, age);
		createButtons();
		levelRunner(this.score);

		window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.add(panel);
		window.add(headerPanel);

		return window;
	}

	public void createContainers(String name, int age) {

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
					gameReset();
				}
			}
		});
	}

//	Creates button matrix
	public JButton[][] createButtons() {

		int columnWidth = 0;
		int columnHeight = 0;
		level = levelRunner(score);
		button_array = new JButton[row][row];

//		Creating Buttons
		try {
			Cursor invalidCursor = Cursor.getSystemCustomCursor("Invalid.32x32");
			Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < button_array[i].length; j++) {
					button_array[i][j] = new JButton();
					button_array[i][j].setName("btn_" + ((i * 5) + j));
					button_array[i][j].setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
					button_array[i][j].setLocation(columnWidth, columnHeight);
					button_array[i][j].setBorder(BorderFactory.createCompoundBorder(
							BorderFactory.createLineBorder(null, 1), BorderFactory.createLineBorder(null, 0)));
					button_array[i][j].addActionListener(this);
					button_array[i][j].setVisible(true);
					button_array[i][j].setCursor(handCursor);
					panel.add(button_array[i][j]);
					panel.setVisible(true);

					columnWidth += 200; // location of button increases to x direction by 186px
				}
				columnHeight += 120; // location of button increases to y direction by 104px
				columnWidth = 0;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		assignValueToButton(this.button_array);
		return button_array;
	}

	public void assignValueToButton(JButton[][] button_array) {

		randNumber = new RandomNumberGenerator();
		randomNumberList = randNumber.uniqueRandomNumberGenerator(row, level, age);
		try {
			Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < button_array[i].length; j++) {
					if (this.level == 1) {
						if (button_array[i][j] == button_array[j][i] || button_array[i][j] == button_array[0][4]
								|| button_array[i][j] == button_array[1][3] || button_array[i][j] == button_array[3][1]
								|| button_array[i][j] == button_array[4][0]) {
							button_array[i][j].setText("" + randomNumberList[i][j]);
						} else {
							button_array[i][j].setText(null);
							button_array[i][j].setEnabled(false);
						}
						panel.add(button_array[i][j]);
						panel.setVisible(true);
					} else {
						if (level == 2) {
							button_array[i][j].setText("" + randomNumberList[i][j]);
						} else if (level == 3) {
							button_array[i][j].setText("" + randomNumberList[i][j]);
						} else if (level == 4) {
							button_array[i][j].setText("" + randomNumberList[i][j]);
						} else if (level == 5) {
							button_array[i][j].setText("" + randomNumberList[i][j]);
						}
					}
					button_array[i][j].setBackground(colorList[this.level-1]);
					button_array[i][j].setFont(new Font("serif", Font.BOLD, 25));
					button_array[i][j].setCursor(handCursor);
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public void actionPerformed(ActionEvent e) {

//		get button value, when clicked
		int btnValue = Integer.parseInt(e.getActionCommand());

		selectButton(button_array, row);
		numList.add(btnValue);

		secondCapturedNum = numList.get((numList.size() - 1));

		if (numList.size() > 1) {
			firstCapturedNum = numList.get((numList.size() - 2));
			MathOperation targetValue = returnTargetValue(firstCapturedNum, secondCapturedNum);

			for (int i = 0; i < row; i++) {
				for (int j = 0; j < button_array[i].length; j++) {
					if ((button_array[i][j].getText() == null)) {
						button_array[i][j].setEnabled(false);
						continue;
					}

					if (level == 1 && (Integer.parseInt(button_array[i][j].getText()) == targetValue.add)) {
						this.score += Integer.parseInt(button_array[i][j].getText());
						button_array[i][j].setText(null);
						button_array[i][j].setEnabled(false);
						button_array[i][j].setBackground(UIManager.getColor("Button.background"));
						this.currentScoreLevel_1 = this.score;
						clickedButton_array[mouseCount - 1].setBackground(Color.LIGHT_GRAY);
					} else if (level == 2 && (Integer.parseInt(button_array[i][j].getText()) == targetValue.add)) {
						this.score += Integer.parseInt(button_array[i][j].getText());
						button_array[i][j].setText(null);
						button_array[i][j].setEnabled(false);
						button_array[i][j].setBackground(UIManager.getColor("Button.background"));
						this.currentScoreLevel_2 = this.score;
						clickedButton_array[mouseCount - 1].setBackground(Color.CYAN);
					} else if (level == 3 && (Integer.parseInt(button_array[i][j].getText()) == targetValue.add
							|| Integer.parseInt(button_array[i][j].getText()) == targetValue.minus)) {
						System.out.println("Before  :   " + this.score);
						this.score += Integer.parseInt(button_array[i][j].getText());
						System.out.println("After  :   " + this.score);
						button_array[i][j].setText(null);
						button_array[i][j].setEnabled(false);
						button_array[i][j].setBackground(UIManager.getColor("Button.background"));
						this.currentScoreLevel_3 = this.score;
						clickedButton_array[mouseCount - 1].setBackground(Color.MAGENTA);
					} else if (level == 4 && (Integer.parseInt(button_array[i][j].getText()) == targetValue.add
							|| Integer.parseInt(button_array[i][j].getText()) == targetValue.minus
							|| Integer.parseInt(button_array[i][j].getText()) == targetValue.div)) {
						this.score += Integer.parseInt(button_array[i][j].getText());
						button_array[i][j].setText(null);
						button_array[i][j].setEnabled(false);
						button_array[i][j].setBackground(UIManager.getColor("Button.background"));
						this.currentScoreLevel_4 = this.score;
						clickedButton_array[mouseCount - 1].setBackground(Color.ORANGE);
					} else if (level == 5 && (Integer.parseInt(button_array[i][j].getText()) == targetValue.add
							|| Integer.parseInt(button_array[i][j].getText()) == targetValue.minus
							|| Integer.parseInt(button_array[i][j].getText()) == targetValue.div
							|| Integer.parseInt(button_array[i][j].getText()) == targetValue.mult)) {
						this.score += Integer.parseInt(button_array[i][j].getText());
						button_array[i][j].setText(null);
						button_array[i][j].setEnabled(false);
						button_array[i][j].setBackground(UIManager.getColor("Button.background"));
						clickedButton_array[mouseCount - 1].setBackground(Color.YELLOW);
					}
				}
			}
			playerScore.setText("" + this.score);
			levelRunner(this.score);
			this.mouseCount = 0;
			targetValue.add = 0;
			targetValue.div = 0;
			targetValue.minus = 0;
			targetValue.mult = 0;
			numList.clear();
			playerLevel.setText("" + this.level);
		}

	}

	public MathOperation returnTargetValue(int firstCapturedNum, int secondCapturedNum) {

		this.firstCapturedNum = firstCapturedNum;
		this.secondCapturedNum = secondCapturedNum;

		return new MathOperation(firstCapturedNum + secondCapturedNum, Math.abs(firstCapturedNum - secondCapturedNum),
				(double) firstCapturedNum / secondCapturedNum, firstCapturedNum * secondCapturedNum);
	}

	public int levelRunner(int score) {

		if (this.level == 1 && (score >= 75 && score < 450)) {
			JOptionPane.showMessageDialog(panel, "COngrats! You Got Level 2 " + "\nScore: " + this.score);

			this.level = 2;
			mathOperator.setText("Operator: +");
			gameReset();

		} else if (this.level == 2 && (score >= 450 && score < 1000)) {
			JOptionPane.showMessageDialog(panel,
					"Congrats! You Got Level 3" + "\nScore: " + this.score + "\nINSTRUCTION "
							+ "\nThe absolute sum and subtraction of two numbers " + "\nFor example: firstnumber is 5 "
							+ "\ngreen colred number is 2 " + "\n5 + 2 = 7 \n5 - 2 = 3 "
							+ "\n 7 and 3 will disappear from the screen");

			this.level = 3;
			mathOperator.setText("Operators:  +, -");
			gameReset();

		} else if (this.level == 3 && (score >= 1000 && score < 2000)) {
			JOptionPane.showMessageDialog(panel,
					"COngrats! You Got Level 4 " + "\nScore: " + this.score + "\nAddition \nSubtraction \nDivision ");

			this.level = 4;
			mathOperator.setText("Operators:  +, -, " + "\u00F7\"");
			gameReset();
		} else if (this.level == 4 && score >= 2000) {
			JOptionPane.showMessageDialog(panel, "COngrats! You Got Level 5 " + "\nScore: " + this.score
					+ "\nAddition \nSubtraction \nDivision \nMultiplication");

			this.level = 5;
			mathOperator.setText("Operators:  +, -, " + "\u00F7\"" + ", *");
			gameReset();
		} else if (score < 75) {
			this.level = 1;
		}
		
		row = 5;

		return level;
	}

	public int getLevel(int score) {
		this.score = score;
		levelRunner(score);

		return level;
	}

	public int getScore() {
		return this.score;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setScore(int score) {
		this.score = score;
	}

//		Resets the game -replaces the existing buttun values with a unique random number
	public void gameReset() {
		this.mouseCount = 0;
		randNumber = new RandomNumberGenerator();
		randomNumberList = randNumber.uniqueRandomNumberGenerator(row, this.level, age);

		if (this.level == 1) {
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < button_array[i].length; j++) {
					if (button_array[i][j] == button_array[j][i] || button_array[i][j] == button_array[0][4]
							|| button_array[i][j] == button_array[1][3] || button_array[i][j] == button_array[3][1]
							|| button_array[i][j] == button_array[4][0]) {
						button_array[i][j].setText("" + randomNumberList[i][j]);
						button_array[i][j].setFont(new Font("serif", Font.BOLD, 25));
						button_array[i][j].setBackground(Color.LIGHT_GRAY);
						button_array[i][j].setEnabled(true);
					} else {
						button_array[i][j].setText(null);
						button_array[i][j].setEnabled(false);
					}

				}
			}
			this.score = 0;
			playerScore.setText("" + this.score);
			panel.setBackground(Color.CYAN);
		} else {
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < button_array[i].length; j++) {
					button_array[i][j].setEnabled(true);
					button_array[i][j].setText("" + randomNumberList[i][j]);
					button_array[i][j].setFont(new Font("serif", Font.BOLD, 25));

					if (level == 2) {
						this.score = this.currentScoreLevel_1;
						button_array[i][j].setBackground(Color.CYAN);
						playerScore.setText("" + this.currentScoreLevel_1);
						panel.setBackground(Color.CYAN);
					} else if (level == 3) {
						this.score = this.currentScoreLevel_2;
						button_array[i][j].setBackground(Color.MAGENTA);
						playerScore.setText("" + this.currentScoreLevel_2);
						panel.setBackground(Color.MAGENTA);
					} else if (level == 4) {
						this.score = this.currentScoreLevel_3;
						button_array[i][j].setBackground(Color.ORANGE);
						playerScore.setText("" + this.currentScoreLevel_3);
						panel.setBackground(Color.orange);
					} else {
						this.score = this.currentScoreLevel_4;
						button_array[i][j].setBackground(Color.YELLOW);
						playerScore.setText("" + this.currentScoreLevel_4);
						panel.setBackground(Color.YELLOW);
					}
				}
			}
		}
		this.mouseCount = 0;
		panel.setLocation(0, 50);
		panel.setVisible(true);
	}

	public void selectButton(JButton[][] button_array, int row) {

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < button_array[i].length; j++) {

				button_array[i][j].addMouseListener(new MouseListener() {
					public void mouseClicked(MouseEvent e) {
						for (int i = 0; i < row; i++) {
							for (int j = 0; j < button_array[i].length; j++) {
								if (e.getSource() == button_array[i][j] && button_array[i][j].isEnabled()) {
									if (e.getClickCount() >= 0 && mouseCount >= 0) {
										mouseCount++;
										clickedButton_array = new JButton[mouseCount];
										clickedButton_array[mouseCount - 1] = button_array[i][j];
										clickedButton_array[mouseCount - 1].setBackground(Color.GREEN);
									}
								}
								if ((e.getClickCount() == 2) && (e.getSource() == button_array[i][j])) {
									if (level == 1) {
										button_array[i][j].setBackground(Color.LIGHT_GRAY);
									} else if (level == 2) {
										button_array[i][j].setBackground(Color.CYAN);
									} else if (level == 3) {
										button_array[i][j].setBackground(Color.MAGENTA);
									} else if (level == 4) {
										button_array[i][j].setBackground(Color.ORANGE);
									} else if (level == 5) {
										button_array[i][j].setBackground(Color.YELLOW);
									}
									mouseCount = 0;
									numList.clear();
								}
							}
						}
					}

					public void mousePressed(MouseEvent e) {
					}

					public void mouseReleased(MouseEvent e) {
					}

					public void mouseEntered(MouseEvent e) {
					}

					public void mouseExited(MouseEvent e) {
					}

				});
			}
		}
	}
}
