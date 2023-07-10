package me.synology.zankyonokaze;


import java.awt.color.*; //導入java.awt.color包，此包含有所有顏色空間和色彩型別相關的類別。
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;   //導入java.awt.Color類別，該類別用於封裝顏色的RGB值。
import java.awt.Container; //導入java.awt.Container類別，該類別是Swing組件的容器，用於保存和管理其他組件。
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame; //導入javax.swing.JFrame類別，此類別代表一個應用程式視窗，用於添加其他GUI組件。
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.IOException;
	

	

	public class ADV_Beta_002 { // 定義一個名為ADV_Beta_001的public class。
		
		JFrame GameWindow; // 宣告一個JFrame物件名稱為GameWindow，這將是遊戲的主視窗。
		Container GameContainer; // 宣告一個Container物件名稱為GameContainer，這將是主視窗中的容器，用於保存和管理其他GUI組件。
		JPanel TitleNamePanel, StartGButtonPanel, LoadGButtonPanel, ScoreLButtonPanel, ExtraButtonPanel,
				MainStoryPanel, ChoiceButtonPanel ,PrologueDialoguePanel ,PlayerPanel;
		JLabel TitleNameLabel, PrologueDialogue, GameTimeLabel,GameTimeLabelNumer, ItemLabel, ItemLabelName;
		Font TitleFont = new Font("Ink Free", Font.BOLD, 100);	//開始標題的字體
		Font TitleBFont = new Font("Times New Roman", Font.ITALIC, 30);
		Font NormalBFont = new Font("標楷體", Font.PLAIN, 24);	//介面的字體
		Font NormalSFont = new Font("微軟正黑體", Font.PLAIN,22);	//故事的字體
		JButton StartGameButton, LoadGameButton, ScoreListButton, ExtraButton,
				Choice1, Choice2, Choice3, Choice4;
		JTextArea MainStoryArea;
		
		
		TitleScreenProcess TSProcess = new TitleScreenProcess();
		ChoiceProcess CProcess = new ChoiceProcess();
		
		
	    private int PrologueDialogueIndex = 0;
	    private String[] PrologueDialogues = {
	            "咦？我好像睡著了...",
	            "果然這幾天太累了嗎...",
	            "話說，這裡是哪裡...",
	    };
		
	    private String PlayerItem, Position;
	    
	    
	    class BackgroundImagePanel extends JPanel {	//待研究，導入背景圖片
	    	
	        private Image img;
	        public BackgroundImagePanel(String imgPath) {
	            this(new ImageIcon(imgPath).getImage());
	        }

	        public BackgroundImagePanel(Image img) {
	            this.img = img;
	            Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	            setPreferredSize(size);
	            setMinimumSize(size);
	            setMaximumSize(size);
	            setSize(size);
	            setLayout(null);
	        }

	        @Override
	        public void paintComponent(Graphics g) {
	            super.paintComponent(g);
//	            g.drawImage(img, 0, 0, getWidth(), getHeight(), null); //根據圖片檔案解析度進行調整
	            g.drawImage(img, 0, 0, null);	//不進行圖片解析度調整，須注意圖檔解析度大小	            
	        }
	    }
	    
	    
	    private Clip clip;	//待研究，導入BGM的播放模組
	    
	    public void playMusic(String filePath) {
	    	
	        try {
	            // Stop any currently playing music
	            if (clip != null) {
	                clip.stop();
	                clip.close();
	            }

	            // Open the audio file
	            AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource(filePath));

	            // Get a sound clip resource.
	            clip = AudioSystem.getClip();

	            // Open audio clip and load samples from the audio input stream.
	            clip.open(audioIn);
	            clip.start();
	        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
	            e.printStackTrace();
	            
	        }
	    }	    
	    
	    
		public static void main(String[] args) { // Java主程式的進入點。
			
			new ADV_Beta_002(); // 建立ADV_Beta_001類別的一個新實例，這將自動調用下面的建構子並開啟視窗。
//	        SwingUtilities.invokeLater(ADV_Beta_002::new);
		}
		
		public ADV_Beta_002() { // ADV_Beta_001類別的建構子。
			
			GameWindow = new JFrame("E s c a p e Ver.B02"); // 建立一個新的JFrame物件並分配給GameWindow。
			GameWindow.setSize(1280, 720); // 設置GameWindow的解析度
			GameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定視窗關閉行為，當視窗關閉時，應用程式將完全終止。
			GameWindow.getContentPane().setBackground(Color.black); // 將視窗的背景色設為黑色。
			GameWindow.setLayout(null); // 設定視窗的佈局管理器為null，這意味著你將自行控制所有元件的大小和位置。
			GameWindow.setVisible(true); // 設定視窗為可見，這樣使用者就能看見視窗了。
			GameWindow.setResizable(false);	// 禁止使用者自行調整視窗大小

			
			GameContainer = GameWindow.getContentPane(); // 將GameWindow的內容窗格分配給GameContainer，現在你可以在這個容器中添加其他組件。
			
			
			TitleNamePanel = new JPanel();	//標題的背景面板
			TitleNamePanel.setBounds(340, 100, 600, 150);
			TitleNamePanel.setBackground(Color.black);
			TitleNameLabel = new JLabel("E s c a p e");	//標題的文字顯示框
			TitleNameLabel.setForeground(Color.red);
			TitleNameLabel.setFont(TitleFont);
						
			StartGButtonPanel = new JPanel();	//開始按鈕的背景面板
			StartGButtonPanel.setBounds(550, 350, 200, 70);
			StartGButtonPanel.setBackground(Color.black);
			StartGameButton = new JButton("START");	//開始遊戲按鈕的按鈕面板
			StartGameButton.setBackground(Color.black);
			StartGameButton.setForeground(Color.white);
			StartGameButton.setFont(TitleBFont);
			StartGameButton.addActionListener(TSProcess);	//開始遊戲點擊的動作處理程序
			StartGameButton.setFocusPainted(false);	//關掉被選取的按鍵的強調外框
						
			LoadGButtonPanel = new JPanel();	//讀取按鈕的背景面板
			LoadGButtonPanel.setBounds(550, 420, 200, 70);
			LoadGButtonPanel.setBackground(Color.black);
			LoadGameButton = new JButton("Load Game");	//讀取遊戲按鈕的按鈕面板
			LoadGameButton.setBackground(Color.black);
			LoadGameButton.setForeground(Color.white);
			LoadGameButton.setFont(TitleBFont);
			LoadGameButton.setFocusPainted(false);	//關掉被選取的按鍵的強調外框
						
			ScoreLButtonPanel = new JPanel();	//計分按鈕的背景面板
			ScoreLButtonPanel.setBounds(550, 490, 200, 70);
			ScoreLButtonPanel.setBackground(Color.black);
			ScoreListButton = new JButton("Game Score");	//計分遊戲按鈕的按鈕面板
			ScoreListButton.setBackground(Color.black);
			ScoreListButton.setForeground(Color.white);
			ScoreListButton.setFont(TitleBFont);
			ScoreListButton.setFocusPainted(false);	//關掉被選取的按鍵的強調外框
			
			ExtraButtonPanel = new JPanel();	//Extra按鈕的背景面板
			ExtraButtonPanel.setBounds(550, 560, 200, 70);
			ExtraButtonPanel.setBackground(Color.black);
			ExtraButton = new JButton("Extra");	//Extea資訊按鈕的按鈕面板
			ExtraButton.setBackground(Color.black);
			ExtraButton.setForeground(Color.white);
			ExtraButton.setFont(TitleBFont);
			ExtraButton.setFocusPainted(false);	//關掉被選取的按鍵的強調外框
			
			
			TitleNamePanel.add(TitleNameLabel);	//把標題文字框加入到標題背景面板
			StartGButtonPanel.add(StartGameButton);	//把開始遊戲按鈕加入到開始按鈕面板
			LoadGButtonPanel.add(LoadGameButton);	//把讀取遊戲按鈕加入到讀取按鈕面板
			ScoreLButtonPanel.add(ScoreListButton);	//把計分按鈕加入到計分面板
			ExtraButtonPanel.add(ExtraButton);	//Extra資訊按鈕加入到Extra面板
			
			
			TitleNamePanel.setOpaque(false);	//讓Panel背景可以變成透明化
			StartGButtonPanel.setOpaque(false);
			LoadGButtonPanel.setOpaque(false);
			ScoreLButtonPanel.setOpaque(false);
			ExtraButtonPanel.setOpaque(false);
			
//			TitleNameLabel.setContentAreaFilled(false);			
			StartGameButton.setContentAreaFilled(false);	
			LoadGameButton.setContentAreaFilled(false);	
			ScoreListButton.setContentAreaFilled(false);	
			ExtraButton.setContentAreaFilled(false);	
			
			
			GameContainer.add(TitleNamePanel);
			GameContainer.add(StartGButtonPanel);
			GameContainer.add(LoadGButtonPanel);
			GameContainer.add(ScoreLButtonPanel);
			GameContainer.add(ExtraButtonPanel);

			
		}

		public void CreatStoryPrologueScreen() {	//這一段需要再研究原理，主要是一個序幕對話，透過滑鼠點擊
			
			TitleNamePanel.setVisible(false);
			StartGButtonPanel.setVisible(false);
			LoadGButtonPanel.setVisible(false);
			ScoreLButtonPanel.setVisible(false);
			ExtraButtonPanel.setVisible(false);
			

		    PrologueDialoguePanel = new JPanel();
		    PrologueDialoguePanel.setBounds(140, 300, 1000, 150);
		    PrologueDialoguePanel.setBackground(Color.black);
		    PrologueDialoguePanel.setLayout(new BorderLayout());
		    
		    PrologueDialogue = new JLabel("", SwingConstants.CENTER);
		    PrologueDialogue.setForeground(Color.green);
		    PrologueDialogue.setFont(new Font("Serif", Font.PLAIN, 24));
		    PrologueDialoguePanel.add(PrologueDialogue, BorderLayout.CENTER);

		    PrologueDialoguePanel.addMouseListener(new MouseAdapter() {
		        @Override
		        public void mouseClicked(MouseEvent e) {
		            if(PrologueDialogueIndex < PrologueDialogues.length) {
		             PrologueDialogue.setText(PrologueDialogues[PrologueDialogueIndex++]);
		            }
		            else {
		            	CreateGameScreen();  
		            }
		        }
		    });
		    GameWindow.add(PrologueDialoguePanel);
		    
		}
		
		
		public void CreateGameScreen() {	//創建遊戲主要故事介面
			
			TitleNamePanel.setVisible(false);
			StartGButtonPanel.setVisible(false);
			LoadGButtonPanel.setVisible(false);
			ScoreLButtonPanel.setVisible(false);
			ExtraButtonPanel.setVisible(false);
			PrologueDialoguePanel.setVisible(false);
			
			
			MainStoryPanel = new JPanel();
			MainStoryPanel.setBounds(140, 470, 1000, 150);
			MainStoryPanel.setBackground(Color.gray);
			GameContainer.add(MainStoryPanel);
			
			MainStoryArea = new JTextArea("Test Main Story Area");
			MainStoryArea.setBounds(140, 100, 1000, 250);
			MainStoryArea.setBackground(Color.black);
			MainStoryArea.setForeground(Color.white);
			MainStoryArea.setFont(NormalSFont);
			MainStoryArea.setLineWrap(true);
			MainStoryArea.setEditable(false);
			MainStoryPanel.add(MainStoryArea);
			
			
			ChoiceButtonPanel = new JPanel();
			ChoiceButtonPanel.setBounds(170, 635, 900, 40);
			ChoiceButtonPanel.setBackground(Color.black);
			ChoiceButtonPanel.setLayout(new GridLayout(1, 4));
			GameContainer.add(ChoiceButtonPanel);
			
			Choice1 = new JButton("Choice 1");
			Choice1.setBackground(Color.black);
			Choice1.setForeground(Color.white);
			Choice1.setFont(NormalBFont);
			Choice1.addActionListener(CProcess);	//處理對話選項相對應的劇情與位置
			Choice1.setActionCommand("CB1");	//讓程式可以區分不同的選項按鈕
			Choice1.setFocusPainted(false);	//關掉被選取的按鍵的強調外框
			ChoiceButtonPanel.add(Choice1);
			
			Choice2 = new JButton("Choice 2");
			Choice2.setBackground(Color.black);
			Choice2.setForeground(Color.white);
			Choice2.setFont(NormalBFont);
			Choice2.addActionListener(CProcess);	//處理對話選項相對應的劇情與位置
			Choice2.setActionCommand("CB2");	//讓程式可以區分不同的選項按鈕
			Choice2.setFocusPainted(false);	//關掉被選取的按鍵的強調外框
			ChoiceButtonPanel.add(Choice2);
			
			Choice3 = new JButton("Choice 3");
			Choice3.setBackground(Color.black);
			Choice3.setForeground(Color.white);
			Choice3.setFont(NormalBFont);
			Choice3.addActionListener(CProcess);	//處理對話選項相對應的劇情與位置
			Choice3.setActionCommand("CB3");	//讓程式可以區分不同的選項按鈕
			Choice3.setFocusPainted(false);	//關掉被選取的按鍵的強調外框
			ChoiceButtonPanel.add(Choice3);
			
			Choice4 = new JButton("Choice 4");
			Choice4.setBackground(Color.black);
			Choice4.setForeground(Color.white);
			Choice4.setFont(NormalBFont);
			Choice4.addActionListener(CProcess);	//處理對話選項相對應的劇情與位置
			Choice4.setActionCommand("CB4");	//讓程式可以區分不同的選項按鈕
			Choice4.setFocusPainted(false);	//關掉被選取的按鍵的強調外框
			ChoiceButtonPanel.add(Choice4);
			
			
			PlayerPanel = new JPanel();
			PlayerPanel.setBounds(140, 50, 500, 50);
			PlayerPanel.setBackground(Color.red);
			PlayerPanel.setLayout(new GridLayout(1,4));
			GameContainer.add(PlayerPanel);
			GameTimeLabel = new JLabel("Test");
			GameTimeLabel.setFont(NormalBFont);
			GameTimeLabel.setForeground(Color.white);
			PlayerPanel.add(GameTimeLabel);
			
			ItemLabel = new JLabel("道具：");
			ItemLabel.setFont(NormalBFont);
			ItemLabel.setForeground(Color.white);
			PlayerPanel.add(ItemLabel);
			ItemLabelName = new JLabel();
			ItemLabelName.setFont(NormalBFont);
			ItemLabelName.setForeground(Color.white);
			PlayerPanel.add(ItemLabelName);
			
			PlayerSetup();	//當創建CreateGameScreen後自動調用PalyerSetup
			
		}
		
		
		public void PlayerSetup() {
			
			PlayerItem = "None";	//玩家目前持有的道具
			ItemLabelName.setText(PlayerItem);	//調用玩家道具的顯示內容
			
			ClassRoom();
		}
		
		
		public void ClassRoom() {
			
			Position = "ClassRoom";
			
			
			MainStoryArea.setText("你正在平常上課熟悉的教室裡，你似乎趴在電腦桌上睡著了。\n你想要...");	
		    MainStoryPanel = new BackgroundImagePanel("dir1/Background/ClassRoom.jpg");
		    
		    
			Choice1.setText("環顧四周");
			Choice2.setText("嘗試開門");
			Choice3.setText("嘗試開窗");
			Choice4.setText("繼續睡覺");
			
		}
		
		public void LookAroundCRoom() {
			
		    if (MainStoryPanel != null) {	//檢查 MainStoryPanel 是否存在，如果存在，從 GameContainer 中移除它。
		        GameContainer.remove(MainStoryPanel);
		    }
		    
		    
			Position = "LookAroundCRoom";
			
			
			MainStoryArea.setText("空無一人的教室裡，只有你獨自一人。\n冷氣顯得有點冷 \n還是趕快回家吧...");
		    MainStoryPanel = new BackgroundImagePanel("dir1/Background/LookAroundCRoom.jpg");
		    GameContainer.add(MainStoryPanel);	//將新的 MainStoryPanel 加入 GameContainer
		    GameContainer.revalidate();	//確保 UI 更新
		    GameContainer.repaint();
		    
			Choice1.setText(">>>");
			Choice2.setText("");
			Choice3.setText("");
			Choice4.setText("");
			
		}
		
		public void TryCDoor() {
			
		    if (MainStoryPanel != null) {	//檢查 MainStoryPanel 是否存在，如果存在，從 GameContainer 中移除它。
		        GameContainer.remove(MainStoryPanel);
		    }
		    
		    			
			Position = "TryCDoor";			
			MainStoryArea.setText("奇怪，教室門被反鎖了 \n找找看有什麼辦法打開吧");
		    MainStoryPanel = new BackgroundImagePanel("dir1/Background/TryCDoor.jpg");
		    GameContainer.add(MainStoryPanel);	//將新的 MainStoryPanel 加入 GameContainer
		    GameContainer.revalidate();	//確保 UI 更新
		    GameContainer.repaint();			
			
			Choice1.setText(">>>");
			Choice2.setText("尋找桌子");
			Choice3.setText("");
			Choice4.setText("");
			
		}
		
		public void TryCWindow() {

			Position = "TryCWindow";	
			MainStoryArea.setText("這裡是十八樓耶，別傻了...");
			
			
			Choice1.setText("仍執意要打開");
			Choice2.setText("再看看周遭");
			Choice3.setText("");
			Choice4.setText("");
			
		}
				public void ForceOpenWindow() {
		
					Position = "ForceOpenWindow";	
					MainStoryArea.setText("if I can Fly. \n你掉了下去");
					
					
					Choice1.setText("");
					Choice2.setText("");
					Choice3.setText("");
					Choice4.setText("");
					
				}		
			
		public void GoSleep() {

			Position = "GoSleep";	
			MainStoryArea.setText("還是繼續睡吧... \n你決定繼續睡覺，希望醒來時一切都是夢。");
			
			
			Choice1.setText("");
			Choice2.setText("");
			Choice3.setText("");
			Choice4.setText("");
			
		}
		public class TitleScreenProcess implements ActionListener {	//負責處理點選開始遊戲後，程式執行進入點
			
			public void actionPerformed(ActionEvent event) {
				
				CreatStoryPrologueScreen();
			}
		}
		
		
		public class ChoiceProcess implements ActionListener {	//負責處理四個對話選項的程式執行進入點
			
			public void actionPerformed(ActionEvent event) {
				
				String PlayerChoice = event.getActionCommand();	//選擇對話中任一選項，會被判定為玩家的選擇，讓程式辨別哪個選項被選擇

				switch(Position) {	//處理選項被選擇後，所對應的劇情，透過switch可以簡化程式碼，也可用if 然後對應相對應的劇情，但是會大幅增加程式碼
				case "ClassRoom":	//在 switch 語句中為可能的值和與該值關聯的程式碼塊，此處用來匹配相對應的遊戲場景，有點像是每個案件對應的事件
					switch(PlayerChoice) {
					case "CB1":LookAroundCRoom(); break;
					case "CB2":TryCDoor(); break;
					case "CB3":TryCWindow(); break;
					case "CB4":LookAroundCRoom(); break;
					}
					break;
					
						case "LookAroundCRoom":
							switch(PlayerChoice) {
							case "CB1": ClassRoom(); break;
							}
							break;
							
						case "TryCDoor":
							switch(PlayerChoice) {
							case "CB1": ClassRoom(); break;
							}
							break;
							
						case "TryCWindow":
							switch(PlayerChoice) {
							case "CB1": ForceOpenWindow(); break;
							case "CB2": ClassRoom(); break;
							}
							break;
						case "GoSleep":
							switch(PlayerChoice) {
							case "CB1": ClassRoom(); break;
							}
							break;
							
				}
			}
		}
		
		
		public void CreateBadEndScreen() {	//創建遊戲壞結局介面

			TitleNamePanel.setVisible(false);
			StartGButtonPanel.setVisible(false);
			LoadGButtonPanel.setVisible(false);
			ScoreLButtonPanel.setVisible(false);
			ExtraButtonPanel.setVisible(false);
			PrologueDialoguePanel.setVisible(false);
			MainStoryPanel.setVisible(false);
			ChoiceButtonPanel.setVisible(false);
			PlayerPanel.setVisible(false);
			
		}
		
		
		public void CreateGoodEndScreen() {	//創建遊戲好結局介面
			
			TitleNamePanel.setVisible(false);
			StartGButtonPanel.setVisible(false);
			LoadGButtonPanel.setVisible(false);
			ScoreLButtonPanel.setVisible(false);
			ExtraButtonPanel.setVisible(false);
			PrologueDialoguePanel.setVisible(false);
			MainStoryPanel.setVisible(false);
			ChoiceButtonPanel.setVisible(false);
			PlayerPanel.setVisible(false);
			
		}
	}