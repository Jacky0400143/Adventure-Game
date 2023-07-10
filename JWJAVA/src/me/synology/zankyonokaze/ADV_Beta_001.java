package me.synology.zankyonokaze;

import java.awt.color.*; //導入java.awt.color包，此包含有所有顏色空間和色彩型別相關的類別。
import java.awt.Color;   //導入java.awt.Color類別，該類別用於封裝顏色的RGB值。
import java.awt.Container; //導入java.awt.Container類別，該類別是Swing組件的容器，用於保存和管理其他組件。
import javax.swing.JFrame; //導入javax.swing.JFrame類別，此類別代表一個應用程式視窗，用於添加其他GUI組件。

public class ADV_Beta_001 { // 定義一個名為ADV_Beta_001的公共類別。
	
	JFrame GameWindow; // 宣告一個JFrame物件名稱為GameWindow，這將是遊戲的主視窗。
	Container GameContainer; // 宣告一個Container物件名稱為GameContainer，這將是主視窗中的容器，用於保存和管理其他GUI組件。
	
	public static void main(String[] args) { // Java主程式的進入點。
		
		new ADV_Beta_001(); // 建立ADV_Beta_001類別的一個新實例，這將自動調用下面的建構子並開啟視窗。
	}
	
	public ADV_Beta_001() { // ADV_Beta_001類別的建構子。
		
		GameWindow = new JFrame(); // 建立一個新的JFrame物件並分配給GameWindow。
		GameWindow.setSize(1280, 720); // 設置GameWindow的解析度
		GameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定視窗關閉行為，當視窗關閉時，應用程式將完全終止。
		GameWindow.getContentPane().setBackground(Color.black); // 將視窗的背景色設為黑色。
		GameWindow.setLayout(null); // 設定視窗的佈局管理器為null，這意味著你將自行控制所有元件的大小和位置。
		GameWindow.setVisible(true); // 設定視窗為可見，這樣使用者就能看見視窗了。
		GameContainer = GameWindow.getContentPane(); // 將GameWindow的內容窗格分配給GameContainer，現在你可以在這個容器中添加其他組件。
	}
}