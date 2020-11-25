package draught1;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public abstract class AbsGameCanvas extends JPanel implements Runnable{

	private ArrayList<AbsSprite> spriteAry;
	private boolean playing;
	private boolean gameOver;
	private Thread animThread;
	private int sleepTime;
	
	public AbsGameCanvas()
	{
		spriteAry = new ArrayList<AbsSprite>();
		playing = false;
		gameOver = false;
		animThread = null;
		sleepTime = MyConsts.SLEEPTIME;
	}
	
	public void initAnimation()
	{
		if(animThread == null)
		{
			animThread = new Thread(this);
			animThread.start();
		}
	}
	
	@Override
	public void run()
	{
		try {
			while(isPlaying())
			{
				updateComponent();
				repaint();
				Thread.sleep(getSleepTime());
			}
		} catch(InterruptedException ex) {
			
		}
	}
	
	public void updateComponent()
	{
		for(AbsSprite element : spriteAry)
			if(element.isActive())
				element.updateSprite();
	}
	
	public abstract void paintPieces(Graphics2D g2d);
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		for(AbsSprite element : spriteAry)
			if(element.isVisible())
				element.paintSprite(g2d);
		
		paintPieces(g2d);
		
	}
	
	public void startGame()
	{
		setPlaying(true);
		initAnimation();
	}
	
	public void pauseGame()
	{
		setPlaying(false);
	}
	
	public void resumeGame()
	{
		setPlaying(true);
	}
	
	public void stopGame()
	{
		if(gameOver)
		{
			setPlaying(false);
			animThread.interrupt();
			animThread = null;
		}
	}
	
	public abstract void announceGameResult(Graphics2D g2d, AbsPlayer winner);

	public ArrayList<AbsSprite> getSpriteAry() {
		return spriteAry;
	}

	public void setSpriteAry(ArrayList<AbsSprite> spriteAry) {
		this.spriteAry = spriteAry;
	}

	public boolean isPlaying() {
		return playing;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public int getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}
}
