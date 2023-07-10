package me.synology.zankyonokaze;

	import java.awt.*;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import javax.swing.*;

	public class ADV_Alpha_x003 extends JFrame {
	    // 設定淡出文字和淡入效果
	    private JLabel label;
	    private float alpha = 1f;
	    private String[] messages = {"我好像睡著了", "果然是太累了嗎", "這裡是哪裡"};
	    private int currentIndex = 0;

	    // 文字冒險遊戲的部分
	    private JFrame GameFrame; 
	    private JTextArea textArea;
	    private JButton button;
	    private JPanel TitleTextPanel, StoryTextPanel, ButtonPanel;
	    private Font WindowFont = new Font("Times New Roman", Font.PLAIN, 24);
	    private Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
	    private Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
	    private String PlayerName; 

	    public ADV_Alpha_x003() {
	        // 設定淡出文字的初始設定
	        setLayout(new BorderLayout());
	        getContentPane().setBackground(Color.BLACK);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        setSize(800, 600);

	        label = new JLabel();
	        label.setFont(new Font("Serif", Font.BOLD, 32));
	        label.setForeground(new Color(1f, 1f, 1f, alpha));
	        label.setHorizontalAlignment(JLabel.CENTER);
	        label.setVerticalAlignment(JLabel.CENTER);
	        add(label);

	        // 使用 Timer 控制文字淡出效果
	        new Timer(100, new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                alpha += -0.01f;
	                label.setForeground(new Color(1f, 1f, 1f, alpha));

	                if (alpha <= 0) {
	                    alpha = 1f;
	                    if (currentIndex >= messages.length) {
	                        ((Timer)e.getSource()).stop();
	                        startGame();
	                    } else {
	                        label.setText(messages[currentIndex++]);
	                    }
	                }
	            }
	        }).start();

	        setVisible(true);
	    }

	    public void startGame() {
	        // 關閉淡出文字視窗
	        setVisible(false);
	        
	        // 以下是文字冒險遊戲的初始設定
	        GameFrame = new JFrame("文字冒險遊戲");
	        GameFrame.setSize(1280, 720); 
	        GameFrame.setResizable(false); 
	        GameFrame.setFont(normalFont); 
	        GameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	        
	        textArea = new JTextArea();
	        textArea.setEditable(false);
	        GameFrame.getContentPane().add(textArea);

	        button = new JButton("START");
	        button.addActionListener(e -> {
	            textArea.setText("你發現你在一個房間裡醒來。有一扇門在你面前。\n你想要打開門嗎？(是/否)\n");
	            button.setEnabled(false);
	        });

	        GameFrame.getContentPane().add(textArea, BorderLayout.NORTH);
	        GameFrame.getContentPane().add(button, BorderLayout.SOUTH);
	        
	        // 設定遊戲視窗並顯示
	        GameFrame.setVisible(true); 
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                new ADV_Alpha_x003();
	            }
	        });
	    }	
}


