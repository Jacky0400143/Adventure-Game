package me.synology.zankyonokaze;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ADV_Alpha_x002 {
    private JFrame frame;
    private JTextArea storyArea;
    private JButton[] optionsButtons;

    private boolean foundKey = false; // 尋找鑰匙的選項
    private boolean calledForHelp = false; // 叫喊救援的選項
    private boolean triedWindow = false; // 嘗試窗戶的選項
    private boolean keptSleeping = false; // 繼續睡覺的選項
    
    public ADV_Alpha_x002() {
        // 創建視窗
        frame = new JFrame("逃離電腦教室");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // 創建主要故事區域
        storyArea = new JTextArea();
        storyArea.setEditable(false);
        storyArea.setText("你在電腦教室醒來，發現門被鎖住了。\n\n你要做什麼？\n");
        storyArea.setBorder(new EmptyBorder(10, 10, 10, 10)); // 添加邊界
        storyArea.setOpaque(true); // 設定背景為不透明
        storyArea.setBackground(Color.BLACK); // 設定背景色為黑色
        storyArea.setForeground(Color.WHITE); // 設定文字顏色為白色

        // 為 JTextArea 建立 JScrollPane
        JScrollPane scrollPane = new JScrollPane(storyArea);
        scrollPane.setBorder(null); // 移除邊界
        frame.add(scrollPane, BorderLayout.CENTER);

        // 創建選擇按鈕
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1));
        optionsPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // 添加邊界
        frame.add(optionsPanel, BorderLayout.SOUTH);

        optionsButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            optionsButtons[i] = new JButton();
            optionsButtons[i].addActionListener(new OptionListener());
            optionsPanel.add(optionsButtons[i]);
        }

        // 設定選擇按鈕的文字
        optionsButtons[0].setText("尋找教室裡的鑰匙");
        optionsButtons[1].setText("使用窗戶逃脫");
        optionsButtons[2].setText("叫出救援");
        optionsButtons[3].setText("繼續睡覺");
    }

    public void show() {
        frame.setVisible(true);
    }

    private class OptionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            if (source == optionsButtons[0]) {
                foundKey = true;
                storyArea.setText("你找到了一把鑰匙，但是它不適合鎖。\n\n你要做什麼？\n");
            } else if (source == optionsButtons[1]) {
                triedWindow = true;
                storyArea.setText("窗戶被鎖住了，你無法打開它。\n\n你要做什麼？\n");
            } else if (source == optionsButtons[2]) {
                calledForHelp = true;
                storyArea.setText("你嘗試叫喊，但沒有人回應。\n\n你要做什麼？\n");
            } else if (source == optionsButtons[3]) {
                keptSleeping = true;
                storyArea.setText("你決定繼續睡覺，希望醒來時一切都是夢。\n\n你要做什麼？\n");
            }

            // 這裡添加一個新的規則：如果玩家尋找了鑰匙並且叫喊了救援，那麼他們就會找到一個手機並且逃脫。
            if (foundKey && calledForHelp) {
                storyArea.setText("你看到了一個手機在地上。你撿起來並撥打了求救電話，你成功的逃脫了電腦教室。恭喜你勝利！");
            }
        }
    }

    public static void main(String[] args) {
    	ADV_Alpha_x001 game = new ADV_Alpha_x001();
        game.show();
    }
}
