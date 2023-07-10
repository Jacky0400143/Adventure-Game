package me.synology.zankyonokaze;

	//引入需要的Java Swing及AWT類別
	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	//定義主要的TextAdventureGame類別
	public class text {
	
	 // 定義類別變量，用於顯示對話的標籤
	 private JLabel dialogue;
	 // 定義類別變量，用於追蹤目前對話的索引
	 private int dialogueIndex = 0;
	 // 定義對話內容的陣列
	 private String[] dialogues = {
	         "我好像睡著了...",
	         "果然是太累了嗎...",
	         "這裡是哪裡..."
	 };
	
	 // 定義TextAdventureGame的建構子
	 public text() {
	     // 創建視窗並設定關閉動作和大小
	     JFrame frame = new JFrame("Text Adventure Game");
	     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     frame.setSize(600, 400);
	
	     // 創建主要的面板，設定背景顏色為黑色和版面配置為邊緣配置(BorderLayout)
	     JPanel panel = new JPanel();
	     panel.setBackground(Color.BLACK);
	     panel.setLayout(new BorderLayout());
	
	     // 創建對話標籤，設定文字顏色為白色和字型，並將標籤加入面板中間
	     dialogue = new JLabel("", SwingConstants.CENTER);
	     dialogue.setForeground(Color.WHITE);
	     dialogue.setFont(new Font("Serif", Font.PLAIN, 24));
	     panel.add(dialogue, BorderLayout.CENTER);
	
	     // 創建下一對話按鈕，設定其行為為顯示下一對話或載入遊戲選項，並將按鈕加入面板下方
	     JButton nextDialogueButton = new JButton("Next Dialogue");
	     nextDialogueButton.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	             if(dialogueIndex < dialogues.length) {
	                 dialogue.setText(dialogues[dialogueIndex++]);
	             }
	             else {
	                 // Load Game Options...
	             }
	         }
	     });
	     panel.add(nextDialogueButton, BorderLayout.SOUTH);
	
	     // 將面板加入視窗並顯示視窗
	     frame.add(panel);
	     frame.setVisible(true);
	 }
	
	 // 定義main方法，此方法為應用程式的入口點
	 public static void main(String[] args) {
	     // 使用Swing工具包的invokeLater方法確保Swing操作在事件分派線程中執行
	     SwingUtilities.invokeLater(text::new);
	 }
	}