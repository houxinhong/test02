package com.cqeec.entity;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	
	
	public void launcher() {
		this.setTitle("贪吃蛇");
		this.setSize(700, 700);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
