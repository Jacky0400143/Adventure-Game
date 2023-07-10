package me.synology.zankyonokaze;

	// 載入所需的API
	import java.io.*; // 用於讀取檔案或圖像
	import java.awt.BorderLayout; // GUI中的版面配置管理器
	import java.awt.Color; // 用於設定顏色
	import java.awt.Font; // 用於設定字型
	import java.awt.Graphics; // 用於繪製圖像和文字
	import java.awt.Image; // 用於操作圖像
	import javax.imageio.ImageIO; // 用於讀取和寫入圖像
	import javax.swing.JButton; // GUI中的按鈕
	import javax.swing.JFrame; // GUI中的視窗框架
	import javax.swing.JPanel; // GUI中的面板，用於放置其他元件
	import javax.swing.JTextArea; // GUI中的文字顯示區域
	import javax.swing.JOptionPane; // GUI中的對話框

	// 定義一個新的類別，名為ADV_Alpha_003，並且繼承自JFrame
	public class Test02 extends JFrame {
	    // 定義類別內的變數，包含框架、文字區域、按鈕、面板、字型和玩家名稱
	    private static JFrame GameFrame;
	    private static JTextArea textArea;
	    private static JButton button;
	    private static JPanel TitleTextPanel, StoryTextPanel, ButtonPanel;
	    private static Font WindowFont = new Font("Times New Roman", Font.PLAIN, 24);
	    private static Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
	    private static Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
	    private static String PlayerName;

	    // 這是一個方法，用於顯示對話框，讓玩家輸入名字，並檢查輸入是否有效
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

	    // 主方法，程式的進入點
	    public static void main(String[] args) {

	    	Test02 PlayerWindow = new Test02();
	    	PlayerWindow .showInputDialog(); // 呼叫showInputDialog方法
	        GameFrame = new JFrame("文字冒險遊戲"); // 創建一個新的JFrame物件
	        GameFrame.setSize(1280, 720); // 設定視窗大小
	        GameFrame.setResizable(false); // 設定視窗無法調整大小
	        GameFrame.setFont(normalFont); // 設定視窗字型
	        GameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 當關閉視窗時結束程式

	        textArea = new JTextArea(); // 創建一個新的文字區域
	        textArea.setEditable(false); // 設定文字區域為不可編輯

	        GameFrame.getContentPane().add(textArea); // 將文字區域添加到視窗的內容窗格

	        button = new JButton("START"); // 創建一個新的按鈕
	        button.addActionListener(e -> { // 為按鈕設定事件處理器
	            startGame(); // 呼叫startGame方法
	            button.setEnabled(false); // 設定按鈕為不可點擊
	        });

	        GameFrame.getContentPane().add(textArea, BorderLayout.NORTH); // 將文字區域添加到視窗的北邊
	        GameFrame.getContentPane().add(button, BorderLayout.SOUTH); // 將按鈕添加到視窗的南邊

	        // 創建一個新的面板，並覆寫其paintComponent方法以設定背景圖片
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
	        GameFrame.getContentPane().add(StoryTextPanel, BorderLayout.CENTER); // 將面板添加到視窗的中央
	        GameFrame.setVisible(true); // 設定視窗為可見
		}

	    // 這是一個方法，用於開始遊戲
	    private static void startGame() {
	        // 更新文字區域的文字
	        textArea.setText("你發現你在一個房間裡醒來。有一扇門在你面前。\n");
	        textArea.append("你想要打開門嗎？(是/否)\n");

	        // 創建兩個新的按鈕，分別對應選擇"是"和"否"
	        JButton yesButton = new JButton("是");
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

	        // 創建一個新的面板，並將兩個按鈕添加到面板中
	        JPanel ButtonPanel = new JPanel();
	        ButtonPanel.setLayout(new BorderLayout());        
	       GameFrame.getContentPane().add(noButton, BorderLayout.EAST); // 將"否"按鈕添加到視窗的東側
	GameFrame.getContentPane().add(yesButton, BorderLayout.WEST); // 將"是"按鈕添加到視窗的西側

	// 重新驗證視窗和重新繪製視窗
	GameFrame.revalidate();
	GameFrame.repaint();
	}

	} // 結束類別的定義
