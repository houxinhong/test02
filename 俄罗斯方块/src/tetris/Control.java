package tetris;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Control extends KeyAdapter {

	private Shape shape;
	private GamePanel gamePanel;
	private Ground ground;
	public Shape getShape() {
		
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public Ground getGround() {
		
		return ground;
	}

	
	public void setGround(Ground ground) {
		this.ground = ground;
	}

	/**
	 * 将此键盘监听器添加至面板对象中 当按下键盘时面板对象会将接收值（键盘按压事件） 而后面板对象会调用此方法（键盘按压方法）
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// 通过某方法获取到键盘所按下的值，而后调用图形对象的移动方法完成操作
		int keycode = e.getKeyCode();
		switch (keycode) {
		case KeyEvent.VK_UP:
			shape.spin();
			break;
		case KeyEvent.VK_DOWN:
			shape.downMove(ground);
			break;
		case KeyEvent.VK_LEFT:
			shape.leftMove(ground);
			break;
		case KeyEvent.VK_RIGHT:
			shape.rightMove(ground);
			break;

		}
		gamePanel.dispaly(shape, ground);
	}

	/**
	 * 控制器的构造方法
	 * 
	 * @param shape
	 * @param gamePanel
	 * @param ground
	 */
	public Control(Shape shape, GamePanel gamePanel, Ground ground) {
		super();
		this.shape = shape;
		this.gamePanel = gamePanel;
		this.ground = ground;
	}

	/**
	 * 控制器使图形下落的方法 本质上调用了Shape类的downMove方法
	 */
	public void shapeDown() {
		shape.downMove(ground);
		gamePanel.dispaly(shape, ground);
		ground.eliminateRows();
	}

}
