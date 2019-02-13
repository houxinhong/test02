package com.cqeec.core;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.cqeec.entity.Food;
import com.cqeec.entity.GameFrame;
import com.cqeec.entity.GamePanel;
import com.cqeec.entity.Snake;

public class GameTest {

	public static void main(String[] args) {
		//初始化窗体对象
		GameFrame frame = new GameFrame();
		frame.launcher();
		//初始化面板对象
		GamePanel panel=new GamePanel();
		panel.init();
		//创建控制器
		Snake snake=new Snake(12, 12, Color.RED);
		Food food=new Food();
		Control control = new Control(snake, food, panel);
		// 监听器的添加
		snake.addSnakeListener(control);
		panel.addKeyListener(control);
		// 创建按钮对象
		// 开始按钮
		JButton button1 = new JButton("开始");
		button1.setBounds(0, 550, 70, 50);
		// 减速按钮
		JButton button2 = new JButton("减速");
		button2.setBounds(100, 550, 70, 50);
		// 加速按钮
		JButton button3 = new JButton("加速");
		button3.setBounds(200, 550, 70, 50);
		// 添加按钮监听器（隐匿内部类）
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				control.startGame();
				panel.requestFocus();
			}
		});
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				control.snake.updateSpeed(0);
				panel.requestFocus();
			}
		});
		button3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				control.snake.updateSpeed(1);
				panel.requestFocus();
			}
		});
		// 将按钮与文本放入面板对象中去
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		//参数赋值
		panel.fuzhi(snake, food);
		//将面板放入窗体对象中去
		frame.add(panel);
		//设置窗体为可见
		frame.setVisible(true);
		
		
	}

}
