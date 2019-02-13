package com.cqeec.entity;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import com.cqeec.util.Global;

public class GamePanel extends JPanel {
	public Snake snake;
	private Food food;

	public void fuzhi(Snake snake, Food food) {
		this.food = food;
		this.snake = snake;
	}
	
	
	//画出对应的视图
	public void display() {
		//间接调用paintComponent方法，完成对象的绘画
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		  //去除轨迹
		    super.paintComponent(g);
		  //画出对应的对象
	        snake.drawMe(g);
			food.drawMe(g);
			Ground.drawMe(g);
	}
	
	public void init() {
		this.setLayout(null);
		this.setSize(Global.CELL_SIZE*Global.WIDTH_VISUAL+100,Global.CELL_SIZE*Global.HEIGHT_VISUAL+100);
		this.setFocusable(true);
	}
	

}
