import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.GraphicsDevice.WindowTranslucency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GuessWho {
	
	//Initiate GUI variables
	static JFrame window = new JFrame("Window");
	static JPanel gamePanel = new JPanel(); 
	static JPanel options = new JPanel(); 
	static JPanel winLoseScreen = new JPanel(); 
	static JPanel whoGoFirst = new JPanel(); 
	static JButton charButton[][] = new JButton[4][6]; 
	static JButton playerComp = new JButton("Player vs Computer");
	static JButton playerPlayer = new JButton("Player vs Player");
	static JButton CompComp = new JButton("Computer vs Computer");
	static JButton realWorld = new JButton("Play with real items");
	static JButton confirm = new JButton("Confirm"); 
	static JButton confirmQuest = new JButton("Confirm"); 
	static JButton confirmChanges = new JButton("Confirm changes"); 
	static JButton confirmAnswer = new JButton("Confirm answer"); 
	static JButton yes = new JButton("Yes");
	static JButton no = new JButton("No"); 
	static JButton restart = new JButton("Restart"); 
	static JButton player1First = new JButton("Player 1");  
	static JButton player2First = new JButton("Player 2"); 
	static JLabel chooseText = new JLabel("Choose the player that goes first"); 
	static JLabel winLose = new JLabel(); 
	static JLabel title = new JLabel("Choose the game mode"); 
	static JLabel selection = new JLabel("Choose your character");
	static JLabel character = new JLabel("N/A"); 
	static JLabel computerText = new JLabel("Your opponent is waiting for your question...");
	static JLabel compCards = new JLabel("Your opponent has flipped 0 cards...");
	static JLabel yourCharacter = new JLabel("Your character is..."); 
	static JLabel playerGUI = new JLabel(); 
	static JLabel chooseChar = new JLabel("Please choose a character from your deck"); 
	static JComboBox questions;
	static JTextArea answer = new JTextArea("Insert your answer here"); 
	
	static Characters[][] chars = new Characters[4][6];
	static Characters compChar; 
	static Characters playerChar; 
	
	//Initiate various fonts
	static Font font = new Font("Size", Font.BOLD , 20);
	static Font font2 = new Font("Character", Font.BOLD, 50); 
	
	//Initiate standard variables
	static boolean[][] aiChars = new boolean[4][6]; 
	static boolean gameStarted = false;
	static boolean won = false; 
	static boolean lying = false; 
	static boolean realW = false; 
	
	static String[] questionList = new String[25]; 
	static String selectedQuestion; 
	static String aiSelectedQuestion; 
	
	static int aiCards = 24; 
	
	static ArrayList<ImageIcon> images = new ArrayList<ImageIcon>(); 
	
	static Canvas background = new Canvas(); 
	
	//Initiate images
	static ImageIcon Olivia = new ImageIcon("C:/Files/IMG_3789.jpg");
	static ImageIcon Nick = new ImageIcon("C:/Files/IMG_3792.jpg");
	static ImageIcon David = new ImageIcon("C:/Files/IMG_3781.jpg");
	static ImageIcon Leo = new ImageIcon("IMG_3794.jpg");
	static ImageIcon Emma = new ImageIcon("C:/Files/IMG_3777.jpg");
	static ImageIcon Ben = new ImageIcon("C:/Files/IMG_3778.jpg");
	static ImageIcon Eric = new ImageIcon("C:/Files/IMG_3791.jpg");
	static ImageIcon Rachel = new ImageIcon("C:/Files/IMG_3784.jpg");
	static ImageIcon Amy = new ImageIcon("C:/Files/IMG_3771.jpg");
	static ImageIcon Mike = new ImageIcon("C:/Files/IMG_3779.jpg");
	static ImageIcon Gabe = new ImageIcon("C:/Files/IMG_3786.jpg");
	static ImageIcon Jordan = new ImageIcon("C:/Files/IMG_3785.jpg");
	static ImageIcon Carmen = new ImageIcon("C:/Files/IMG_3790.jpg");
	static ImageIcon Joe = new ImageIcon("C:/Files/IMG_3772.jpg");
	static ImageIcon Mia = new ImageIcon("C:/Files/IMG_3782.jpg");
	static ImageIcon Sam = new ImageIcon("C:/Files/IMG_3774.jpg");
	static ImageIcon Sofia = new ImageIcon("C:/Files/IMG_3783.jpg");
	static ImageIcon Lily = new ImageIcon("C:/Files/IMG_3788.jpg");
	static ImageIcon Daniel = new ImageIcon("C:/Files/IMG_3776.jpg");
	static ImageIcon Al = new ImageIcon("C:/Files/IMG_3787.jpg");
	static ImageIcon Laura = new ImageIcon("C:/Files/IMG_3793.jpg");
	static ImageIcon Liz = new ImageIcon("C:/Files/IMG_3773.jpg");
	static ImageIcon Katie = new ImageIcon("C:/Files/IMG_3775.jpg");
	static ImageIcon Farah = new ImageIcon("C:/Files/IMG_3780.jpg");
	
	//Set Images
	static Image test = Toolkit.getDefaultToolkit().getImage("C:/Files/WinScreen.png");
	
	//Main method
	public static void main(String[] args) {
		
		//Set properties for the game window
		window.setSize(1000, 700);
		window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.setLayout(null);
		window.setResizable(false);
		
		window.add(background); 

		/*
		startButton.setBounds(window.getWidth()/2-50, window.getHeight()/2-10, 100, 20);
		startButton.setText("Start Game");
		startButton.addActionListener(new StartGame());
		window.add(startButton); 
		*/
		
		//Set properties for the computer flipped cards
		compCards.setBounds(500, 330, 400, 30);
		compCards.setFont(font);
		
		//Set properties for the win/lose screen
		winLoseScreen.setLayout(new BoxLayout(winLoseScreen, BoxLayout.Y_AXIS));
		winLoseScreen.setBounds(window.getWidth()/2-250, 200, 500, 500);
		winLoseScreen.add(winLose);
		winLoseScreen.add(restart); 
		winLose.setFont(font);
		winLose.setSize(500, 50);
		restart.setFont(font); 
		restart.addActionListener(new Restart());
		
		//Set properties for the game panel
		gamePanel.setLayout(new GridLayout(4, 6));
		gamePanel.setBounds(50, 50, 400, 500);
		gamePanel.setVisible(true);
		
		//Set properties for the selection screen
		selection.setBounds(600, 20, 500, 100);
		selection.setFont(font);
		
		//Set properties for the confirm button
		confirm.setBounds(600, 250, 300, 100);
		confirm.addActionListener(new Confirm());
		confirm.setFont(font);
		
		//Set properties for character selection label
		character.setBounds(600, 150, 300, 100);
		character.setFont(font2);
		
		//Set properties for which player goes first GUI
		//window.add(whoGoFirst); 
		whoGoFirst.setLayout(new BoxLayout(whoGoFirst, BoxLayout.Y_AXIS)); 
		whoGoFirst.add(chooseText);	
		whoGoFirst.setBounds(window.getWidth()/2-250, 200, 500, 500);
		
		chooseText.setFont(font); 
		chooseText.setAlignmentX(Component.CENTER_ALIGNMENT);

		whoGoFirst.add(player1First);
		player1First.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		whoGoFirst.add(player2First);
		player2First.setAlignmentX(Component.CENTER_ALIGNMENT);
				
		//Set properties for game option menu
		window.add(options);
		options.setBounds(window.getWidth()/2-250, 200, 500, 500);
		options.add(title); 
		options.setLayout(new BoxLayout(options, BoxLayout.Y_AXIS));
		title.setFont(font);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
				
		//Set properties for player vs computer button
		options.add(playerComp);
		playerComp.setAlignmentX(Component.CENTER_ALIGNMENT);
		playerComp.addActionListener(new StartPlayerComp()); 
		
		//Set properties for player 
		options.add(realWorld);
		realWorld.addActionListener(new StartRealGame()); 
		realWorld.setAlignmentX(Component.CENTER_ALIGNMENT); 
		
		//Set properties for confirm question button
		confirmQuest.setBounds(500, 200, 150, 50); 
		confirmQuest.setFont(font); 
		confirmQuest.addActionListener(new AskQuestion()); 
		
		//Set properties for computer output text
		computerText.setBounds(500, 50, 500, 50); 
		computerText.setFont(font); 
		
		//Set properties for confirm changes button
		confirmChanges.setBounds(500, 200, 300, 50);
		confirmChanges.setFont(font);
		confirmChanges.addActionListener(new ConfirmChanges()); 
		
		//Set properties for yes button
		yes.setBounds(480, 200, 100, 50); 
		yes.setFont(font);
		yes.addActionListener(new YesButton());
		
		//Set properties for no button
		no.setBounds(580, 200, 100, 50);
		no.setFont(font);
		no.addActionListener(new NoButton());
		
		//Set properties for answer text
		answer.setBounds(500, 400, 300, 50);
		answer.setFont(font);
		
		//Set properties for confirm answer button
		confirmAnswer.setBounds(800, 400, 100, 50); 
		confirmAnswer.setFont(font); 
		confirmAnswer.addActionListener(new ConfirmAnswer()); 
		
		//Set properties for player's selected character gui
		playerGUI.setBounds(500, 500, 80, 120);
		
		//Set properties for text that shows player's character
		yourCharacter.setBounds(590, 500, 300, 50); 
		yourCharacter.setFont(font); 
		
		//Set grid for button
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 6; j++) {
				
				charButton[i][j] = new JButton(); 
				charButton[i][j].addActionListener(new CharSelection()); 
				gamePanel.add(charButton[i][j]); 
				
			}
		}	
		
		//Initialize all characters and attributes; 
		chars[0][0] = new Characters("Olivia", "Brown", false, "Dark Skin", "Black", false, false, false, false, "Tied", false); 
	    chars[1][0] = new Characters("Nick", "Brown", true, "Light Skin", "Blonde", false, false, false, false, "Short", true); 
	    chars[2][0] = new Characters("David", "Brown", true, "Light Skin", "Blonde", true, false, true, true, "Short", false); 
	    chars[3][0] = new Characters("Leo", "Brown", true, "Dark Skin", "White", true, false, true, false, "Short", false); 
	    chars[0][1] = new Characters("Emma", "Brown", false, "Light Skin", "Ginger", false, false, false, false, "Tied", false); 
	    chars[1][1] = new Characters("Ben", "Brown", true, "Light Skin", "Brown", false, true, false, false, "Short", true); 
	    chars[2][1] = new Characters("Eric", "Blue", true, "Light Skin", "Black", false, false, false, false, "Short", false); 
	    chars[3][1] = new Characters("Rachel", "Blue", false, "Light Skin", "Brown", false, true, false, false, "Long", true); 
	    chars[0][2] = new Characters("Amy", "Brown", false, "Light Skin", "Black", false, true, false, false, "Short", false); 
	    chars[1][2] = new Characters("Mike", "Brown", true, "Light Skin", "Black", false, false, true, true, "Short", false); 
	    chars[2][2] = new Characters("Gabe", "Brown", true, "Dark Skin", "Black", false, false, false, false, "Short", false); 
	    chars[3][2] = new Characters("Jordan", "Brown", true, "Dark Skin", "Black", true, false, false, false, "Short", true); 
	    chars[0][3] = new Characters("Carmen", "Brown", false, "Dark Skin", "White", false, false, true, false, "Short", true); 
	    chars[1][3] = new Characters("Joe", "Brown", true, "Dark Skin", "White", true, true, true, false, "Bald", false); 
	    chars[2][3] = new Characters("Mia", "Brown", false, "Dark Skin", "Black", false, false, true, false, "Long", false); 
	    chars[3][3] = new Characters("Sam", "Green", true, "Dark Skin", "Black", false, false, false, true, "Short", false); 
	    chars[0][4] = new Characters("Sofia", "Green", false, "Dark Skin", "Brown", false, false, true, false, "Short", true); 
	    chars[1][4] = new Characters("Lily", "Green", false, "Dark Skin", "Brown", false, false, true, true, "Long", false); 
	    chars[2][4] = new Characters("Daniel", "Green", true, "Light Skin", "Ginger", true, false, false, false, "Tied", false); 
	    chars[3][4] = new Characters("Al", "Green", true, "Dark Skin", "White", true, true, false, false, "Bald", false); 
	    chars[0][5] = new Characters("Laura", "Green", false, "Dark Skin", "Black", false, false, true, false, "Long", true); 
	    chars[1][5] = new Characters("Liz", "Blue", false, "Light Skin", "White", false, true, true, false, "Long", false); 
	    chars[2][5] = new Characters("Katie", "Blue", false, "Light Skin", "Blonde", false, false, false, true, "Tied", false); 
	    chars[3][5] = new Characters("Farah", "Blue", false, "Dark Skin", "Black", false, false, false, false, "Tied", false); 
		
	    //initiate player character placeholder
	    playerChar = chars[0][0]; 
	    
		//Set icon image for button
		charButton[0][0].setIcon(Olivia);
		charButton[1][0].setIcon(Nick);
		charButton[2][0].setIcon(David);
		charButton[3][0].setIcon(Leo);
		charButton[0][1].setIcon(Emma);
		charButton[1][1].setIcon(Ben);
		charButton[2][1].setIcon(Eric);
		charButton[3][1].setIcon(Rachel);
		charButton[0][2].setIcon(Amy);
		charButton[1][2].setIcon(Mike);
		charButton[2][2].setIcon(Gabe);
		charButton[3][2].setIcon(Jordan);
		charButton[0][3].setIcon(Carmen);
		charButton[1][3].setIcon(Joe);
		charButton[2][3].setIcon(Mia);
		charButton[3][3].setIcon(Sam);
		charButton[0][4].setIcon(Sofia);
		charButton[1][4].setIcon(Lily);
		charButton[2][4].setIcon(Daniel);
		charButton[3][4].setIcon(Al);
		charButton[0][5].setIcon(Laura);
		charButton[1][5].setIcon(Liz);
		charButton[2][5].setIcon(Katie);
		charButton[3][5].setIcon(Farah);
	
		//Set name for each button
		charButton[0][0].setText("Olivia"); 
		charButton[1][0].setText("Nick"); 
		charButton[2][0].setText("David"); 
		charButton[3][0].setText("Leo"); 
		charButton[0][1].setText("Emma"); 
		charButton[1][1].setText("Ben"); 
		charButton[2][1].setText("Eric"); 
		charButton[3][1].setText("Rachel"); 
		charButton[0][2].setText("Amy"); 
		charButton[1][2].setText("Mike"); 
		charButton[2][2].setText("Gabe"); 
		charButton[3][2].setText("Jordan"); 
		charButton[0][3].setText("Carmen"); 
		charButton[1][3].setText("Joe"); 
		charButton[2][3].setText("Mia"); 
		charButton[3][3].setText("Sam"); 
		charButton[0][4].setText("Sofia"); 
		charButton[1][4].setText("Lily"); 
		charButton[2][4].setText("Daniel"); 
		charButton[3][4].setText("Al"); 
		charButton[0][5].setText("Laura"); 
		charButton[1][5].setText("Liz"); 
		charButton[2][5].setText("Katie"); 
		charButton[3][5].setText("Farah"); 
	
		//Add questions to question bank
		questionList[0] = "Is the eye colour brown?"; 
		questionList[1] = "Is the eye colour green?"; 
		questionList[2] = "Is the eye colour blue?"; 
		questionList[3] = "Is the person a male?"; 
		questionList[4] = "Is the person a female?"; 
		questionList[5] = "Does the person have a light skin tone?"; 
		questionList[6] = "Does this person have a dark skin tone?"; 
		questionList[7] = "Is the hair colour black?"; 
		questionList[8] = "Is the hair colour brown?"; 
		questionList[9] = "Is the hair colour ginger?"; 
		questionList[10] = "Is the hair colour blonde?"; 
		questionList[11] = "Is the hair colour white?"; 
		questionList[12] = "Does the person have facial hair?"; 
		questionList[13] = "Does the person have no facial hair?"; 
		questionList[14] = "Is the person wearing glasses?"; 
		questionList[15] = "Is the person not wearing glasses?"; 
		questionList[16] = "Does the person have visible teeth?"; 
		questionList[17] = "Is the person not showing teeth?"; 
		questionList[18] = "Is the person wearing a hat?"; 
		questionList[19] = "Is the person not wearing a hat?"; 
		questionList[20] = "Does the person have short hair?"; 
		questionList[21] = "Does the person have their hair tied up?"; 
		questionList[22] = "Does the person have long hair?"; 
		questionList[23] = "Is the person bald?"; 
		questionList[24] = "Does the person have an ear piercing?"; 

		//Set properties for questions text
		questions = new JComboBox(questionList); 
		questions.setBounds(500, 120, 400, 50); 
		questions.setFont(font);
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 6; j++) {
				
				aiChars[i][j] = true; 
				
			}
		}
		/*
		int num = 50; 
		while (num >= 0) {
			num--;
			compCharacter();
		}
		*/
		
		window.repaint();
	}
	
	static class Canvas extends JPanel {
		public Canvas() {
			
			setFocusable(true); 
			requestFocusInWindow(); 
			
		}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			g.drawImage(test, 0, 0, getWidth(), getHeight(), this);

		}
	}
	
	public static void getRanQuestion() {
		
		int ranNum = (int)(Math.random()*25); 
		
		aiSelectedQuestion = questionList[ranNum]; 
		
	}
	
	public static void compCharacter() {
				
		int iValue = (int)(Math.random()*4); 
		int jValue = (int)(Math.random()*6);
		compChar = chars[iValue][jValue];
	
		System.out.println(compChar.getName()); 

				
	}
	
	static class Restart implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			window.remove(winLoseScreen);
			window.remove(compCards); 
			window.remove(playerGUI);
			window.remove(yourCharacter);
			compCards.setText("Your opponent has flipped 0 cards...");
			window.add(options); 
			window.repaint();
			
			gameStarted = false; 
			realW = false; 
			
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 6; j++) {
					
					charButton[i][j].setBackground(null);
					aiChars[i][j] = true; 
					charButton[0][0].setIcon(Olivia);
					charButton[1][0].setIcon(Nick);
					charButton[2][0].setIcon(David);
					charButton[3][0].setIcon(Leo);
					charButton[0][1].setIcon(Emma);
					charButton[1][1].setIcon(Ben);
					charButton[2][1].setIcon(Eric);
					charButton[3][1].setIcon(Rachel);
					charButton[0][2].setIcon(Amy);
					charButton[1][2].setIcon(Mike);
					charButton[2][2].setIcon(Gabe);
					charButton[3][2].setIcon(Jordan);
					charButton[0][3].setIcon(Carmen);
					charButton[1][3].setIcon(Joe);
					charButton[2][3].setIcon(Mia);
					charButton[3][3].setIcon(Sam);
					charButton[0][4].setIcon(Sofia);
					charButton[1][4].setIcon(Lily);
					charButton[2][4].setIcon(Daniel);
					charButton[3][4].setIcon(Al);
					charButton[0][5].setIcon(Laura);
					charButton[1][5].setIcon(Liz);
					charButton[2][5].setIcon(Katie);
					charButton[3][5].setIcon(Farah);
					
				}
			}

		}
	}
	
	static class ConfirmAnswer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			if (answer.getText().equals(compChar.getName())) {
				
				System.out.println("You won!"); 
				window.getContentPane().removeAll();
				window.add(winLoseScreen); 
				window.repaint();
				winLose.setText("You won!");
				
			}
			else {
				System.out.println("You lost!"); 
				window.getContentPane().removeAll();
				window.add(winLoseScreen); 
				window.repaint();
				winLose.setText("You Lost!");
				
			}
		}
	}
	
	//Implement action for yes button
	static class YesButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			System.out.println(aiCards); 
			
			lying = false; 
			int ranNum = (int)(Math.random()*(10000)); 
			
			if (aiSelectedQuestion == questions.getItemAt(0)) {
				
				if (playerChar.getEyeColor() != "Brown" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getEyeColor() != "Brown") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
				
			}
			if (aiSelectedQuestion == questions.getItemAt(1)) {
				
				if (playerChar.getEyeColor() != "Green" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getEyeColor() != "Green") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
				
			}
			if (aiSelectedQuestion == questions.getItemAt(2)) {
				
				if (playerChar.getEyeColor() != "Green" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getEyeColor() != "Blue") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
				
			}
			if (aiSelectedQuestion == questions.getItemAt(3)) {
				
				if (playerChar.getGender() != true && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getGender() != true) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
				
			}
			if (aiSelectedQuestion == questions.getItemAt(4)) {
				
				if (playerChar.getGender() != false && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getGender() != false) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(5)) {
				
				if (playerChar.getSkinTone() != "Light Skin" && realW == false) {
					
					 
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getSkinTone() != "Light Skin") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(6)) {
				if (playerChar.getSkinTone() != "Dark Skin" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getSkinTone() != "Dark Skin") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(7)) {
				if (playerChar.getHairColor() != "Black" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getHairColor() != "Black") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(8)) {
				
				if (playerChar.getHairColor() != "Brown" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getHairColor() != "Brown") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(9)) {
				
				if (playerChar.getHairColor() != "Ginger" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getHairColor() != "Ginger") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(10)) {
				if (playerChar.getHairColor() != "Blonde" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getHairColor() != "Blonde") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(11)) {
				
				if (playerChar.getHairColor() != "White" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getHairColor() != "White") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(12)) {
				
				if (playerChar.getFacialHair() != true && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getFacialHair() != true) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(13)) {
				
				if (playerChar.getFacialHair() != false && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getFacialHair() != false) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(14)) {
				
				if (playerChar.getGlasses() != true && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getGlasses() != true) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(15)) {
				
				if (playerChar.getGlasses() != false && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getGlasses() != false) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(16)) {
			
				if (playerChar.getVisibleTeeth() != true && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getVisibleTeeth() != true) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(17)) {
				
				if (playerChar.getVisibleTeeth() != false && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getVisibleTeeth() != false) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(18)) {
				
				if (playerChar.getWearHat() != true && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getWearHat() != true) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(19)) {
				
				if (playerChar.getWearHat() != false && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getWearHat() != false) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(20)) {
				if (playerChar.getHairLength() != "Short" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getHairLength() != "Short") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(21)) {
				
				if (playerChar.getHairLength() != "Tied" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getHairLength() != "Tied") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(22)) {
				
				if (playerChar.getHairLength() != "Long" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getHairLength() != "Long") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(23)) {
				
				if (playerChar.getHairLength() != "Bald" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getHairLength() != "Bald") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(24)) {
				
				if (playerChar.getPiercings() != true && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getPiercings() != true) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			
			if (lying == false) {
				window.remove(yes);
				window.remove(no);
				window.add(questions);
				window.add(confirmQuest); 
				window.repaint();
				computerText.setText("Your opponent is waiting for your question...");
			}
			
			int num = 0; 
			
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 6; j++) {
					
					if (aiChars[i][j] == false) {
						num++; 
					}
				}
			}
			
			compCards.setText("Your opponent has flipped " + num + " cards...");
			
			if (num >= 23) {
				window.getContentPane().removeAll();
				window.add(winLoseScreen); 
				window.repaint();
				
				Characters guessChar = null; 
				
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 6; j++) {
						
						if (aiChars[i][j] == true) {
							guessChar = chars[i][j]; 
						}
						
					}
				}
				
				winLose.setText("You lost! The Ai guessed that your card was " + guessChar.getName());
			}

		}
	}
	
	//Implement action for no button
	static class NoButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			lying = false; 
			int ranNum = (int)(Math.random()*(10000)); 
			
			if (aiSelectedQuestion == questions.getItemAt(0)) {
				
				if (playerChar.getEyeColor() == "Brown" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getEyeColor() == "Brown") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
				
			}
			if (aiSelectedQuestion == questions.getItemAt(1)) {
				
				if (playerChar.getEyeColor() == "Green" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getEyeColor() == "Green") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
				
			}
			if (aiSelectedQuestion == questions.getItemAt(2)) {
				
				if (playerChar.getEyeColor() == "Green" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getEyeColor() == "Blue") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
				
			}
			if (aiSelectedQuestion == questions.getItemAt(3)) {
				
				if (playerChar.getGender() == true && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getGender() == true) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
				
			}
			if (aiSelectedQuestion == questions.getItemAt(4)) {
				
				if (playerChar.getGender() == false && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getGender() == false) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(5)) {
				
				if (playerChar.getSkinTone() == "Light Skin" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getSkinTone() == "Light Skin") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(6)) {
				if (playerChar.getSkinTone() == "Dark Skin" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getSkinTone() == "Dark Skin") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(7)) {
				if (playerChar.getHairColor() == "Black" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getHairColor() == "Black") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(8)) {
				
				if (playerChar.getHairColor() == "Brown" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getHairColor() == "Brown") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(9)) {
				
				if (playerChar.getHairColor() == "Ginger" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getHairColor() == "Ginger") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(10)) {
				if (playerChar.getHairColor() == "Blonde" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getHairColor() == "Blonde") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(11)) {
				
				if (playerChar.getHairColor() == "White" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getHairColor() == "White") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(12)) {
				
				if (playerChar.getFacialHair() == true && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getFacialHair() == true) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(13)) {
				
				if (playerChar.getFacialHair() == false && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getFacialHair() == false) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(14)) {
				
				if (playerChar.getGlasses() == true && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getGlasses() == true) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(15)) {
				
				if (playerChar.getGlasses() == false && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getGlasses() == false) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(16)) {
			
				if (playerChar.getVisibleTeeth() == true && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getVisibleTeeth() == true) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(17)) {
				
				if (playerChar.getVisibleTeeth() == false && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getVisibleTeeth() == false) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(18)) {
				
				if (playerChar.getWearHat() == true && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getWearHat() == true) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(19)) {
				
				if (playerChar.getWearHat() == false && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getWearHat() == false) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(20)) {
				if (playerChar.getHairLength() == "Short" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getHairLength() == "Short") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(21)) {
				
				if (playerChar.getHairLength() == "Tied" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getHairLength() == "Tied") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(22)) {
				
				if (playerChar.getHairLength() == "Long" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getHairLength() == "Long") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(23)) {
				
				if (playerChar.getHairLength() == "Bald" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getHairLength() == "Bald") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(24)) {
				
				if (playerChar.getPiercings() == true && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getPiercings() == true) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			
			if (lying == false) {
				window.remove(yes);
				window.remove(no);
				window.add(questions);
				window.add(confirmQuest); 
				window.repaint();
				computerText.setText("Your opponent is waiting for your question...");
			}
			
			int num = 0; 
			
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 6; j++) {
					
					if (aiChars[i][j] == false) {
						num++; 
					}
				}
			}
			
			compCards.setText("Your opponent has flipped " + num + " cards...");

			if (num >= 23) {
				window.getContentPane().removeAll();
				window.add(winLoseScreen); 
				window.repaint();
				Characters guessChar = null; 
				
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 6; j++) {
						
						if (aiChars[i][j] == true) {
							guessChar = chars[i][j]; 
						}
						
					}
				}
				
				if (guessChar != null) {
					winLose.setText("You lost! The Ai guessed that you card was " + guessChar.getName());			
				}
				else
					winLose.setText("None of the characters match your description");
				
			}
			
		}
	}
	
	//Implement action for confirm changes button
	static class ConfirmChanges implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			getRanQuestion(); 
			
			window.remove(confirmChanges);
			window.repaint();
			int ranNum = (int)(Math.random()*(10000)); 
			computerText.setText("Your opponent is coming up with a question...");
			computerText.setText(aiSelectedQuestion);
			window.add(no);
			window.add(yes);
					
		}
	}
	
	//Implement action for character selection button
	static class CharSelection implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			JButton button = (JButton)e.getSource(); 
			
			if (gameStarted == false) {
				character.setText(button.getText());
				if (button.getText() == "Olivia") {
					playerGUI.setIcon(Olivia);
					yourCharacter.setText("Your character is Olivia");
				}
				else if (button.getText() == "Nick") {
					playerGUI.setIcon(Nick);
					yourCharacter.setText("Your character is Nick");
				}
				else if (button.getText() == "Leo") {
					playerGUI.setIcon(Leo);
					yourCharacter.setText("Your character is Leo");

				}
				else if (button.getText() == "Emma") {
					playerGUI.setIcon(Emma);
					yourCharacter.setText("Your character is Emma");

				}
				else if (button.getText() == "Ben") {
					playerGUI.setIcon(Ben);
					yourCharacter.setText("Your character is Ben");

				}
				else if (button.getText() == "Eric") {
					playerGUI.setIcon(Eric);
					yourCharacter.setText("Your character is Eric");

				}
				else if (button.getText() == "Rachel") {
					playerGUI.setIcon(Rachel);
					yourCharacter.setText("Your character is Rachel");

				}
				else if (button.getText() == "Amy") {
					playerGUI.setIcon(Amy);
					yourCharacter.setText("Your character is Amy");

				}
				else if (button.getText() == "Mike") {
					playerGUI.setIcon(Mike);
					yourCharacter.setText("Your character is Mike");

				}
				else if (button.getText() == "Gabe") {
					playerGUI.setIcon(Gabe);
					yourCharacter.setText("Your character is Gabe");

				}
				else if (button.getText() == "Jordan") {
					playerGUI.setIcon(Jordan);
					yourCharacter.setText("Your character is Jordan");

				}
				else if (button.getText() == "Carmen") {
					playerGUI.setIcon(Carmen);
					yourCharacter.setText("Your character is Carmen");

				}
				else if (button.getText() == "Joe") {
					playerGUI.setIcon(Joe);
					yourCharacter.setText("Your character is Joe");

				}
				else if (button.getText() == "Mia") {
					playerGUI.setIcon(Mia);
					yourCharacter.setText("Your character is Mia");

				}
				else if (button.getText() == "Sam") {
					playerGUI.setIcon(Sam);
					yourCharacter.setText("Your character is Sam");

				}
				else if (button.getText() == "Sofia") {
					playerGUI.setIcon(Sofia);
					yourCharacter.setText("Your character is Sofia");

				}
				else if (button.getText() == "Lily") {
					playerGUI.setIcon(Lily);
					yourCharacter.setText("Your character is Lily");

				}
				else if (button.getText() == "Daniel") {
					playerGUI.setIcon(Daniel);
					yourCharacter.setText("Your character is Daniel");

				}
				else if (button.getText() == "Al") {
					playerGUI.setIcon(Al);
					yourCharacter.setText("Your character is Al");

				}
				else if (button.getText() == "Laura") {
					playerGUI.setIcon(Laura);
					yourCharacter.setText("Your character is Laura");

				}
				else if (button.getText() == "Liz") {
					playerGUI.setIcon(Liz);
					yourCharacter.setText("Your character is Liz");

				}
				else if (button.getText() == "Katie") {
					playerGUI.setIcon(Katie);
					yourCharacter.setText("Your character is Katie");

				}
				else if (button.getText() == "Farah") {
					playerGUI.setIcon(Farah);
					yourCharacter.setText("Your character is Farah");

				}
				else if (button.getText() == "David") {
					playerGUI.setIcon(David);
					yourCharacter.setText("Your character is David");

				}
			}
			else if (button.getBackground() != Color.black){
				button.setIcon(null);
				button.setBackground(Color.black);
			}
			else if (button.getBackground() == Color.black) {
				button.setBackground(null);
				if (button.getText() == "Olivia") {
					button.setIcon(Olivia);
				}
				else if (button.getText() == "Nick") {
					button.setIcon(Nick);
				}
				else if (button.getText() == "Leo") {
					button.setIcon(Leo);
				}
				else if (button.getText() == "Emma") {
					button.setIcon(Emma);
				}
				else if (button.getText() == "Ben") {
					button.setIcon(Ben);
				}
				else if (button.getText() == "Eric") {
					button.setIcon(Eric);
				}
				else if (button.getText() == "Rachel") {
					button.setIcon(Rachel);
				}
				else if (button.getText() == "Amy") {
					button.setIcon(Amy);
				}
				else if (button.getText() == "Mike") {
					button.setIcon(Mike);
				}
				else if (button.getText() == "Gabe") {
					button.setIcon(Gabe);
				}
				else if (button.getText() == "Jordan") {
					button.setIcon(Jordan);
				}
				else if (button.getText() == "Carmen") {
					button.setIcon(Carmen);
				}
				else if (button.getText() == "Joe") {
					button.setIcon(Joe);
				}
				else if (button.getText() == "Mia") {
					button.setIcon(Mia);
				}
				else if (button.getText() == "Sam") {
					button.setIcon(Sam);
				}
				else if (button.getText() == "Sofia") {
					button.setIcon(Sofia);
				}
				else if (button.getText() == "Lily") {
					button.setIcon(Lily);
				}
				else if (button.getText() == "Daniel") {
					button.setIcon(Daniel);
				}
				else if (button.getText() == "Al") {
					button.setIcon(Al);
				}
				else if (button.getText() == "Laura") {
					button.setIcon(Laura);
				}
				else if (button.getText() == "Liz") {
					button.setIcon(Liz);
				}
				else if (button.getText() == "Katie") {
					button.setIcon(Katie);
				}
				else if (button.getText() == "Farah") {
					button.setIcon(Farah);
				}
				else if (button.getText() == "David") {
					button.setIcon(David);
				}
				
			}
			
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 6;j++) {
					
					if (chars[i][j].getName() == character.getText()) {
						
						playerChar = chars[i][j]; 
						System.out.println(playerChar.getName()); 
						
					}
					
				}
			}
			
		}
	}
	
	//Implement action for ask questions button
	static class AskQuestion implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			int ranNum = (int)(Math.random()*(10000)); 
			String question; 
			
			window.remove(questions);
			window.remove(confirmQuest);
			window.setSize(999, 700);
			window.setSize(1000, 700);
						
			if (questions.getSelectedIndex() == 0) {
				if (compChar.getEyeColor() == "Brown") {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 1) {
				if (compChar.getEyeColor() == "Green") {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 2) {
				if (compChar.getEyeColor() == "Blue") {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 3) {
				if (compChar.getGender() == true) {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 4) {
				if (compChar.getGender() == false) {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 5) {
				if (compChar.getSkinTone() == "Light Skin") {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 6) {
				if (compChar.getSkinTone() == "Dark Skin") {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 7) {
				if (compChar.getHairColor() == "Black") {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 8) {
				if (compChar.getHairColor() == "Brown") {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 9) {
				if (compChar.getHairColor() == "Ginger") {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 10) {
				if (compChar.getHairColor() == "Blonde") {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 11) {
				if (compChar.getHairColor() == "White") {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 12) {
				if (compChar.getFacialHair() == true) {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 13) {
				if (compChar.getFacialHair() == false) {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 14) {
				if (compChar.getGlasses() == true) {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 15) {
				if (compChar.getGlasses() == false) {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 16) {
				if (compChar.getVisibleTeeth() == true) {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 17) {
				if (compChar.getVisibleTeeth() == false) {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 18) {
				if (compChar.getWearHat() == true) {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 19) {
				if (compChar.getWearHat() == false) {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 20) {
				if (compChar.getHairLength() == "Short") {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 21) {
				if (compChar.getHairLength() == "Tied") {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 22) {
				if (compChar.getHairLength() == "Long") {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 23) {
				if (compChar.getHairLength() == "Bald") {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 24) {
				if (compChar.getPiercings() == true) {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			
			window.add(confirmChanges); 
			
		}
	}
	
	//Implement action for confirm button
	static class Confirm implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			if (character.getText() != "N/A" || realW == true) {
			
				gameStarted = true; 
								
				window.remove(selection);
				window.remove(confirm);
				window.remove(character);
				window.remove(chooseChar);
				//window.remove(gamePanel); 
				
				//window.add(whoGoFirst); 
								
				if (realW == false) {
					window.add(playerGUI); 
					window.add(yourCharacter); 
				}
				
				window.add(questions);
				window.add(confirmQuest); 
				window.add(computerText); 
				window.add(answer);
				window.add(confirmAnswer); 
				window.add(compCards);
				window.add(gamePanel); 
				
				gamePanel.setBounds(20, window.getHeight()/2-300, 400, 500);
	
				window.repaint();
								
				selectedQuestion = String.valueOf(questions.getSelectedItem());
		
			}
		}
	}
	
	//Implement action for start player vs computer button
	static class StartPlayerComp implements ActionListener {
		public void actionPerformed(ActionEvent e) {
	
			compCharacter(); 

			window.remove(options);
			window.add(gamePanel); 
			window.add(selection); 
			window.add(confirm); 
			window.add(character); 
			window.setSize(999, 700);
			window.setSize(1000, 700);
			
		}
	}
	
	//Implement action for start real life plays button
	static class StartRealGame implements ActionListener {
		public void actionPerformed(ActionEvent e) {
	
			gameStarted = true; 
			realW = true; 

			compCharacter(); 

			window.remove(options);
							
			window.add(chooseChar); 
			chooseChar.setFont(font);
			chooseChar.setBounds(0, 0, 500, 500);
			
			window.add(confirm); 
			
			window.repaint();

			selectedQuestion = String.valueOf(questions.getSelectedItem());
		}
	}
}
