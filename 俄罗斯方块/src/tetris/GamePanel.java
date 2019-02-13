package tetris;

import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * 游戏面板
 * 
 * @author Administrator
 *
 */
public class GamePanel extends JPanel {
	// 定义需要被画的组件成员变量
	private Shape shape;
	private Ground ground;

	/**
	 * 由此方法给需要被画的成员赋值
	 */
	public void dispaly(Shape shape, Ground ground) {
		this.shape = shape;
		this.ground = ground;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		// 消除轨迹
		super.paintComponent(g);
		shape.drawMe(g);
		ground.drawMe(g);
	}

}
