package draught;

import javax.swing.JFrame;

public class Draught extends JFrame {

	private GamePanel gamePanel;
	
	public Draught()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("DRAUGHT");
		setSize(MyConsts.CV_WIDTH, MyConsts.CV_HEIGHT);
		gamePanel = new GamePanel();
		add(gamePanel);
		
		setVisible(true);
	}
	
	public static void main(String args[])
	{
		new Draught();
	}
}
