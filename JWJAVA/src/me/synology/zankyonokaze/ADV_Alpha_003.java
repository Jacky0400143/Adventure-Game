package me.synology.zankyonokaze;


import java.io.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.JButton;	//載入視窗按鈕API
import javax.swing.JFrame;	//載入視窗框架API
import javax.swing.JPanel;
import javax.swing.JTextArea;	//載入文字顯示區域API
import javax.swing.JOptionPane;	//載入選項視窗API


public class ADV_Alpha_003 extends JFrame {
    private static JFrame GameFrame; //引用JFrame並命名為GameFrame
    private static JTextArea textArea;	//文本顯示位置
    private static JButton button;	//	按鍵
    private static JPanel TitleTextPanel, StoryTextPanel, ButtonPanel;
    private static Font WindowFont = new Font("Times New Roman", Font.PLAIN, 24);	//視窗字體設定
    private static Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);	//標題字體設定
    private static Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);	//一般字體設定
    private static String PlayerName; //玩家遊戲名稱

    
    public void showInputDialog() {
    	PlayerName = JOptionPane.showInputDialog(this, "請輸入玩家名稱", "輸入名稱", JOptionPane.PLAIN_MESSAGE);
            if (PlayerName == null || PlayerName.trim().isEmpty()) {
                int choice = JOptionPane.showConfirmDialog(StoryTextPanel, ButtonPanel, PlayerName, 0);
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0);
                } else {
                    showInputDialog();
                }
            }
    }
    
    
    public static void main(String[] args) {

    	ADV_Alpha_003 PlayerWindow = new ADV_Alpha_003();
    	PlayerWindow .showInputDialog();
        GameFrame = new JFrame("文字冒險遊戲");	//視窗標題
        GameFrame.setSize(1280, 720);	//視窗解析度
        GameFrame.setResizable(false);	//固定視窗大小 使用戶無法調整大小
        GameFrame.setFont(normalFont);	//字體
        GameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//結束視窗時，終止程序
//        GameFrame.getContentPane().setBackground(Color.black);

        textArea = new JTextArea();
        textArea.setEditable(false);
//        textArea.setBackground(Color.BLACK);	//可以更換文本顯示區域的背景顏色
        GameFrame.getContentPane().add(textArea);

        button = new JButton("START");
//        button.setFont(titleFont);
        button.addActionListener(e -> {
            startGame();
            button.setEnabled(false);
        });
        
        
        GameFrame.getContentPane().add(textArea, BorderLayout.NORTH);
        GameFrame.getContentPane().add(button, BorderLayout.SOUTH);
        

        StoryTextPanel = new JPanel() {
	    @Override
	    protected void paintComponent(Graphics g) {
	    	super.paintComponent(g);
	    	try {
	            Image backgroundImage = ImageIO.read(new File("dir1/Background/01.png"));
	            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
    };
    GameFrame.getContentPane().add(StoryTextPanel, BorderLayout.CENTER);
    GameFrame.setVisible(true);	//顯示視窗 Display the window.
	}
    
    
    private static void startGame() {
        textArea.setText("你發現你在一個房間裡醒來。有一扇門在你面前。\n");
        textArea.append("你想要打開門嗎？(是/否)\n");

       JButton yesButton = new JButton("是");
        //button.setText("是");
        //button.setEnabled(true);
        yesButton.addActionListener(e -> {
            textArea.append("你走出房子，看到一片廣闊的草地。\n");
            textArea.append("遊戲結束。\n");
            button.setEnabled(false);
        });

        JButton noButton = new JButton("否");
        noButton.addActionListener(e -> {
            textArea.append("你決定不打開門。遊戲結束。\n");
            button.setEnabled(false);
        });
        
        JPanel ButtonPanel = new JPanel();
        ButtonPanel.setLayout(new BorderLayout());        
        GameFrame.getContentPane().add(noButton, BorderLayout.EAST);
        GameFrame.getContentPane().add(yesButton, BorderLayout.WEST);
      

        GameFrame.revalidate();
        GameFrame.repaint();
    }
}

