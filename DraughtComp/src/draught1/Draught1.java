package draught1;

import javax.swing.JFrame;

public class Draught1 extends JFrame{

	private GameCanvas gameCanvas;
	
	public Draught1()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("DRAUGHT");
		setSize(MyConsts.CV_WIDTH, MyConsts.CV_HEIGHT);
		gameCanvas = new GameCanvas();
		add(gameCanvas);
		
		setVisible(true);
	}
	
	public static void main(String args[])
	{
		new Draught1();
	}
}
