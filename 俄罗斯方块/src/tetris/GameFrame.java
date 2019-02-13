package tetris;

import javax.swing.JFrame;

/**
 * 游戏窗体兼测试类
 * 
 * @author Administrator
 *
 */
public class GameFrame extends JFrame {
	/**
	 * 初始化窗体对象方法
	 */
	public void lancher() {
		this.setTitle("俄罗斯方块1.0");
		this.setLocation(500, 300);
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		//
		// 创建窗体并添加面板
		GameFrame gameFrame = new GameFrame();
		gameFrame.lancher();
		GamePanel gamePanel = new GamePanel();
		gameFrame.add(gamePanel);
		gamePanel.setFocusable(true);// 让面板获得焦点

		// 创建控制器并将控制器放入面板中监听键盘事件
		Ground ground = new Ground();
		Shape shape = new Shape();
		Control control = new Control(shape, gamePanel, ground);
		gamePanel.addKeyListener(control);

		// 创建一个新的线程
		Thread other = new Thread(new OtherThread(control));
		other.start();
	}

	private static class OtherThread implements Runnable {
		private Control control;

		public OtherThread(Control control) {
			super();
			this.control = control;
		}

		@Override
		public void run() {
			while (control.getShape().flag) {
				control.shapeDown();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// 将方块变为障碍物
			int[][] obstacleArray = control.getGround().arrayAbleDestory;
			Shape shape = control.getShape();
			for (int y = 0; y < 22; y++) {
				for (int x = 0; x < 22; x++) {
					if (x < 21 && y < 21) {
						for (int i = 0; i < 4; i++) {
							for (int j = 0; j < 4; j++) {
								int body[][] = shape.shapes[shape.shapeType];
								if (body[shape.shapeStatus][i * 4 + j] == 1) {
									obstacleArray[shape.location_y + i][shape.location_x + j] = 1;
								}
							}
						}
					}

				}
			}
			// 重置控制器中的Shape对象
			control.setShape(new Shape());
			// 重新创建一个线程
			new Thread(new OtherThread(control)).start();

		}

	}

}
