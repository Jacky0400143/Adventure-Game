package me.synology.zankyonokaze;

	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.MouseAdapter;
	import java.awt.event.MouseEvent;

	public class Test04 {	//滑鼠點擊，遊戲開頭對白

	    private JLabel dialogue;
	    private int dialogueIndex = 0;
	    private String[] dialogues = {
	            "我好像睡著了...",
	            "果然是太累了嗎...",
	            "這裡是哪裡..."
	    };

	    public Test04() {
	        JFrame frame = new JFrame("Text Adventure Game");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(600, 400);

	        JPanel panel = new JPanel();
	        panel.setBackground(Color.BLACK);
	        panel.setLayout(new BorderLayout());

	        dialogue = new JLabel("", SwingConstants.CENTER);
	        dialogue.setForeground(Color.WHITE);
	        dialogue.setFont(new Font("Serif", Font.PLAIN, 24));
	        panel.add(dialogue, BorderLayout.CENTER);

	        panel.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                if(dialogueIndex < dialogues.length) {
	                    dialogue.setText(dialogues[dialogueIndex++]);
	                }
	                else {
	                    // Load Game Options...
	                }
	            }
	        });

	        frame.add(panel);
	        frame.setVisible(true);
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(Test04::new);
	    }
	}
