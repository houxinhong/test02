package tetris;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 障碍物
 * 
 * @author Administrator
 *
 */
public class Ground {
	// 定义边框障碍物数组（不可销毁）
	public int[][] arrayEnableDestory = new int[22][22];
	// 定义图形障碍物（可通过消行销毁）
	public int[][] arrayAbleDestory = new int[21][21];

	/**
	 * 构造方法初始化边框障碍物
	 */
	public Ground() {
		for (int y = 0; y < 22; y++) {
			for (int x = 0; x < 22; x++) {
				if (x == 0 || y == 0 || x == 21 || y == 21) {
					arrayEnableDestory[y][x] = 1;
				}
			}
		}
	}

	public void drawMe(Graphics g) {
		

		for (int y = 0; y < 22; y++) {
			for (int x = 0; x < 22; x++) {
				if (arrayEnableDestory[y][x] == 1) {
					g.setColor(Color.RED);
					g.draw3DRect(x * Global.CELL_SIZE, y * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
				}
				if (x < 21 && y < 21) {
					if (arrayAbleDestory[y][x] == 1) {
						g.setColor(Color.blue);
						g.fill3DRect(x * Global.CELL_SIZE, y * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE,
								true);
					}
				}
				if(x>0&&y>0&&x<21&&y<21){
					System.out.println(x);
					g.setColor(Color.YELLOW);
					g.draw3DRect(x * Global.CELL_SIZE, y * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
				}
			}
		}
	}

	public boolean isMove(Shape shape) {
		for (int y = 0; y < 22; y++) {
			for (int x = 0; x < 22; x++) {
				if (arrayEnableDestory[y][x] == 1) {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 4; j++) {
							int body[][] = shape.shapes[shape.shapeType];
							if (body[shape.shapeStatus][i * 4 + j] == 1 && shape.location_x + j == x
									&& shape.location_y + i == y) {
								return true;
							}
						}
					}
				}

				if (x < 21 && y < 21) {
					if (arrayAbleDestory[y][x] == 1) {
						for (int i = 0; i < 4; i++) {
							for (int j = 0; j < 4; j++) {
								int body[][] = shape.shapes[shape.shapeType];
								if (body[shape.shapeStatus][i * 4 + j] == 1 && shape.location_x + j == x
										&& shape.location_y + i == y) {
									return true;
								}
							}
						}
					}
				}

			}
		}
		return false;
	}

	/**
	 * 满格消行
	 */
	public void eliminateRows() {
		for (int y = 0; y < 21; y++) {
			int count = 0;
			for (int x = 0; x < 21; x++) {
				if (arrayAbleDestory[y][x] == 1) {
					count++;
				}

			}
			if (count == 20) {
				for (int i = 0; i < 21; i++) {
					for (int j = 0; j < 21; j++) {
						if (i < y) {
							arrayAbleDestory[y - i][j] = arrayAbleDestory[y - i - 1][j];
						}
					}
				}
			}
		}

	}

	/**
	 * 判断可消除的障碍物是否触碰到了敏感线
	 * 
	 * @return
	 */
	public boolean isGameOver() {
		for (int y = 0; y < Global.HEIGHT - 1; y++) {
			for (int x = 0; x < Global.WIDTH - 1; x++) {
				if (this.arrayAbleDestory[y][x] == 1) {
					if (y == 6) {
						return true;
					}
				}

			}
		}
		return false;
	}

}
