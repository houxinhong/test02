package com.cqeec.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import com.cqeec.core.Control;
import com.cqeec.listener.SnakeListener;
import com.cqeec.util.Global;

public class Snake {
	//方向常量
	public final static int RIGHT=1;
	public final static int LEFT=-1;
	public final static int UP=2;
	public final static int DOWN=-2;
	//蛇移动的速度默认每500ms移动一次
	public int time = 500;
	//蛇尾的坐标对象
	public Point tail = null;
	//蛇移动的老方向（蛇身数据改变时使用的方向）
	public int oldDirection = RIGHT;
	//蛇移动的新方向（老方向改变时依赖的数据）
	public int newDirection = RIGHT;
	//蛇的位置信息集合
	public LinkedList<Point> body=new LinkedList<>();
	//蛇身体的颜色
	public Color color;
	//蛇的监听器
	public SnakeListener snakeListener;
	
	/**
	 *控制蛇头初始位置 
	 * 蛇身体的长度默认为3
	 */
	public Snake(int x,int y,Color color) {
	  	 for(int i=0;i<3;i++) {
	  		 body.add(new Point((x-i)*Global.CELL_SIZE,y*Global.CELL_SIZE));
	  	 }
	  	 this.color=color;
	}

    /**
     * 画自己
     * @param g
     */
	public void drawMe(Graphics g) {
		int cell_size=Global.CELL_SIZE;
		g.setColor(this.color);
		for(Point p:body) {
			//为了数据显示正常，所以这里画的位置为相对位置，相对于参照物的位置
			g.fill3DRect(p.x-Global.reference.x, p.y-Global.reference.y,cell_size, cell_size,true);
		}
	}
	
	/**
	 * 获取蛇头的方法
	 */
	public Point getSnakeHead() {
		return this.body.getFirst();
	}
	
	/**
	 * 蛇改变方向的方法
	 * @param direction
	 */
	public void changeDirection(int direction) {
		newDirection = direction;
	}
	
	/**
	 *吃到食物后消失的尾巴加回来 
	 */
	public void eatFood() {
		body.addLast(tail);
	}
	/**
	 * 是否吃到自己
	 * @return
	 */
	public boolean isEatSelf() {
		for (int i = 1; i < body.size(); i++) {
			if (body.getFirst().equals(body.get(i)))
				return true;
		}
		return false;
	}
	/**
	 * 是否吃到食物
	 */
	public boolean isEatFood(Food food){
		if(this.body.getFirst().x==food.x&&this.body.getFirst().y==food.y)
		{
			return true;
		}
		return false;
	}
	/**
	 * 是否吃到障碍物
	 */
	public boolean isEatGround() {
		Point head=this.body.getFirst();
		for(Point p:Ground.ground_position_data) {
			if(p.x==head.x&&p.y==head.y) {
				return true;
			}
		}
		return false;
	}
	
	
	
	/**
	 * 修改蛇速度
	 * @param i
	 */
	public void updateSpeed(int i) {
		if (i == 0)
			time += 100;
		if (i == 1 && time > 100)
			time -= 100;
	}
	
	/**
	 * 蛇移动
	 */
	public void move() {
		tail = body.removeLast();
		int x = body.getFirst().x;
		int y = body.getFirst().y;
		if (oldDirection + newDirection != 0)
			oldDirection = newDirection;
		switch (oldDirection) {
		case UP:
			body.addFirst(new Point(x, y - 1*Global.CELL_SIZE));
			if(this.body.getFirst().y<84*Global.CELL_SIZE) {
				if(this.body.getFirst().y>=12*Global.CELL_SIZE) {
					Global.reference.y=Global.reference.y-Global.CELL_SIZE;
					if(Global.reference.y<0)Global.reference.y=0;
				}
			}
			break;
		case DOWN:
			body.addFirst(new Point(x, y + 1*Global.CELL_SIZE));
			if(this.body.getFirst().y>12*Global.CELL_SIZE) {
				if(this.body.getFirst().y<=84*Global.CELL_SIZE) {
					Global.reference.y=Global.reference.y+Global.CELL_SIZE;
				}
			}
			break;
		case LEFT:
			body.addFirst(new Point(x - 1*Global.CELL_SIZE, y));
			if(this.body.getFirst().x<84*Global.CELL_SIZE) {
			  if(this.body.getFirst().x>=12*Global.CELL_SIZE) {
					Global.reference.x=Global.reference.x-Global.CELL_SIZE;
				}
			}
			break;
		case RIGHT:
			body.addFirst(new Point(x + 1*Global.CELL_SIZE, y));
			if(this.body.getFirst().x>12*Global.CELL_SIZE) {
				if(this.body.getFirst().x<=84*Global.CELL_SIZE) {
					Global.reference.x=Global.reference.x+Global.CELL_SIZE;
				}
			}
			break;
		}
	}
	
	public void start() {
		new SnakeDirver().start();
	}

	private class SnakeDirver extends Thread {
		@Override
		public void run() {
			//显示数据并让线程深睡相应时间
			snakeListener.showView();
			try {
				sleep(time);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//循环改变数据直到游戏结束
			while (true) {
				//加头消尾
				move();
				//监听器在蛇移动后判断数据是否出现异常并显示数据
				snakeListener.snakeMove();
				try {
					sleep(time);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

	public void addSnakeListener(SnakeListener snakeListener) {
		// TODO Auto-generated method stub
		this.snakeListener=snakeListener; 
	}
	
	

}
