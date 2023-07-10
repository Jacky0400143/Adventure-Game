package me.synology.zankyonokaze;


	import java.awt.*;
	import java.awt.event.ActionEvent;
	import javax.swing.*;
	import java.io.File;
	import java.io.IOException;
	import javax.imageio.ImageIO;

	public class Test03 extends JFrame {
	    private static JFrame GameFrame;
	    private static JTextArea textArea;
	    private static JButton startButton, pauseButton;
	    private static JPanel panelContainer;
	    private static Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
	    private static String PlayerName;

	    public void showInputDialog() {
	        PlayerName = JOptionPane.showInputDialog(this, "請輸入玩家名稱", "輸入名稱", JOptionPane.PLAIN_MESSAGE);
	        if (PlayerName == null || PlayerName.trim().isEmpty()) {
	            int choice = JOptionPane.showConfirmDialog(null, "你沒有輸入名稱，是否結束遊戲？", "確認", JOptionPane.YES_NO_OPTION);
	            if (choice == JOptionPane.YES_OPTION) {
	                System.exit(0);
	            } else {
	                showInputDialog();
	            }
	        }
	    }

	    public static void main(String[] args) {
	        ADV_Alpha_003 PlayerWindow = new ADV_Alpha_003();
	        PlayerWindow.showInputDialog();

	        GameFrame = new JFrame("文字冒險遊戲");
	        GameFrame.setSize(1280, 720);
	        GameFrame.setResizable(false);
	        GameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        // Create a panel to contain other panels (CardLayout).
	        panelContainer = new JPanel(new CardLayout());

	        textArea = new JTextArea();
	        textArea.setEditable(false);
	        textArea.setFont(normalFont);
	        textArea.setLineWrap(true);
	        textArea.setWrapStyleWord(true);

	        startButton = new JButton("START");
	        startButton.addActionListener(e -> {
	            startGame();
	            startButton.setEnabled(false);
	            pauseButton.setEnabled(true);
	        });

	        pauseButton = new JButton("PAUSE");
	        pauseButton.addActionListener(e -> {
	            if (textArea.isEnabled()) {
	                textArea.setEnabled(false);
	                pauseButton.setText("RESUME");
	            } else {
	                textArea.setEnabled(true);
	                pauseButton.setText("PAUSE");
	            }
	        });
	        pauseButton.setEnabled(false);

	        // Create button panel.
	        JPanel buttonPanel = new JPanel();
	        buttonPanel.setLayout(new FlowLayout());
	        buttonPanel.add(startButton);
	        buttonPanel.add(pauseButton);

	        panelContainer.add(buttonPanel, "buttonPanel");
	        panelContainer.add(new JScrollPane(textArea), "textArea");

	        GameFrame.getContentPane().add(panelContainer);
	        GameFrame.setVisible(true);
	    }

	    private static void startGame() {
	        textArea.setText("你發現你在一個房間裡醒來。有一扇門在你面前。\n");
	        textArea.append("你想要打開門嗎？(是/否)\n");

	        JButton yesButton = new JButton("是");
	        yesButton.addActionListener(e -> {
	            textArea.append("你走出房子，看到一片廣闊的草地。\n");
	            textArea.append("遊戲結束。\n");
	            pauseButton.setEnabled(false);
	        });

	        JButton noButton = new JButton("否");
	        noButton.addActionListener(e -> {
	            textArea.append("你決定不打開門。遊戲結束。\n");
	            pauseButton.setEnabled(false);
	        });

	        // Add the yes/no buttons to the button panel.
	        JPanel buttonPanel = (JPanel) panelContainer.getComponent(0);
	        buttonPanel.add(yesButton);
	        buttonPanel.add(noButton);
	    }
	}