package com.cqeec.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cqeec.util.Global;

public class Ground {
	//初始化障碍物数据
	public static List<Point> ground_position_data=new ArrayList<>();
	static {
		for(int i=0;i<100;i++) {
			for(int j=0;j<100;j++) {
				if(i==0||i==99||j==0||j==99) {
					ground_position_data.add(new Point(j*Global.CELL_SIZE, i*Global.CELL_SIZE));
				}
			}
		}
	}
	//设置障碍物的颜色(默认为黑色)
	public static Color color=Color.BLACK;
	

	public static void drawMe(Graphics g) {
		g.setColor(Ground.color);
		int cell_size=Global.CELL_SIZE;
		for(Point p:ground_position_data) {
			//为了数据显示正常，所以这里画的位置为相对位置，相对于参照物的位置
			g.fill3DRect(p.x-Global.reference.x, p.y-Global.reference.y,cell_size, cell_size, true);
		}
	}

}
