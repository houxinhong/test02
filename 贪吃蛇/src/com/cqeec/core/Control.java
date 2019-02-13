package com.cqeec.core;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import com.cqeec.entity.Food;
import com.cqeec.entity.GamePanel;
import com.cqeec.entity.Ground;
import com.cqeec.entity.Snake;
import com.cqeec.listener.SnakeListener;
import com.cqeec.util.Global;

public class Control extends KeyAdapter implements SnakeListener {
	public Snake snake;
	public Food food;
	public Ground ground;
	public GamePanel gamePanle;

	public Control(Snake snake, Food food, GamePanel gamePanel) {
		this.gamePanle = gamePanel;
		this.food = food;
		this.snake = snake;
	}

	@Override
	public void snakeMove() {
		//判断蛇是否自己吃到自jiddd
		if(snake.isEatSelf()) {
			JOptionPane.showConfirmDialog(null, "GameOver");
			System.exit(0);
		}
		//判断蛇是否撞到障碍物
		if(snake.isEatGround()) {
			if(JOptionPane.showConfirmDialog(null, "GameOver")==1) {
				Snake snake=new Snake(12,12, Color.green);
				snake.addSnakeListener(this);
				this.snake=snake;
				gamePanle.snake=snake;
				Global.reference.x=0;
				Global.reference.y=0;
			}else {
				System.exit(0);
			}
		}
		//判断蛇是否吃到食物
		if(snake.isEatFood(food)) {
			//重新给食物坐标赋值
			//利用控制器中现有数据进行食物生成位置排除
			food.setLocation(getPoint());
			//将蛇的消失的尾巴补起来
			snake.body.addLast(snake.tail);
		}
		showView();
	}
	@Override
	public void showView() {
		this.gamePanle.display();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		switch (keyCode) {
		case KeyEvent.VK_UP:
			snake.changeDirection(Snake.UP);
			break;
		case KeyEvent.VK_DOWN:
			snake.changeDirection(Snake.DOWN);
			break;
		case KeyEvent.VK_LEFT:
			snake.changeDirection(Snake.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			snake.changeDirection(Snake.RIGHT);
			break;
		}
	}

	public void startGame() {
		snake.start();
	}

	public Point getPoint() {
		int x, y, pd;
		while (true) {
			pd = 1;
			// 生成点不在右下障碍物上
			x = new Random().nextInt(Global.WIDTH_VISUAL - 1);
			y = new Random().nextInt(Global.HEIGHT_VISUAL - 1);
			// 生成点不在左上障碍物上
			if (x != 0 && y != 0) {
				// 通过setter方法调用出蛇类对象的私有属性body，然后再判断生成新的食物点是否在其蛇身上
				for (int i = 0; i < snake.body.size(); i++) {
					if (x*Global.CELL_SIZE == snake.body.get(i).x && y*Global.CELL_SIZE == snake.body.get(i).y)
						pd++;
				}
				// 如果pd不等于0则说明其不在蛇身上然后再返回一个新的point对象回去
				if (pd == 1)
					return new Point(x*Global.CELL_SIZE, y*Global.CELL_SIZE);
			}

		}
	}


}