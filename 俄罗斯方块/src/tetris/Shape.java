package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JOptionPane;

/**
 * 图形类
 * 
 * @author Administrator
 *
 */
public class Shape {
	// 生命状态
	public boolean flag = true;
	// 起始方块的x与y值，不过这里是代表x,y方向的格子数
	// 格子尺寸已在全局变量中设置
	public int location_x = 3;
	public int location_y = 3;

	// 方块的类型及状态的定义
	public int shapeType = new Random().nextInt(7);
	public int shapeStatus = new Random().nextInt(4);

	// 方块的形状 第一组代表方块类型有S、Z、L、J、I、O、T 7种 第二组 代表旋转几次 第三四组为 方块矩阵
	public final int shapes[][][] = new int[][][] {
			// i
			{ { 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 },
					{ 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 } },
			// s
			{ { 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
					{ 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 } },
			// z
			{ { 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 } },
			// j
			{ { 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
			// o
			{ { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
			// l
			{ { 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 }, { 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
			// t
			{ { 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0 } } };

	/**
	 * 旋转的方法
	 */
	public void spin() {
		this.shapeStatus = ++shapeStatus == 4 ? shapeStatus = 0 : shapeStatus;
	}

	/**
	 * 方块往左移动的方法
	 */
	public void leftMove(Ground ground) {
		location_x--;
		location_x = ground.isMove(this) ? location_x + 1 : location_x;
	}

	/**
	 * 方块往右移动的方法
	 */
	public void rightMove(Ground ground) {
		location_x++;
		location_x = ground.isMove(this) ? location_x - 1 : location_x;
	}

	/**
	 * 方块往下移动的方法
	 */
	public void downMove(Ground ground) {
		location_y++;
		if (ground.isMove(this)) {
			this.flag = false;
		}
		location_y = ground.isMove(this) ? location_y - 1 : location_y;
		if (ground.isGameOver()) {
			JOptionPane.showConfirmDialog(null, "GameOver");
			System.exit(0);
		}
	}

	/**
	 * 画自己
	 * 
	 * @param g
	 */
	public void drawMe(Graphics g) {
		g.setColor(Color.GREEN);
		int body[][] = shapes[shapeType];
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (body[shapeStatus][y * 4 + x] == 1) {
					g.fill3DRect((x + location_x) * Global.CELL_SIZE, (y + location_y) * Global.CELL_SIZE,
							Global.CELL_SIZE, Global.CELL_SIZE, true);
				}
			}
		}
	}

}
