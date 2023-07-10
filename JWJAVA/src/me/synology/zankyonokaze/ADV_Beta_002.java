package me.synology.zankyonokaze;


	import java.awt.color.*; //導入java.awt.color包，此包含有所有顏色空間和色彩型別相關的類別。
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.Color;   //導入java.awt.Color類別，該類別用於封裝顏色的RGB值。
	import java.awt.Container; //導入java.awt.Container類別，該類別是Swing組件的容器，用於保存和管理其他組件。
	import java.awt.Font;

	import javax.swing.JFrame; //導入javax.swing.JFrame類別，此類別代表一個應用程式視窗，用於添加其他GUI組件。
	import javax.swing.JPanel;
	import javax.swing.JLabel;
	import javax.swing.JButton;
	import javax.swing.JTextArea;
	

	public class ADV_Beta_002 { // 定義一個名為ADV_Beta_001的public class。
		
		JFrame GameWindow; // 宣告一個JFrame物件名稱為GameWindow，這將是遊戲的主視窗。
		Container GameContainer; // 宣告一個Container物件名稱為GameContainer，這將是主視窗中的容器，用於保存和管理其他GUI組件。
		JPanel TitleNamePanel, StartGButtonPanel, LoadGButtonPanel, ScoreLButtonPanel, ExtraButtonPanel,
				MainStoryPanel, ChoiceButtonPanel;
		JLabel TitleNameLabel;
		Font TitleFont = new Font("Ink Free", Font.BOLD, 100);	//開始標題的字體
		Font NormalBFont = new Font("微軟正黑體", Font.PLAIN, 24);	//開始按鈕的字體
		JButton StartGameButton, LoadGameButton, ScoreListButton, ExtraButton;
		JTextArea MainStoryArea;
		
		TitleScreenProcess TSProcess = new TitleScreenProcess();
		
		
		public static void main(String[] args) { // Java主程式的進入點。
			
			new ADV_Beta_002(); // 建立ADV_Beta_001類別的一個新實例，這將自動調用下面的建構子並開啟視窗。
		}
		
		public ADV_Beta_002() { // ADV_Beta_001類別的建構子。
			
			GameWindow = new JFrame(); // 建立一個新的JFrame物件並分配給GameWindow。
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
			StartGameButton.setFont(NormalBFont);
			StartGameButton.addActionListener(TSProcess);	//開始遊戲點擊的動作處理程序
						
			LoadGButtonPanel = new JPanel();	//讀取按鈕的背景面板
			LoadGButtonPanel.setBounds(550, 420, 200, 70);
			LoadGButtonPanel.setBackground(Color.black);
			LoadGameButton = new JButton("Load Game");	//讀取遊戲按鈕的按鈕面板
			LoadGameButton.setBackground(Color.black);
			LoadGameButton.setForeground(Color.white);
			LoadGameButton.setFont(NormalBFont);
						
			ScoreLButtonPanel = new JPanel();	//計分按鈕的背景面板
			ScoreLButtonPanel.setBounds(550, 490, 200, 70);
			ScoreLButtonPanel.setBackground(Color.black);
			ScoreListButton = new JButton("Game Score");	//計分遊戲按鈕的按鈕面板
			ScoreListButton.setBackground(Color.black);
			ScoreListButton.setForeground(Color.white);
			ScoreListButton.setFont(NormalBFont);
			
			ExtraButtonPanel = new JPanel();	//Extra按鈕的背景面板
			ExtraButtonPanel.setBounds(550, 560, 200, 70);
			ExtraButtonPanel.setBackground(Color.black);
			ExtraButton = new JButton("Extra");	//Extea資訊按鈕的按鈕面板
			ExtraButton.setBackground(Color.black);
			ExtraButton.setForeground(Color.white);
			ExtraButton.setFont(NormalBFont);
			
			
			TitleNamePanel.add(TitleNameLabel);	//把標題文字框加入到標題背景面板
			StartGButtonPanel.add(StartGameButton);	//把開始遊戲按鈕加入到開始按鈕面板
			LoadGButtonPanel.add(LoadGameButton);	//把讀取遊戲按鈕加入到讀取按鈕面板
			ScoreLButtonPanel.add(ScoreListButton);	//把計分按鈕加入到計分面板
			ExtraButtonPanel.add(ExtraButton);	//Extra資訊按鈕加入到Extra面板
			
			
			TitleNamePanel.setOpaque(false);
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
		
		public void CreateGameScreen() {
			
			TitleNamePanel.setVisible(false);
			StartGButtonPanel.setVisible(false);
			LoadGButtonPanel.setVisible(false);
			ScoreLButtonPanel.setVisible(false);
			ExtraButtonPanel.setVisible(false);
			
			MainStoryPanel = new JPanel();
			MainStoryPanel.setBounds(140, 500, 1000, 150);
			MainStoryPanel.setBackground(Color.blue);
			GameContainer.add(MainStoryPanel);
			
			
			MainStoryArea = new JTextArea("Test Main Story Area");
			MainStoryArea.setBounds(140, 100, 1000, 250);
			MainStoryArea.setBackground(Color.black);
			MainStoryArea.setForeground(Color.white);
			MainStoryArea.setFont(NormalBFont);
			MainStoryArea.setLineWrap(true);
			MainStoryPanel.add(MainStoryArea);
			
			ChoiceButtonPanel = new JPanel();
			ChoiceButtonPanel.setBounds(350, 600, 500, 150);
			ChoiceButtonPanel.setBackground(Color.red);
			GameContainer.add(ChoiceButtonPanel);
			
		}
		
		public class TitleScreenProcess implements ActionListener {
			
			public void actionPerformed(ActionEvent event) {
				
				CreateGameScreen();
			}
		}
		
		
	}