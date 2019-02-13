package com.cqeec.entity;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;
import java.util.Map;

import com.cqeec.util.Global;

public class Food {
	//食物的位置
	public int x;
	public int y;
    /**
     * 食物的初始位置为6 ，6
     */
	public Food() {
		x=Global.CELL_SIZE*6;
		y=Global.CELL_SIZE*6;
	}

	/**
	 * 判断是哪条蛇吃到的食物
	 * 返回null则谁都没吃到食物
	 * @param snakes
	 * @return
	 */
	public Snake who_eat_food(List<Snake> snakes) {
		for(Snake snake:snakes) {
			if(snake.getSnakeHead().x==x&&snake.getSnakeHead().y==y) {
				return snake;
			}
		}
		return null;
		
	}
	
	/**
	 * 食物画自己
	 * @param g
	 */
	public void drawMe(Graphics g) {
		g.setColor(Color.yellow);
		int x = this.x;
		int y = this.y;
		int cell_size=Global.CELL_SIZE;
		//为了数据显示正常，所以这里画的位置为相对位置，相对于参照物的位置
		g.fill3DRect(x-Global.reference.x,y-Global.reference.y,cell_size,cell_size, true);
	}
    /**
       * 重新设置食物的位置
     * @param point
     */
	public void setLocation(Point point) {
		this.x = point.x;
		this.y = point.y;
	}
}